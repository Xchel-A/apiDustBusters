package com.dustbuster.dustbusterApi.Entity;

public class ChargeRequest {
    private String userIdOpenpay;
    private ChargeData data;

    public String getUserIdOpenpay() {
        return userIdOpenpay;
    }

    public void setUserIdOpenpay(String userIdOpenpay) {
        this.userIdOpenpay = userIdOpenpay;
    }

    public ChargeData getData() {
        return data;
    }

    public void setData(ChargeData data) {
        this.data = data;
    }
}
