package com.doanfpt.management.application.respone;

import java.util.HashMap;
import java.util.Map;

public class ResponeData {
    private String status;
    private String message;
    private boolean isError;
    private Map<String, Object> result;

    public ResponeData() {
        result = new HashMap<>();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public void putResult(String key, Object value) {
        this.result.put(key, value);
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

}
