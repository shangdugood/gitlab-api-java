package com.css.gitapi.util.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 15:18
 */
public enum DeployStrategy {
    CONTINUOUS("continuous"),
    MANUAL("manual"),
    TIMED_INCREMENTAL("timed_incremental");
    private String desc;
    DeployStrategy(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
