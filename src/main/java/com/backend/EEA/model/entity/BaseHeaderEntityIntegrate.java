package com.backend.EEA.model.entity;

import com.backend.EEA.common.models.entities.EEAEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseHeaderEntityIntegrate extends BaseEntity implements Serializable {

    @Id
    @Column(name = "ID")
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTITY_ID", insertable = false, updatable = false)
    protected EEAEntity entity;

    @NotNull
    @Column(name = "ENTITY_ID")
    protected Long entityId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseHeaderEntityIntegrate that = (BaseHeaderEntityIntegrate) o;
        return id != null && Objects.equals(id, that.id);
    }


}
