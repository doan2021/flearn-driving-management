package com.flearndriving.management.application.common;

import com.flearndriving.management.application.utils.DateTimeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Common {

    private Common() {
        throw new IllegalStateException("Utility class");
    }

    public static Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE);
        Date dateReturn = new Date();
        try {
            dateReturn = dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateReturn;
    }

    public static Date addDays(Date date, Integer days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); // minus number would decrement the days
        return cal.getTime();
    }

    public static Date getSystemDate() {
        return new Date();
    }

    public static boolean isValidEmailAddress(String email) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(Constant.PATTERN_EMAIL);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidPhoneNumber(String numberPhone) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(Constant.PATTERN_PHONENUMBER);
        java.util.regex.Matcher m = p.matcher(numberPhone);
        return m.matches();
    }

    public static boolean isValidName(String chapterName) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(Constant.PATTERN_CHAPTER_NAME);
        java.util.regex.Matcher m = p.matcher(chapterName);
        return m.matches();
    }

    public static boolean isValidContent(String chapterContent) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(Constant.PATTERN_CHAPTER_CONTENT);
        java.util.regex.Matcher m = p.matcher(chapterContent);
        return m.matches();
    }

    public static String generateFileName(MultipartFile multipartFile, String label) {
        // Init extension new
        String extension = MimeTypes.lookupExt(Objects.requireNonNull(multipartFile.getContentType()));
        // Remove extension old
        String fileName = multipartFile.getOriginalFilename().substring(0, multipartFile.getOriginalFilename().lastIndexOf("."));
        return DateTimeUtils.dateToString(Common.getSystemDate(), Constant.PATTERN_FORMAT_DATE_TIME) + "_" + label + "_"
                + fileName.replace(" ", "_") + "." + extension;
    }

    public static Boolean isInvalidMaxLengthString(String field, Integer maxLength) {
        if (field == null) {
            return true;
        }
        return !(field.length() <= maxLength);
    }
}
