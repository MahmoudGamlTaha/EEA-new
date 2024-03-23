package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.enums.OperatingTimePerCycle;
import com.backend.EEA.model.enums.TypeOfCharcoal;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Table(name = "Request_detail")
@Getter
@Setter
@Entity
public class RequestDetail extends BaseHeaderEntityGen {

    @ManyToOne
    @JoinColumn(name = "request_id", insertable = false, updatable = false)
    private  RequestHeader requestHeader;

    @Column(name = "request_id")
    private Long requestHeaderId;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "company_id")
    private Long companyId;

    @ManyToOne
    @JoinColumn(name = "company_activity_id", insertable = false, updatable = false)
    private CompanyActivity companyActivity;

    @Column(name = "company_activity_id")
    private Long companyActivityId;

    @ManyToOne
    @JoinColumn(name = "accept_attach_id")
    private Attachment companyAcceptance;
    /// change harbor request
    @ManyToOne
    @JoinColumn(name = "front_image_from_acceptance")
    private Attachment frontImageFromAcceptance;


    @Column(name = "company_accept_number")
    private Long companyAcceptanceNumber;

    @Column(name = "intermediate_store_id")
    private Long intermediateStoreId;

    @Column(name = "accept_date")
    private Date acceptDate;
    @OneToMany(mappedBy = "requestDetail")
    private Set<Attachment> otherAttachment;

    @ManyToMany(mappedBy = "requestDetails")
    private List<Harbor> harbors;

    @Column(name = "quantities_to_be_completed")
    private Integer quantitiesToBeCompleted;

    @Column(name = "exported_quantities")
    private Integer exportedQuantities;

    @Column(name = "reasons_for_not_completing_the_quantity")
    private String reasonsForNotCompletingTheQuantity;

    //Request approval of the developed form
    @Column(name = "developer_name")
    private String developerName;

    @Column(name = "describe_it")
    private String describeIt;

    @Column(name = "Title_of_the_developed_model")
    private String titleOfTheDevelopedModel;

    @ManyToOne
    @JoinColumn(name = "government_id",insertable = false,updatable = false)
    private Government government;

    @Column(name = "government_id")
    private Long governmentId;

    @Column(name = "name_of_the_developed_model")
    private String nameOfTheDevelopedModel;


    @Column(name = "location_of_the_required_form_to_be_approved")
    private String locationOfTheRequiredFormToBeApproved;

    @Column(name = "the_coordinates_x")
    private Integer theCoordinatesX;

    @Column(name = "the_coordinates_y")
    private Integer theCoordinatesY;

    @Column(name = "explain_how_to_operate")
    private String explainHowToOperate;

    @Column(name = "quantity_of_wood_produced")
    private Integer quantityOfWoodProduced;

    @Column(name = "quantity_of_charcoal_produced")
    private Integer quantityOfCharcoalProduced;

    @Column(name = "operating_time_per_cycle")
    private Integer operatingTimePerCycle;

    @Column(name = "monthly_production")
    private Integer monthlyProduction;

    @Column(name = "disposal_of_hazardous_waste")
    private String disposalOfHazardousWaste;

    @Column(name = "time_per_cycle")
    private OperatingTimePerCycle timePerCycle;

    @Column(name = "the_industrial_register_to_be_used")
    private String industrialRegister;

    @Column(name = "amount_of_vegetable_rennet")  //should name changed
    private Double amountOfCoalPlanInTon;

    @Column(name = "notes_for_the_chief_of_staff")
    private String notesForTheChiefOfStaff;

    @Enumerated(EnumType.ORDINAL)
    private TypeOfCharcoal type;

    //@Enumerated(EnumType.ORDINAL)
    private Long coalTypeId;

    @Transient
    List<Long> harborIds;

    @Column(name = "landing_harbor_id")
    Long landingHarborId;
}
