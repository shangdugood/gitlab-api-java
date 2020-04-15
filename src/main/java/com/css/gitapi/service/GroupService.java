package com.css.gitapi.service;

import com.css.gitapi.enums.HttpCode;
import com.css.gitapi.httputil.HttpGetWithBody;
import com.css.gitapi.httputil.ResponseGetPageTools;
import com.css.gitapi.model.*;
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
 * @date 2020/4/9 11:43
 */
public class GroupService {
    private final String COMMON_URL = "http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/";


    /**
     * 删除群组的hook
     *
     * @param private_token 用户的private_token
     * @param hook_id       要删除的hook的id
     * @return
     * @throws Exception
     */
    public String delGroupHook(String private_token, String group_id, Integer hook_id) throws Exception {

        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (hook_id == null || "".equals(hook_id)) {
            return "The hook_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDelete httpDelete = new HttpDelete(COMMON_URL + group_id + "/hooks/" + hook_id);
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

    /**
     * 修改群组的hook
     *
     * @param private_token 用户的private_token
     * @param hook_id       要修改的hook的ID
     * @param params        添加hook的参数，其中group_id和hook_url是必须的
     * @return
     * @throws Exception
     */
    public String modifyGroupHook(String private_token, Integer hook_id, CreateGroupHookParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (hook_id == null || "".equals(hook_id)) {
            return "The hook_id is required.";
        } else if (params.getGroup_id() == null || "".equals(params.getGroup_id())) {
            return "The params's group_id is required.";
        } else if (params.getHook_url() == null || "".equals(params.getHook_url())) {
            return "The params's hook_url is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(COMMON_URL + params.getGroup_id() + "/hooks/" + hook_id);
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
    }

    /**
     * 为群组添加hook
     *
     * @param private_token 用户的private_token
     * @param params        添加hook的参数，其中group_id和hook_url是必须的
     * @return
     * @throws Exception
     */
    public String addGroupHook(String private_token, CreateGroupHookParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (params.getGroup_id() == null || "".equals(params.getGroup_id())) {
            return "The params's group_id is required.";
        } else if (params.getHook_url() == null || "".equals(params.getHook_url())) {
            return "The params's hook_url is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + params.getGroup_id() + "/hooks");
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
    }

    /**
     * 查询群组指定的hook
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL路径
     * @param hook_id       指定的hook的ID
     * @return
     * @throws Exception
     */
    public String getGroupHook(String private_token, String group_id, Integer hook_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (hook_id == null || "".equals(hook_id)) {
            return "The hook_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COMMON_URL + group_id + "/hooks/" + hook_id);
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 查询群组的hooks
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL路径
     * @return
     * @throws Exception
     */
    public String getGroupHooks(String private_token, String group_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COMMON_URL + group_id + "/hooks");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 搜索群组,关键字的长度至少为连续3位
     * 可以查到用户自己的讨论组以及其他公共讨论组
     *
     * @param private_token 用户的private_token
     * @param key_word      关键字
     * @return
     * @throws Exception
     */
    public String searchGroup(String private_token, String key_word) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (key_word == null || "".equals(key_word)) {
            return "The key_word is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("search", key_word));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            httpGet.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 删除群组
     *
     * @param private_token 群组拥有者的或者管理员的private_token
     * @param group_id      要删除的群组的ID或者URL路径
     * @return
     * @throws Exception
     */
    public String delGroup(String private_token, String group_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null) {
            return "The group_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDelete httpDelete = new HttpDelete(COMMON_URL + group_id);
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
    }

    /**
     * 修改群组
     *
     * @param private_token 群组拥有者的或者管理员的private_token
     * @param group_id      要修改的群组的ID
     * @param groupParams   群组的信息，其中name和path是必须的
     * @return
     * @throws Exception
     */
    public String updateGroup(String private_token, Integer group_id, CreateGroupParams groupParams) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null) {
            return "The group_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(COMMON_URL + group_id);
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
    }

    /**
     * 将一个项目转移到一个群组中
     * Transfer a project to the Group namespace.
     * Available only to instance administrators,
     * although an alternative API endpoint is available which does not require instance administrator access.
     * Transferring projects may fail when tagged packages exist in the project's repository.
     *
     * @param root_private_token 管理员的private_token
     * @param group_id           群组的ID或者URL路径
     * @param project_id         项目的ID或者URL路径
     * @return
     * @throws Exception
     */
    public String transferProjectToGroup(String root_private_token, String group_id, String project_id) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + group_id + "/projects/" + project_id);
            httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 创建群组
     *
     * @param private_token 用户的private_token
     * @param groupParams   群组的信息，其中name和path是必须的
     * @return
     * @throws Exception
     */
    public String createGroup(String private_token, CreateGroupParams groupParams) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (groupParams.getName() == null || "".equals(groupParams.getName())) {
            return "The groupParams's name is required.";
        } else if (groupParams.getPath() == null || "".equals(groupParams.getPath())) {
            return "The groupParams's path is required.";
        } else {
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
    }

    /**
     * 查找群组的细节信息
     * Get all details of a group.
     * This endpoint can be accessed without authentication if the group is publicly accessible.
     * In case the user that requests is admin of the group, it will return the runners_token for the group too.
     *
     * @param private_token 用户的private_token
     * @param group_id      群组ID或者URL地址
     * @return
     * @throws Exception
     */
    public String getGroupDetails(String private_token, String group_id) throws Exception {
        return getGroupDetails(private_token, group_id, false, false);
    }

    /**
     * 查找群组的细节信息
     * Get all details of a group.
     * This endpoint can be accessed without authentication if the group is publicly accessible.
     * In case the user that requests is admin of the group, it will return the runners_token for the group too.
     *
     * @param private_token          用户的private_token
     * @param group_id               群组ID或者URL地址
     * @param with_custom_attributes 结果是否携带custom_attributes
     * @return
     * @throws Exception
     */
    public String getGroupDetails(String private_token, boolean with_custom_attributes, String group_id) throws Exception {
        return getGroupDetails(private_token, group_id, with_custom_attributes, false);
    }

    /**
     * 查找群组的细节信息
     * Get all details of a group.
     * This endpoint can be accessed without authentication if the group is publicly accessible.
     * In case the user that requests is admin of the group, it will return the runners_token for the group too.
     *
     * @param private_token 用户的private_token
     * @param group_id      群组ID或者URL地址
     * @param with_projects 结果是否携带群组的项目信息
     * @return
     * @throws Exception
     */
    public String getGroupDetails(String private_token, String group_id, boolean with_projects) throws Exception {
        return getGroupDetails(private_token, group_id, false, with_projects);
    }

    /**
     * 查找群组的细节信息
     * Get all details of a group.
     * This endpoint can be accessed without authentication if the group is publicly accessible.
     * In case the user that requests is admin of the group, it will return the runners_token for the group too.
     *
     * @param private_token          用户的private_token
     * @param group_id               群组ID或者URL地址
     * @param with_custom_attributes 结果是否携带custom_attributes
     * @param with_projects          结果是否携带群组的项目信息
     * @return
     * @throws Exception
     */
    public String getGroupDetails(String private_token, String group_id, boolean with_custom_attributes, boolean with_projects) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + group_id);
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
    }

    /**
     * 查找群组的项目
     * Get a list of projects in this group.
     * When accessed without authentication, only public projects are returned.
     * By default, this request returns 20 results at a time because the API results are paginated.
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL
     * @param params        查找群组的条件
     * @return
     * @throws Exception
     */
    public String getGroupProjects(String private_token, String group_id, ListGroupProjectParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + group_id + "/projects");
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
    }

    /**
     * 查找一个群组的子群组
     * Get a list of visible direct subgroups in this group.
     * When accessed without authentication, only public groups are returned.
     * By default, this request returns 20 results at a time because the API results are paginated.
     *
     * @param private_token   用户的private_token
     * @param parent_group_id 父群组的ID或者URL
     * @param params          查找群组的条件
     * @return
     * @throws Exception
     */
    public String getSubGroups(String private_token, String parent_group_id, ListGroupsParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (parent_group_id == null || "".equals(parent_group_id)) {
            return "The parent_group_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + parent_group_id + "/subgroups");
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
    }

    /**
     * 查找所有群组
     * Get a list of visible groups for the authenticated user.
     * When accessed without authentication, only public groups are returned.
     * By default, this request returns 20 results at a time because the API results are paginated.
     *
     * @param private_token 用户的private_token
     * @param params        搜索群组的条件
     * @return
     * @throws Exception
     */
    public String getGroups(String private_token, ListGroupsParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else {
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
    }


}
