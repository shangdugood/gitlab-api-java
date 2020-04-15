package com.css.gitapi.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 15:55
 */
public enum UserAccessLevel {
    NONE(null),
    GUEST(10),
    REPORTER(20),
    DEVELOPER(30),
    MAINTAINER(40),
    OWNER(50);

    private Integer level;

    UserAccessLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }
}
