package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseDetailEntity;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "currency_rate")
public class CurrencyRate extends BaseHeaderEntityGen {
  @Column(name = "rate")
  Double rate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_id", insertable = false, updatable = false)
  Currency currency;

  @Column(name = "currency_id")
  Long currencyId;

  @Column(name = "rate_date")
   Date date;
}