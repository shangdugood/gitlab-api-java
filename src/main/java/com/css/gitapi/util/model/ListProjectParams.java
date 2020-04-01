package com.css.gitapi.util.model;

import com.css.gitapi.util.enums.ProListOrderBy;
import com.css.gitapi.util.enums.SortMethod;
import com.css.gitapi.util.enums.UserAccessLevel;
import com.css.gitapi.util.enums.Visibility;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 15:31
 */
public class ListProjectParams {
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
     * Include custom attributes in response (admins only)
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
     * Limit by projects which use the given programming language
     */
    private String with_programming_language;
    /**
     * Limit projects where the wiki checksum calculation has failed
     */
    private boolean wiki_checksum_failed;
    /**
     * Limit projects where the repository checksum calculation has failed
     */
    private boolean repository_checksum_failed;
    /**
     * Limit by current user minimal access level
     */
    private UserAccessLevel min_access_level;
    /**
     * Limit results to projects with IDs greater than the specified ID
     */
    private Integer id_after;
    /**
     * Limit results to projects with IDs less than the specified ID
     */
    private Integer id_before;

    public ListProjectParams() {
        this.visibility = Visibility.NONE;
        this.order_by = ProListOrderBy.CREATED_TIME;
        this.sort = SortMethod.DESC;
        this.min_access_level = UserAccessLevel.NONE;
        this.simple = true;
        this.id_after = null;
        this.id_before = null;

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

    public boolean isMembership() {
        return membership;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
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

    public String getWith_programming_language() {
        return with_programming_language;
    }

    public void setWith_programming_language(String with_programming_language) {
        this.with_programming_language = with_programming_language;
    }

    public boolean isWiki_checksum_failed() {
        return wiki_checksum_failed;
    }

    public void setWiki_checksum_failed(boolean wiki_checksum_failed) {
        this.wiki_checksum_failed = wiki_checksum_failed;
    }

    public boolean isRepository_checksum_failed() {
        return repository_checksum_failed;
    }

    public void setRepository_checksum_failed(boolean repository_checksum_failed) {
        this.repository_checksum_failed = repository_checksum_failed;
    }

    public UserAccessLevel getMin_access_level() {
        return min_access_level;
    }

    public void setMin_access_level(UserAccessLevel min_access_level) {
        this.min_access_level = min_access_level;
    }

    public Integer getId_after() {
        return id_after;
    }

    public void setId_after(Integer id_after) {
        this.id_after = id_after;
    }

    public Integer getId_before() {
        return id_before;
    }

    public void setId_before(Integer id_before) {
        this.id_before = id_before;
    }
}
