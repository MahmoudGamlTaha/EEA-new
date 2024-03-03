package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.common.security.HarborType;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Harbor_request")
public class HarborChangeRequest extends BaseHeaderEntityGen {


    @JoinColumn(name = "country_id",insertable = false, updatable = false)
    @ManyToOne()
    private RequestDetail requestDetail;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "harbor_type")
    private HarborType harborType;
}
