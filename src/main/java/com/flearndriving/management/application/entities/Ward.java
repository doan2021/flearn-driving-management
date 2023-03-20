package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Ward extends AbstractEntity {

    @Column
    private String wardName;

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;
}
