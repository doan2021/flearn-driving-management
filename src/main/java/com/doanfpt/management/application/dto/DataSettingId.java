package com.doanfpt.management.application.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DataSettingId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2115843545776350840L;

    @Column(name = "key_value", length = 3)
    private String keyValue;

    @Column(name = "index_value")
    private Integer indexValue;

    public DataSettingId(String keyValue, Integer indexValue) {
        super();
        this.keyValue = keyValue;
        this.indexValue = indexValue;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Integer getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(Integer indexValue) {
        this.indexValue = indexValue;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
