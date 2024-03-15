package com.backend.EEA.model.entity;

import com.backend.EEA.common.models.entities.EEAEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseHeaderEntityGen extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	protected Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "ENTITY_ID", insertable = false, updatable = false)
	protected EEAEntity entity;

	@NotNull
	@Column(name = "ENTITY_ID")
	protected Long entityId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		BaseHeaderEntityGen that = (BaseHeaderEntityGen) o;
		return id != null && Objects.equals(id, that.id);
	}

}
