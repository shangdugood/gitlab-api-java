package com.css.gitapi.util.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 10:02
 */
public enum Identify {
    ADMINISTRATOR(1, "管理员"),
    REGUALRACCOUNT(2, "普通用户");


    Integer idd;
    String description;

    Identify(int idd, String description) {
        this.idd = idd;
        this.description = description;
    }

    public Integer getIdd() {
        return idd;
    }

    public String getDescription() {
        return description;
    }
}
