package com.backend.EEA.common.config.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Configuration {

    @Id
    @Column(name = "config_key")
    private String key;

    @Column(name = "config_value")
    private String value;
}
