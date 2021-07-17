package com.doanfpt.management.application.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.doanfpt.management.application.model.DataSettingId;

@Entity
@Table(name = "data_setting")
public class DataSetting {

    @EmbeddedId
    private DataSettingId dataSettingId;

    @Column(name = "value")
    private String value;

    @Column(name = "value_1")
    private String value1;

    @Column(name = "value_2")
    private String value2;

    @Column(name = "value_3")
    private String value3;

    @Column(name = "value_4")
    private String value4;

    @Column(name = "value_5")
    private String value5;

    @Column(name = "description")
    private String description;

    public DataSettingId getDataSettingId() {
        return dataSettingId;
    }

    public void setDataSettingId(DataSettingId dataSettingId) {
        this.dataSettingId = dataSettingId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String getValue5() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
