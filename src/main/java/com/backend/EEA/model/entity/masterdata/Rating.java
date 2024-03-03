package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Rating")
public class Rating extends BaseHeaderEntityGen {

    @Column(name = "rate_number")
     Long rateStar;

    @Column(name = "service_id")
     Long serviceId;

    @Column(name = "suggestion")
     String suggestion;
}