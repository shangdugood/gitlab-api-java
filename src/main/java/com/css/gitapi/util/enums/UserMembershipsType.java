package com.css.gitapi.util.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/3 16:58
 */
public enum UserMembershipsType {
    PROJECT("Project"), NAMESPACE("Namespace");
    private String desc;

    UserMembershipsType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
