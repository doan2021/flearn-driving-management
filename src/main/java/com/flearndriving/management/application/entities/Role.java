package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role extends AbstractEntity {

    @Column
    private String roleName;

    @Column
    private String content;
}
