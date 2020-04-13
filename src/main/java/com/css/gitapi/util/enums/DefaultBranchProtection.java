package com.css.gitapi.util.enums;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/13 14:12
 */
public enum DefaultBranchProtection {
    NOPROTECTION(0, "No protection. Developers and maintainers can: -Push new commits -Force push changes -Delete the branch"),
    PARTIALPROTECTION(1, "Developers and maintainers can:  - Push new commits"),
    FULLPROTECTION(2, "Only maintainers can:  - Push new commits");

    private Integer code;
    private String desc;

    DefaultBranchProtection(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
