package com.backend.EEA.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Column(name = "changer_id")
    protected Long changerId;

    @Column(name = "last_update_date")
    protected Date lastUpdateDate;

    @Column(name = "created_date")
    @CreationTimestamp
    protected Date createdDate;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
