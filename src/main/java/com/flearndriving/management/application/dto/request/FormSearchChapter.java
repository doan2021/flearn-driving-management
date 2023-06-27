package com.flearndriving.management.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormSearchChapter {

    private String index;

    private String name;

    private String updateAtFrom;

    private String updateAtTo;

    private Integer pageNumber;

}
