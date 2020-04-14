package com.css.gitapi.util.controller;

import com.css.gitapi.util.enums.HttpCode;
import com.css.gitapi.util.httputil.HttpGetWithBody;
import com.css.gitapi.util.model.AddMemberParams;
import com.css.gitapi.util.model.GetMemberParams;
import com.css.gitapi.util.model.Global;
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
 * @date 2020/4/14 9:12
 */
public class MemberController {
    public String getProjectMembers(String private_token, String project_id, GetMemberParams params) throws Exception {
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

    public String getProjectMembersWithInherited(String private_token, String project_id, GetMemberParams params) throws Exception {
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

    public String getGroupMembers(String private_token, String group_id, GetMemberParams params) throws Exception {
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

    public String getGroupMembersWithInherited(String private_token, String group_id, GetMemberParams params) throws Exception {
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

    public String getOneGroupMember(String private_token, String group_id, String user_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/members/" + user_id);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getOneProjectMember(String private_token, String project_id, String user_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + project_id + "/members/" + user_id);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getOneGroupMemberWithInherited(String private_token, String group_id, String user_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/groups/" + group_id + "/members/all/" + user_id);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getOneProjectMemberWithInherited(String private_token, String project_id, String user_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/projects/" + project_id + "/members/all/" + user_id);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String addMemberToGroup(String private_token, AddMemberParams params) throws Exception {
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

    public String addMemberToProject(String private_token, AddMemberParams params) throws Exception {
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

    public String modifyMemberOfGroup(String private_token, AddMemberParams params) throws Exception {
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

    public String modifyMemberOfProject(String private_token, AddMemberParams params) throws Exception {
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

    public String delMemberfromProject(String private_token, String project_id, Integer user_id) throws Exception {
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

    public String delMemberfromGroup(String private_token, String group_id, Integer user_id) throws Exception {
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
