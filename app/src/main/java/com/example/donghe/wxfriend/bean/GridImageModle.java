package com.example.donghe.wxfriend.bean;

import java.util.List;

/**
 * Created by dong.he on 2017/2/6.
 */

public class GridImageModle {
    private String imgUrl;
    private List<ListItemModle> list;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<ListItemModle> getList() {
        return list;
    }

    public void setList(List<ListItemModle> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GridImageModle{" +
                "imgUrl='" + imgUrl + '\'' +
                ", list=" + list +
                '}';
    }
}
