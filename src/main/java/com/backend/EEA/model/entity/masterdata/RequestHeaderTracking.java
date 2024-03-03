package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.enums.CustomerRequestStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "request_header_tracking")
@Entity
@Getter
@Setter
public class RequestHeaderTracking extends BaseHeaderEntityGen {
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "state")
    private String state;

    @Column(name = "status")
    private CustomerRequestStatus status;

    @Column(name ="action_order")
    private Integer actionOrder;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "administrative_id")
    private AdministrativeStructure structure;

    @PostLoad
    private void setState(){
        if(state == null){
            if(structure != null){
                setState("".concat(" الطلب حاليا عند ادارة ").concat( structure.getName()));
            }
        }
    }

}
