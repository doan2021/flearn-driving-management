package com.flearndriving.management.application.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.dto.DataSettingId;
import com.flearndriving.management.application.entities.DataSetting;

@Repository
public interface DataSettingRespository extends JpaRepository<DataSetting, DataSettingId> {
}
