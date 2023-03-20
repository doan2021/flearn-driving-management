package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String createBy;

    @Column
    private Date createAt;

    @Column
    private String updateBy;

    @Column
    private Date updateAt;

}
