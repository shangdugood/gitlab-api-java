package com.css.gitapi.util.model;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/13 16:46
 */
public class CreateGroupHookParams {
    /**
     * 群组的ID
     */
    private String group_id;
    /**
     * 要添加的hook的URL
     */
    private String hook_url;
    /**
     * Trigger hook on push events
     */
    private boolean push_events;
    /**
     * Trigger hook on issues events
     */
    private boolean issues_events;
    /**
     * Trigger hook on confidential issues events
     */
    private boolean confidential_issues_events;
    /**
     * Trigger hook on merge requests events
     */
    private boolean merge_requests_events;
    /**
     * Trigger hook on tag push events
     */
    private boolean tag_push_events;
    /**
     * Trigger hook on note events
     */
    private boolean note_events;
    /**
     * Trigger hook on job events
     */
    private boolean job_events;
    /**
     * Trigger hook on pipeline events
     */
    private boolean pipeline_events;
    /**
     * Trigger hook on wiki events
     */
    private boolean wiki_page_events;
    /**
     * Do SSL verification when triggering the hook
     */
    private boolean enable_ssl_verification;
    /**
     * Secret token to validate received payloads; this will not be returned in the response
     */
    private String token;

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public void setHook_url(String hook_url) {
        this.hook_url = hook_url;
    }

    public String getGroup_id() {
        return group_id;
    }

    public String getHook_url() {
        return hook_url;
    }

    public CreateGroupHookParams(String group_id, String hook_url) {
        this.group_id = group_id;
        this.hook_url = hook_url;
    }

    public boolean isPush_events() {
        return push_events;
    }

    public void setPush_events(boolean push_events) {
        this.push_events = push_events;
    }

    public boolean isIssues_events() {
        return issues_events;
    }

    public void setIssues_events(boolean issues_events) {
        this.issues_events = issues_events;
    }

    public boolean isConfidential_issues_events() {
        return confidential_issues_events;
    }

    public void setConfidential_issues_events(boolean confidential_issues_events) {
        this.confidential_issues_events = confidential_issues_events;
    }

    public boolean isMerge_requests_events() {
        return merge_requests_events;
    }

    public void setMerge_requests_events(boolean merge_requests_events) {
        this.merge_requests_events = merge_requests_events;
    }

    public boolean isTag_push_events() {
        return tag_push_events;
    }

    public void setTag_push_events(boolean tag_push_events) {
        this.tag_push_events = tag_push_events;
    }

    public boolean isNote_events() {
        return note_events;
    }

    public void setNote_events(boolean note_events) {
        this.note_events = note_events;
    }

    public boolean isJob_events() {
        return job_events;
    }

    public void setJob_events(boolean job_events) {
        this.job_events = job_events;
    }

    public boolean isPipeline_events() {
        return pipeline_events;
    }

    public void setPipeline_events(boolean pipeline_events) {
        this.pipeline_events = pipeline_events;
    }

    public boolean isWiki_page_events() {
        return wiki_page_events;
    }

    public void setWiki_page_events(boolean wiki_page_events) {
        this.wiki_page_events = wiki_page_events;
    }

    public boolean isEnable_ssl_verification() {
        return enable_ssl_verification;
    }

    public void setEnable_ssl_verification(boolean enable_ssl_verification) {
        this.enable_ssl_verification = enable_ssl_verification;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
