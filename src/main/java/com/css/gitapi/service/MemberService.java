package com.css.gitapi.service;

import com.css.gitapi.enums.HttpCode;
import com.css.gitapi.httputil.HttpGetWithBody;
import com.css.gitapi.model.AddMemberParams;
import com.css.gitapi.model.GetMemberParams;
import com.css.gitapi.model.Global;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/14 15:54
 */
public class MemberService {

    /**
     * 删除群组中的成员
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL路径
     * @param user_id       要删除的成员的ID
     * @return
     * @throws Exception
     */
    public String delMemberFromGroup(String private_token, String group_id, Integer user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (user_id == null) {
            return "The user_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDelete httpPut = new HttpDelete("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/members/" + user_id);
            httpPut.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpPut);
            if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
                return Global.getPorpties("delGroupMemberSuccess");
            } else if (response.getEntity() != null) {
                return EntityUtils.toString(response.getEntity());
            } else {
                return response.getStatusLine().getStatusCode() + ".";
            }
        }
    }

    /**
     * 删除项目中的成员
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL路径
     * @param user_id       要删除的成员的ID
     * @return
     * @throws Exception
     */
    public String delMemberFromProject(String private_token, String project_id, Integer user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else if (user_id == null) {
            return "The user_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDelete httpPut = new HttpDelete("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + project_id + "/members/" + user_id);
            httpPut.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpPut);
            if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
                return Global.getPorpties("delProjectMemberSuccess");
            } else if (response.getEntity() != null) {
                return EntityUtils.toString(response.getEntity());
            } else {
                return response.getStatusLine().getStatusCode() + ".";
            }
        }
    }

    /**
     * 修改项目中的成员
     *
     * @param private_token 用户的private_token
     * @param params        修改的成员的参数信息
     * @return
     * @throws Exception
     */
    public String modifyMemberOfGroup(String private_token, AddMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The project_id is required.";
        } else if (params.getUser_id() == null) {
            return "The params'user_id is required.";
        } else if (params.getAccess_level() == null) {
            return "The params'access_level is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + params.getId() + "/members/" + params.getUser_id());
            httpPut.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("user_id", params.getUser_id().toString()));
            formparams.add(new BasicNameValuePair("access_level", params.getAccess_level().getLevel().toString()));
            if (params.getExpires_at() != null) {
                formparams.add(new BasicNameValuePair("expires_at", new SimpleDateFormat("yyyy-MM-dd").format(params.getExpires_at())));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            httpPut.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPut);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 修改项目中的成员
     *
     * @param private_token 用户的private_token
     * @param params        修改的成员的参数信息
     * @return
     * @throws Exception
     */
    public String modifyMemberOfProject(String private_token, AddMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The project_id is required.";
        } else if (params.getUser_id() == null) {
            return "The params'user_id is required.";
        } else if (params.getAccess_level() == null) {
            return "The params'access_level is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + params.getId() + "/members/" + params.getUser_id());
            httpPut.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("user_id", params.getUser_id().toString()));
            formparams.add(new BasicNameValuePair("access_level", params.getAccess_level().getLevel().toString()));
            if (params.getExpires_at() != null) {
                formparams.add(new BasicNameValuePair("expires_at", new SimpleDateFormat("yyyy-MM-dd").format(params.getExpires_at())));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            httpPut.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPut);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 向项目中添加成员
     *
     * @param private_token 用户的private_token
     * @param params        添加的成员的参数信息
     * @return
     * @throws Exception
     */
    public String addMemberToProject(String private_token, AddMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The project_id is required.";
        } else if (params.getUser_id() == null) {
            return "The params'user_id is required.";
        } else if (params.getAccess_level() == null) {
            return "The params'access_level is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpGet = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + params.getId() + "/members");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("user_id", params.getUser_id().toString()));
            formparams.add(new BasicNameValuePair("access_level", params.getAccess_level().getLevel().toString()));
            if (params.getExpires_at() != null) {
                formparams.add(new BasicNameValuePair("expires_at", new SimpleDateFormat("yyyy-MM-dd").format(params.getExpires_at())));

            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            httpGet.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 向群组中添加成员
     *
     * @param private_token 用户的private_token
     * @param params        添加的成员的参数信息
     * @return
     * @throws Exception
     */
    public String addMemberToGroup(String private_token, AddMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The group_id is required.";
        } else if (params.getUser_id() == null) {
            return "The params'user_id is required.";
        } else if (params.getAccess_level() == null) {
            return "The params'access_level is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpGet = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + params.getId() + "/members");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("user_id", params.getUser_id().toString()));
            formparams.add(new BasicNameValuePair("access_level", params.getAccess_level().getLevel().toString()));
            if (params.getExpires_at() != null) {
                formparams.add(new BasicNameValuePair("expires_at", new SimpleDateFormat("yyyy-MM-dd").format(params.getExpires_at())));

            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            httpGet.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 查询项目的成员(指定单个成员,包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @param user_id       指定的用户ID
     * @return
     * @throws Exception
     */
    public String getOneProjectMemberWithInherited(String private_token, String project_id, String user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + project_id + "/members/all/" + user_id);
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 查询群组的成员(指定单个成员,包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL
     * @param user_id       指定的用户ID
     * @return
     * @throws Exception
     */
    public String getOneGroupMemberWithInherited(String private_token, String group_id, String user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/members/all/" + user_id);
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 查询项目的成员(指定单个成员)
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @param user_id       指定的用户ID
     * @return
     * @throws Exception
     */
    public String getOneProjectMember(String private_token, String project_id, String user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + project_id + "/members/" + user_id);
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 查询群组的成员(指定单个成员)
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL
     * @param user_id       指定的用户ID
     * @return
     * @throws Exception
     */
    public String getOneGroupMember(String private_token, String group_id, String user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/members/" + user_id);
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 查询群组的成员(包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param group_id      项目的ID或者URL
     * @return
     * @throws Exception
     */
    public String getGroupMembersWithInherited(String private_token, String group_id) throws Exception {
        return getGroupMembersWithInherited(private_token, group_id, null);
    }

    /**
     * 查询群组的成员(包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param group_id      项目的ID或者URL
     * @param params        获取成员所需的参数
     * @return
     * @throws Exception
     */
    public String getGroupMembersWithInherited(String private_token, String group_id, GetMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/members/all");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            if (params != null) {
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                if (params.getSearch() != null && !"".equals(params.getSearch())) {
                    formparams.add(new BasicNameValuePair("query", params.getSearch()));
                }
                if (params.getUser_ids() != null && params.getUser_ids().size() > 0) {
                    formparams.add(new BasicNameValuePair("user_ids", params.getUser_ids().toString()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

                httpGet.setEntity(entity);
            }
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 查询项目的成员(包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @return
     * @throws Exception
     */
    public String getProjectMembersWithInherited(String private_token, String project_id) throws Exception {
        return getProjectMembersWithInherited(private_token, project_id, null);
    }

    /**
     * 查询项目的成员(包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @param params        获取成员所需的参数
     * @return
     * @throws Exception
     */
    public String getProjectMembersWithInherited(String private_token, String project_id, GetMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + project_id + "/members/all");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            if (params != null) {
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                if (params.getSearch() != null && !"".equals(params.getSearch())) {
                    formparams.add(new BasicNameValuePair("query", params.getSearch()));
                }
                if (params.getUser_ids() != null && params.getUser_ids().size() > 0) {
                    formparams.add(new BasicNameValuePair("user_ids", params.getUser_ids().toString()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

                httpGet.setEntity(entity);
            }
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 查询群组的成员
     *
     * @param private_token 用户的private_token
     * @param group_id      项目的ID或者URL
     * @return
     * @throws Exception
     */
    public String getGroupMembers(String private_token, String group_id) throws Exception {
        return getGroupMembers(private_token, group_id, null);
    }

    /**
     * 查询群组的成员
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL
     * @param params        获取成员所需的参数
     * @return
     * @throws Exception
     */
    public String getGroupMembers(String private_token, String group_id, GetMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/members");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            if (params != null) {
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                if (params.getSearch() != null && !"".equals(params.getSearch())) {
                    formparams.add(new BasicNameValuePair("query", params.getSearch()));
                }
                if (params.getUser_ids() != null && params.getUser_ids().size() > 0) {
                    formparams.add(new BasicNameValuePair("user_ids", params.getUser_ids().toString()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

                httpGet.setEntity(entity);
            }
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 查询项目的成员
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @return
     * @throws Exception
     */
    public String getProjectMembers(String private_token, String project_id) throws Exception {
        return getProjectMembers(private_token, project_id, null);
    }

    /**
     * 查询项目的成员
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @param params        获取成员所需的参数
     * @return
     * @throws Exception
     */
    public String getProjectMembers(String private_token, String project_id, GetMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + project_id + "/members");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            if (params != null) {
                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
                if (params.getSearch() != null && !"".equals(params.getSearch())) {
                    formparams.add(new BasicNameValuePair("query", params.getSearch()));
                }
                if (params.getUser_ids() != null && params.getUser_ids().size() > 0) {
                    formparams.add(new BasicNameValuePair("user_ids", params.getUser_ids().toString()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

                httpGet.setEntity(entity);
            }
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }
}
