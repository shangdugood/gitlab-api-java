package com.css.gitapi.model;

import com.css.gitapi.enums.ProListOrderBy;
import com.css.gitapi.enums.SortMethod;
import com.css.gitapi.enums.UserAccessLevel;
import com.css.gitapi.enums.Visibility;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/1 16:42
 */
public class ListForkProjectParams {
    /**
     * 项目ID或项目的URL路径
     * (required)
     */
    private String id;
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
     * Return projects sorted in asc or desc order.
     * Default is desc
     */
    private SortMethod sort;
    /**
     * Return list of projects matching the search criteria
     */
    private String search;
    /**
     * Return only limited fields for each project.
     * This is a no-op without authentication as then only simple fields are returned.
     */
    private boolean simple;
    /**
     * Limit by projects explicitly owned by the current user
     */
    private boolean owned;
    /**
     * Limit by projects that the current user is a member of
     */
    private boolean membership;
    /**
     * Limit by projects starred by the current user
     */
    private boolean starred;
    /**
     * Include project statistics
     */
    private boolean statistics;
    /**
     * Include custom attributes in response
     * (admins only)
     */
    private boolean with_custom_attributes;
    /**
     * Limit by enabled issues feature
     */
    private boolean with_issues_enabled;
    /**
     * Limit by enabled merge requests feature
     */
    private boolean with_merge_requests_enabled;
    /**
     * Limit by current user minimal access level
     */
    private UserAccessLevel min_access_level;
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

    public ListForkProjectParams(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isArchived() {
        return archived;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public ProListOrderBy getOrder_by() {
        return order_by;
    }

    public SortMethod getSort() {
        return sort;
    }

    public String getSearch() {
        return search;
    }

    public boolean isSimple() {
        return simple;
    }

    public boolean isOwned() {
        return owned;
    }

    public boolean isMembership() {
        return membership;
    }

    public boolean isStarred() {
        return starred;
    }

    public boolean isStatistics() {
        return statistics;
    }

    public boolean isWith_custom_attributes() {
        return with_custom_attributes;
    }

    public boolean isWith_issues_enabled() {
        return with_issues_enabled;
    }

    public boolean isWith_merge_requests_enabled() {
        return with_merge_requests_enabled;
    }

    public UserAccessLevel getMin_access_level() {
        return min_access_level;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public void setOrder_by(ProListOrderBy order_by) {
        this.order_by = order_by;
    }

    public void setSort(SortMethod sort) {
        this.sort = sort;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setSimple(boolean simple) {
        this.simple = simple;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public void setStatistics(boolean statistics) {
        this.statistics = statistics;
    }

    public void setWith_custom_attributes(boolean with_custom_attributes) {
        this.with_custom_attributes = with_custom_attributes;
    }

    public void setWith_issues_enabled(boolean with_issues_enabled) {
        this.with_issues_enabled = with_issues_enabled;
    }

    public void setWith_merge_requests_enabled(boolean with_merge_requests_enabled) {
        this.with_merge_requests_enabled = with_merge_requests_enabled;
    }

    public void setMin_access_level(UserAccessLevel min_access_level) {
        this.min_access_level = min_access_level;
    }
}
