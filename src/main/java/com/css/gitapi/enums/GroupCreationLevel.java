package com.css.gitapi.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/13 14:07
 */
public enum GroupCreationLevel {
    NOONE("noone"),
    MAINTAINER("maintainer"),
    DEVELOPER("developer");

    private String desc;

    GroupCreationLevel(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
