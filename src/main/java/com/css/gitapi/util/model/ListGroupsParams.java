package com.css.gitapi.util.model;

import com.css.gitapi.util.enums.GroupsOrderBy;
import com.css.gitapi.util.enums.SortMethod;
import com.css.gitapi.util.enums.UserAccessLevel;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/9 9:24
 */
public class ListGroupsParams {
    /**
     * 不包含的群组ID集合
     */
    private int[] skip_groups;
    /**
     * Show all the groups you have access to (defaults to false for authenticated users, true for admin);
     * Attributes owned and min_access_level have precedence
     */
    private boolean all_available;
    /**
     * Return the list of authorized groups matching the search criteria
     */
    private String search;
    /**
     * Order groups by name, path or id. Default is name
     */
    private GroupsOrderBy order_by;
    /**
     * Order groups in asc or desc order. Default is asc
     */
    private SortMethod sort;
    /**
     * Include group statistics (admins only)
     */
    private boolean statistics;
    /**
     * Include custom attributes in response (admins only)
     */
    private boolean with_custom_attributes;
    /**
     * Limit to groups explicitly owned by the current user
     */
    private boolean owned;
    /**
     * Limit to groups where current user has at least this access level
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

    public int[] getSkip_groups() {
        return skip_groups;
    }

    public void setSkip_groups(int[] skip_groups) {
        this.skip_groups = skip_groups;
    }

    public boolean isAll_available() {
        return all_available;
    }

    public void setAll_available(boolean all_available) {
        this.all_available = all_available;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public GroupsOrderBy getOrder_by() {
        return order_by;
    }

    public void setOrder_by(GroupsOrderBy order_by) {
        this.order_by = order_by;
    }

    public SortMethod getSort() {
        return sort;
    }

    public void setSort(SortMethod sort) {
        this.sort = sort;
    }

    public boolean isStatistics() {
        return statistics;
    }

    public void setStatistics(boolean statistics) {
        this.statistics = statistics;
    }

    public boolean isWith_custom_attributes() {
        return with_custom_attributes;
    }

    public void setWith_custom_attributes(boolean with_custom_attributes) {
        this.with_custom_attributes = with_custom_attributes;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public UserAccessLevel getMin_access_level() {
        return min_access_level;
    }

    public void setMin_access_level(UserAccessLevel min_access_level) {
        this.min_access_level = min_access_level;
    }
}
