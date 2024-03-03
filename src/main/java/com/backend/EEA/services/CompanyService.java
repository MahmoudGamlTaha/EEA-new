package com.backend.EEA.services;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.business.dao.repositories.masterdat.AttachmentRepository;
import com.backend.EEA.business.dao.repositories.masterdat.CompanyActivateRepository;
import com.backend.EEA.business.dao.repositories.masterdat.CompanyRepository;
import com.backend.EEA.business.dao.specifications.masterdata.CompanySpecifications;
import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.mapper.masterdata.CompanyActivateMapper;
import com.backend.EEA.mapper.masterdata.CompanyMapper;
import com.backend.EEA.model.dto.masterdata.CompanyActivateDto;
import com.backend.EEA.model.dto.masterdata.CompanyDto;
import com.backend.EEA.model.dto.masterdata.HarborDto;
import com.backend.EEA.model.dto.search.CompanySearchForm;
import com.backend.EEA.model.dto.search.HarborSearchForm;
import com.backend.EEA.model.entity.masterdata.Attachment;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.CompanyActivate;
import com.backend.EEA.model.entity.masterdata.Harbor;
import com.backend.EEA.model.enums.CompanyRequestStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService extends BaseService<Company, CompanyDto, CompanySearchForm> {
    CompanyRepository companyRepository;

    CompanyMapper companyMapper;
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    CompanyActivateRepository companyActivateRepository;

    @Autowired
    CompanyActivateMapper companyActivateMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    protected Specification<Company> buildSpecification(CompanySearchForm searchParams) {
        searchParams.setEntityId(getEntityId());
        return CompanySpecifications.buildSpecifications(searchParams);
    }

    @Override
    protected List<CompanyDto> mapDataListToDtoList(List<Company> list) {
        return list.stream().map(companyMapper::toCompanyDto).collect(Collectors.toList());
    }

    @Override
    protected CompanyDto prepareEntityToDto(Company object) {
        return companyMapper.toCompanyDto(object);
    }

    @Override
    protected Company prepareDtoToEntity(CompanyDto object) {
        return companyMapper.toCompany(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, Company object) {
      object.setId(id);
    }

    @Override
    public void doAfterCreate(Company company){
        List<Attachment> attachments = new LinkedList<>();
        company.getAttachments().stream().forEach(attachment -> {
            Attachment attachment1 = attachmentRepository.findById(attachment.getId()).orElse(null);
            if(attachment1 == null){
                throw new BusinessException("attachment error");
            }
            attachment1.setCompanyId(company.getId());
            attachment1.setTemp(false);
            attachment1.setFileField(attachment.getFileField());
            attachment1.setValidFromDate(attachment.getValidFromDate());
            attachment1.setValidToDate(attachment.getValidToDate());
            attachment1.setLastUpdateDate(new Date());
            attachments.add(attachment1);
        });

        this.attachmentRepository.saveAll(attachments);
    }

    @Override
    public CompanySearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, CompanySearchForm.class);
    }

    public String activateCompany(CompanyActivateDto companyActivateDto){
     Company company  = this.companyRepository.findById(companyActivateDto.getCompanyId()).orElse(null);
     if(company != null){
         CompanyActivate companyActivate = companyActivateMapper.toCompanyActivateEntity(companyActivateDto);
         companyActivate.setChangerId(company.getChangerId());
         companyActivate.setEntityId(company.getEntityId());
        // this.companyActivateRepository.findbyCompanyId()
         this.companyActivateRepository.save(companyActivate);
         company.setStatus(CompanyRequestStatus.Activated);
         this.companyRepository.save(company);
         return "activated";
     }
     return "companyNotFound";
    }
    private void doAfterActivate(CompanyActivate companyActivate){
        List<Attachment> attachments = new LinkedList<>();
        companyActivate.getAttachments().forEach(attachment -> {
            Attachment attachment1 = attachmentRepository.findById(attachment.getId()).orElse(null);
            if(attachment1 == null){
                return;
            }
            attachment1.setCompanyActivateId(companyActivate.getId());
            attachment1.setTemp(false);
            attachment1.setFileField(attachment.getFileField());
            attachment1.setValidFromDate(attachment.getValidFromDate());
            attachment1.setValidToDate(attachment.getValidToDate());
            attachment1.setLastUpdateDate(new Date());
            attachments.add(attachment1);
        });
        this.attachmentRepository.saveAll(attachments);
    }

}
