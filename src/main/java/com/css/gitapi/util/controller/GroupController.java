package com.css.gitapi.util.controller;

import com.css.gitapi.util.enums.HttpCode;
import com.css.gitapi.util.httputil.HttpGetWithBody;
import com.css.gitapi.util.httputil.ResponseGetPageTools;
import com.css.gitapi.util.model.*;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/9 9:20
 */
public class GroupController {
    public String getGroups(String private_token, ListGroupsParams params) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();


        formparams.add(new BasicNameValuePair("all_available", String.valueOf(params.isAll_available())));
        formparams.add(new BasicNameValuePair("statistics", String.valueOf(params.isStatistics())));
        formparams.add(new BasicNameValuePair("with_custom_attributes", String.valueOf(params.isWith_custom_attributes())));
        formparams.add(new BasicNameValuePair("owned", String.valueOf(params.isOwned())));
        if (params.getPagination() != null) {
            formparams.add(new BasicNameValuePair("page", params.getPagination().getPage().toString()));
            formparams.add(new BasicNameValuePair("per_page", params.getPagination().getPer_page().toString()));
        }
        if (params.getSkip_groups() != null && params.getSkip_groups().length > 0) {
            formparams.add(new BasicNameValuePair("skip_groups", params.getSkip_groups().toString()));
        }
        if (params.getSearch() != null && !"".equals(params.getSearch())) {
            formparams.add(new BasicNameValuePair("search", params.getSearch()));
        }
        if (params.getOrder_by() != null) {
            formparams.add(new BasicNameValuePair("order_by", params.getOrder_by().toString()));
        }
        if (params.getMin_access_level() != null) {
            formparams.add(new BasicNameValuePair("sort", params.getMin_access_level().toString()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return ResponseGetPageTools.getPageInfoByReponse(response) + EntityUtils.toString(entity1);
    }

    public String getSubGroups(String private_token, String parentGroupId, ListGroupsParams params) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + parentGroupId + "/subgroups");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        formparams.add(new BasicNameValuePair("all_available", String.valueOf(params.isAll_available())));
        formparams.add(new BasicNameValuePair("statistics", String.valueOf(params.isStatistics())));
        formparams.add(new BasicNameValuePair("with_custom_attributes", String.valueOf(params.isWith_custom_attributes())));
        formparams.add(new BasicNameValuePair("owned", String.valueOf(params.isOwned())));
        if (params.getPagination() != null) {
            formparams.add(new BasicNameValuePair("page", params.getPagination().getPage().toString()));
            formparams.add(new BasicNameValuePair("per_page", params.getPagination().getPer_page().toString()));
        }
        if (params.getSkip_groups() != null && params.getSkip_groups().length > 0) {
            formparams.add(new BasicNameValuePair("skip_groups", params.getSkip_groups().toString()));
        }
        if (params.getSearch() != null && !"".equals(params.getSearch())) {
            formparams.add(new BasicNameValuePair("search", params.getSearch()));
        }
        if (params.getOrder_by() != null) {
            formparams.add(new BasicNameValuePair("order_by", params.getOrder_by().toString()));
        }
        if (params.getMin_access_level() != null) {
            formparams.add(new BasicNameValuePair("sort", params.getMin_access_level().toString()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }


    public String getGroupProjects(String private_token, String group_id, ListGroupProjectParams params) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/projects");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("id", group_id));
        formparams.add(new BasicNameValuePair("archived", String.valueOf(params.isArchived())));
        formparams.add(new BasicNameValuePair("simple", String.valueOf(params.isSimple())));
        formparams.add(new BasicNameValuePair("with_custom_attributes", String.valueOf(params.isWith_custom_attributes())));
        formparams.add(new BasicNameValuePair("owned", String.valueOf(params.isOwned())));
        formparams.add(new BasicNameValuePair("starred", String.valueOf(params.isStarred())));
        formparams.add(new BasicNameValuePair("with_issues_enabled", String.valueOf(params.isWith_issues_enabled())));
        formparams.add(new BasicNameValuePair("with_merge_requests_enabled", String.valueOf(params.isWith_merge_requests_enabled())));
        formparams.add(new BasicNameValuePair("with_shared", String.valueOf(params.isWith_shared())));
        formparams.add(new BasicNameValuePair("include_subgroups", String.valueOf(params.isInclude_subgroups())));
        if (params.getPagination() != null) {
            formparams.add(new BasicNameValuePair("page", params.getPagination().getPage().toString()));
            formparams.add(new BasicNameValuePair("per_page", params.getPagination().getPer_page().toString()));
        }
        if (params.getVisibility() != null) {
            formparams.add(new BasicNameValuePair("visibility", params.getVisibility().getDesc()));
        }
        if (params.getSearch() != null && !"".equals(params.getSearch())) {
            formparams.add(new BasicNameValuePair("search", params.getSearch()));
        }
        if (params.getOrder_by() != null) {
            formparams.add(new BasicNameValuePair("order_by", params.getOrder_by().toString()));
        }
        if (params.getMin_access_level() != null) {
            formparams.add(new BasicNameValuePair("sort", params.getMin_access_level().toString()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getGroupDetails(String private_token, String group_id, boolean with_custom_attributes, boolean with_projects) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("id", group_id));
        formparams.add(new BasicNameValuePair("with_custom_attributes", String.valueOf(with_custom_attributes)));
        formparams.add(new BasicNameValuePair("with_projects", String.valueOf(with_projects)));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String createGroup(String private_token, CreateGroupParams groupParams) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        formparams.add(new BasicNameValuePair("name", groupParams.getName()));
        formparams.add(new BasicNameValuePair("path", groupParams.getPath()));
        formparams.add(new BasicNameValuePair("share_with_group_lock", String.valueOf(groupParams.isShare_with_group_lock())));
        formparams.add(new BasicNameValuePair("require_two_factor_authentication", String.valueOf(groupParams.isRequire_two_factor_authentication())));
        formparams.add(new BasicNameValuePair("auto_devops_enabled", String.valueOf(groupParams.isAuto_devops_enabled())));
        formparams.add(new BasicNameValuePair("emails_disabled", String.valueOf(groupParams.isEmails_disabled())));
        formparams.add(new BasicNameValuePair("mentions_disabled", String.valueOf(groupParams.isMentions_disabled())));
        formparams.add(new BasicNameValuePair("lfs_enabled", String.valueOf(groupParams.isLfs_enabled())));
        formparams.add(new BasicNameValuePair("request_access_enabled", String.valueOf(groupParams.isRequest_access_enabled())));

        if (groupParams.getDescription() != null && !"".equals(groupParams.getDescription())) {
            formparams.add(new BasicNameValuePair("description", groupParams.getDescription()));
        }
        if (groupParams.getVisibility() != null) {
            formparams.add(new BasicNameValuePair("visibility", groupParams.getVisibility().getDesc()));
        }
        if (groupParams.getTwo_factor_grace_period() != null) {
            formparams.add(new BasicNameValuePair("two_factor_grace_period", groupParams.getTwo_factor_grace_period().toString()));
        }
        if (groupParams.getProject_creation_level() != null) {
            formparams.add(new BasicNameValuePair("project_creation_level", groupParams.getProject_creation_level().getDesc()));
        }
        if (groupParams.getSubgroup_creation_level() != null) {
            formparams.add(new BasicNameValuePair("subgroup_creation_level", groupParams.getSubgroup_creation_level().getDesc()));
        }
        if (groupParams.getParent_id() != null) {
            formparams.add(new BasicNameValuePair("description", groupParams.getParent_id().toString()));
        }
        if (groupParams.getDefault_branch_protection() != null) {
            formparams.add(new BasicNameValuePair("description", groupParams.getDefault_branch_protection().getCode().toString()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String transferProjectToGroup(String root_private_token, String group_id, String project_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/projects/" + project_id);
        httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String updateGroup(String private_token, Integer group_id, CreateGroupParams groupParams) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id);
        httpPut.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        formparams.add(new BasicNameValuePair("name", groupParams.getName()));
        formparams.add(new BasicNameValuePair("path", groupParams.getPath()));
        formparams.add(new BasicNameValuePair("share_with_group_lock", String.valueOf(groupParams.isShare_with_group_lock())));
        formparams.add(new BasicNameValuePair("require_two_factor_authentication", String.valueOf(groupParams.isRequire_two_factor_authentication())));
        formparams.add(new BasicNameValuePair("auto_devops_enabled", String.valueOf(groupParams.isAuto_devops_enabled())));
        formparams.add(new BasicNameValuePair("emails_disabled", String.valueOf(groupParams.isEmails_disabled())));
        formparams.add(new BasicNameValuePair("mentions_disabled", String.valueOf(groupParams.isMentions_disabled())));
        formparams.add(new BasicNameValuePair("lfs_enabled", String.valueOf(groupParams.isLfs_enabled())));
        formparams.add(new BasicNameValuePair("request_access_enabled", String.valueOf(groupParams.isRequest_access_enabled())));

        if (groupParams.getDescription() != null && !"".equals(groupParams.getDescription())) {
            formparams.add(new BasicNameValuePair("description", groupParams.getDescription()));
        }
        if (groupParams.getVisibility() != null) {
            formparams.add(new BasicNameValuePair("visibility", groupParams.getVisibility().getDesc()));
        }
        if (groupParams.getTwo_factor_grace_period() != null) {
            formparams.add(new BasicNameValuePair("two_factor_grace_period", groupParams.getTwo_factor_grace_period().toString()));
        }
        if (groupParams.getProject_creation_level() != null) {
            formparams.add(new BasicNameValuePair("project_creation_level", groupParams.getProject_creation_level().getDesc()));
        }
        if (groupParams.getSubgroup_creation_level() != null) {
            formparams.add(new BasicNameValuePair("subgroup_creation_level", groupParams.getSubgroup_creation_level().getDesc()));
        }
        if (groupParams.getParent_id() != null) {
            formparams.add(new BasicNameValuePair("description", groupParams.getParent_id().toString()));
        }
        if (groupParams.getDefault_branch_protection() != null) {
            formparams.add(new BasicNameValuePair("description", groupParams.getDefault_branch_protection().getCode().toString()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPut.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPut);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String delGroup(String private_token, String group_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id);
        httpDelete.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delGroupSuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String searchGroup(String private_token, String key_words) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("search", key_words));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getGroupHooks(String private_token, String group_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/hooks");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getGroupHook(String private_token, String group_id, Integer hook_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/hooks/" + hook_id);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String addGroupHook(String private_token, CreateGroupHookParams params) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + params.getGroup_id() + "/hooks");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("id", params.getGroup_id()));
        formparams.add(new BasicNameValuePair("url", params.getHook_url()));
        formparams.add(new BasicNameValuePair("push_events", String.valueOf(params.isPush_events())));
        formparams.add(new BasicNameValuePair("issues_events", String.valueOf(params.isIssues_events())));
        formparams.add(new BasicNameValuePair("confidential_issues_events", String.valueOf(params.isConfidential_issues_events())));
        formparams.add(new BasicNameValuePair("merge_requests_events", String.valueOf(params.isMerge_requests_events())));
        formparams.add(new BasicNameValuePair("tag_push_events", String.valueOf(params.isTag_push_events())));
        formparams.add(new BasicNameValuePair("note_events", String.valueOf(params.isNote_events())));
        formparams.add(new BasicNameValuePair("job_events", String.valueOf(params.isJob_events())));
        formparams.add(new BasicNameValuePair("pipeline_events", String.valueOf(params.isPipeline_events())));
        formparams.add(new BasicNameValuePair("wiki_page_events", String.valueOf(params.isWiki_page_events())));
        formparams.add(new BasicNameValuePair("enable_ssl_verification", String.valueOf(params.isEnable_ssl_verification())));

        if (params.getToken() != null && !"".equals(params.getToken())) {
            formparams.add(new BasicNameValuePair("token", params.getToken()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String modifyGroupHook(String private_token, Integer hook_id, CreateGroupHookParams params) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + params.getGroup_id() + "/hooks/" + hook_id);
        httpPut.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("id", params.getGroup_id()));
        formparams.add(new BasicNameValuePair("hook_id", hook_id.toString()));
        formparams.add(new BasicNameValuePair("url", params.getHook_url()));
        formparams.add(new BasicNameValuePair("push_events", String.valueOf(params.isPush_events())));
        formparams.add(new BasicNameValuePair("issues_events", String.valueOf(params.isIssues_events())));
        formparams.add(new BasicNameValuePair("confidential_issues_events", String.valueOf(params.isConfidential_issues_events())));
        formparams.add(new BasicNameValuePair("merge_requests_events", String.valueOf(params.isMerge_requests_events())));
        formparams.add(new BasicNameValuePair("tag_push_events", String.valueOf(params.isTag_push_events())));
        formparams.add(new BasicNameValuePair("note_events", String.valueOf(params.isNote_events())));
        formparams.add(new BasicNameValuePair("job_events", String.valueOf(params.isJob_events())));
        formparams.add(new BasicNameValuePair("pipeline_events", String.valueOf(params.isPipeline_events())));
        formparams.add(new BasicNameValuePair("wiki_page_events", String.valueOf(params.isWiki_page_events())));
        formparams.add(new BasicNameValuePair("enable_ssl_verification", String.valueOf(params.isEnable_ssl_verification())));

        if (params.getToken() != null && !"".equals(params.getToken())) {
            formparams.add(new BasicNameValuePair("token", params.getToken()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpPut.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPut);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String delGroupHook(String private_token, String group_id, Integer hook_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/hooks/" + hook_id);
        httpDelete.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delGroupHookSuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

}
