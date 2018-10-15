package com.ni.projectw.goodthingsweb.model;


public class GoodThingsContent {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "GoodThingsContent{" +
                "content='" + content + '\'' +
                '}';
    }
}
