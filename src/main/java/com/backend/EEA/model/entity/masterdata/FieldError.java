package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "field_error")
public class FieldError extends BaseHeaderEntityGen {
    @Column(name = "code")
    private String code;

    @Column(name = "field")
    private String field;

    @Column(name= "filed_code")
    private String fieldCode;

    private Long requestId;
}
