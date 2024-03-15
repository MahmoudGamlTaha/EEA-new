package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.model.entity.masterdata.AdministrativeStructure;
import com.backend.EEA.model.entity.masterdata.Harbor;
import com.backend.EEA.model.entity.masterdata.RequestDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestHeaderModelDto{

    private String name;

    private Date shipDate;

    private String importCoalCompany;

    private String shipName;

    private Date arrivedDate;

    private HarborDto landingHarbor;

    private AdministrativeStructureDto administrativeStructure;

    private Double weightInTon ;

    private Long coalTypeId;

    private String shipmentStages;

    private Long unloadWayId;

    private HarborDto importHarbor;

    private Long companyId;

    private CompanyDto  companyDto;
}
