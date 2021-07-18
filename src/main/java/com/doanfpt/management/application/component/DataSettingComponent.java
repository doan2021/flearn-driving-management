package com.doanfpt.management.application.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.doanfpt.management.application.entities.DataSetting;
import com.doanfpt.management.application.model.DataSettingId;
import com.doanfpt.management.application.responsitories.DataSettingRespository;

@Component
public class DataSettingComponent {

    public static final DataSettingId SETTING_UPLOAD_IMAGE = new DataSettingId("ULF", 0);
    
    public static final DataSettingId SETTING_UPLOAD_PDF = new DataSettingId("ULF", 1);
    
    public static final DataSettingId SETTING_EXAM_DATE = new DataSettingId("EXA", 0);

    @Autowired
    DataSettingRespository dataSettingRespository;

    public DataSetting getDataSettingUploadImage() {
        return dataSettingRespository.getOne(SETTING_UPLOAD_IMAGE);
    }
    
    public DataSetting getDataSettingUploadPdf() {
        return dataSettingRespository.getOne(SETTING_UPLOAD_PDF);
    }
    
    public DataSetting getDataSettingExamDate() {
        return dataSettingRespository.getOne(SETTING_EXAM_DATE);
    }
}
