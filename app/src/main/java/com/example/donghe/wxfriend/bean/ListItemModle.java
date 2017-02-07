package com.example.donghe.wxfriend.bean;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dong.he on 2017/2/6.
 */

public class ListItemModle {

    private int id;
    private String name;
    private String headImg;
    private String content;
//    public String[] urls;
    public List<String> urls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "ListItemModle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", headImg='" + headImg + '\'' +
                ", content='" + content + '\'' +
                ", urls=" + urls +
                '}';
    }
}
