package com.css.gitapi.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 15:07
 */
public enum Visibility {
    NONE(null),
    PUBLIC("public"),
    PRIVATE("private"),
    INTERNAL("internal");
    private String desc;

    Visibility(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
