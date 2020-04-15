package com.css.gitapi.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 15:37
 */
public enum ProListOrderBy {
    ID("id"), NAME("name"), PATH("path"), CREATED_TIME("created_at"),
    UPDATE_TIME("updated_at"), LAST_ACTIVITY_TIME("last_activity_at");

    private String desc;

    ProListOrderBy(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
