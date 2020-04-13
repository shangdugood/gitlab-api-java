package com.css.gitapi.util.controller;

import com.css.gitapi.util.enums.HttpCode;
import com.css.gitapi.util.enums.ProListOrderBy;
import com.css.gitapi.util.enums.SortMethod;
import com.css.gitapi.util.enums.UserAccessLevel;
import com.css.gitapi.util.httputil.HttpDeleteWithBody;
import com.css.gitapi.util.httputil.HttpGetWithBody;
import com.css.gitapi.util.model.*;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 11:10
 */
public class ProjectController {
    public String getAllProjects(ListProjectParams params, String private_token) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("archived", String.valueOf(params.isArchived())));
        formparams.add(new BasicNameValuePair("order_by", params.getOrder_by().getDesc()));
        formparams.add(new BasicNameValuePair("sort", params.getSort().getDesc()));
        formparams.add(new BasicNameValuePair("search", params.getSearch()));
        formparams.add(new BasicNameValuePair("simple", String.valueOf(params.isSimple())));
        formparams.add(new BasicNameValuePair("owned", String.valueOf(params.isOwned())));
        formparams.add(new BasicNameValuePair("membership", String.valueOf(params.isMembership())));
        formparams.add(new BasicNameValuePair("starred", String.valueOf(params.isStarred())));
        formparams.add(new BasicNameValuePair("statistics", String.valueOf(params.isStatistics())));
        formparams.add(new BasicNameValuePair("with_custom_attributes", String.valueOf(params.isWith_custom_attributes())));
        formparams.add(new BasicNameValuePair("with_issues_enabled", String.valueOf(params.isWith_issues_enabled())));
        formparams.add(new BasicNameValuePair("with_merge_requests_enabled", String.valueOf(params.isWith_merge_requests_enabled())));
        formparams.add(new BasicNameValuePair("with_programming_language", params.getWith_programming_language()));
        formparams.add(new BasicNameValuePair("wiki_checksum_failed", String.valueOf(params.isWiki_checksum_failed())));
        formparams.add(new BasicNameValuePair("repository_checksum_failed", String.valueOf(params.isRepository_checksum_failed())));
        if (params.getPagination() != null) {
            formparams.add(new BasicNameValuePair("page", params.getPagination().getPage().toString()));
            formparams.add(new BasicNameValuePair("per_page", params.getPagination().getPer_page().toString()));
        }
        if (params.getVisibility().getDesc() != null) {
            formparams.add(new BasicNameValuePair("visibility", params.getVisibility().getDesc()));
        }
        if (params.getMin_access_level().getLevel() != null) {
            formparams.add(new BasicNameValuePair("min_access_level", String.valueOf(params.getMin_access_level().getLevel())));
        }
        if (params.getId_after() != null) {
            formparams.add(new BasicNameValuePair("id_after", String.valueOf(params.getId_after())));
        }
        if (params.getId_before() != null) {
            formparams.add(new BasicNameValuePair("id_before", String.valueOf(params.getId_before())));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getUserProjects(String userId, String private_token, ListProjectParams params) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/projects");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("user_id", userId));
        formparams.add(new BasicNameValuePair("archived", String.valueOf(params.isArchived())));
        formparams.add(new BasicNameValuePair("order_by", params.getOrder_by().getDesc()));
        formparams.add(new BasicNameValuePair("sort", params.getSort().getDesc()));
        formparams.add(new BasicNameValuePair("search", params.getSearch()));
        formparams.add(new BasicNameValuePair("simple", String.valueOf(params.isSimple())));
        formparams.add(new BasicNameValuePair("owned", String.valueOf(params.isOwned())));
        formparams.add(new BasicNameValuePair("membership", String.valueOf(params.isMembership())));
        formparams.add(new BasicNameValuePair("starred", String.valueOf(params.isStarred())));
        formparams.add(new BasicNameValuePair("statistics", String.valueOf(params.isStatistics())));
        formparams.add(new BasicNameValuePair("with_custom_attributes", String.valueOf(params.isWith_custom_attributes())));
        formparams.add(new BasicNameValuePair("with_issues_enabled", String.valueOf(params.isWith_issues_enabled())));
        formparams.add(new BasicNameValuePair("with_merge_requests_enabled", String.valueOf(params.isWith_merge_requests_enabled())));
        formparams.add(new BasicNameValuePair("with_programming_language", params.getWith_programming_language()));
        formparams.add(new BasicNameValuePair("wiki_checksum_failed", String.valueOf(params.isWiki_checksum_failed())));
        formparams.add(new BasicNameValuePair("repository_checksum_failed", String.valueOf(params.isRepository_checksum_failed())));
        if (params.getPagination() != null) {
            formparams.add(new BasicNameValuePair("page", params.getPagination().getPage().toString()));
            formparams.add(new BasicNameValuePair("per_page", params.getPagination().getPer_page().toString()));
        }
        if (params.getVisibility().getDesc() != null) {
            formparams.add(new BasicNameValuePair("visibility", params.getVisibility().getDesc()));
        }
        if (params.getMin_access_level().getLevel() != null) {
            formparams.add(new BasicNameValuePair("min_access_level", String.valueOf(params.getMin_access_level().getLevel())));
        }
        if (params.getId_after() != null) {
            formparams.add(new BasicNameValuePair("id_after", String.valueOf(params.getId_after())));
        }
        if (params.getId_before() != null) {
            formparams.add(new BasicNameValuePair("id_before", String.valueOf(params.getId_before())));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getUserStarredProjects(String userId, String private_token, ListProjectParams params) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/starred_projects");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("user_id", userId));
        formparams.add(new BasicNameValuePair("archived", String.valueOf(params.isArchived())));
        formparams.add(new BasicNameValuePair("order_by", params.getOrder_by().getDesc()));
        formparams.add(new BasicNameValuePair("sort", params.getSort().getDesc()));
        formparams.add(new BasicNameValuePair("search", params.getSearch()));
        formparams.add(new BasicNameValuePair("simple", String.valueOf(params.isSimple())));
        formparams.add(new BasicNameValuePair("owned", String.valueOf(params.isOwned())));
        formparams.add(new BasicNameValuePair("membership", String.valueOf(params.isMembership())));
        formparams.add(new BasicNameValuePair("starred", String.valueOf(params.isStarred())));
        formparams.add(new BasicNameValuePair("statistics", String.valueOf(params.isStatistics())));
        formparams.add(new BasicNameValuePair("with_custom_attributes", String.valueOf(params.isWith_custom_attributes())));
        formparams.add(new BasicNameValuePair("with_issues_enabled", String.valueOf(params.isWith_issues_enabled())));
        formparams.add(new BasicNameValuePair("with_merge_requests_enabled", String.valueOf(params.isWith_merge_requests_enabled())));
        formparams.add(new BasicNameValuePair("with_programming_language", params.getWith_programming_language()));
        formparams.add(new BasicNameValuePair("wiki_checksum_failed", String.valueOf(params.isWiki_checksum_failed())));
        formparams.add(new BasicNameValuePair("repository_checksum_failed", String.valueOf(params.isRepository_checksum_failed())));
        if (params.getPagination() != null) {
            formparams.add(new BasicNameValuePair("page", params.getPagination().getPage().toString()));
            formparams.add(new BasicNameValuePair("per_page", params.getPagination().getPer_page().toString()));
        }
        if (params.getVisibility().getDesc() != null) {
            formparams.add(new BasicNameValuePair("visibility", params.getVisibility().getDesc()));
        }
        if (params.getMin_access_level().getLevel() != null) {
            formparams.add(new BasicNameValuePair("min_access_level", String.valueOf(params.getMin_access_level().getLevel())));
        }
        if (params.getId_after() != null) {
            formparams.add(new BasicNameValuePair("id_after", String.valueOf(params.getId_after())));
        }
        if (params.getId_before() != null) {
            formparams.add(new BasicNameValuePair("id_before", String.valueOf(params.getId_before())));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getProjectById(String projectId, String private_token, ListSingleProjectParams params) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("id", projectId));
        formparams.add(new BasicNameValuePair("statistics", String.valueOf(params.isStatistics())));
        formparams.add(new BasicNameValuePair("license", String.valueOf(params.isLicense())));
        formparams.add(new BasicNameValuePair("with_custom_attributes", String.valueOf(params.isWith_custom_attributes())));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getProjectUsers(String projectId, String private_token, String search, int[] skip_users_id, Pagination pagination) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/users");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (pagination != null) {
            formparams.add(new BasicNameValuePair("page", pagination.getPage().toString()));
            formparams.add(new BasicNameValuePair("per_page", pagination.getPer_page().toString()));
        }
        if (!"".equals(search)) {
            formparams.add(new BasicNameValuePair("search", search));
        }
        if (skip_users_id.length != 0) {
            formparams.add(new BasicNameValuePair("skip_users", skip_users_id.toString()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }


    public String createProject(CreateProjectParams project, String creator_private_token) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects");
        httpPost.addHeader("PRIVATE-TOKEN", creator_private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", project.getName()));
        params.add(new BasicNameValuePair("path", project.getPath()));
        params.add(new BasicNameValuePair("description", project.getDescription()));
        params.add(new BasicNameValuePair("use_custom_template", String.valueOf(project.isUse_custom_template())));
        params.add(new BasicNameValuePair("emails_disabled", String.valueOf(project.isEmails_disabled())));
        params.add(new BasicNameValuePair("resolve_outdated_diff_discussions", String.valueOf(project.isResolve_outdated_diff_discussions())));
        params.add(new BasicNameValuePair("container_registry_enabled", String.valueOf(project.isContainer_registry_enabled())));
        params.add(new BasicNameValuePair("shared_runners_enabled", String.valueOf(project.isShared_runners_enabled())));
        params.add(new BasicNameValuePair("public_builds", String.valueOf(project.isPublic_builds())));
        params.add(new BasicNameValuePair("only_allow_merge_if_pipeline_succeeds", String.valueOf(project.isOnly_allow_merge_if_pipeline_succeeds())));
        params.add(new BasicNameValuePair("only_allow_merge_if_all_discussions_are_resolved", String.valueOf(project.isOnly_allow_merge_if_all_discussions_are_resolved())));
        params.add(new BasicNameValuePair("autoclose_referenced_issues", String.valueOf(project.isAutoclose_referenced_issues())));
        params.add(new BasicNameValuePair("remove_source_branch_after_merge", String.valueOf(project.isRemove_source_branch_after_merge())));
        params.add(new BasicNameValuePair("lfs_enabled", String.valueOf(project.isLfs_enabled())));
        params.add(new BasicNameValuePair("request_access_enabled", String.valueOf(project.isRequest_access_enabled())));
        params.add(new BasicNameValuePair("printing_merge_request_link_enabled", String.valueOf(project.isPrinting_merge_request_link_enabled())));
        params.add(new BasicNameValuePair("build_git_strategy", project.getBuild_git_strategy()));
        params.add(new BasicNameValuePair("build_coverage_regex", project.getBuild_coverage_regex()));
        params.add(new BasicNameValuePair("ci_config_path", project.getCi_config_path()));
        params.add(new BasicNameValuePair("auto_devops_enabled", String.valueOf(project.isAuto_devops_enabled())));
        params.add(new BasicNameValuePair("external_authorization_classification_label", project.getExternal_authorization_classification_label()));
        params.add(new BasicNameValuePair("mirror", String.valueOf(project.isMirror())));
        params.add(new BasicNameValuePair("mirror_trigger_builds", String.valueOf(project.isMirror_trigger_builds())));
        params.add(new BasicNameValuePair("initialize_with_readme", String.valueOf(project.isInitialize_with_readme())));
        params.add(new BasicNameValuePair("packages_enabled", String.valueOf(project.isPackages_enabled())));
        if (project.getNamespace_id() != null) {
            params.add(new BasicNameValuePair("namespace_id", project.getNamespace_id().toString()));
        }
        if (project.getDefault_branch() != null) {
            params.add(new BasicNameValuePair("default_branch", project.getDefault_branch()));
        }
        if (project.getRepository_access_level() != null) {
            params.add(new BasicNameValuePair("repository_access_level", project.getRepository_access_level().getDesc()));
        }
        if (project.getMerge_requests_access_level() != null) {
            params.add(new BasicNameValuePair("merge_requests_access_level", project.getMerge_requests_access_level().getDesc()));
        }
        if (project.getBuilds_access_level() != null) {
            params.add(new BasicNameValuePair("builds_access_level", project.getBuilds_access_level().getDesc()));
        }
        if (project.getWiki_access_level() != null) {
            params.add(new BasicNameValuePair("wiki_access_level", project.getWiki_access_level().getDesc()));
        }
        if (project.getSnippets_access_level() != null) {
            params.add(new BasicNameValuePair("snippets_access_level", project.getSnippets_access_level().getDesc()));
        }
        if (project.getPages_access_level() != null) {
            params.add(new BasicNameValuePair("pages_access_level", project.getPages_access_level().getDesc()));
        }
        if (project.getVisibility() != null) {
            params.add(new BasicNameValuePair("visibility", project.getVisibility().getDesc()));
        }
        if (project.getAuto_devops_deploy_strategy() != null) {
            params.add(new BasicNameValuePair("auto_devops_deploy_strategy", project.getAuto_devops_deploy_strategy().getDesc()));
        }
        if (project.getContainer_expiration_policy_attributes() != null) {
            params.add(new BasicNameValuePair("container_expiration_policy_attributes", project.getContainer_expiration_policy_attributes().toString()));
        }
        if (project.getMerge_method() != null) {
            params.add(new BasicNameValuePair("merge_method", project.getMerge_method().getDesc()));
        }
        if (project.getTag_list() != null) {
            params.add(new BasicNameValuePair("tag_list", project.getTag_list().toString()));
        }
        if (project.getBuild_timeout() != null) {

            params.add(new BasicNameValuePair("build_timeout", project.getBuild_timeout().toString()));
        }
        if (project.getApprovals_before_merge() != null) {
            params.add(new BasicNameValuePair("approvals_before_merge", project.getApprovals_before_merge().toString()));
        }

        if (project.getTemplate_project_id() != null) {
            params.add(new BasicNameValuePair("template_project_id", project.getTemplate_project_id().toString()));
        }

        if (project.getGroup_with_project_templates_id() != null) {
            params.add(new BasicNameValuePair("group_with_project_templates_id", project.getGroup_with_project_templates_id().toString()));
        }

        if (project.isAuto_cancel_pending_pipelines()) {
            params.add(new BasicNameValuePair("auto_cancel_pending_pipelines", "enabled"));
        } else {
            params.add(new BasicNameValuePair("auto_cancel_pending_pipelines", "disabled"));
        }

        if (project.getImport_url() != null) {
            params.add(new BasicNameValuePair("template_name", project.getTemplate_name()));
        }
        if (project.getRepository_storage() != null) {
            params.add(new BasicNameValuePair("repository_storage", project.getRepository_storage()));
        }


        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);

    }

    public String createProjectForUser(CreateProjectParams project, String root_private_token, String userId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/user/" + userId);
        httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("name", project.getName()));
        params.add(new BasicNameValuePair("path", project.getPath()));
        params.add(new BasicNameValuePair("description", project.getDescription()));
        params.add(new BasicNameValuePair("use_custom_template", String.valueOf(project.isUse_custom_template())));
        params.add(new BasicNameValuePair("emails_disabled", String.valueOf(project.isEmails_disabled())));
        params.add(new BasicNameValuePair("resolve_outdated_diff_discussions", String.valueOf(project.isResolve_outdated_diff_discussions())));
        params.add(new BasicNameValuePair("container_registry_enabled", String.valueOf(project.isContainer_registry_enabled())));
        params.add(new BasicNameValuePair("shared_runners_enabled", String.valueOf(project.isShared_runners_enabled())));
        params.add(new BasicNameValuePair("public_builds", String.valueOf(project.isPublic_builds())));
        params.add(new BasicNameValuePair("only_allow_merge_if_pipeline_succeeds", String.valueOf(project.isOnly_allow_merge_if_pipeline_succeeds())));
        params.add(new BasicNameValuePair("only_allow_merge_if_all_discussions_are_resolved", String.valueOf(project.isOnly_allow_merge_if_all_discussions_are_resolved())));
        params.add(new BasicNameValuePair("autoclose_referenced_issues", String.valueOf(project.isAutoclose_referenced_issues())));
        params.add(new BasicNameValuePair("remove_source_branch_after_merge", String.valueOf(project.isRemove_source_branch_after_merge())));
        params.add(new BasicNameValuePair("lfs_enabled", String.valueOf(project.isLfs_enabled())));
        params.add(new BasicNameValuePair("request_access_enabled", String.valueOf(project.isRequest_access_enabled())));
        params.add(new BasicNameValuePair("printing_merge_request_link_enabled", String.valueOf(project.isPrinting_merge_request_link_enabled())));
        params.add(new BasicNameValuePair("build_git_strategy", project.getBuild_git_strategy()));
        params.add(new BasicNameValuePair("build_coverage_regex", project.getBuild_coverage_regex()));
        params.add(new BasicNameValuePair("ci_config_path", project.getCi_config_path()));
        params.add(new BasicNameValuePair("auto_devops_enabled", String.valueOf(project.isAuto_devops_enabled())));
        params.add(new BasicNameValuePair("external_authorization_classification_label", project.getExternal_authorization_classification_label()));
        params.add(new BasicNameValuePair("mirror", String.valueOf(project.isMirror())));
        params.add(new BasicNameValuePair("mirror_trigger_builds", String.valueOf(project.isMirror_trigger_builds())));
        params.add(new BasicNameValuePair("initialize_with_readme", String.valueOf(project.isInitialize_with_readme())));
        params.add(new BasicNameValuePair("packages_enabled", String.valueOf(project.isPackages_enabled())));
        if (project.getNamespace_id() != null) {
            params.add(new BasicNameValuePair("namespace_id", project.getNamespace_id().toString()));
        }
        if (project.getDefault_branch() != null) {
            params.add(new BasicNameValuePair("default_branch", project.getDefault_branch()));
        }
        if (project.getRepository_access_level() != null) {
            params.add(new BasicNameValuePair("repository_access_level", project.getRepository_access_level().getDesc()));
        }
        if (project.getMerge_requests_access_level() != null) {
            params.add(new BasicNameValuePair("merge_requests_access_level", project.getMerge_requests_access_level().getDesc()));
        }
        if (project.getBuilds_access_level() != null) {
            params.add(new BasicNameValuePair("builds_access_level", project.getBuilds_access_level().getDesc()));
        }
        if (project.getWiki_access_level() != null) {
            params.add(new BasicNameValuePair("wiki_access_level", project.getWiki_access_level().getDesc()));
        }
        if (project.getSnippets_access_level() != null) {
            params.add(new BasicNameValuePair("snippets_access_level", project.getSnippets_access_level().getDesc()));
        }
        if (project.getPages_access_level() != null) {
            params.add(new BasicNameValuePair("pages_access_level", project.getPages_access_level().getDesc()));
        }
        if (project.getVisibility() != null) {
            params.add(new BasicNameValuePair("visibility", project.getVisibility().getDesc()));
        }
        if (project.getAuto_devops_deploy_strategy() != null) {
            params.add(new BasicNameValuePair("auto_devops_deploy_strategy", project.getAuto_devops_deploy_strategy().getDesc()));
        }
        if (project.getContainer_expiration_policy_attributes() != null) {
            params.add(new BasicNameValuePair("container_expiration_policy_attributes", project.getContainer_expiration_policy_attributes().toString()));
        }
        if (project.getMerge_method() != null) {
            params.add(new BasicNameValuePair("merge_method", project.getMerge_method().getDesc()));
        }
        if (project.getTag_list() != null) {
            params.add(new BasicNameValuePair("tag_list", project.getTag_list().toString()));
        }
        if (project.getBuild_timeout() != null) {

            params.add(new BasicNameValuePair("build_timeout", project.getBuild_timeout().toString()));
        }
        if (project.getApprovals_before_merge() != null) {
            params.add(new BasicNameValuePair("approvals_before_merge", project.getApprovals_before_merge().toString()));
        }

        if (project.getTemplate_project_id() != null) {
            params.add(new BasicNameValuePair("template_project_id", project.getTemplate_project_id().toString()));
        }

        if (project.getGroup_with_project_templates_id() != null) {
            params.add(new BasicNameValuePair("group_with_project_templates_id", project.getGroup_with_project_templates_id().toString()));
        }

        if (project.isAuto_cancel_pending_pipelines()) {
            params.add(new BasicNameValuePair("auto_cancel_pending_pipelines", "enabled"));
        } else {
            params.add(new BasicNameValuePair("auto_cancel_pending_pipelines", "disabled"));
        }

        if (project.getImport_url() != null) {
            params.add(new BasicNameValuePair("template_name", project.getTemplate_name()));
        }
        if (project.getRepository_storage() != null) {
            params.add(new BasicNameValuePair("repository_storage", project.getRepository_storage()));
        }


        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);

    }

    public String modifyProById(String projectId, CreateProjectParams project, String private_token) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId);
        httpPut.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("id", projectId));
        params.add(new BasicNameValuePair("name", project.getName()));
        params.add(new BasicNameValuePair("path", project.getPath()));
        params.add(new BasicNameValuePair("description", project.getDescription()));
        params.add(new BasicNameValuePair("emails_disabled", String.valueOf(project.isEmails_disabled())));
        params.add(new BasicNameValuePair("resolve_outdated_diff_discussions", String.valueOf(project.isResolve_outdated_diff_discussions())));
        params.add(new BasicNameValuePair("container_registry_enabled", String.valueOf(project.isContainer_registry_enabled())));
        params.add(new BasicNameValuePair("shared_runners_enabled", String.valueOf(project.isShared_runners_enabled())));
        params.add(new BasicNameValuePair("public_builds", String.valueOf(project.isPublic_builds())));
        params.add(new BasicNameValuePair("only_allow_merge_if_pipeline_succeeds", String.valueOf(project.isOnly_allow_merge_if_pipeline_succeeds())));
        params.add(new BasicNameValuePair("only_allow_merge_if_all_discussions_are_resolved", String.valueOf(project.isOnly_allow_merge_if_all_discussions_are_resolved())));
        params.add(new BasicNameValuePair("autoclose_referenced_issues", String.valueOf(project.isAutoclose_referenced_issues())));
        params.add(new BasicNameValuePair("remove_source_branch_after_merge", String.valueOf(project.isRemove_source_branch_after_merge())));
        params.add(new BasicNameValuePair("lfs_enabled", String.valueOf(project.isLfs_enabled())));
        params.add(new BasicNameValuePair("request_access_enabled", String.valueOf(project.isRequest_access_enabled())));
        params.add(new BasicNameValuePair("printing_merge_request_link_enabled", String.valueOf(project.isPrinting_merge_request_link_enabled())));
        params.add(new BasicNameValuePair("build_git_strategy", project.getBuild_git_strategy()));
        params.add(new BasicNameValuePair("build_coverage_regex", project.getBuild_coverage_regex()));
        params.add(new BasicNameValuePair("ci_config_path", project.getCi_config_path()));
        params.add(new BasicNameValuePair("auto_devops_enabled", String.valueOf(project.isAuto_devops_enabled())));
        params.add(new BasicNameValuePair("external_authorization_classification_label", project.getExternal_authorization_classification_label()));
        params.add(new BasicNameValuePair("mirror", String.valueOf(project.isMirror())));
        params.add(new BasicNameValuePair("mirror_trigger_builds", String.valueOf(project.isMirror_trigger_builds())));
        params.add(new BasicNameValuePair("packages_enabled", String.valueOf(project.isPackages_enabled())));
        params.add(new BasicNameValuePair("only_mirror_protected_branches", String.valueOf(project.isOnly_mirror_protected_branches())));
        params.add(new BasicNameValuePair("mirror_overwrites_diverged_branches", String.valueOf(project.isMirror_overwrites_diverged_branches())));
        params.add(new BasicNameValuePair("service_desk_enabled", String.valueOf(project.isService_desk_enabled())));

        if (project.getNamespace_id() != null) {
            params.add(new BasicNameValuePair("namespace_id", project.getNamespace_id().toString()));
        }
        if (project.getDefault_branch() != null) {
            params.add(new BasicNameValuePair("default_branch", project.getDefault_branch()));
        }
        if (project.getRepository_access_level() != null) {
            params.add(new BasicNameValuePair("repository_access_level", project.getRepository_access_level().getDesc()));
        }
        if (project.getMerge_requests_access_level() != null) {
            params.add(new BasicNameValuePair("merge_requests_access_level", project.getMerge_requests_access_level().getDesc()));
        }
        if (project.getBuilds_access_level() != null) {
            params.add(new BasicNameValuePair("builds_access_level", project.getBuilds_access_level().getDesc()));
        }
        if (project.getWiki_access_level() != null) {
            params.add(new BasicNameValuePair("wiki_access_level", project.getWiki_access_level().getDesc()));
        }
        if (project.getSnippets_access_level() != null) {
            params.add(new BasicNameValuePair("snippets_access_level", project.getSnippets_access_level().getDesc()));
        }
        if (project.getPages_access_level() != null) {
            params.add(new BasicNameValuePair("pages_access_level", project.getPages_access_level().getDesc()));
        }
        if (project.getVisibility() != null) {
            params.add(new BasicNameValuePair("visibility", project.getVisibility().getDesc()));
        }
        if (project.getAuto_devops_deploy_strategy() != null) {
            params.add(new BasicNameValuePair("auto_devops_deploy_strategy", project.getAuto_devops_deploy_strategy().getDesc()));
        }
        if (project.getContainer_expiration_policy_attributes() != null) {
            params.add(new BasicNameValuePair("container_expiration_policy_attributes", project.getContainer_expiration_policy_attributes().toString()));
        }
        if (project.getMerge_method() != null) {
            params.add(new BasicNameValuePair("merge_method", project.getMerge_method().getDesc()));
        }
        if (project.getTag_list() != null) {
            params.add(new BasicNameValuePair("tag_list", project.getTag_list().toString()));
        }
        if (project.getBuild_timeout() != null) {

            params.add(new BasicNameValuePair("build_timeout", project.getBuild_timeout().toString()));
        }
        if (project.getApprovals_before_merge() != null) {
            params.add(new BasicNameValuePair("approvals_before_merge", project.getApprovals_before_merge().toString()));
        }

        if (project.isAuto_cancel_pending_pipelines()) {
            params.add(new BasicNameValuePair("auto_cancel_pending_pipelines", "enabled"));
        } else {
            params.add(new BasicNameValuePair("auto_cancel_pending_pipelines", "disabled"));
        }

        if (project.getImport_url() != null) {
            params.add(new BasicNameValuePair("template_name", project.getTemplate_name()));
        }
        if (project.getRepository_storage() != null) {
            params.add(new BasicNameValuePair("repository_storage", project.getRepository_storage()));
        }
        if (project.getSuggestion_commit_message() != null) {
            params.add(new BasicNameValuePair("suggestion_commit_message", project.getSuggestion_commit_message()));
        }
        if (project.getCi_default_git_depth() != null) {
            params.add(new BasicNameValuePair("ci_default_git_depth", project.getCi_default_git_depth().toString()));
        }
        if (project.getMirror_user_id() != null) {
            params.add(new BasicNameValuePair("mirror_user_id", project.getMirror_user_id().toString()));
        }


        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);

        httpPut.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPut);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);

    }


    public String forkProject(String private_token, ForkProjectParams project) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + project.getId() + "/fork");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("id", project.getId()));
        if (project.getNamespace_id() != null) {
            params.add(new BasicNameValuePair("namespace_id", project.getNamespace_id().toString()));
        }
        if (project.getNamespace_path() != null && !"".equals(project.getNamespace_path())) {
            params.add(new BasicNameValuePair("namespace_path", project.getNamespace_path()));
        }
        if (project.getPath() != null && !"".equals(project.getPath())) {
            params.add(new BasicNameValuePair("path", project.getPath()));
        }
        if (project.getName() != null && !"".equals(project.getName())) {
            params.add(new BasicNameValuePair("name", project.getName()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }


    public String getProjectForks(String private_token, ListForkProjectParams project) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + project.getId() + "/forks");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("id", project.getId()));
        if (project.getPagination() != null) {
            params.add(new BasicNameValuePair("page", project.getPagination().getPage().toString()));
            params.add(new BasicNameValuePair("per_page", project.getPagination().getPer_page().toString()));
        }
        if (project.getVisibility() != null) {
            params.add(new BasicNameValuePair("visibility", project.getVisibility().getDesc()));
        }
        if (project.getOrder_by() != null) {
            params.add(new BasicNameValuePair("order_by", project.getOrder_by().getDesc()));
        }
        if (project.getSort() != null) {
            params.add(new BasicNameValuePair("sort", project.getSort().toString()));
        }
        if (project.getSearch() != null && !"".equals(project.getSearch())) {
            params.add(new BasicNameValuePair("sort", project.getSearch()));
        }
        if (project.getMin_access_level() != null) {
            params.add(new BasicNameValuePair("min_access_level", project.getMin_access_level().getLevel().toString()));
        }
        params.add(new BasicNameValuePair("archived", String.valueOf(project.isArchived())));
        params.add(new BasicNameValuePair("simple", String.valueOf(project.isSimple())));
        params.add(new BasicNameValuePair("owned", String.valueOf(project.isOwned())));
        params.add(new BasicNameValuePair("membership", String.valueOf(project.isMembership())));
        params.add(new BasicNameValuePair("starred", String.valueOf(project.isStarred())));
        params.add(new BasicNameValuePair("statistics", String.valueOf(project.isStatistics())));
        params.add(new BasicNameValuePair("with_custom_attributes", String.valueOf(project.isWith_custom_attributes())));
        params.add(new BasicNameValuePair("with_issues_enabled", String.valueOf(project.isWith_issues_enabled())));
        params.add(new BasicNameValuePair("with_merge_requests_enabled", String.valueOf(project.isWith_merge_requests_enabled())));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }


    public String starProject(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/star");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == HttpCode.NOTMODIFIED.getCode()) {
            return Global.getPorpties("isStarted");
        } else {
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    public String unStarProject(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/unstar");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == HttpCode.NOTMODIFIED.getCode()) {
            return Global.getPorpties("isUnStarted");
        } else {
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    public String getProjectStarrers(String private_token, String projectId, String searchCondition, Pagination pagination) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/starrers");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (pagination != null) {
            params.add(new BasicNameValuePair("page", pagination.getPage().toString()));
            params.add(new BasicNameValuePair("per_page", pagination.getPer_page().toString()));
        }
        if (searchCondition != null && !"".equals(searchCondition)) {
            params.add(new BasicNameValuePair("search", searchCondition));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getLanguagesPercentage(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/languages");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String archiveProject(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/archive");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);

    }

    public String unArchiveProject(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/unarchive");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);

    }

    public String delProject(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId);
        httpDelete.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);

        httpDelete.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return HttpCode.DELETESUCCESS.getDescription();
        } else if (response.getStatusLine().getStatusCode() == HttpCode.MARKEDDELETE.getCode()) {
            return HttpCode.MARKEDDELETE.getDescription();
        } else {
            return EntityUtils.toString(response.getEntity());
        }
    }

    public String restoreProject(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/restore");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        return EntityUtils.toString(response.getEntity());
    }

    public String uploadFileToProject(String private_token, String projectId, String filePath) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/uploads");
        httpPost.addHeader("Content-Type", "multipart/form-data");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        params.add(new BasicNameValuePair("file", "@" + filePath));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        return EntityUtils.toString(response.getEntity());
    }

    public String shareProjectWithGroup(String private_token, String projectId, Integer group_id, UserAccessLevel group_access, Date expires_at) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/share");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        params.add(new BasicNameValuePair("group_id", group_id.toString()));
        params.add(new BasicNameValuePair("group_access", group_access.getLevel().toString()));
        if (expires_at != null) {
            params.add(new BasicNameValuePair("expires_at", new SimpleDateFormat("yyyy-MM-dd").format(expires_at)));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        return EntityUtils.toString(response.getEntity());
    }

    public String stopShareProjectWithGroup(String private_token, String projectId, Integer groupId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/share/" + groupId);
        httpDelete.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        params.add(new BasicNameValuePair("group_id", groupId.toString()));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);

        httpDelete.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpDelete);

        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("stopShareSuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String getProjectHooks(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/hooks");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getProjectHook(String private_token, String projectId, Integer hook_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/hooks/" + hook_id);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        params.add(new BasicNameValuePair("hook_id", hook_id.toString()));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String addProjectHook(String private_token, CreateProjectHookParams hook) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + hook.getId() + "/hooks");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", hook.getId()));
        params.add(new BasicNameValuePair("url", hook.getUrl()));
        params.add(new BasicNameValuePair("push_events", String.valueOf(hook.isPush_events())));
        params.add(new BasicNameValuePair("issues_events", String.valueOf(hook.isIssues_events())));
        params.add(new BasicNameValuePair("confidential_issues_events", String.valueOf(hook.isConfidential_issues_events())));
        params.add(new BasicNameValuePair("merge_requests_events", String.valueOf(hook.isMerge_requests_events())));
        params.add(new BasicNameValuePair("tag_push_events", String.valueOf(hook.isTag_push_events())));
        params.add(new BasicNameValuePair("note_events", String.valueOf(hook.isNote_events())));
        params.add(new BasicNameValuePair("job_events", String.valueOf(hook.isJob_events())));
        params.add(new BasicNameValuePair("pipeline_events", String.valueOf(hook.isPipeline_events())));
        params.add(new BasicNameValuePair("wiki_page_events", String.valueOf(hook.isWiki_page_events())));
        params.add(new BasicNameValuePair("enable_ssl_verification", String.valueOf(hook.isEnable_ssl_verification())));
        if (hook.getPush_events_branch_filter() != null && !"".equals(hook.getPush_events_branch_filter())) {
            params.add(new BasicNameValuePair("push_events_branch_filter", hook.getPush_events_branch_filter()));
        }
        if (hook.getToken() != null && !"".equals(hook.getToken())) {
            params.add(new BasicNameValuePair("token", hook.getToken()));
        }

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String modifyProjectHook(String private_token, Integer hook_id, CreateProjectHookParams hook) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + hook.getId() + "/hooks/" + hook_id);
        httpPut.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", hook.getId()));
        params.add(new BasicNameValuePair("hook_id", hook_id.toString()));
        params.add(new BasicNameValuePair("url", hook.getUrl()));
        params.add(new BasicNameValuePair("push_events", String.valueOf(hook.isPush_events())));
        params.add(new BasicNameValuePair("issues_events", String.valueOf(hook.isIssues_events())));
        params.add(new BasicNameValuePair("confidential_issues_events", String.valueOf(hook.isConfidential_issues_events())));
        params.add(new BasicNameValuePair("merge_requests_events", String.valueOf(hook.isMerge_requests_events())));
        params.add(new BasicNameValuePair("tag_push_events", String.valueOf(hook.isTag_push_events())));
        params.add(new BasicNameValuePair("note_events", String.valueOf(hook.isNote_events())));
        params.add(new BasicNameValuePair("job_events", String.valueOf(hook.isJob_events())));
        params.add(new BasicNameValuePair("pipeline_events", String.valueOf(hook.isPipeline_events())));
        params.add(new BasicNameValuePair("wiki_page_events", String.valueOf(hook.isWiki_page_events())));
        params.add(new BasicNameValuePair("enable_ssl_verification", String.valueOf(hook.isEnable_ssl_verification())));
        if (hook.getPush_events_branch_filter() != null && !"".equals(hook.getPush_events_branch_filter())) {
            params.add(new BasicNameValuePair("push_events_branch_filter", hook.getPush_events_branch_filter()));
        }
        if (hook.getToken() != null && !"".equals(hook.getToken())) {
            params.add(new BasicNameValuePair("token", hook.getToken()));
        }

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpPut.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPut);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String delProjectHook(String private_token, String projectId, Integer hook_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/hooks/" + hook_id);
        httpDelete.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        params.add(new BasicNameValuePair("hook_id", hook_id.toString()));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpDelete.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpDelete);

        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delProHookSuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String createForkBetweenExistProject(String private_token, String projectId, Integer forked_from_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/fork/" + forked_from_id);
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        params.add(new BasicNameValuePair("forked_from_id", forked_from_id.toString()));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String delExistFork(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/fork");
        httpDelete.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpDelete.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delProForkSuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String searchProjectByName(String private_token, String proName, ProListOrderBy order_by, SortMethod sort) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("search", proName));
        if (order_by != null) {
            params.add(new BasicNameValuePair("order_by", order_by.getDesc()));
        }
        if (sort != null) {
            params.add(new BasicNameValuePair("sort", sort.getDesc()));
        }

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String houseKeepingProject(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/housekeeping");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }


    public String getProjectPushRules(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/push_rule");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String addProjectPushRules(String private_token, AddProjectPushRuleParams project) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + project.getId() + "/push_rule");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", project.getId()));
        params.add(new BasicNameValuePair("deny_delete_tag", String.valueOf(project.isDeny_delete_tag())));
        params.add(new BasicNameValuePair("member_check", String.valueOf(project.isMember_check())));
        params.add(new BasicNameValuePair("prevent_secrets ", String.valueOf(project.isPrevent_secrets())));
        params.add(new BasicNameValuePair("commit_committer_check ", String.valueOf(project.isCommit_committer_check())));
        params.add(new BasicNameValuePair("reject_unsigned_commits  ", String.valueOf(project.isReject_unsigned_commits())));
        if (project.getCommit_message_regex() != null && !"".equals(project.getCommit_message_regex())) {
            params.add(new BasicNameValuePair("commit_message_regex", project.getCommit_message_regex()));
        }
        if (project.getCommit_message_negative_regex() != null && !"".equals(project.getCommit_message_negative_regex())) {
            params.add(new BasicNameValuePair("commit_message_negative_regex", project.getCommit_message_regex()));
        }
        if (project.getBranch_name_regex() != null && !"".equals(project.getBranch_name_regex())) {
            params.add(new BasicNameValuePair("branch_name_regex", project.getBranch_name_regex()));
        }
        if (project.getAuthor_email_regex() != null && !"".equals(project.getAuthor_email_regex())) {
            params.add(new BasicNameValuePair("author_email_regex", project.getAuthor_email_regex()));
        }
        if (project.getFile_name_regex() != null && !"".equals(project.getFile_name_regex())) {
            params.add(new BasicNameValuePair("file_name_regex", project.getFile_name_regex()));
        }
        if (project.getMax_file_size() != null) {
            params.add(new BasicNameValuePair("max_file_size", project.getMax_file_size().toString()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String modProjectPushRules(String private_token, AddProjectPushRuleParams project) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + project.getId() + "/push_rule");
        httpPut.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", project.getId()));
        params.add(new BasicNameValuePair("deny_delete_tag", String.valueOf(project.isDeny_delete_tag())));
        params.add(new BasicNameValuePair("member_check", String.valueOf(project.isMember_check())));
        params.add(new BasicNameValuePair("prevent_secrets ", String.valueOf(project.isPrevent_secrets())));
        params.add(new BasicNameValuePair("commit_committer_check ", String.valueOf(project.isCommit_committer_check())));
        params.add(new BasicNameValuePair("reject_unsigned_commits  ", String.valueOf(project.isReject_unsigned_commits())));
        if (project.getCommit_message_regex() != null && !"".equals(project.getCommit_message_regex())) {
            params.add(new BasicNameValuePair("commit_message_regex", project.getCommit_message_regex()));
        }
        if (project.getCommit_message_negative_regex() != null && !"".equals(project.getCommit_message_negative_regex())) {
            params.add(new BasicNameValuePair("commit_message_negative_regex", project.getCommit_message_regex()));
        }
        if (project.getBranch_name_regex() != null && !"".equals(project.getBranch_name_regex())) {
            params.add(new BasicNameValuePair("branch_name_regex", project.getBranch_name_regex()));
        }
        if (project.getAuthor_email_regex() != null && !"".equals(project.getAuthor_email_regex())) {
            params.add(new BasicNameValuePair("author_email_regex", project.getAuthor_email_regex()));
        }
        if (project.getFile_name_regex() != null && !"".equals(project.getFile_name_regex())) {
            params.add(new BasicNameValuePair("file_name_regex", project.getFile_name_regex()));
        }
        if (project.getMax_file_size() != null) {
            params.add(new BasicNameValuePair("max_file_size", project.getMax_file_size().toString()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpPut.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPut);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String delProjectPushRules(String private_token, String projectId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/push_rule");
        httpDelete.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpDelete.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delPushRuleSuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String transferProjectToNewNamespace(String private_token, String projectId, String namespace) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/transfer");
        httpPut.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("namespace", namespace));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpPut.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPut);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String downloadSnapshot(String root_private_token, String projectId, boolean wiki) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId + "/snapshot");
        httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", projectId));
        params.add(new BasicNameValuePair("wiki", String.valueOf(wiki)));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }
}
