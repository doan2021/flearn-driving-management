package com.doanfpt.management.application.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.dto.DataSettingId;
import com.doanfpt.management.application.entities.DataSetting;

@Repository
public interface DataSettingRespository extends JpaRepository<DataSetting, DataSettingId> {
}
