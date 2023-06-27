package com.flearndriving.management.application.converter;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.common.MimeTypes;
import com.flearndriving.management.application.entities.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Component
public class DocumentConverter {

    public Document buildDocument(final MultipartFile source) {
        return Document.builder()
                .fileName(Common.generateFileName(source, Constant.DOCUMENT_ORTHER_LABEL))
                .originFileName(source.getOriginalFilename())
                .extension(MimeTypes.lookupExt(Objects.requireNonNull(source.getContentType())))
                .contentType(source.getContentType())
                .size(source.getSize())
                .type(Constant.TYPE_DOCUMENT_ORTHER)
                .description("New document")
                .build();
    }
}
