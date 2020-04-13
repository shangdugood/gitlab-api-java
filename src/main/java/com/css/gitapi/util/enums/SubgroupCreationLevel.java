package com.css.gitapi.util.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/13 14:09
 */
public enum SubgroupCreationLevel {
    OWNER("owner"),
    MAINTAINER("maintainer");

    private String desc;

    SubgroupCreationLevel(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
