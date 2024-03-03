package com.backend.EEA.controllers.masterdata;


import com.backend.EEA.controllers.BaseRestController;
import com.backend.EEA.model.dto.masterdata.CountryDto;
import com.backend.EEA.model.dto.masterdata.RdfDto;
import com.backend.EEA.model.dto.search.CountrySearchForm;
import com.backend.EEA.model.dto.search.RdfSearchForm;
import com.backend.EEA.model.entity.masterdata.Country;
import com.backend.EEA.model.entity.masterdata.Rdf;
import com.backend.EEA.model.pojos.ResponsePojo;
import com.backend.EEA.services.CountryService;
import com.backend.EEA.services.RdfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portal-data/rdf")
public class RdfController extends BaseRestController<Rdf, RdfDto, RdfSearchForm> {

    private RdfService rdfService;

    public RdfController(RdfService rdfService) {
        super(rdfService);
        this.rdfService = rdfService;
    }
    @GetMapping(value = "/rdf-by-request")
    public ResponseEntity<ResponsePojo> findRdfByRequestId(@RequestParam("requestId") Long requestId){
        Rdf rdf = this.rdfService.findRdfByRequestId(requestId);
        return buildResponseEntity(true, "success",rdf, HttpStatus.OK);
    }


}
