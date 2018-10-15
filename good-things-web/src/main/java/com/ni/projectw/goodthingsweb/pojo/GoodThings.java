package com.ni.projectw.goodthingsweb.pojo;

public class GoodThings {

    private String srcMobile;
    private String srcName;
    private String srcProvince;
    private String srcCity;
    private String categoryType;
    private String recName;
    private String recProvince;
    private String recCity;
    private String content;

    public String getSrcMobile() {
        return srcMobile;
    }

    public void setSrcMobile(String srcMobile) {
        this.srcMobile = srcMobile;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getSrcProvince() {
        return srcProvince;
    }

    public void setSrcProvince(String srcProvince) {
        this.srcProvince = srcProvince;
    }

    public String getSrcCity() {
        return srcCity;
    }

    public void setSrcCity(String srcCity) {
        this.srcCity = srcCity;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecProvince() {
        return recProvince;
    }

    public void setRecProvince(String recProvince) {
        this.recProvince = recProvince;
    }

    public String getRecCity() {
        return recCity;
    }

    public void setRecCity(String recCity) {
        this.recCity = recCity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "GoodThings{" +
                "srcMobile='" + srcMobile + '\'' +
                ", srcName='" + srcName + '\'' +
                ", srcProvince='" + srcProvince + '\'' +
                ", srcCity='" + srcCity + '\'' +
                ", categoryType='" + categoryType + '\'' +
                ", recName='" + recName + '\'' +
                ", recProvince='" + recProvince + '\'' +
                ", recCity='" + recCity + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
