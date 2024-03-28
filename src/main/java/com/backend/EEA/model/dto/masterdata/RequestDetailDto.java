package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.model.entity.masterdata.Attachment;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import com.backend.EEA.model.entity.masterdata.Government;
import com.backend.EEA.model.enums.OperatingTimePerCycle;
import com.backend.EEA.model.enums.StorageType;
import com.backend.EEA.model.enums.TypeOfCharcoal;
import com.backend.EEA.model.enums.TypesAndQuantitiesOfCoal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDetailDto {

 //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
 private long id;

 List<AttachmentDto> otherAttachment;

 CompanyDto company;
 private Long companyId;

 private Long companyActivityId;

 CompanyActivityDto companyActivity;

 Long intermediateStoreId;

 @NumberFormat
 String companyAcceptanceNumber;

 private AttachmentDto companyAcceptance;

 private Date acceptDate;

 //Request approval of the developed form
 private String developerName;

 private String describeIt;

 private String titleOfTheDevelopedModel;

 private Long governmentId;

 private String nameOfTheDevelopedModel;

 private String locationOfTheRequiredFormToBeApproved;

 private Integer theCoordinatesX;

 private Integer theCoordinatesY;

 private String explainHowToOperate;

 private Integer quantityOfWoodProduced;

 private Integer quantityOfCharcoalProduced;

 private Integer operatingTimePerCycle;

 private Integer monthlyProduction;

 private String disposalOfHazardousWaste;

 private OperatingTimePerCycle timePerCycle;

 private Long industrialRegister;

 private Integer amountOfCoalPlanInTon;

 private String notesForTheChiefOfStaff;

 private Long coalTypeId;
 private Long landingHarborId;
 List<Long> harborIds;

 private TypesAndQuantitiesOfCoal typesAndQuantitiesOfCoal;

 private Date shipmentDate;

 private CompanyDto companyCarrier;

 private Long companyCarrierId;

 private StorageType storageType;

 private String embroideryEquipment;

 private Integer theHeightOfTheCoal;

 private Integer estimatedQuantityOfStoredCoal;

 private String availabilityOfCarbonMonoxideDevices;

 private String warehouseVentilationSystem;

 private String storeFloorType;

 private String windbreaksAreFlat;
}
