package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseDetailEntity;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "complain")
public class Complains extends BaseHeaderEntityGen {

    @Column(name = "summary")
    private String summary;

     @Column(name = "replies")
     private String replies;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "request_header_id",insertable = false, updatable = false)
     private RequestHeader requestHeader ;

     @Column(name = "requester_id")
     private Long requesterId;

     @Column(name = "request_header_id")
     private Long requestHeaderId;

}