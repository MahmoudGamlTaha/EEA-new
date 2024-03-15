package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.dto.masterdata.AttachmentDto;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.enums.CustomerRequestStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "request_header")
public class RequestHeader extends BaseHeaderEntityGen {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "type_id")
    private Long type;

    @OneToOne()
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private RequestType requestType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester", insertable = false, updatable = false)
    private User requester;

    @Column(name = "requester")
    private Long requesterId;

    @Column(name = "ship_date")
    private Date shipDate;

    @Column(name = "parent_id")
    private Long parentId;          // later use

    @ManyToOne
    @JoinColumn(name = "import_harbor_id", insertable = false, updatable = false)
    private Harbor importHarbor;

    @Column(name = "import_harbor_id")
    private Long importHarborId;

    @ManyToOne
    @JoinColumn(name = "landing_harbor_id", insertable = false, updatable = false)
    private Harbor landingHarbor;

    @Column(name = "landing_harbor_id")
    private Long landingHarborId;

    @Column(name = "arrived_date")
    private Date arrivedDate;

    @Column(name = "company_id")
    private Long companyId;

    @ManyToOne
    @JoinColumn(name = "company_id",insertable = false,updatable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "source_country_id", insertable = false, updatable = false)
    private Country sourceCountry;

    @Column(name = "source_country_id")
    private Long sourceCountryId;

    @Column(name = "ship_name")
    private String shipName;

    @Column(name = "description")
    private String description;

    @Column(name = "weight_ton_total")
    private Double weightInTon ;

    @Column(name = "price_per_ton")
    private Double pricePerTon;

    @Column(name = "unload_way_id")
    private Long unloadWayId;

    @Column(name = "coal_id")
    private Long coalTypeId;

    @Column(name = "renew_permit_status")
    private String renewPermitStatus;

    @Column(name = "shipment_stages")
    private String shipmentStages;
    @Column(name = "import_coal_company")
    private String importCoalCompany;

    @Column(name = "import_coal_company_id")
    private Long importCoalCompanyId;
    @Transient
    private double totalPrice;

    private Currency currency;
    @Column(name = "currency_id")
    private Long currencyId;

    @Column(name = "total_price_char")
    private String totalPriceInChar;
    @Column(name = "price_per_ton_char")
    private String pricePerTonChar;
    @Column(name = "confirm_payment")
    private Boolean confirmPayment;
    @Column(name = "confirm_agent_exist")
    private Boolean confirmAgentExist;
    @Column(name = "confirm_hook_used")
    private Boolean confirmHookUsed;
    @Column(name = "administrative_forward_to")
    private Long administrativeForwardTo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrative_forward_to", insertable = false, updatable = false)
    private AdministrativeStructure administrativeStructure;

    @OneToMany(mappedBy = "requestHeader", cascade = CascadeType.MERGE)
    List<RequestDetail> requestDetail;

    @OneToMany(mappedBy = "requestHeader")// cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    @OneToMany(mappedBy = "requestHeader")
    private List<Comment> commentsList;

    @Column(name = "category")
    private Integer category;

    @Column(name = "assign_user")
    private Long assignUser;

    @Column(name = "paid_status")
    String paidStatus;

    @PostLoad
    void totalPrice() {
        if (pricePerTon == null){
            pricePerTon = 0.0;
        }
        if(weightInTon == null){
            weightInTon = 0.0;
        }
        this.totalPrice = pricePerTon * weightInTon;
    }
    private CustomerRequestStatus status;
    //  1 for rdf // 0 for others

}
