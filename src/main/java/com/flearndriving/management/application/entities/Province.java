package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Province extends AbstractEntity {

    @Column
    private String provinceName;
}
