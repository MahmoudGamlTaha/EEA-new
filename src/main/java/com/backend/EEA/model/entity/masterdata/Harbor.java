package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.common.security.HarborType;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.entity.BaseHeaderEntityIntegrate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Harbor")
public class Harbor extends BaseHeaderEntityGen {
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;


    @JoinColumn(name = "country_id",insertable = false, updatable = false)
    @ManyToOne()
    private Country country;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "harbor_type")
    private HarborType harborType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "harbor_request_detail",
            joinColumns = @JoinColumn(name = "harbor_id"),
            inverseJoinColumns = @JoinColumn(name = "request_detail_id"))
    private List<RequestDetail> requestDetails = new ArrayList<>();

    public void setRequestDetails(List<RequestDetail> requestDetails) {
        this.requestDetails = new ArrayList<>(requestDetails);
    }

    public List<RequestDetail> getRequestDetails() {
        return new ArrayList<>(requestDetails);
    }

}
