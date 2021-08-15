package com.flearndriving.management.application.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.flearndriving.management.application.dto.AccountPrincipal;
import com.flearndriving.management.application.utils.DateTimeUtils;

public class Common {
    
    private Common() {
        throw new IllegalStateException("Utility class");
    }

    public static float percentQuestion(int correctNumber, int incorrectNumber) {
        return (correctNumber * 100.0f) / (correctNumber + incorrectNumber);
    }

    public static String getFirstName(String fullNameGoogle) {
        if (fullNameGoogle == null || "".equals(fullNameGoogle)) {
            return fullNameGoogle;
        }
        String[] name = fullNameGoogle.split(" ");
        return name[name.length - 1];
    }

    public static String getLastName(String fullNameGoogle) {
        if (fullNameGoogle == null || "".equals(fullNameGoogle)) {
            return fullNameGoogle;
        }
        String[] name = fullNameGoogle.split(" ");
        String lastName = "";
        for (int i = 0; i < name.length - 1; i++) {
            if (i == name.length - 2) {
                lastName = lastName.concat(name[i]);
            } else {
                lastName = lastName.concat(name[i]).concat(" ");
            }
        }
        return lastName;
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

    public static String getUsernameLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountPrincipal loginedUser = (AccountPrincipal) auth.getPrincipal();
        return loginedUser.getUsername();
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
        String extension = MimeTypes.lookupExt(multipartFile.getContentType());
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
