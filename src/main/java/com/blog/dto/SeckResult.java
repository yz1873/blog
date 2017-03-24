package com.blog.dto;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class SeckResult {
    private boolean success;

    private String message;

    public SeckResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
