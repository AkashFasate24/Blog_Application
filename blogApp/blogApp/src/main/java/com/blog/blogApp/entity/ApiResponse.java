package com.blog.blogApp.entity;

public class ApiResponse {
    public String meassage;
    public Boolean success;

    public String getMeassage() {
        return meassage;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setMeassage(String meassage) {
        this.meassage = meassage;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ApiResponse(String meassage, Boolean success) {
        this.meassage = meassage;
        this.success = success;
    }
}
