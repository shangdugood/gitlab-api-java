package com.css.gitapi.util.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/9 9:26
 */
public enum GroupsOrderBy {
    NAME("name"),
    PATH("path"),
    ID("id");

    private String desc;

    GroupsOrderBy(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
