package com.css.gitapi.util.model;

import com.css.gitapi.util.enums.ProListOrderBy;
import com.css.gitapi.util.enums.SortMethod;
import com.css.gitapi.util.enums.UserAccessLevel;
import com.css.gitapi.util.enums.Visibility;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/9 15:01
 */
public class ListGroupProjectParams {
    /**
     * Limit by archived status
     */
    private boolean archived;
    /**
     * Limit by visibility public, internal, or private
     */
    private Visibility visibility;
    /**
     * Return projects ordered by id, name, path, created_at, updated_at, or last_activity_at fields.
     * Default is created_at
     */
    private ProListOrderBy order_by;
    /**
     * ASC OR DESC
     * Default is DESC
     */
    private SortMethod sort;
    /**
     * Return list of projects matching the search criteria
     */
    private String search;
    /**
     * Return only limited fields for each project.
     * This is a no-op without authentication as then only simple fields are returned.
     * Default is true
     */
    private boolean simple;
    /**
     * Limit by projects explicitly owned by the current user
     */
    private boolean owned;
    /**
     * Limit by projects starred by the current user
     */
    private boolean starred;
    /**
     * Limit by enabled issues feature
     */
    private boolean with_issues_enabled;
    /**
     * Limit by enabled merge requests feature
     */
    private boolean with_merge_requests_enabled;
    /**
     * Include projects shared to this group.
     * Default is true
     */
    private boolean with_shared = true;
    /**
     * Include projects in subgroups of this group.
     * Default is false
     */
    private boolean include_subgroups = false;
    /**
     * Limit by current user minimal access level
     */
    private UserAccessLevel min_access_level;
    /**
     * Include custom attributes in response (admins only)
     */
    private boolean with_custom_attributes;
    /**
     * 分页信息，可选，默认为page=1,per_page=20;
     */
    private Pagination pagination;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public ProListOrderBy getOrder_by() {
        return order_by;
    }

    public void setOrder_by(ProListOrderBy order_by) {
        this.order_by = order_by;
    }

    public SortMethod getSort() {
        return sort;
    }

    public void setSort(SortMethod sort) {
        this.sort = sort;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public boolean isSimple() {
        return simple;
    }

    public void setSimple(boolean simple) {
        this.simple = simple;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public boolean isWith_issues_enabled() {
        return with_issues_enabled;
    }

    public void setWith_issues_enabled(boolean with_issues_enabled) {
        this.with_issues_enabled = with_issues_enabled;
    }

    public boolean isWith_merge_requests_enabled() {
        return with_merge_requests_enabled;
    }

    public void setWith_merge_requests_enabled(boolean with_merge_requests_enabled) {
        this.with_merge_requests_enabled = with_merge_requests_enabled;
    }

    public boolean isWith_shared() {
        return with_shared;
    }

    public void setWith_shared(boolean with_shared) {
        this.with_shared = with_shared;
    }

    public boolean isInclude_subgroups() {
        return include_subgroups;
    }

    public void setInclude_subgroups(boolean include_subgroups) {
        this.include_subgroups = include_subgroups;
    }

    public UserAccessLevel getMin_access_level() {
        return min_access_level;
    }

    public void setMin_access_level(UserAccessLevel min_access_level) {
        this.min_access_level = min_access_level;
    }

    public boolean isWith_custom_attributes() {
        return with_custom_attributes;
    }

    public void setWith_custom_attributes(boolean with_custom_attributes) {
        this.with_custom_attributes = with_custom_attributes;
    }
}
