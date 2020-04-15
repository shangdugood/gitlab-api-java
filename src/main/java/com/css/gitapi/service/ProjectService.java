package com.css.gitapi.service;

import com.css.gitapi.enums.HttpCode;
import com.css.gitapi.enums.ProListOrderBy;
import com.css.gitapi.enums.SortMethod;
import com.css.gitapi.enums.UserAccessLevel;
import com.css.gitapi.httputil.HttpDeleteWithBody;
import com.css.gitapi.httputil.HttpGetWithBody;
import com.css.gitapi.model.*;
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
 * @date 2020-04-03 11:07:24
 */
public class ProjectService {

    private final String COMMON_URL = "http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/";

    /**
     * 下载一个项目的快照
     *
     * @param root_private_token 管理员的private_token
     * @param projectId          项目的ID
     * @return tar格式的文件流
     * @throws Exception
     */
    public String downloadSnapshot(String root_private_token, String projectId) throws Exception {
        return downloadSnapshot(root_private_token, projectId, false);
    }

    /**
     * 下载一个项目的快照
     *
     * @param root_private_token 管理员的private_token
     * @param projectId          项目的ID
     * @param wiki               是否下载wiki页
     * @return tar格式的文件流
     * @throws Exception
     */
    public String downloadSnapshot(String root_private_token, String projectId, boolean wiki) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + projectId + "/snapshot");
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

    /**
     * 给一个项目设置一个新的命名空间
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID
     * @param namespace     The ID or path of the namespace to transfer to project to
     * @return
     * @throws Exception
     */
    public String transerProjectToNewNamespace(String private_token, String projectId, String namespace) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (namespace == null || "".equals(namespace)) {
            return "The namespace is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(COMMON_URL + projectId + "/transfer");
            httpPut.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("namespace", namespace));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
            httpPut.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPut);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 删除项目的推送规则(GitLab必须为STARTER版本才可以使用)
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或者URL路径
     * @return 返回删除结果
     * @throws Exception
     */
    public String delPorjectPushRules(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(COMMON_URL + projectId + "/push_rule");
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
    }

    /**
     * 修改项目的推送规则(GitLab必须为STARTER版本才可以使用)
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param project       推送规则的参数
     * @return 返回推送规则结果
     * @throws Exception
     */
    public String modPorjectPushRules(String private_token, AddProjectPushRuleParams project) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (project.getId() == null || "".equals(project.getId())) {
            return "The params'id is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(COMMON_URL + project.getId() + "/push_rule");
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
    }

    /**
     * 添加项目的推送规则(GitLab必须为STARTER版本才可以使用)
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param project       推送规则的参数
     * @return 返回推送规则结果
     * @throws Exception
     */
    public String addPorjectPushRules(String private_token, AddProjectPushRuleParams project) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (project.getId() == null || "".equals(project.getId())) {
            return "The params'id is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + project.getId() + "/push_rule");
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
    }

    /**
     * 查找项目的推送规则(GitLab必须为STARTER版本才可以使用)
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID
     * @return 返回推送规则结果
     * @throws Exception
     */
    public String getPorjectPushRules(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + projectId + "/push_rule");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", projectId));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
            httpGet.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 对一个项目执行housekeeping
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID或者NAMESPACE/PROJECT_NAME
     * @return 返回执行结果
     * @throws Exception
     */
    public String houseKeepingProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + projectId + "/housekeeping");
            httpPost.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", projectId));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 根据项目名称查找项目，经测试，需要至少三个连续字符匹配。
     * eg:项目名称：abcd，查找条件可以为:abcd,abc,bcd
     * 查找范围：用户参与的项目以及public的项目
     *
     * @param private_token 用户的private_token
     * @param proName       搜索条件，必须
     * @return 返回项目集合
     * @throws Exception
     */
    public String searchProjectByName(String private_token, String proName) throws Exception {
        return searchProjectByName(private_token, proName, null, null);
    }

    /**
     * 根据项目名称查找项目，经测试，需要至少三个连续字符匹配。
     * eg:项目名称：abcd，查找条件可以为:abcd,abc,bcd
     * 查找范围：用户参与的项目以及public的项目
     *
     * @param private_token 用户的private_token
     * @param proName       搜索条件，必须
     * @param order_by      排序条件，非必须
     * @return 返回项目集合
     * @throws Exception
     */
    public String searchProjectByName(String private_token, String proName, ProListOrderBy order_by) throws Exception {
        return searchProjectByName(private_token, proName, order_by, null);
    }

    /**
     * 根据项目名称查找项目，经测试，需要至少三个连续字符匹配。
     * eg:项目名称：abcd，查找条件可以为:abcd,abc,bcd
     * 查找范围：用户参与的项目以及public的项目
     *
     * @param private_token 用户的private_token
     * @param proName       搜索条件，必须
     * @param sort          排序规则，非必须
     * @return 返回项目集合
     * @throws Exception
     */
    public String searchProjectByName(String private_token, String proName, SortMethod sort) throws Exception {
        return searchProjectByName(private_token, proName, null, sort);
    }

    /**
     * 根据项目名称查找项目，经测试，需要至少三个连续字符匹配。
     * eg:项目名称：abcd，查找条件可以为:abcd,abc,bcd
     * 查找范围：用户参与的项目以及public的项目
     *
     * @param private_token 用户的private_token
     * @param proName       搜索条件，必须
     * @param order_by      排序条件，非必须
     * @param sort          排序规则，非必须
     * @return 返回项目集合
     * @throws Exception
     */
    public String searchProjectByName(String private_token, String proName, ProListOrderBy order_by, SortMethod sort) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (proName == null || "".equals(proName)) {
            return "The proName is required!";
        } else {
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
    }

    /**
     * 删除项目的fork
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID或者URL路径
     * @return 返回删除结果
     * @throws Exception
     */
    public String delExistProjectFork(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(COMMON_URL + projectId + "/fork");
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
    }


    /**
     * 将一个项目fork到另一个项目
     *
     * @param private_token 管理员或者新项目的private_token
     * @param projectId     新项目的ID或者URL路径
     * @param fork_from_id  fork的项目的ID
     * @return 返回新的项目的信息
     * @throws Exception
     */
    public String createForkBetweenExistProjects(String private_token, String projectId, Integer fork_from_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (fork_from_id == null) {
            return "The fork_from_id is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + projectId + "/fork/" + fork_from_id);
            httpPost.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", projectId));
            params.add(new BasicNameValuePair("forked_from_id", fork_from_id.toString()));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 删除项目的hook
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或者URL路径
     * @param hook_id       hook的ID
     * @return 返回成功/失败
     * @throws Exception
     */
    public String delProjectHook(String private_token, String projectId, Integer hook_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (hook_id == null) {
            return "The hook_id is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(COMMON_URL + projectId + "/hooks/" + hook_id);
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
    }

    /**
     * 修改项目的hook的信息
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param hook_id       要修改的hook的ID
     * @param hook          修改后的hook的信息
     * @return 返回修改后的hook的信息
     * @throws Exception
     */
    public String modifyProjectHook(String private_token, Integer hook_id, CreateProjectHookParams hook) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (hook_id == null) {
            return "The hook_id is required!";
        } else if (hook.getId() == null || "".equals(hook.getId())) {
            return "The params'id is required!";
        } else if (hook.getUrl() == null || "".equals(hook.getUrl())) {
            return "The params'url is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(COMMON_URL + hook.getId() + "/hooks/" + hook_id);
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
    }

    /**
     * 为项目添加hook
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param hook          添加hook的参数
     * @return 返回添加后的hook信息
     * @throws Exception
     */
    public String addProjectHook(String private_token, CreateProjectHookParams hook) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (hook.getId() == null || "".equals(hook.getId())) {
            return "The params'id is required!";
        } else if (hook.getUrl() == null || "".equals(hook.getUrl())) {
            return "The params'url is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + hook.getId() + "/hooks");
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
    }

    /**
     * 获得指定项目的指定hook
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID或者URL路径
     * @param hook_id       hook的ID
     * @return 返回hook
     * @throws Exception
     */
    public String getProjectHook(String private_token, String projectId, Integer hook_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (hook_id == null) {
            return "The hook_id is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + projectId + "/hooks/" + hook_id);
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
    }

    /**
     * 获得指定项目的hooks
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID或者URL路径
     * @return 返回hooks集合
     * @throws Exception
     */
    public String getProjectHooks(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + projectId + "/hooks");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", projectId));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
            httpGet.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 停止与群组分享该项目
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID或URL路径
     * @param group_id      群组ID
     * @return 返回结果：成功/失败
     * @throws Exception
     */
    public String stopShareProjectWithGroup(String private_token, String projectId, Integer group_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (group_id == null) {
            return "The group_id is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(COMMON_URL + projectId + "/share/" + group_id);
            httpDelete.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", projectId));
            params.add(new BasicNameValuePair("group_id", group_id.toString()));
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
    }

    /**
     * 将项目与指定的群组共享
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或者URL路径
     * @param group_id      群组ID
     * @param group_access  群组在这个项目中的级别
     * @return 返回共享结果
     * @throws Exception
     */
    public String shareProjectWithGroup(String private_token, String projectId, Integer group_id, UserAccessLevel group_access) throws Exception {
        return shareProjectWithGroup(private_token, projectId, group_id, group_access, null);
    }

    /**
     * 将项目与指定的群组共享
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或者URL路径
     * @param group_id      群组ID
     * @param group_access  群组在这个项目中的级别
     * @param expires_at    共享到期时间
     * @return 返回共享结果
     * @throws Exception
     */
    public String shareProjectWithGroup(String private_token, String projectId, Integer group_id, UserAccessLevel group_access, Date expires_at) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (group_id == null) {
            return "The group_id is required!";
        } else if (group_access == null) {
            return "The group_access is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + projectId + "/share");
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
    }

    /**
     * 恢复已删除的项目
     * gitlab v12.6 PREMIUM版本及之后PREMIUM版本，可以使用该方法
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID
     * @return 项目的详细信息
     * @throws Exception
     */
    public String restoreProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + projectId + "/restore");
            httpPost.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", projectId));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);

            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        }
    }


    /**
     * 删除项目
     * gitlab v12.6版本之前，该方法会删除项目及与该项目所有有关的关系
     * gitlab v12.6 PREMIUM版本及之后PREMIUM版本，该方法会标记项目为删除，在一定期限内可以恢复，期限可以由管理员设置
     * To change this period:
     * <p>
     * 1、Select the desired option.
     * 2、Click Save changes.
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID
     * @return 删除成功/失败
     * @throws Exception
     */
    public String delProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(COMMON_URL + projectId);
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
    }

    /**
     * 解打包一个项目
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或URL路径
     * @return 返回项目的详细信息
     * @throws Exception
     */
    public String unArchiveProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + projectId + "/unarchive");
            httpPost.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", projectId));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 打包一个项目
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或URL路径
     * @return 返回项目的详细信息
     * @throws Exception
     */
    public String archiveProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + projectId + "/archive");
            httpPost.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", projectId));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 获取项目所用语言占比
     *
     * @param projectId     项目ID
     * @param private_token 管理员或者项目创建者的private_token
     * @return 返回语言占比的json格式字符串
     * @throws Exception
     */
    public String getProjectLanguagePercentage(String projectId, String private_token) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COMMON_URL + projectId + "/languages");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 获取指定项目的星标者
     *
     * @param projectId     项目ID或者URL路径
     * @param private_token 管理员或者项目创建者的private_token
     * @param pagination    分页信息，可以为null，默认page=1,per_page=20
     * @return 用户信息的集合
     * @throws Exception
     */
    public String getProjectStarrers(String private_token, String projectId, Pagination pagination) throws Exception {
        return getProjectStarrers(private_token, projectId, "", pagination);
    }

    /**
     * 获取指定项目的星标者
     *
     * @param projectId       项目ID或者URL路径
     * @param private_token   管理员或者项目创建者的private_token
     * @param pagination      分页信息，可以为null，默认page=1,per_page=20
     * @param searchCondition 查询条件，包括用户的名称，用户名，邮箱
     * @return 用户信息的集合
     * @throws Exception
     */
    public String getProjectStarrers(String projectId, String private_token, String searchCondition, Pagination pagination) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + projectId + "/starrers");
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
    }

    /**
     * 取消星标一个项目
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     需要星标的项目的ID或者URL路径
     * @return 取消星标项目的信息
     * @throws Exception
     */
    public String unStarProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + projectId + "/unstar");
            httpPost.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpCode.NOTMODIFIED.getCode()) {
                return Global.getPorpties("isUnStarted");
            } else {
                HttpEntity entity1 = response.getEntity();
                return EntityUtils.toString(entity1);
            }
        }
    }


    /**
     * 星标一个项目
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     需要星标的项目的ID或者URL路径
     * @return 星标项目的信息
     * @throws Exception
     */
    public String starProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + projectId + "/star");
            httpPost.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpCode.NOTMODIFIED.getCode()) {
                return Global.getPorpties("isStarted");
            } else {
                HttpEntity entity1 = response.getEntity();
                return EntityUtils.toString(entity1);
            }
        }
    }

    /**
     * 获取指定项目的forks
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param project       项目信息（项目ID是必要的）
     * @return 返回项目的fork的项目信息
     * @throws Exception
     */
    public String getProjectForks(String private_token, ListForkProjectParams project) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (project.getId() == null || "".equals(project.getId())) {
            return "GitLabForkProParams'id is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + project.getId() + "/forks");
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

    }

    /**
     * Fork一个项目到自己的git空间
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param project       要fork的项目的信息
     * @return 返回fork成功后的项目信息
     * @throws Exception
     */
    public String forkProject(String private_token, ForkProjectParams project) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (project.getId() == null || "".equals(project.getId())) {
            return "GitLabForkProParams'id is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + project.getId() + "/fork");
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
    }

    /**
     * 根据项目的ID修改项目信息
     *
     * @param projectId     项目ID或项目URL路径
     * @param project       项目信息
     * @param private_token 管理员或者项目创建者的private_token
     * @return 项目修改后的信息
     * @throws Exception
     */
    public String modifyProjectByProjectId(String projectId, CreateProjectParams project, String private_token) throws Exception {
        if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if ((project.getName() == null || "".equals(project.getName())) &&
                (project.getPath() == null || "".equals(project.getPath()))) {
            return "The path and name of the project provide at least one!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(COMMON_URL + projectId);
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

    }


    /**
     * 创建项目
     *
     * @param project               创建项目的参数
     * @param creator_private_token 创建者的private_token
     * @return 返回项目信息
     * @throws Exception
     */
    public String createProject(CreateProjectParams project, String creator_private_token) throws Exception {
        if (creator_private_token == null || "".equals(creator_private_token)) {
            return "The creator_private_token is required!";
        } else if ((project.getName() == null || "".equals(project.getName())) &&
                (project.getPath() == null || "".equals(project.getPath()))) {
            return "The path and name of the project provide at least one!";
        } else {
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
    }

    /**
     * 为指定用户创建项目
     *
     * @param project            创建项目的参数
     * @param root_private_token 管理员的private_token
     * @param userId             指定用户的ID
     * @return 返回项目信息
     * @throws Exception
     */
    public String createProjectForUser(CreateProjectParams project, String root_private_token, String userId) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        } else if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required";
        } else if ((project.getName() == null || "".equals(project.getName())) &&
                (project.getPath() == null || "".equals(project.getPath()))) {
            return "The path and name of the project provide at least one!";
        } else {
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

    }

    /**
     * 获取所有项目
     *
     * @param params        获取项目的参数
     * @param private_token 管理员或者项目参与者的private_token
     * @return 项目的信息
     * @throws Exception
     */
    public String getAllProjects(ListProjectParams params, String private_token) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required";
        } else {
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
    }

    /**
     * 获取指定用户的项目
     *
     * @param userId        指定用户的ID
     * @param private_token 管理员的private_token
     * @param params        关于项目的参数
     * @return 项目的信息
     * @throws Exception
     */
    public String getUserProjects(String userId, String private_token, ListProjectParams params) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        } else if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required";
        } else {
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
    }

    /**
     * 获取指定用户的星标项目
     *
     * @param userId        指定用户的ID
     * @param private_token 管理员的private_token
     * @param params        关于项目的参数
     * @return 项目的集合
     * @throws Exception
     */
    public String getUserStarredProjects(String userId, String private_token, ListProjectParams params) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        } else if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required";
        } else {
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

    }

    /**
     * 根据项目的ID获取项目的详细信息
     *
     * @param projectId     项目ID或者URL路径
     * @param private_token 管理员或者项目参与者的private_token
     * @param params        项目的参数
     * @return 项目的信息
     * @throws Exception
     */
    public String getProjectById(String projectId, String private_token, ListSingleProjectParams params) throws Exception {
        if (projectId == null || "".equals(projectId)) {
            return "The projectId is required";
        } else if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + projectId);
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
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId     项目的ID或者URL路径
     * @param private_token 管理员或者项目创建者的private_token
     * @param search        成员的搜索条件，包括name,username
     * @param skip_users_id 不需要查找的用户的ID
     * @param pagination    分页信息，可以为null，默认page=1,per_page=20
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String private_token, String search, int[] skip_users_id, Pagination pagination) throws Exception {
        if (projectId == null || "".equals(projectId)) {
            return "The projectId is required";
        } else if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + projectId + "/users");
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

    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId     项目的ID或者URL路径
     * @param private_token 管理员或者项目创建者的private_token
     * @param search        成员的搜索条件，包括name,username
     * @param pagination    分页信息，可以为null，默认page=1,per_page=20
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String private_token, String search, Pagination pagination) throws Exception {
        return getProjectUsers(projectId, private_token, search, new int[0], pagination);
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId     项目的ID或者URL路径
     * @param private_token 管理员或者项目创建者的private_token
     * @param skip_users_id 不需要查找的用户的ID
     * @param pagination    分页信息，可以为null，默认page=1,per_page=20
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String private_token, int[] skip_users_id, Pagination pagination) throws Exception {
        return getProjectUsers(projectId, private_token, "", skip_users_id, pagination);
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId     项目的ID或者URL路径
     * @param private_token 管理员或者项目创建者的private_token
     * @param pagination    分页信息，可以为null，默认page=1,per_page=20
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String private_token, Pagination pagination) throws Exception {
        return getProjectUsers(projectId, private_token, "", new int[0], pagination);
    }


}
