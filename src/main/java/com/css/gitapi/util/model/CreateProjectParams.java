package com.css.gitapi.util.model;

import com.css.gitapi.util.enums.DeployStrategy;
import com.css.gitapi.util.enums.EnabledLevel;
import com.css.gitapi.util.enums.MergeMethod;
import com.css.gitapi.util.enums.Visibility;

import java.util.HashMap;
import java.util.List;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 14:34
 */
public class CreateProjectParams {
    /**
     *项目名称
     * is required if path is not provided
     */
    private String name;
    /**
     *项目路径
     * is required if name is not provided
     */
    private String path;
    /**
     * Namespace for the new project (defaults to the current user's namespace)
     */
    private Integer namespace_id;
    /**
     *master by default
     */
    private String default_branch;
    /**
     * 项目介绍
     */
    private String description;
    private EnabledLevel issues_access_level;
    private EnabledLevel repository_access_level;
    private EnabledLevel merge_requests_access_level;
    private EnabledLevel builds_access_level;
    private EnabledLevel wiki_access_level;
    private EnabledLevel snippets_access_level;
    private EnabledLevel pages_access_level;
    /**
     * 是否邮件通知
     */
    private boolean emails_disabled;
    /**
     * 是否自动解决合并冲突
     */
    private boolean resolve_outdated_diff_discussions;
    /**
     * 是否可以通过容器注册
     */
    private boolean container_registry_enabled;


    /**
     * Update the container expiration policy for this project
     */
    private HashMap<String,Object> container_expiration_policy_attributes;
    private String cadence;
    private String keep_n;
    private String older_than;
    private String name_regex;
    private boolean enabled;



    /**
     * 是否共享
     */
    private boolean shared_runners_enabled;
    /**
     * 可见性
     */
    private Visibility visibility;
    /**
     * URL to import repository from
     */
    private String import_url;
    /**
     * If true, jobs can be viewed by non-project-members
     */
    private boolean public_builds;
    /**
     * Set whether merge requests can only be merged with successful jobs
     */
    private boolean only_allow_merge_if_pipeline_succeeds;
    /**
     * Set whether merge requests can only be merged when all the discussions are resolved
     */
    private boolean only_allow_merge_if_all_discussions_are_resolved;
    /**
     *合并方法
     */
    private MergeMethod merge_method;
    /**
     * Set whether auto-closing referenced issues on default branch
     */
    private boolean autoclose_referenced_issues;
    /**
     * Enable Delete source branch option by default for all new merge requests
     */
    private boolean remove_source_branch_after_merge;
    /**
     * Enable LFS
     */
    private boolean lfs_enabled;
    /**
     * Allow users to request member access
     */
    private boolean request_access_enabled;
    /**
     * The list of tags for a project
     */
    private List<String> tag_list;
    /**
     * Show link to create/view merge request when pushing from the command line
     */
    private boolean printing_merge_request_link_enabled;
    /**
     * The Git strategy. Defaults to fetch
     */
    private String build_git_strategy;
    /**
     * The maximum amount of time in minutes that a job is able run (in seconds)
     */
    private Integer build_timeout;
    /**
     * Auto-cancel pending pipelines (Note: this is not a boolean, but enabled/disabled
     */
    private boolean auto_cancel_pending_pipelines;
    /**
     *Test coverage parsing
     */
    private String build_coverage_regex;
    /**
     * The path to CI config file
     */
    private String ci_config_path;
    /**
     * Enable Auto DevOps for this project
     */
    private boolean auto_devops_enabled;
    /**
     * Auto Deploy strategy (continuous, manual or timed_incremental)
     */
    private DeployStrategy auto_devops_deploy_strategy;
    /**
     *Which storage shard the repository is on. Available only to admins
     */
    private String repository_storage;
    /**
     * How many approvers should approve merge requests by default
     */
    private Integer approvals_before_merge;
    /**
     * The classification label for the project
     */
    private String external_authorization_classification_label;
    /**
     *  Enables pull mirroring in a project
     */
    private boolean mirror;
    /**
     * Pull mirroring triggers builds
     */
    private boolean mirror_trigger_builds;
    /**
     * false by default
     * (Only Create Project Used)
     */
    private boolean initialize_with_readme;
    /**
     * 是否启用临时名称
     * (Only Create Project Used)
     */
    private boolean use_custom_template;
    /**
     * When used without use_custom_template, name of a built-in project template.
     * When used with use_custom_template, name of a custom project template
     */
    private String template_name;
    /**
     * When used with use_custom_template, project ID of a custom project template.
     * This is preferable to using template_name since template_name may be ambiguous.
     * (Only Create Project Used)
     */
    private Integer template_project_id;
    /**
     * For group-level custom templates, specifies ID of group from which all the custom project templates are sourced.
     * Leave empty for instance-level templates.
     * Requires use_custom_template to be true
     * (Only Create Project Used)
     */
    private Integer group_with_project_templates_id;
    /**
     * Enable or disable packages repository feature
     */
    boolean packages_enabled;

    /**
     * The commit message used to apply merge request suggestions
     * (Only Modify Project Used)
     */
    String suggestion_commit_message;
    /**
     * Default number of revisions for shallow cloning
     * (Only Modify Project Used)
     */
    Integer ci_default_git_depth;
    /**
     * (STARTER) User responsible for all the activity surrounding a pull mirror event
     * (Only Modify Project Used)
     */
    Integer mirror_user_id;
    /**
     * (STARTER) Only mirror protected branches
     * (Only Modify Project Used)
     */
    boolean only_mirror_protected_branches;
    /**
     * (STARTER) Pull mirror overwrites diverged branches
     * (Only Modify Project Used)
     */
    boolean mirror_overwrites_diverged_branches;
    /**
     * (PREMIUM ONLY) Enable or disable service desk feature
     * (Only Modify Project Used)
     */
    boolean service_desk_enabled;



    public CreateProjectParams(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Integer getNamespace_id() {
        return namespace_id;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public String getDescription() {
        return description;
    }

    public EnabledLevel getIssues_access_level() {
        return issues_access_level;
    }

    public EnabledLevel getRepository_access_level() {
        return repository_access_level;
    }

    public EnabledLevel getMerge_requests_access_level() {
        return merge_requests_access_level;
    }

    public EnabledLevel getBuilds_access_level() {
        return builds_access_level;
    }

    public EnabledLevel getWiki_access_level() {
        return wiki_access_level;
    }

    public EnabledLevel getSnippets_access_level() {
        return snippets_access_level;
    }

    public EnabledLevel getPages_access_level() {
        return pages_access_level;
    }

    public boolean isEmails_disabled() {
        return emails_disabled;
    }

    public boolean isResolve_outdated_diff_discussions() {
        return resolve_outdated_diff_discussions;
    }

    public boolean isContainer_registry_enabled() {
        return container_registry_enabled;
    }

    public HashMap<String, Object> getContainer_expiration_policy_attributes() {
        return container_expiration_policy_attributes;
    }

    public boolean isShared_runners_enabled() {
        return shared_runners_enabled;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public String getImport_url() {
        return import_url;
    }

    public boolean isPublic_builds() {
        return public_builds;
    }

    public boolean isOnly_allow_merge_if_pipeline_succeeds() {
        return only_allow_merge_if_pipeline_succeeds;
    }

    public boolean isOnly_allow_merge_if_all_discussions_are_resolved() {
        return only_allow_merge_if_all_discussions_are_resolved;
    }

    public MergeMethod getMerge_method() {
        return merge_method;
    }

    public boolean isAutoclose_referenced_issues() {
        return autoclose_referenced_issues;
    }

    public boolean isRemove_source_branch_after_merge() {
        return remove_source_branch_after_merge;
    }

    public boolean isLfs_enabled() {
        return lfs_enabled;
    }

    public boolean isRequest_access_enabled() {
        return request_access_enabled;
    }

    public List<String> getTag_list() {
        return tag_list;
    }

    public boolean isPrinting_merge_request_link_enabled() {
        return printing_merge_request_link_enabled;
    }

    public String getBuild_git_strategy() {
        return build_git_strategy;
    }

    public Integer getBuild_timeout() {
        return build_timeout;
    }

    public boolean isAuto_cancel_pending_pipelines() {
        return auto_cancel_pending_pipelines;
    }

    public String getBuild_coverage_regex() {
        return build_coverage_regex;
    }

    public String getCi_config_path() {
        return ci_config_path;
    }

    public boolean isAuto_devops_enabled() {
        return auto_devops_enabled;
    }

    public DeployStrategy getAuto_devops_deploy_strategy() {
        return auto_devops_deploy_strategy;
    }

    public String getRepository_storage() {
        return repository_storage;
    }

    public Integer getApprovals_before_merge() {
        return approvals_before_merge;
    }

    public String getExternal_authorization_classification_label() {
        return external_authorization_classification_label;
    }

    public boolean isMirror() {
        return mirror;
    }

    public boolean isMirror_trigger_builds() {
        return mirror_trigger_builds;
    }

    public boolean isInitialize_with_readme() {
        return initialize_with_readme;
    }

    public boolean isUse_custom_template() {
        return use_custom_template;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public Integer getTemplate_project_id() {
        return template_project_id;
    }

    public Integer getGroup_with_project_templates_id() {
        return group_with_project_templates_id;
    }

    public boolean isPackages_enabled() {
        return packages_enabled;
    }

    public String getSuggestion_commit_message() {
        return suggestion_commit_message;
    }

    public Integer getCi_default_git_depth() {
        return ci_default_git_depth;
    }

    public Integer getMirror_user_id() {
        return mirror_user_id;
    }

    public boolean isOnly_mirror_protected_branches() {
        return only_mirror_protected_branches;
    }

    public boolean isMirror_overwrites_diverged_branches() {
        return mirror_overwrites_diverged_branches;
    }

    public boolean isService_desk_enabled() {
        return service_desk_enabled;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setNamespace_id(Integer namespace_id) {
        this.namespace_id = namespace_id;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIssues_access_level(EnabledLevel issues_access_level) {
        this.issues_access_level = issues_access_level;
    }

    public void setRepository_access_level(EnabledLevel repository_access_level) {
        this.repository_access_level = repository_access_level;
    }

    public void setMerge_requests_access_level(EnabledLevel merge_requests_access_level) {
        this.merge_requests_access_level = merge_requests_access_level;
    }

    public void setBuilds_access_level(EnabledLevel builds_access_level) {
        this.builds_access_level = builds_access_level;
    }

    public void setWiki_access_level(EnabledLevel wiki_access_level) {
        this.wiki_access_level = wiki_access_level;
    }

    public void setSnippets_access_level(EnabledLevel snippets_access_level) {
        this.snippets_access_level = snippets_access_level;
    }

    public void setPages_access_level(EnabledLevel pages_access_level) {
        this.pages_access_level = pages_access_level;
    }

    public void setEmails_disabled(boolean emails_disabled) {
        this.emails_disabled = emails_disabled;
    }

    public void setResolve_outdated_diff_discussions(boolean resolve_outdated_diff_discussions) {
        this.resolve_outdated_diff_discussions = resolve_outdated_diff_discussions;
    }

    public void setContainer_registry_enabled(boolean container_registry_enabled) {
        this.container_registry_enabled = container_registry_enabled;
    }

    public void setCadence(String cadence) {
        this.cadence = cadence;
        this.container_expiration_policy_attributes.put("cadence",cadence);
    }

    public void setKeep_n(String keep_n) {
        this.keep_n = keep_n;
        this.container_expiration_policy_attributes.put("keep_n",keep_n);
    }

    public void setOlder_than(String older_than) {
        this.older_than = older_than;
        this.container_expiration_policy_attributes.put("older_than",older_than);
    }

    public void setName_regex(String name_regex) {
        this.name_regex = name_regex;
        this.container_expiration_policy_attributes.put("name_regex",name_regex);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        this.container_expiration_policy_attributes.put("enabled",enabled);
    }

    public void setShared_runners_enabled(boolean shared_runners_enabled) {
        this.shared_runners_enabled = shared_runners_enabled;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public void setImport_url(String import_url) {
        this.import_url = import_url;
    }

    public void setPublic_builds(boolean public_builds) {
        this.public_builds = public_builds;
    }

    public void setOnly_allow_merge_if_pipeline_succeeds(boolean only_allow_merge_if_pipeline_succeeds) {
        this.only_allow_merge_if_pipeline_succeeds = only_allow_merge_if_pipeline_succeeds;
    }

    public void setOnly_allow_merge_if_all_discussions_are_resolved(boolean only_allow_merge_if_all_discussions_are_resolved) {
        this.only_allow_merge_if_all_discussions_are_resolved = only_allow_merge_if_all_discussions_are_resolved;
    }

    public void setMerge_method(MergeMethod merge_method) {
        this.merge_method = merge_method;
    }

    public void setAutoclose_referenced_issues(boolean autoclose_referenced_issues) {
        this.autoclose_referenced_issues = autoclose_referenced_issues;
    }

    public void setRemove_source_branch_after_merge(boolean remove_source_branch_after_merge) {
        this.remove_source_branch_after_merge = remove_source_branch_after_merge;
    }

    public void setLfs_enabled(boolean lfs_enabled) {
        this.lfs_enabled = lfs_enabled;
    }

    public void setRequest_access_enabled(boolean request_access_enabled) {
        this.request_access_enabled = request_access_enabled;
    }

    public void setTag_list(List<String> tag_list) {
        this.tag_list = tag_list;
    }

    public void setPrinting_merge_request_link_enabled(boolean printing_merge_request_link_enabled) {
        this.printing_merge_request_link_enabled = printing_merge_request_link_enabled;
    }

    public void setBuild_git_strategy(String build_git_strategy) {
        this.build_git_strategy = build_git_strategy;
    }

    public void setBuild_timeout(Integer build_timeout) {
        this.build_timeout = build_timeout;
    }

    public void setAuto_cancel_pending_pipelines(boolean auto_cancel_pending_pipelines) {
        this.auto_cancel_pending_pipelines = auto_cancel_pending_pipelines;
    }

    public void setBuild_coverage_regex(String build_coverage_regex) {
        this.build_coverage_regex = build_coverage_regex;
    }

    public void setCi_config_path(String ci_config_path) {
        this.ci_config_path = ci_config_path;
    }

    public void setAuto_devops_enabled(boolean auto_devops_enabled) {
        this.auto_devops_enabled = auto_devops_enabled;
    }

    public void setAuto_devops_deploy_strategy(DeployStrategy auto_devops_deploy_strategy) {
        this.auto_devops_deploy_strategy = auto_devops_deploy_strategy;
    }

    public void setRepository_storage(String repository_storage) {
        this.repository_storage = repository_storage;
    }

    public void setApprovals_before_merge(Integer approvals_before_merge) {
        this.approvals_before_merge = approvals_before_merge;
    }

    public void setExternal_authorization_classification_label(String external_authorization_classification_label) {
        this.external_authorization_classification_label = external_authorization_classification_label;
    }

    public void setMirror(boolean mirror) {
        this.mirror = mirror;
    }

    public void setMirror_trigger_builds(boolean mirror_trigger_builds) {
        this.mirror_trigger_builds = mirror_trigger_builds;
    }

    public void setInitialize_with_readme(boolean initialize_with_readme) {
        this.initialize_with_readme = initialize_with_readme;
    }

    public void setUse_custom_template(boolean use_custom_template) {
        this.use_custom_template = use_custom_template;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public void setTemplate_project_id(Integer template_project_id) {
        this.template_project_id = template_project_id;
    }

    public void setGroup_with_project_templates_id(Integer group_with_project_templates_id) {
        this.group_with_project_templates_id = group_with_project_templates_id;
    }

    public void setPackages_enabled(boolean packages_enabled) {
        this.packages_enabled = packages_enabled;
    }

    public void setSuggestion_commit_message(String suggestion_commit_message) {
        this.suggestion_commit_message = suggestion_commit_message;
    }

    public void setCi_default_git_depth(Integer ci_default_git_depth) {
        this.ci_default_git_depth = ci_default_git_depth;
    }

    public void setMirror_user_id(Integer mirror_user_id) {
        this.mirror_user_id = mirror_user_id;
    }

    public void setOnly_mirror_protected_branches(boolean only_mirror_protected_branches) {
        this.only_mirror_protected_branches = only_mirror_protected_branches;
    }

    public void setMirror_overwrites_diverged_branches(boolean mirror_overwrites_diverged_branches) {
        this.mirror_overwrites_diverged_branches = mirror_overwrites_diverged_branches;
    }

    public void setService_desk_enabled(boolean service_desk_enabled) {
        this.service_desk_enabled = service_desk_enabled;
    }
}
