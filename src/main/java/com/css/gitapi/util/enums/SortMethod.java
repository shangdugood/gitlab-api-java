package com.css.gitapi.util.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 15:45
 */
public enum SortMethod {
    ASC("asc"),
    DESC("desc");

    private String desc;
    SortMethod(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
