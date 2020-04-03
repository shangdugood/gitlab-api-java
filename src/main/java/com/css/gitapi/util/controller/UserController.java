package com.css.gitapi.util.controller;

import com.css.gitapi.util.enums.HttpCode;
import com.css.gitapi.util.enums.ImpersonationTokenScopes;
import com.css.gitapi.util.enums.ImpersonationTokenState;
import com.css.gitapi.util.enums.UserMembershipsType;
import com.css.gitapi.util.httputil.HttpDeleteWithBody;
import com.css.gitapi.util.httputil.HttpGetWithBody;
import com.css.gitapi.util.model.CreateUserParams;
import com.css.gitapi.util.model.Global;
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
    public static String getAllUser(String private_token) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String addUser(CreateUserParams user, String root_private_token) throws IOException {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users");
        httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
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
        formparams.add(new BasicNameValuePair("force_random_password", String.valueOf(user.getForce_random_password())));
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


    public String getUserByUserId(String private_token, String userId) throws IOException {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpCode.OK.getCode()) {
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        } else {
            return "获取失败，请检查用户ID是否存在或者root_private_token是否正确";
        }
    }

    public String modifyUserById(CreateUserParams user, String userId, String root_private_token) throws IOException {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        httpPut.addHeader("PRIVATE-TOKEN", root_private_token);
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
        formparams.add(new BasicNameValuePair("force_random_password", String.valueOf(user.getForce_random_password())));
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


    public String deleteAuthenticationIdentityFromUser(String userId, String provider, String root_private_token) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/identities/" + provider);
        httpDelete.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", userId));
        params.add(new BasicNameValuePair("provider", provider));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);

        httpDelete.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delAuthIdtSuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String delUserById(String userId, String root_private_token, boolean hard_delete) throws IOException {

        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId);
        httpDelete.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("id", userId));
        formparams.add(new BasicNameValuePair("hard_delete", String.valueOf(hard_delete)));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpDelete.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delUserSuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String getCurrentUsersForNormalAccount(String private_token) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getCurrentUserFail");
        }
    }


    public String getCurrentUsersForAdmin(String root_private_token, String currentUserId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user");
        httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (currentUserId != null && !"".equals(currentUserId)) {
            formparams.add(new BasicNameValuePair("sudo", currentUserId));
        }

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getCurrentUserFail");
        }
    }


    public String getUserStatus(String private_token) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/status");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserStatusFail");
        }
    }

    public String getUserStatusByUserIdOrUserName(String id_or_username) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + id_or_username + "/status");
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserStatusFail");
        }
    }


    public String setUserStatus(String private_token, String emoji, String message) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/status");
        httpPut.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (emoji != null && !"".equals(emoji)) {
            formparams.add(new BasicNameValuePair("emoji", emoji));
        }
        if (message != null && !"".equals(message)) {
            formparams.add(new BasicNameValuePair("message", message));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPut.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPut);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(response.getEntity());
    }

    public String getUserSSHKeys(String private_token) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/keys");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserSSHKeysFail");
        }
    }

    public String getUserSSHKeysByUserIdorUsername(String id_or_username) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + id_or_username + "/keys");
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserSSHKeysFail");
        }
    }

    public String getUserSSHKeyByKeyId(String private_token, Integer keyId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/keys/" + keyId);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserSSHKeysFail");
        }
    }

    public String addSSHKey(String private_token, String title, String key) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/keys");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("title", title));
        formparams.add(new BasicNameValuePair("key", key));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }


    public String addSSHKeyForUser(String root_private_token, String userId, String title, String key) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/keys");
        httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("title", title));
        formparams.add(new BasicNameValuePair("key", key));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String delSSHKeyForUser(String root_private_token, String userId, String keyId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/keys/" + keyId);
        httpDelete.addHeader("PRIVATE-TOKEN", root_private_token);

        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delUserSSHkeySuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String delSSHKey(String private_token, String keyId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/keys/" + keyId);
        httpDelete.addHeader("PRIVATE-TOKEN", private_token);

        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delUserSSHkeySuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }


    public String getUserGPGKeys(String private_token) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/gpg_keys");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserGPGKeysFail");
        }
    }

    public String getUserGPGKeysByUserId(String userId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/gpg_keys");
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserGPGKeysFail");
        }
    }

    public String getUserGPGKeyByKeyId(String private_token, String keyId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/gpg_keys/" + keyId);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserGPGKeysFail");
        }
    }

    public String getUserGPGKeyByKeyIdForAdmin(String root_private_token, String userId, String keyId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/gpg_keys/" + keyId);
        httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserGPGKeysFail");
        }
    }

    public String addGPGKey(String private_token, String key) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/gpg_keys");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("key", key));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }


    public String addGPGKeyForUser(String root_private_token, String userId, String key) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/gpg_keys");
        httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("key", key));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String delGPGKeyForUser(String root_private_token, String userId, String keyId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/gpg_keys/" + keyId);
        httpDelete.addHeader("PRIVATE-TOKEN", root_private_token);

        CloseableHttpResponse response = httpClients.execute(httpDelete);
        HttpEntity entity1 = response.getEntity();
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delUserGPGkeySuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String delGPGKey(String private_token, String keyId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/gpg_keys/" + keyId);
        httpDelete.addHeader("PRIVATE-TOKEN", private_token);

        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delUserSSHkeySuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String getEmails(String private_token) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/emails");
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserEmailsFail");
        }
    }

    public String getOneEmail(String private_token, String email_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/emails/" + email_id);
        httpGet.addHeader("PRIVATE-TOKEN", private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserEmailsFail");
        }
    }


    public String getUserEmailsForAdmin(String root_private_token, String userId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/emails");
        httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            return EntityUtils.toString(entity1);
        } else {
            return Global.getPorpties("getUserEmailsFail");
        }
    }

    public String addEmail(String private_token, String email) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/emails");
        httpPost.addHeader("PRIVATE-TOKEN", private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("email", email));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String addEmailForUser(String root_private_token, String userId, String email, boolean skip_confirmation) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/emails");
        httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("email", email));
        formparams.add(new BasicNameValuePair("skip_confirmation", String.valueOf(skip_confirmation)));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String delEmailForUser(String root_private_token, String userId, String email_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/emails/" + email_id);
        httpDelete.addHeader("PRIVATE-TOKEN", root_private_token);

        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delUserEmailSuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String delEmail(String private_token, String email_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/emails/" + email_id);
        httpDelete.addHeader("PRIVATE-TOKEN", private_token);

        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delUserEmailSuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }


    public String blockUser(String root_private_token, Integer userId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/block");
        httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String unblockUser(String root_private_token, Integer userId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/unblock");
        httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String deactivateUser(String root_private_token, Integer userId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/deactivate");
        httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String activeUser(String root_private_token, Integer userId) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/active");
        httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getUserImpersonationTokens(String root_private_token, Integer userId, ImpersonationTokenState state) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/impersonation_tokens");
        httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (state != null) {
            formparams.add(new BasicNameValuePair("state", state.getDesc()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getUserOneImpersonationToken(String root_private_token, Integer userId, Integer impersonation_token_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/impersonation_tokens/" + impersonation_token_id);
        httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String createOneImpersonationToken(String root_private_token, Integer userId, String impersonation_name, ArrayList<ImpersonationTokenScopes> scopes, String expires_at) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/impersonation_tokens");
        httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("name", impersonation_name));
        formparams.add(new BasicNameValuePair("scopes", scopes.toString()));
        if (expires_at != null && !"".equals(expires_at)) {
            formparams.add(new BasicNameValuePair("expires_at", expires_at));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpPost);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }


    public String delOneImpersonationToken(String root_private_token, Integer user_id, Integer impersonation_token_id) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + user_id + "/impersonation_tokens/" + impersonation_token_id);
        httpDelete.addHeader("PRIVATE-TOKEN", root_private_token);

        CloseableHttpResponse response = httpClients.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == HttpCode.DELETESUCCESS.getCode()) {
            return Global.getPorpties("delImpersonationTokenSuccess");
        } else if (response.getEntity() != null) {
            return EntityUtils.toString(response.getEntity());
        } else {
            return response.getStatusLine().getStatusCode() + ".";
        }
    }

    public String getUserActivies(String root_private_token, String from) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/activities");
        httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (from != null && !"".equals(from)) {
            formparams.add(new BasicNameValuePair("from", from));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

    public String getUserMemberships(String root_private_token, Integer userId, UserMembershipsType type) throws Exception {
        CloseableHttpClient httpClients = HttpClients.createDefault();
        HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/" + userId + "/memberships");
        httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (type != null && !"".equals(type)) {
            formparams.add(new BasicNameValuePair("from", type.getDesc()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        httpGet.setEntity(entity);
        CloseableHttpResponse response = httpClients.execute(httpGet);
        HttpEntity entity1 = response.getEntity();
        return EntityUtils.toString(entity1);
    }

}
