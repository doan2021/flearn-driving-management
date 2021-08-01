package com.doanfpt.management.application.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.DataSetting;
import com.doanfpt.management.application.model.DataSettingId;

@Repository
public interface DataSettingRespository extends JpaRepository<DataSetting, DataSettingId> {
}
