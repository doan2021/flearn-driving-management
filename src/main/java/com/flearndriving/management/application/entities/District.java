package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class District extends AbstractEntity {

    @Column
    private String districtName;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private Province province;
}
