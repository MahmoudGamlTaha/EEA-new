package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.AttachmentDto;
import com.backend.EEA.model.dto.masterdata.CompanyDto;
import com.backend.EEA.model.dto.search.AttachmentSearchForm;
import com.backend.EEA.model.dto.search.CompanySearchForm;
import com.backend.EEA.model.entity.masterdata.Attachment;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.pojos.ResponsePojo;
import com.backend.EEA.services.AttachmentService;
import com.backend.EEA.services.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portal-data/attachment")
public class AttachmentController extends BaseRestController<Attachment, AttachmentDto, AttachmentSearchForm> {

    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        super(attachmentService);
        this.attachmentService = attachmentService;
    }
}
