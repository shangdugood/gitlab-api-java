package com.css.gitapi.util.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 15:10
 */
public enum  MergeMethod {
    MERGE("merge"),
    REBASE_MERGE("rebase_merge"),
    FF("ff");
    private String desc;
    MergeMethod(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
