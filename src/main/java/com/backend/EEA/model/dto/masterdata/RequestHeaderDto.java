package com.backend.EEA.model.dto.masterdata;

import com.backend.EEA.common.config.serialize.JsonDateDeserializer;
import com.backend.EEA.common.config.serialize.JsonDateSerializer;
import com.backend.EEA.model.entity.masterdata.RequestType;
import com.backend.EEA.model.entity.masterdata.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestHeaderDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private String name;

    private String code;

    private RequestType requestType;

    private Long requestTypeId;

    private Long requesterId;

    private Long administrativeForwardTo;

    private AdministrativeStructureDto administrativeStructure;

    private String notes;

    private Double totalPrice;

    private Double weightInTon;

    private String totalPriceInChar;

    private Double pricePerTon;

    private String pricePerTonChar;

    private CoalTypeDto coalType;

    private String shipName;

    private Long coalTypeId;
    private Long companyId;
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date shipDate;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date arrivedDate;

    private HarborDto importHarbor;

    private Long importHarborId;

    private Long landingHarborId;

    private HarborDto landingHarbor;

    private CurrencyDto currency;

    private Long currencyId;

    private CountryDto sourceCountry;

    private Long sourceCountryId;

    private List<CommentsDto> commentsList;

    private String shipmentStages;
    private String importCoalCompany;
    private AttachmentDto invoice;

    private UnloadWayDto unloadWay;

    private Long unLoadWayId;

    private Boolean confirmPayment;

    private Boolean confirmAgentExist;

    private Boolean confirmHookUsed;

    @NotNull
    private List<RequestDetailDto> requestDetail;

    private List<AttachmentDto> attachments;

    private String status;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date createdDate;

    private int category;
}
