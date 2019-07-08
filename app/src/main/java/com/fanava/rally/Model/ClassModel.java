package com.fanava.rally.Model;

import java.util.List;

public class ClassModel {


    List<ClassItem> listItem_last;
    List<ClassItem> listItem_after;
    List<ClassItem> listItem_online;

    public List<ClassItem> getListItem_last() {
        return listItem_last;
    }

    public void setListItem_last(List<ClassItem> listItem_last) {
        this.listItem_last = listItem_last;
    }

    public List<ClassItem> getListItem_after() {
        return listItem_after;
    }

    public void setListItem_after(List<ClassItem> listItem_after) {
        this.listItem_after = listItem_after;
    }

    public List<ClassItem> getListItem_online() {
        return listItem_online;
    }

    public void setListItem_online(List<ClassItem> listItem_online) {
        this.listItem_online = listItem_online;
    }
}
