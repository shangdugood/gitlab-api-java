package com.css.gitapi.util.model;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/1 11:02
 */
public class GitLabSingleProParams {
    /**
     * 返回结果是否包含项目的统计信息
     */
    private boolean statistics;
    /**
     * 返回结果是否包含项目的licenses
     */
    private boolean license;
    /**
     * 返回结果是否包含项目的custom_attributes（admins only）
     */
    private boolean with_custom_attributes;

    public boolean isStatistics() {
        return statistics;
    }

    public void setStatistics(boolean statistics) {
        this.statistics = statistics;
    }

    public boolean isLicense() {
        return license;
    }

    public void setLicense(boolean license) {
        this.license = license;
    }

    public boolean isWith_custom_attributes() {
        return with_custom_attributes;
    }

    public void setWith_custom_attributes(boolean with_custom_attributes) {
        this.with_custom_attributes = with_custom_attributes;
    }
}
