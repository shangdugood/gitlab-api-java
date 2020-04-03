package com.css.gitapi.util.model;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/3 10:39
 */
public class AddProjectPushRuleParams {
    /**
     * The ID of the project or NAMESPACE/PROJECT_NAME
     */
    private String id;
    /**
     * Deny deleting a tag
     */
    private boolean deny_delete_tag;
    /**
     * Restrict commits by author (email) to existing GitLab users
     */
    private boolean member_check;
    /**
     * GitLab will reject any files that are likely to contain secrets
     */
    private boolean prevent_secrets;
    /**
     * All commit messages must match this, e.g. Fixed \d+\..*
     */
    private String commit_message_regex;
    /**
     * No commit message is allowed to match this, e.g. ssh\:\/\/
     */
    private String commit_message_negative_regex;
    /**
     * All branch names must match this, e.g. `(feature
     */
    private String branch_name_regex;
    /**
     * All commit author emails must match this, e.g. @my-company.com$
     */
    private String author_email_regex;
    /**
     * All committed filenames must not match this, e.g. `(jar
     */
    private String file_name_regex;
    /**
     * Maximum file size (MB)
     */
    private Integer max_file_size;
    /**
     * Users can only push commits to this repository that were committed with one of their own verified emails.
     */
    private boolean commit_committer_check;
    /**
     * Reject commit when it is not signed through GPG.
     */
    private boolean reject_unsigned_commits;

    public AddProjectPushRuleParams(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDeny_delete_tag(boolean deny_delete_tag) {
        this.deny_delete_tag = deny_delete_tag;
    }

    public void setMember_check(boolean member_check) {
        this.member_check = member_check;
    }

    public void setPrevent_secrets(boolean prevent_secrets) {
        this.prevent_secrets = prevent_secrets;
    }

    public void setCommit_message_regex(String commit_message_regex) {
        this.commit_message_regex = commit_message_regex;
    }

    public void setCommit_message_negative_regex(String commit_message_negative_regex) {
        this.commit_message_negative_regex = commit_message_negative_regex;
    }

    public void setBranch_name_regex(String branch_name_regex) {
        this.branch_name_regex = branch_name_regex;
    }

    public void setAuthor_email_regex(String author_email_regex) {
        this.author_email_regex = author_email_regex;
    }

    public void setFile_name_regex(String file_name_regex) {
        this.file_name_regex = file_name_regex;
    }

    public void setMax_file_size(Integer max_file_size) {
        this.max_file_size = max_file_size;
    }

    public void setCommit_committer_check(boolean commit_committer_check) {
        this.commit_committer_check = commit_committer_check;
    }

    public void setReject_unsigned_commits(boolean reject_unsigned_commits) {
        this.reject_unsigned_commits = reject_unsigned_commits;
    }


    public boolean isDeny_delete_tag() {
        return deny_delete_tag;
    }

    public boolean isMember_check() {
        return member_check;
    }

    public boolean isPrevent_secrets() {
        return prevent_secrets;
    }

    public String getCommit_message_regex() {
        return commit_message_regex;
    }

    public String getCommit_message_negative_regex() {
        return commit_message_negative_regex;
    }

    public String getBranch_name_regex() {
        return branch_name_regex;
    }

    public String getAuthor_email_regex() {
        return author_email_regex;
    }

    public String getFile_name_regex() {
        return file_name_regex;
    }

    public Integer getMax_file_size() {
        return max_file_size;
    }

    public boolean isCommit_committer_check() {
        return commit_committer_check;
    }

    public boolean isReject_unsigned_commits() {
        return reject_unsigned_commits;
    }
}
