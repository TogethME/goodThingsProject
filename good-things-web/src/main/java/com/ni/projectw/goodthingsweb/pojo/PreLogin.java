package com.ni.projectw.goodthingsweb.pojo;

public class PreLogin {

    private String phone;
    private String idyCode;

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdyCode() {
        return idyCode;
    }

    public void setIdyCode(String idyCode) {
        this.idyCode = idyCode;
    }

    @Override
    public String toString() {
        return "PreLogin{" +
                "phone='" + phone + '\'' +
                ", idyCode='" + idyCode + '\'' +
                '}';
    }
}
