package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.*;
import com.backend.EEA.business.dao.specifications.masterdata.RequestHeaderSpecifications;
import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.mapper.masterdata.FieldErrorMapper;
import com.backend.EEA.mapper.masterdata.RequestFeesMapper;
import com.backend.EEA.mapper.masterdata.RequestHeaderTrackingMapper;
import com.backend.EEA.mapper.operation.RequestHeaderMapper;
import com.backend.EEA.model.dto.masterdata.*;
import com.backend.EEA.model.dto.search.RequestHeaderSearchForm;
import com.backend.EEA.model.entity.masterdata.*;
import com.backend.EEA.model.entity.operation.Logger;
import com.backend.EEA.model.enums.CustomerFeesStatus;
import com.backend.EEA.model.enums.CustomerRequestStatus;
import com.backend.EEA.model.payload.request.AddCommentsToRequestHeaderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class RequestHeaderService extends BaseService<RequestHeader, RequestHeaderDto, RequestHeaderSearchForm> {

    RequestHeaderRepository requestHeaderRepository;
    RequestHeaderMapper requestHeaderMapper;
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    RequestDetailRepository requestDetailRepository;

    @Autowired
    AdministrativeStructureRepository administrativeStructureRepository;

    @Autowired
    CompanyRequestConfigRepository companyRequestConfigRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyStatusFlowRepository companyStatusFlowRepository;

    @Autowired
    RequestHeaderTrackingRepository requestHeaderTrackingRepository;

    @Autowired
    RequestHeaderTrackingMapper requestHeaderTrackingMapper;

    @Autowired
    FieldErrorRepository fieldErrorRepository;

    @Autowired
    FieldErrorMapper fieldErrorMapper;

    @Autowired
    LoggerRepository loggerRepository;
    @Autowired
    CoalTypeRepository coalTypeRepository;
    @Autowired
    UnloadWayRepository unloadWayRepository;

    @Autowired
    RequestFeesRepository requestFeesRepository;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    RdfRepository rdfRepository;
    @Autowired
    RequestFeesMapper requestFeesMapper;

    public RequestHeaderService(RequestHeaderRepository requestHeaderRepository, RequestHeaderMapper requestHeaderMapper) {
        super(requestHeaderRepository);
        this.requestHeaderRepository = requestHeaderRepository;
        this.requestHeaderMapper = requestHeaderMapper;
    }


    @Override
    protected Specification<RequestHeader> buildSpecification(RequestHeaderSearchForm requestHeaderSearchForm) {
        UserSessionDto userSessionDto = getLoggedInUserSession();
        if(userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("customer")))
              requestHeaderSearchForm.setRequesterId(userSessionDto.getId());
        else if(userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("user"))||
            userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("department_supervisor"))||
            userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("admin")) ||
            userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("department_agent")) || userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("Notary"))){

           Long administrativeId =  getLoggedInUserSession().getAdministrativeId();
            AdministrativeStructure administrativeStructure = null;
            if(administrativeId != null)
            requestHeaderSearchForm.setAdministrativeId(new Long[]{userSessionDto.getAdministrativeId()});
        }

        return RequestHeaderSpecifications.buildSpecifications(requestHeaderSearchForm);
    }

    @Override
    protected List<RequestHeaderDto> mapDataListToDtoList(List<RequestHeader> list) {

        List<RequestHeaderDto> requestHeaderDtoList = list.stream().map(requestHeaderMapper::toRequestHeaderDto).collect(Collectors.toList());

        return requestHeaderDtoList;
    }

    @Override
    protected RequestHeaderDto prepareEntityToDto(RequestHeader object) {

        return requestHeaderMapper.toRequestHeaderDto(object);
    }

    @Override
    protected RequestHeader prepareDtoToEntity(RequestHeaderDto object) {
        return requestHeaderMapper.toRequestHeader(object);
    }
    @Override
    protected void doBeforeCreate(RequestHeader entity) throws BusinessException {
        List<Attachment> attachments = entity.getAttachments();
        entity.setStatus(CustomerRequestStatus.Created);
        attachments.stream().forEach(attachment -> {
            Attachment attachment1 = this.attachmentRepository.findById(attachment.getId()).orElse(null);
            if(attachment1 == null){
                throw new BusinessException("File error "+ attachment.getUrl());
            };
        });
     //   entity.getRequestDetail().stream().forEach(requestDetail -> {
       //     requestDetail.setRequestHeader(entity);
       // });
        Company company = this.companyRepository.findById(entity.getCompanyId()).orElse(null);
        if(company == null){
            throw new BusinessException("company not found");
        }
        Long activity = company.getActivity_id();
        entity.setCategory(0);
        if(!company.getActivity().getCode().equals("RDF-Cement")){
            activity = 0L;
            entity.setCategory(1);
        }
      CompanyRequestConfig companyRequestConfig = this.companyRequestConfigRepository.
                                findByCompanyActivityIdAndRequestTypeId(activity, entity.getType());
        if(companyRequestConfig != null)
           entity.setAdministrativeForwardTo(companyRequestConfig.getAdministrativeId());
    }
    @Override
    protected void setIdBeforeUpdate(long id, RequestHeader object) {
       object.setId(id);
    }

    @Override
    public RequestHeaderSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper.readValue(searchQuery, RequestHeaderSearchForm.class);
    }
    @Override
    protected void doAfterCreate(RequestHeader entity) throws BusinessException {
        List<Attachment> attachments = entity.getAttachments();
        List<Attachment> updatedAttachments = new LinkedList<>();
        List<Attachment> CompanyAcceptanceAttachments = new LinkedList<>();

        attachments.stream().forEach(attachment -> {
            Attachment attachment1 = this.attachmentRepository.findById(attachment.getId()).orElse(null);
            if(attachment1 == null){
                throw new BusinessException("File error "+ attachment.getUrl());
            };
            attachment1.setId(attachment.getId());
            attachment1.setRequestHeaderId(entity.getId());
            attachment1.setFileField(attachment.getFileField());
            attachment1.setTemp(false);
            attachment1.setEntityId(entity.getEntityId());
            attachment1.setChangerId(entity.getChangerId());
            updatedAttachments.add(attachment1);
        });
        if(updatedAttachments.size() > 0){
            this.attachmentRepository.saveAll(updatedAttachments);
        }
         updatedAttachments.clear();

        LinkedList<RequestDetail> requestDetails =  new LinkedList<RequestDetail>();
        entity.getRequestDetail().stream().forEach(request -> {
            RequestDetail requestDetail = new RequestDetail();
             requestDetail.setRequestHeaderId(entity.getId());
             requestDetail.setCompanyId(request.getCompanyId());
             requestDetail.setCompanyActivityId(request.getCompanyActivityId());
             requestDetail.setEntityId(entity.getEntityId());
             requestDetail.setChangerId(entity.getChangerId());
             requestDetail.setCompanyAcceptance(request.getCompanyAcceptance());
             requestDetail.setAcceptDate(request.getAcceptDate());
             requestDetail.setOtherAttachment(request.getOtherAttachment());
             requestDetail.getOtherAttachment().forEach(attachment -> {
                 Attachment attachment1 = this.attachmentRepository.findById(attachment.getId()).orElse(null);
                 if(attachment1 == null){
                     throw new BusinessException("File error "+ attachment.getUrl());
                 };
                 attachment1.setId(attachment.getId());
                 //attachment1.setRequestHeaderId(request.getId());
                 attachment1.setFileField(attachment.getFileField());
                 attachment1.setTemp(false);
                 attachment1.setEntityId(entity.getEntityId());
                 attachment1.setChangerId(entity.getChangerId());
                 attachment1.setRequestDetail(requestDetail);
                 updatedAttachments.add(attachment1);

             });
            Attachment companyAccept = request.getCompanyAcceptance();
            Attachment companyAcceptOrg = this.attachmentRepository.findById(companyAccept.getId()).orElse(null);
            if(companyAcceptOrg == null){
                throw new BusinessException("File accept error ");
            }
            if(companyAcceptOrg.getTemp()) {
                try {
                    //  companyAcceptOrg.setRequestHeaderId(entity.getId());
                    companyAcceptOrg.setFileField(companyAccept.getFileField());
                    companyAcceptOrg.setTemp(false);
                    companyAcceptOrg.setEntityId(entity.getEntityId());
                    companyAcceptOrg.setChangerId(entity.getChangerId());
                    companyAcceptOrg.setRequestDetail(requestDetail);
              //      this.attachmentRepository.saveAndFlush(companyAcceptOrg);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            // companyAcceptOrg = null;
             requestDetail.setCompanyAcceptanceNumber(request.getCompanyAcceptanceNumber());
           //  requestDetail.setCompanyAcceptance(companyAcceptOrg);
            CompanyAcceptanceAttachments.add(companyAcceptOrg);
             requestDetails.add(requestDetail);
        });
        this.requestDetailRepository.saveAll(requestDetails);
        if(updatedAttachments.size() > 0){
            this.attachmentRepository.saveAll(updatedAttachments);
        }
        if(CompanyAcceptanceAttachments.size() > 0){
            this.attachmentRepository.saveAll(CompanyAcceptanceAttachments);
        }
        List<RequestHeaderTracking> requestHeaderTrackingList = new LinkedList<>();
        RequestHeaderTracking requestHeaderTracking = new RequestHeaderTracking();
        requestHeaderTracking.setRequestId(entity.getId());
        requestHeaderTracking.setState("تقديم الطلب");
        requestHeaderTracking.setEntityId(getEntityId());
        requestHeaderTracking.setActionOrder(1);

           requestHeaderTrackingList.add(requestHeaderTracking);
         requestHeaderTracking = new RequestHeaderTracking();
        requestHeaderTracking.setRequestId(entity.getId());
        requestHeaderTracking.setEntityId(getEntityId());
        requestHeaderTracking.setActionOrder(2);
        AdministrativeStructure administrativeStructure = new AdministrativeStructure();
        administrativeStructure.setId(entity.getAdministrativeForwardTo());
        requestHeaderTracking.setStructure(administrativeStructure);
          requestHeaderTrackingList.add(requestHeaderTracking);
        this.requestHeaderTrackingRepository.saveAll(requestHeaderTrackingList);


    }
    public void setDataBeforeEdit( RequestHeader entity, RequestHeaderDto oldDto ){
        if(entity.getAdministrativeForwardTo() == null)
             entity.setAdministrativeForwardTo(oldDto.getAdministrativeForwardTo());
        if(entity.getStatus() == null) {
            CustomerRequestStatus requestStatus =CustomerRequestStatus.valueOf(oldDto.getStatus());
            entity.setStatus(requestStatus);
        }
        entity.setCreatedDate(oldDto.getCreatedDate());

    }
    protected void doAfterUpdate(RequestHeader entity){
        List<Attachment> attachments = entity.getAttachments();
        List<Attachment> updatedAttachments = new LinkedList<>();
        List<Attachment> CompanyAcceptanceAttachments = new LinkedList<>();

        attachments.stream().forEach(attachment -> {
            Attachment attachment1 = this.attachmentRepository.findById(attachment.getId()).orElse(null);
            if(attachment1 == null){
                throw new BusinessException("File error "+ attachment.getUrl());
            };
            attachment1.setId(attachment.getId());
            attachment1.setRequestHeaderId(entity.getId());
            attachment1.setFileField(attachment.getFileField());
            attachment1.setTemp(false);
            attachment1.setEntityId(entity.getEntityId());
            attachment1.setChangerId(entity.getChangerId());
            updatedAttachments.add(attachment1);
        });
        if(updatedAttachments.size() > 0){
            this.attachmentRepository.saveAll(updatedAttachments);
        }
        updatedAttachments.clear();

        LinkedList<RequestDetail> requestDetails =  new LinkedList<RequestDetail>();
        entity.getRequestDetail().stream().forEach(request -> {
            RequestDetail requestDetail = new RequestDetail();
            if(request.getId() != null&& !request.getId().equals(0L))
              requestDetail.setId(request.getId());
            requestDetail.setRequestHeaderId(entity.getId());
            requestDetail.setCompanyId(request.getCompanyId());
            requestDetail.setCompanyActivityId(request.getCompanyActivityId());
            requestDetail.setEntityId(entity.getEntityId());
            requestDetail.setChangerId(entity.getChangerId());
            requestDetail.setCompanyAcceptance(request.getCompanyAcceptance());
            requestDetail.setAcceptDate(request.getAcceptDate());
            requestDetail.setOtherAttachment(request.getOtherAttachment());
            requestDetail.getOtherAttachment().forEach(attachment -> {
                Attachment attachment1 = this.attachmentRepository.findById(attachment.getId()).orElse(null);
                if(attachment1 == null){
                    throw new BusinessException("File error "+ attachment.getUrl());
                };
                attachment1.setId(attachment.getId());
                //attachment1.setRequestHeaderId(request.getId());
                attachment1.setFileField(attachment.getFileField());
                attachment1.setTemp(false);
                attachment1.setEntityId(entity.getEntityId());
                attachment1.setChangerId(entity.getChangerId());
                attachment1.setRequestDetail(requestDetail);
                updatedAttachments.add(attachment1);

            });
            Attachment companyAccept = request.getCompanyAcceptance();
            Attachment companyAcceptOrg = this.attachmentRepository.findById(companyAccept.getId()).orElse(null);
            if(companyAcceptOrg == null){
                throw new BusinessException("File accept error ");
            }
            if(companyAcceptOrg.getTemp()) {
                try {
                    //  companyAcceptOrg.setRequestHeaderId(entity.getId());
                    companyAcceptOrg.setFileField(companyAccept.getFileField());
                    companyAcceptOrg.setTemp(false);
                    companyAcceptOrg.setEntityId(entity.getEntityId());
                    companyAcceptOrg.setChangerId(entity.getChangerId());
                    companyAcceptOrg.setRequestDetail(requestDetail);
                    //      this.attachmentRepository.saveAndFlush(companyAcceptOrg);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            // companyAcceptOrg = null;
            requestDetail.setCompanyAcceptanceNumber(request.getCompanyAcceptanceNumber());
            //  requestDetail.setCompanyAcceptance(companyAcceptOrg);
            CompanyAcceptanceAttachments.add(companyAcceptOrg);
            requestDetails.add(requestDetail);
        });
        this.requestDetailRepository.saveAll(requestDetails);
        if(updatedAttachments.size() > 0){
            this.attachmentRepository.saveAll(updatedAttachments);
        }
        if(CompanyAcceptanceAttachments.size() > 0){
            this.attachmentRepository.saveAll(CompanyAcceptanceAttachments);
        }
        changeRequestStatus(entity.getId(), CustomerRequestStatus.Created);

    }
    public RequestFeesDto calculateCharge(Long requestId, Double rate){
        RequestHeader requestHeader = this.requestHeaderRepository.findById(requestId).orElse(null);
        Long coalTypeId = requestHeader.getCoalTypeId();
          CoalType coalType = coalTypeRepository.findById(coalTypeId).orElse(null);
          coalType.getRatioPricePerTon();

          UnloadWay unloadWay = unloadWayRepository.findById(requestHeader.getUnloadWayId()).orElse(null);
          Rdf rdf = null;

        RequestFees requestFees = this.requestFeesRepository.findByRequestId(requestId).orElse(new RequestFees());
          Double edraa = requestHeader.getWeightInTon()* requestHeader.getPricePerTon() * coalType.getRatioPricePerTon() * rate ;
         Double edraaFee = unloadWay.getCost() ;
          requestFees.setEdaraFees(edraaFee);

          requestFees.setRequestId(requestId);
          requestFees.setTonPrice(requestHeader.getPricePerTon());
        double extraCost = 0;
        if(requestHeader.getCategory() == 1){
            rdf = rdfRepository.findByRequestId(requestId).orElse(null);

            if(!rdf.getIncludeEnergyReject()){
               extraCost =   requestHeader.getWeightInTon() * .001 * requestHeader.getPricePerTon() * rate;
            }

        }

          requestFees.setRatioEdaraFee(coalType.getRatioPricePerTon());
         RequestFeesDto requestFeesDto = requestFeesMapper.toDto(requestFees);
         requestFeesDto.setTotalFee(edraa + edraaFee);
         requestFeesDto.setRdfTotal(extraCost);
         return requestFeesDto;
    }

    public boolean changeRequestStatus(Long requestId, CustomerRequestStatus requestStatus){
        RequestHeader requestHeader = this.requestHeaderRepository.findById(requestId).orElse(null);
        if(requestHeader == null)
            throw new BusinessException("request not found");
        Company company = companyRepository.findById(requestHeader.getCompanyId()).orElse(null);
        this.requestHeaderRepository.updateRequestStatus(requestId, requestStatus);
        CompanyStatusFlowConfig requestConfig = null;
        if(company.getActivity().getCode().equals("RDF-Cement")) {
            requestConfig = this.companyStatusFlowRepository.findByCustomerRequestStatusAndRequestTypeIdAndCompanyActivityId(requestStatus, requestHeader.getType(), company.getActivity_id());
        }else {
            requestConfig = this.companyStatusFlowRepository.findByCustomerRequestStatusAndRequestTypeIdAndCompanyActivityId(requestStatus, requestHeader.getType(), 0L);
        }
        CustomerRequestStatus oldOne = requestHeader.getStatus();
        Long oldAdministrationId = requestHeader.getAdministrativeForwardTo();
         requestHeader.setStatus(requestStatus);
        if(requestConfig != null) {
            requestHeader.setAdministrativeForwardTo(requestConfig.getAdministrativeId());
            if(oldOne == CustomerRequestStatus.AcceptProtectEEA){
                if(requestStatus == CustomerRequestStatus.CustomerPAID){
                    requestHeader.setPaidStatus(CustomerFeesStatus.PAID.name());
                }else if(oldOne == CustomerRequestStatus.CustomerPAID && requestStatus == CustomerRequestStatus.ConfirmPaymentEEA){
                    requestHeader.setPaidStatus(CustomerFeesStatus.PaidConfirmed.name());
                }
            }

        } if(requestStatus ==CustomerRequestStatus.Rejected){
            requestConfig = new CompanyStatusFlowConfig();
            requestConfig.setAdministrativeId(oldAdministrationId);
            requestHeader.setAdministrativeForwardTo(oldAdministrationId);
        }
        this.requestHeaderRepository.save(requestHeader);
        RequestHeaderTracking lstate =this.requestHeaderTrackingRepository.findTopByRequestIdOrderByActionOrderDesc(requestId);

        RequestHeaderTracking requestHeaderTracking = new RequestHeaderTracking();
        requestHeaderTracking.setRequestId(requestId);
        requestHeaderTracking.setEntityId(getEntityId());
        if(lstate != null)
            requestHeaderTracking.setActionOrder(lstate.getActionOrder() +1);
        else
            requestHeaderTracking.setActionOrder(0);


        AdministrativeStructure structure = new AdministrativeStructure();


        if (requestConfig == null && lstate != null) {
            structure.setId(lstate.getStructure().getId());
        }else {

            structure.setId(requestConfig.getAdministrativeId());
        }
        requestHeaderTracking.setStructure(structure);
        this.requestHeaderTrackingRepository.save(requestHeaderTracking);
        this.Log(requestId, getLoggedInUserSession(), requestStatus.name(), requestHeaderTracking.getStructure().getName());
       return true;
    }
    public boolean changeRequestAdministrative(Long requestId, CustomerRequestStatus requestStatus){

        this.requestHeaderRepository.updateRequestStatus(requestId, requestStatus);
        return true;
    }
    public List<RequestHeaderTrackingDto> trackCustomerRequest(Long requestId){
        RequestHeader requestHeader = this.requestHeaderRepository.findById(requestId).orElse(null);
        if(requestId == null)
             throw new BusinessException("error request");
        Long userId = getLoggedInUserId();
        if(!userId.equals( requestHeader.getRequesterId()))
            throw new BusinessException("error request user");
      List<RequestHeaderTracking> requestHeaderTrackingList = this.requestHeaderTrackingRepository.findByRequestIdOrderByActionOrderDesc(requestId);
        return requestHeaderTrackingList == null?null:requestHeaderTrackingList.stream().map(requestHeaderTrackingMapper::toDto).collect(Collectors.toList());
    }
    public Boolean checkError(ErrorDto code, Long requestId){
       List<FieldError> fieldErrors = new LinkedList<>();
      if(code != null && code.getField() != null){
           code.getField().forEach(code1->{
               FieldError fieldError = new FieldError();
               fieldError.setFieldCode(code1);
               fieldError.setField(code1);
               fieldError.setEntityId(getEntityId());
               fieldError.setRequestId(requestId);
               fieldErrors.add(fieldError);
           });
           this.fieldErrorRepository.saveAll(fieldErrors);
       }
      this.changeRequestStatus(requestId, CustomerRequestStatus.CompleteEntry);
       return true;
    }
    protected void doBeforeEdit(RequestHeader entity) throws BusinessException {
      /*  entity.getRequestDetail().forEach(requestDetail -> {
            requestDetail.setEntityId(getEntityId());
            if(requestDetail.getCompanyAcceptance() !=null) {
                requestDetail.getCompanyAcceptance().setEntityId(getEntityId());
            }
            requestDetail.getOtherAttachment().forEach(attachment -> {
                attachment.setEntityId(getEntityId());
            });

        });*/
    }
    public List<FieldErrorDto> findCheckError(Long requestId){
      List<FieldError> fieldErrors =  this.fieldErrorRepository.findByRequestId(requestId);
       return fieldErrors == null?null:fieldErrors.stream().map(this.fieldErrorMapper::toRoleDto).collect(Collectors.toList());
    }
    public Boolean DeleteError(List<Long> fields, Long requestId){
       Long deleted = this.fieldErrorRepository.deleteAllByIdInAndRequestId(fields, requestId);
        return deleted > 0?true:false;
    }
    public void Log(Long requestId, UserSessionDto userSessionDto, String action, String administration){
        Logger logger = new Logger();
        logger.setAction(action);
        logger.setTable("request_header");
        logger.setOrder(0);
        logger.setEmployeeName(userSessionDto.getName());
        logger.setRowId(requestId);
        logger.setEntityId(getEntityId());
        logger.setChangerId(1L);
        if(administration == null && userSessionDto.getAdministrativeId() != null){

          AdministrativeStructure structure   = this.administrativeStructureRepository.findById(userSessionDto.getAdministrativeId()).orElse(null);
          administration = structure.getName();
        }
        logger.setAdministrativeName(administration);
        this.loggerRepository.save(logger);
    }
    public List<RequestStatusTrackingDto> requestStatusTracking(Long requestId){
        RequestHeader requestHeader = this.requestHeaderRepository.findById(requestId).orElse(null);
        if(requestId == null)
            throw new BusinessException("error request");
        Long userId = getLoggedInUserId();
        if(!userId.equals( requestHeader.getRequesterId()))
            throw new BusinessException("error request user");
        List<RequestStatusTrackingDto> requestStatusDepartmentsList =  initTrackingStatusDepartments();

        List<RequestHeaderTracking> requestHeaderTrackingList = this.requestHeaderTrackingRepository.findByRequestIdOrderByActionOrderDesc(requestId);

        for(RequestHeaderTracking status: requestHeaderTrackingList) {
                RequestStatusTrackingDto requestStatus = new RequestStatusTrackingDto();
                if (status.getActionOrder().equals(1)) {
                    // request submitted successfully -- 1st
                    requestStatusDepartmentsList.get(0).setStatus(true);
                    requestStatusDepartmentsList.get(0).setAdministrativeId(0L);
                    requestStatusDepartmentsList.get(0).setState(status.getState());
                    requestStatusDepartmentsList.get(0).setCreatedDate(status.getCreatedDate());

                } else if (status.getActionOrder().equals(2)  || status.getStructure().getId().equals(8L)) {
                    // coal department - 2nd
                    requestStatusDepartmentsList.get(1).setStatus(true);
                    requestStatusDepartmentsList.get(1).setState(status.getState());
                    requestStatusDepartmentsList.get(1).setAdministrativeId(status.getStructure().getId());
                    requestStatusDepartmentsList.get(1).setCreatedDate(status.getCreatedDate());
                } else if (status.getActionOrder().equals(2) || status.getStructure().getId().equals(11L)) {
                    // rdf department - 2nd
                    requestStatusDepartmentsList.get(4).setStatus(true);
                    requestStatusDepartmentsList.get(4).setState(status.getState());
                    requestStatusDepartmentsList.get(4).setAdministrativeId(status.getStructure().getId());
                    requestStatusDepartmentsList.get(4).setCreatedDate(status.getCreatedDate());
                } else if (status.getActionOrder().equals(3) || status.getStructure().getId().equals(8L)) {
                    // coal department with rdf - 3rd
                    requestStatusDepartmentsList.get(1).setStatus(true);
                    requestStatusDepartmentsList.get(1).setState(status.getState());
                    requestStatusDepartmentsList.get(1).setAdministrativeId(status.getStructure().getId());
                    requestStatusDepartmentsList.get(1).setCreatedDate(status.getCreatedDate());
                }

                else if (status.getActionOrder().equals(3) || status.getStructure().getId().equals(12L)) {
                    // finance - 3rd
                    requestStatusDepartmentsList.get(5).setStatus(true);
                    requestStatusDepartmentsList.get(5).setState(status.getState());
                    requestStatusDepartmentsList.get(5).setAdministrativeId(status.getStructure().getId());
                    requestStatusDepartmentsList.get(5).setCreatedDate(status.getCreatedDate());
                } else if (status.getActionOrder().equals(11) || status.getStructure().getId().equals(12L) ) {
                    // finance with rdf step - 4th
                    requestStatusDepartmentsList.get(5).setStatus(true);
                    requestStatusDepartmentsList.get(5).setState(status.getState());
                    requestStatusDepartmentsList.get(5).setAdministrativeId(status.getStructure().getId());
                    requestStatusDepartmentsList.get(5).setCreatedDate(status.getCreatedDate());
                }
                else if (status.getActionOrder().equals(5) || status.getStructure().getId().equals(10L) ) {
                    // final  5th
                    requestStatusDepartmentsList.get(3).setStatus(true);
                    requestStatusDepartmentsList.get(3).setState(status.getState());
                    requestStatusDepartmentsList.get(3).setAdministrativeId(status.getStructure().getId());
                    requestStatusDepartmentsList.get(3).setCreatedDate(status.getCreatedDate());
                }

        }

        return requestStatusDepartmentsList;
    }

    public  List<RequestStatusTrackingDto> initTrackingStatusDepartments() {
        List<RequestStatusTrackingDto> requestStatusList =  new ArrayList<>();

        requestStatusList.add(new RequestStatusTrackingDto(null, false , null , null));
        requestStatusList.add(new RequestStatusTrackingDto(8L, false , null, null));
        requestStatusList.add(new RequestStatusTrackingDto(9L, false , null, null));
        requestStatusList.add(new RequestStatusTrackingDto(10L, false , null, null));
        requestStatusList.add(new RequestStatusTrackingDto(11L, false , null, null));
        requestStatusList.add(new RequestStatusTrackingDto(12L, false , null, null));
        return  requestStatusList;
    }


  public RequestHeaderDto addCommentsToRequest(Long requestId, AddCommentsToRequestHeaderRequest request) {
      RequestHeader requestHeader = requestHeaderRepository.findById(requestId).orElseThrow(()->new BusinessException("Request Header is not found"));
      for(CommentsDto commentsDto : request.getCommentsList()){
          Comment comment = new Comment();
          comment.setComment(commentsDto.getComment());
          comment.setRequestHeader(requestHeader);
          commentRepository.save(comment);
      }
      return requestHeaderMapper.toRequestHeaderDto(requestHeader);
  }
}
