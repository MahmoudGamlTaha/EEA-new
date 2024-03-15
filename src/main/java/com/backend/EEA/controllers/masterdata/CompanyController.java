package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.AttachmentDto;
import com.backend.EEA.model.dto.masterdata.CompanyActivateDto;
import com.backend.EEA.model.dto.masterdata.CompanyActivityDto;
import com.backend.EEA.model.dto.masterdata.CompanyDto;
import com.backend.EEA.model.dto.search.CompanySearchForm;
import com.backend.EEA.model.dto.search.CurrencyExchangeRateSearchDto;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.pojos.ResponsePojo;
import com.backend.EEA.services.AttachmentService;
import com.backend.EEA.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/portal-data/company")
public class CompanyController extends BaseRestController<Company, CompanyDto, CompanySearchForm> {

    private CompanyService companyService;
     @Autowired
     private AttachmentService attachmentService;

     public CompanyController(CompanyService companyService) {
        super(companyService);
        this.companyService = companyService;
    }
    @PostMapping(value = "/upload", consumes ={ MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ResponsePojo> upload(@RequestBody List<MultipartFile> files){
        List<AttachmentDto> attachmentDtos = attachmentService.upload(files, 1);
       return buildResponseEntity(true, "uploaded", attachmentDtos, HttpStatus.OK);
    }
    @GetMapping(value = "/find-by-owner")
    public ResponseEntity<ResponsePojo> companyByOwner(@RequestParam("ownerId") Long userId ){
         CompanySearchForm companySearchForm = new CompanySearchForm();
         companySearchForm.setOwnerId(userId);
         List<CompanyDto> companies = companyService.findListBySpecifications(companySearchForm);
         return buildResponseEntity(true, "success", companies, HttpStatus.OK);

    }
    @PostMapping(value = "/activate")
    public ResponseEntity<ResponsePojo> activateCompany(@RequestBody CompanyActivateDto companyActivityDto){
        String val = companyService.activateCompany(companyActivityDto);
        if(val.toLowerCase().equals("activated"))
        return buildResponseEntity(true, "success", val, HttpStatus.OK);

        return buildResponseEntity(false, "fail", val, HttpStatus.BAD_REQUEST);
    }
    @GetMapping(value = "/find-by-activity")
    public ResponseEntity<ResponsePojo> findByActivityId(@RequestParam("activityId") Long activity ){
         CompanySearchForm companySearchForm = new CompanySearchForm();
         companySearchForm.setActivityId(activity);
         companySearchForm.setEntityId(companyService.getEntityId());
        List<CompanyDto> companyActivity=  this.companyService.findListBySpecifications(companySearchForm);
         return buildResponseEntity(true, "success",companyActivity, HttpStatus.OK);
    }

    private ResponseEntity<ResponsePojo> assignCompanyToCustomer(@RequestParam("custId") Long custId)
    {
          return null;

    }

    @GetMapping("/get-by-activity/{id}")
    public ResponseEntity<List<CompanyDto>> getCompaniesByActivityId(@PathVariable Long id){
         return ResponseEntity.ok(companyService.getCompaniesByActivityId(id));
    }


}
