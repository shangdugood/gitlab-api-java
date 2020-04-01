package com.css.gitapi.util.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 15:02
 */
public enum EnabledLevel {
    NONE(null),
    DISABLED("disabled"),
    PRIVATE("private"),
    ENABLED("enabled");
    private String desc;
    EnabledLevel(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
