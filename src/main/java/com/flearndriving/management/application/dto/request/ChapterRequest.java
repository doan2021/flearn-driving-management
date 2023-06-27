package com.flearndriving.management.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ChapterRequest {

    private Long id;

    private String name;

    private String content;

    private String description;

    private MultipartFile[] images;

}
