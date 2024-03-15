package com.backend.EEA.model.entity.masterdata;

import com.backend.EEA.model.entity.BaseDetailEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "request_type")
public class RequestType extends BaseDetailEntity {
    @Column(name="NAME", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}