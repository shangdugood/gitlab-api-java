package com.css.gitapi.util.model;

import com.css.gitapi.util.enums.UserAccessLevel;

import java.util.Date;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/14 16:18
 */
public class AddMemberParams {
    /**
     * The ID or URL-encoded path of the project or group owned by the authenticated user
     */
    private String id;
    /**
     * The user ID of the new member
     */
    private Integer user_id;
    /**
     * A valid access level
     */
    private UserAccessLevel access_level;
    /**
     * A date string in the format YEAR-MONTH-DAY
     */
    private Date expires_at;

    public AddMemberParams(String id, Integer user_id, UserAccessLevel access_level) {
        this.id = id;
        this.user_id = user_id;
        this.access_level = access_level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public UserAccessLevel getAccess_level() {
        return access_level;
    }

    public void setAccess_level(UserAccessLevel access_level) {
        this.access_level = access_level;
    }

    public Date getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(Date expires_at) {
        this.expires_at = expires_at;
    }
}
