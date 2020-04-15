package com.css.gitapi.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/3 17:24
 */
public enum ImpersonationTokenState {
    ALL("all"), ACTIVE("active"), INACTIVE("inactive");
    String desc;

    ImpersonationTokenState(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
