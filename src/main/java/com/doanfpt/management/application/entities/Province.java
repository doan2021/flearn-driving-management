/**
 * 
 */
package com.doanfpt.management.application.entities;

import java.util.List;

import javax.persistence.CascadeType;
/**
 * @author tamdu
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "province")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @JsonManagedReference
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private List<District> listDistricts;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<District> getListDistricts() {
        return listDistricts;
    }

    public void setListDistricts(List<District> listDistricts) {
        this.listDistricts = listDistricts;
    }

}
