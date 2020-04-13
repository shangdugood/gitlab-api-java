package com.css.gitapi.util.model;

import com.css.gitapi.util.enums.DefaultBranchProtection;
import com.css.gitapi.util.enums.GroupCreationLevel;
import com.css.gitapi.util.enums.SubgroupCreationLevel;
import com.css.gitapi.util.enums.Visibility;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/10 15:17
 */
public class CreateGroupParams {
    /**
     * 群组名称
     */
    private String name;
    /**
     * 群组路径
     */
    private String path;
    /**
     * 群组描述
     */
    private String description;
    /**
     * 群组可见性
     */
    private Visibility visibility;
    /**
     * Prevent sharing a project with another group within this group.
     */
    private boolean share_with_group_lock;
    /**
     * Require all users in this group to setup Two-factor authentication.
     */
    private boolean require_two_factor_authentication;
    /**
     * Time before Two-factor authentication is enforced (in hours).
     */
    private Integer two_factor_grace_period;
    /**
     * Determine if developers can create projects in the group.
     * Can be noone (No one), maintainer (Maintainers), or developer (Developers + Maintainers).
     */
    private GroupCreationLevel project_creation_level;
    /**
     * Default to Auto DevOps pipeline for all projects within this group.
     */
    private boolean auto_devops_enabled;
    /**
     * Allowed to create subgroups.
     * Can be owner (Owners), or maintainer (Maintainers).
     */
    private SubgroupCreationLevel subgroup_creation_level;
    /**
     * Disable email notifications
     */
    private boolean emails_disabled;
    /**
     * Disable the capability of a group from getting mentioned
     */
    private boolean mentions_disabled;
    /**
     * Enable/disable Large File Storage (LFS) for the projects in this group.
     */
    private boolean lfs_enabled;
    /**
     * Allow users to request member access.
     */
    private boolean request_access_enabled;
    /**
     * The parent group ID for creating nested group.
     */
    private Integer parent_id;
    /**
     * See Options for default_branch_protection.
     * Default to the global level default branch protection setting.
     */
    private DefaultBranchProtection default_branch_protection;

    /**
     * 修改群组后返回这个群组的100个项目，可以添加分页信息进行分页；
     * 创建群组无需考虑此参数。
     */
    private Pagination pagination;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public void setShare_with_group_lock(boolean share_with_group_lock) {
        this.share_with_group_lock = share_with_group_lock;
    }

    public void setRequire_two_factor_authentication(boolean require_two_factor_authentication) {
        this.require_two_factor_authentication = require_two_factor_authentication;
    }

    public void setTwo_factor_grace_period(Integer two_factor_grace_period) {
        this.two_factor_grace_period = two_factor_grace_period;
    }

    public void setProject_creation_level(GroupCreationLevel project_creation_level) {
        this.project_creation_level = project_creation_level;
    }

    public void setAuto_devops_enabled(boolean auto_devops_enabled) {
        this.auto_devops_enabled = auto_devops_enabled;
    }

    public void setSubgroup_creation_level(SubgroupCreationLevel subgroup_creation_level) {
        this.subgroup_creation_level = subgroup_creation_level;
    }

    public void setEmails_disabled(boolean emails_disabled) {
        this.emails_disabled = emails_disabled;
    }

    public void setMentions_disabled(boolean mentions_disabled) {
        this.mentions_disabled = mentions_disabled;
    }

    public void setLfs_enabled(boolean lfs_enabled) {
        this.lfs_enabled = lfs_enabled;
    }

    public void setRequest_access_enabled(boolean request_access_enabled) {
        this.request_access_enabled = request_access_enabled;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public void setDefault_branch_protection(DefaultBranchProtection default_branch_protection) {
        this.default_branch_protection = default_branch_protection;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public boolean isShare_with_group_lock() {
        return share_with_group_lock;
    }

    public boolean isRequire_two_factor_authentication() {
        return require_two_factor_authentication;
    }

    public Integer getTwo_factor_grace_period() {
        return two_factor_grace_period;
    }

    public GroupCreationLevel getProject_creation_level() {
        return project_creation_level;
    }

    public boolean isAuto_devops_enabled() {
        return auto_devops_enabled;
    }

    public SubgroupCreationLevel getSubgroup_creation_level() {
        return subgroup_creation_level;
    }

    public boolean isEmails_disabled() {
        return emails_disabled;
    }

    public boolean isMentions_disabled() {
        return mentions_disabled;
    }

    public boolean isLfs_enabled() {
        return lfs_enabled;
    }

    public boolean isRequest_access_enabled() {
        return request_access_enabled;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public DefaultBranchProtection getDefault_branch_protection() {
        return default_branch_protection;
    }

    public CreateGroupParams(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
