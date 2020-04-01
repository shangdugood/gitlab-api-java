package com.css.gitapi.util.controller;

import com.css.gitapi.util.httputil.HttpDeleteWithBody;
import com.css.gitapi.util.model.GitLabUser;
import com.css.gitapi.util.model.Global;
import com.css.gitapi.util.enums.HttpCode;
import com.css.gitapi.util.enums.Identify;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 10:57
 */
public class UserController {
    public static String getAllUser(Identify idt) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users");
        if (idt == Identify.ADMINISTRATOR) {
            httpGet.addHeader("PRIVATE-TOKEN", Global.root_private_token);
        } else {
            httpGet.addHeader("PRIVATE-TOKEN", Global.regular_private_token);
        }
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String addUser(GitLabUser user) throws IOException {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users");
        httpPost.addHeader("PRIVATE-TOKEN", Global.root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("password", user.getPassword()));
        formparams.add(new BasicNameValuePair("email", user.getEmail()));
        formparams.add(new BasicNameValuePair("username", user.getUsername()));
        formparams.add(new BasicNameValuePair("name", user.getName()));
        formparams.add(new BasicNameValuePair("admin", String.valueOf(user.getIs_admin())));
        formparams.add(new BasicNameValuePair("bio", user.getBio()));
        formparams.add(new BasicNameValuePair("can_create_group", String.valueOf(user.getCan_create_group())));
        formparams.add(new BasicNameValuePair("color_scheme_id", String.valueOf(user.getColor_scheme_id())));
        formparams.add(new BasicNameValuePair("extern_uid", user.getExtern_uid()));
        formparams.add(new BasicNameValuePair("external", String.valueOf(user.getIs_external())));
        formparams.add(new BasicNameValuePair("extra_shared_runners_minutes_limit", user.getExtra_shared_runners_minutes_limit()));
        formparams.add(new BasicNameValuePair("force_random_password", user.getForce_random_password()));
        formparams.add(new BasicNameValuePair("group_id_for_saml", user.getGroup_id_for_saml()));
        formparams.add(new BasicNameValuePair("linkedin", user.getLinkedin()));
        formparams.add(new BasicNameValuePair("location", user.getLocation()));
        formparams.add(new BasicNameValuePair("organization", user.getOrganization()));
        formparams.add(new BasicNameValuePair("private_profile", user.getPrivate_profile()));
        formparams.add(new BasicNameValuePair("projects_limit", String.valueOf(user.getProjects_limit())));
        formparams.add(new BasicNameValuePair("provider", user.getProvider()));
        formparams.add(new BasicNameValuePair("public_email", user.getPublic_email()));
        formparams.add(new BasicNameValuePair("reset_password", String.valueOf(user.getReset_password())));
        formparams.add(new BasicNameValuePair("shared_runners_minutes_limit", user.getShared_runners_minutes_limit()));
        formparams.add(new BasicNameValuePair("skip_confirmation", String.valueOf(user.getSkip_confirmation())));
        formparams.add(new BasicNameValuePair("skype", user.getSkype()));
        formparams.add(new BasicNameValuePair("theme_id", String.valueOf(user.getTheme_id())));
        formparams.add(new BasicNameValuePair("twitter", user.getTwitter()));
        formparams.add(new BasicNameValuePair("website_url", user.getWebsite_url()));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == HttpCode.CREATED.getCode()) {
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        } else {
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    public boolean delUserById(String userId, boolean hard_delete) throws IOException {

        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId);
        httpDelete.addHeader("PRIVATE-TOKEN", Global.root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("id", userId));
        formparams.add(new BasicNameValuePair("hard_delete", String.valueOf(hard_delete)));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpDelete.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean delUserById(String userId) throws IOException {
        return delUserById(userId, false);
    }

    public String getUserByUserId(Identify idt, String userId) throws IOException {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId);
        if (idt == Identify.ADMINISTRATOR) {
            httpGet.addHeader("PRIVATE-TOKEN", Global.root_private_token);
        } else {
            httpGet.addHeader("PRIVATE-TOKEN", Global.regular_private_token);
        }
        CloseableHttpResponse response = httpClients.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpCode.OK.getCode()) {
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        } else {
            return "获取失败，请检查用户ID是否存在或者root_private_token是否正确";
        }
    }

    public String modifyUserById(GitLabUser user, String userId) throws IOException {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        httpPut.addHeader("PRIVATE-TOKEN", Global.root_private_token);
        formparams.add(new BasicNameValuePair("id", userId));
        formparams.add(new BasicNameValuePair("password", user.getPassword()));
        formparams.add(new BasicNameValuePair("email", user.getEmail()));
        formparams.add(new BasicNameValuePair("username", user.getUsername()));
        formparams.add(new BasicNameValuePair("name", user.getName()));
        formparams.add(new BasicNameValuePair("admin", String.valueOf(user.getIs_admin())));
        formparams.add(new BasicNameValuePair("bio", user.getBio()));
        formparams.add(new BasicNameValuePair("can_create_group", String.valueOf(user.getCan_create_group())));
        formparams.add(new BasicNameValuePair("color_scheme_id", String.valueOf(user.getColor_scheme_id())));
        formparams.add(new BasicNameValuePair("extern_uid", user.getExtern_uid()));
        formparams.add(new BasicNameValuePair("external", String.valueOf(user.getIs_external())));
        formparams.add(new BasicNameValuePair("extra_shared_runners_minutes_limit", user.getExtra_shared_runners_minutes_limit()));
        formparams.add(new BasicNameValuePair("force_random_password", user.getForce_random_password()));
        formparams.add(new BasicNameValuePair("group_id_for_saml", user.getGroup_id_for_saml()));
        formparams.add(new BasicNameValuePair("linkedin", user.getLinkedin()));
        formparams.add(new BasicNameValuePair("location", user.getLocation()));
        formparams.add(new BasicNameValuePair("organization", user.getOrganization()));
        formparams.add(new BasicNameValuePair("private_profile", user.getPrivate_profile()));
        formparams.add(new BasicNameValuePair("projects_limit", String.valueOf(user.getProjects_limit())));
        formparams.add(new BasicNameValuePair("provider", user.getProvider()));
        formparams.add(new BasicNameValuePair("public_email", user.getPublic_email()));
        formparams.add(new BasicNameValuePair("reset_password", String.valueOf(user.getReset_password())));
        formparams.add(new BasicNameValuePair("shared_runners_minutes_limit", user.getShared_runners_minutes_limit()));
        formparams.add(new BasicNameValuePair("skip_confirmation", String.valueOf(user.getSkip_confirmation())));
        formparams.add(new BasicNameValuePair("skype", user.getSkype()));
        formparams.add(new BasicNameValuePair("theme_id", String.valueOf(user.getTheme_id())));
        formparams.add(new BasicNameValuePair("twitter", user.getTwitter()));
        formparams.add(new BasicNameValuePair("website_url", user.getWebsite_url()));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpPut.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPut);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }


    public boolean deleteAuthenticationIdentityFromUser(String userId, String provider) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/identities/" + provider);
        httpDelete.addHeader("PRIVATE-TOKEN", Global.root_private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", userId));
        params.add(new BasicNameValuePair("provider", provider));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);

        httpDelete.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return true;
        } else {
            return false;
        }
    }
}
