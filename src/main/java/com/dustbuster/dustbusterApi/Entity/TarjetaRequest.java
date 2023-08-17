package com.dustbuster.dustbusterApi.Entity;

public class TarjetaRequest {
    private String userIdOpenpay;
    private TarjetaData data;

    public String getUserIdOpenpay() {
        return userIdOpenpay;
    }

    public void setUserIdOpenpay(String userIdOpenpay) {
        this.userIdOpenpay = userIdOpenpay;
    }

    public TarjetaData getData() {
        return data;
    }

    public void setData(TarjetaData data) {
        this.data = data;
    }
}

