package com.flearndriving.management.application.common;

public class Constant {

    private Constant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String STATUS_SUCCESS = "success";

    public static final String PAGE_CONTENT_NAME = "pageContent";

    public static final Integer RECORD_PER_PAGE = 15;

    /**
     * Rule format date
     */
    public static final String FORMAT_DATE = "dd/MM/yyyy";

    public static final String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm";

    public static final String PATTERN_FORMAT_DATE_TIME = "yyyyMMddHHmmss";

    public static final Integer HTTPS_STATUS_CODE_500 = 500;

    public static final Integer HTTPS_STATUS_CODE_NOT_FOUND = 404;

    /**
     * Rule type document
     */
    public static final Integer TYPE_DOCUMENT_AVATAR = 1;

    public static final Integer TYPE_DOCUMENT_IDENTITY_CARD_IMAGE_FRONT = 2;

    public static final Integer TYPE_DOCUMENT_ORTHER = 7;

    public static final String DOCUMENT_QUESTION_IMAGE = "DOCUMENT_QUESTION_IMAGE";

    public static final String DOCUMENT_ORTHER_LABEL = "OTHER";

    /**
     * Rule status driving_license_info
     */

    public static final String PATTERN_EMAIL = "^(.+)@(\\S+)$";

    public static final String PATTERN_PHONENUMBER = "^[0][0-9]{9}$";

    public static final String PATTERN_CHAPTER_NAME = "^[0-9]$";

    public static final String PATTERN_CHAPTER_CONTENT = "^[a-zA-Z0-9 ]+$";

    public static final Integer STS_EXAM_OPENING = 1;

    public static final Integer STS_EXAM_CANCEL = 2;
}
