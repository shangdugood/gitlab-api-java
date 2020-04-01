package com.css.gitapi.util.controller;

import com.css.gitapi.util.httputil.HttpGetWithBody;
import com.css.gitapi.util.model.GitLabListProParams;
import com.css.gitapi.util.model.GitLabPro;
import com.css.gitapi.util.model.GitLabSingleProParams;
import com.css.gitapi.util.model.Global;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 11:10
 */
public class ProjectController {
    public String getAllProjects(GitLabListProParams params) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects");
        httpGet.addHeader("PRIVATE-TOKEN", Global.root_private_token);
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

    public String getUserProjects(String userId,GitLabListProParams params) throws Exception{
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/projects");
        httpGet.addHeader("PRIVATE-TOKEN", Global.root_private_token);
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
    public String getUserStarredProjects(String userId,GitLabListProParams params) throws Exception{
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/starred_projects");
        httpGet.addHeader("PRIVATE-TOKEN", Global.root_private_token);
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

    public String getProjectById(String projectId, GitLabSingleProParams params) throws Exception{
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId);
        httpGet.addHeader("PRIVATE-TOKEN", Global.root_private_token);
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

    public String getProjectUsers(String projectId,String search,int[] skip_users_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + projectId +"/users");
        httpGet.addHeader("PRIVATE-TOKEN", Global.root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if(!"".equals(search)){
            formparams.add(new BasicNameValuePair("search", search));
        }
        if(skip_users_id.length != 0){
            formparams.add(new BasicNameValuePair("skip_users", skip_users_id.toString()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }



    public String createProject(GitLabPro project,String creator_private_token) throws Exception {
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
        if (project.getDefault_branch() != null){
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

    public String createProjectForUser(GitLabPro project,String userId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/user/"+userId);
        httpPost.addHeader("PRIVATE-TOKEN", Global.root_private_token);
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
        if (project.getDefault_branch() != null){
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

}
