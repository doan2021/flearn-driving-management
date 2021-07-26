package com.doanfpt.management.application.common;

public class Constant {

    public static final String STATUS_SUCCESS = "success";

    public static final String STATUS_ERROR = "error";

    public static final String STATUS_NOT_FOUND = "not found";

    public static final String LABEL_NAME_SCREEN = "nameScreen";

    public static final String STR_TRUE = "true";

    public static final String STR_FALSE = "false";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String ROLE_USER = "ROLE_USER";

    public static final String GOOGLE_PROVIDER = "GOOGLE";

    public static final String PAGE_CONTENT_NAME = "pageContent";

    public static final Integer RECORD_PER_PAGE = 5;

    public static final Long ROLE_ID_ADMIN = 1L;

    public static final Boolean IS_NOT_DELETE = false;

    /** Rule format date */
    public static final String FORMAT_DATE = "dd/MM/yyyy";

    public static final String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm";

    public static final String PATTERN_FORMAT_DATE_TIME = "yyyyMMddHHmmss";

    public static final Integer HTTPS_STATUS_CODE_500 = 500;

    /** Rule type document */
    public static final Integer TYPE_DOCUMENT_AVATAR = 1;

    public static final Integer TYPE_DOCUMENT_IDENTITY_CARD_IMAGE_FRONT = 2;

    public static final Integer TYPE_DOCUMENT_IDENTITY_CARD_IMAGE_BACK = 3;

    public static final Integer TYPE_DOCUMENT_IMAGE_PORTRAIT = 4;

    public static final Integer TYPE_DOCUMENT_HEALTH_CERTIFICATION_FILE = 5;

    public static final Integer TYPE_DOCUMENT_ORDER = 6;
    
    public static final String DOCUMENT_AVATAR_LABEL = "AVATAR";

    public static final String DOCUMENT_IDENTITY_CARD_IMAGE_FRONT_LABEL = "IDENTITY_CARD_IMAGE_FRONT";

    public static final String DOCUMENT_IDENTITY_CARD_IMAGE_BACK_LABEL = "IDENTITY_CARD_IMAGE_BACK";

    public static final String DOCUMENT_IMAGE_PORTRAIT_LABEL = "IMAGE_PORTRAIT";

    public static final String DOCUMENT_HEALTH_CERTIFICATION_FILE_LABEL = "HEALTH_CERTIFICATION_FILE";
    
    public static final String DOCUMENT_QUESTION_IMAGE = "DOCUMENT_QUESTION_IMAGE";

    public static final String DOCUMENT_ORDER_LABEL = "HEALTH_CERTIFICATION_FILE";
    
    /** Rule status driving_license_info */
    public static final Integer STS_SUBMITTED = 1;
    
    public static final Integer STS_APPROVE = 1;
    
    public static final Integer STS_PAID = 1;

	public static final String PATTERN_EMAIL = "^(.+)@(\\S+)$";

	public static final String PATTERN_PHONENUMBER = "^[0][0-9]{9}$";
	
	public static final String PATTERN_CHAPTER_NAME = "^[a-zA-Z0-9]{1,10}$";
	
	public static final String PATTERN_CHAPTER_CONTENT = "^[a-zA-Z0-9]{1,10}$";

	public static final Integer STS_EXAM_OPENING = 1;
	
	public static final Integer STS_EXAM_CANCEL = 2;
}
