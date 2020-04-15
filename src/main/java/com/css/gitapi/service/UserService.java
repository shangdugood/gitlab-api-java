package com.css.gitapi.service;

import com.css.gitapi.enums.HttpCode;
import com.css.gitapi.enums.ImpersonationTokenScopes;
import com.css.gitapi.enums.ImpersonationTokenState;
import com.css.gitapi.enums.UserMembershipsType;
import com.css.gitapi.httputil.HttpDeleteWithBody;
import com.css.gitapi.httputil.HttpGetWithBody;
import com.css.gitapi.model.CreateUserParams;
import com.css.gitapi.model.Global;
import com.css.gitapi.model.Pagination;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/30 14:35
 */
public class UserService {

    private final String COMMON_URL = "http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users/";

    /**
     * 锁定一个用户
     *
     * @param root_private_token 管理员的private_token
     * @param user_id            用户ID
     * @return 返回结果
     * @throws Exception
     */
    public String blockUser(String root_private_token, Integer user_id) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + user_id + "/block");
            httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 解锁一个用户
     *
     * @param root_private_token 管理员的private_token
     * @param user_id            用户ID
     * @return 返回结果
     * @throws Exception
     */
    public String unblockUser(String root_private_token, Integer user_id) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + user_id + "/unblock");
            httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 将一个用户设置为不活跃状态
     *
     * @param root_private_token 管理员private_token
     * @param user_id            用户的ID
     * @return 返回结果
     * @throws Exception
     */
    public String deactivateUser(String root_private_token, Integer user_id) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + user_id + "/deactivate");
            httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 将一个用户设置为活跃状态
     *
     * @param root_private_token 管理员的private_token
     * @param user_id            用户的ID
     * @return 返回结果
     * @throws Exception
     */
    public String activeUser(String root_private_token, Integer user_id) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + user_id + "/active");
            httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 查找用户的ImpersonationTokens
     *
     * @param root_private_token 管理员的private_token
     * @param user_id            用户的ID
     * @return 返回tokens
     * @throws Exception
     */
    public String getUserImpersonationTokens(String root_private_token, Integer user_id) throws Exception {
        return getUserImpersonationTokens(root_private_token, user_id, null);
    }

    /**
     * 查找用户的ImpersonationTokens
     *
     * @param root_private_token 管理员的private_token
     * @param user_id            用户的ID
     * @param state              ImpersonationToken的状态
     * @return 返回tokens
     * @throws Exception
     */
    public String getUserImpersonationTokens(String root_private_token, Integer user_id, ImpersonationTokenState state) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + user_id + "/impersonation_tokens");
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
    }

    /**
     * 获得用户的某个ImpersonationToken
     *
     * @param root_private_token     管理员的private_token
     * @param user_id                用户的ID
     * @param impersonation_token_id impersonation_token_id
     * @return 返回impersonation_token信息
     * @throws Exception
     */
    public String getUserOneImpersonationToken(String root_private_token, Integer user_id, Integer impersonation_token_id) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else if (impersonation_token_id == null || "".equals(impersonation_token_id)) {
            return "The impersonation_token_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COMMON_URL + user_id + "/impersonation_tokens/" + impersonation_token_id);
            httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 为用户创建一个ImpersonationToken
     *
     * @param root_private_token 管理员的private_token
     * @param user_id            用户的ID
     * @param impersonation_name ImpersonationToken的名称
     * @param scopes             可以访问的域
     * @return 返回结果
     * @throws Exception
     */
    public String createOneImpersonationToken(String root_private_token, Integer user_id, String impersonation_name, ArrayList<ImpersonationTokenScopes> scopes) throws Exception {
        return createOneImpersonationToken(root_private_token, user_id, impersonation_name, scopes, null);
    }

    /**
     * 为用户创建一个ImpersonationToken
     *
     * @param root_private_token 管理员的private_token
     * @param user_id            用户的ID
     * @param impersonation_name ImpersonationToken的名称
     * @param scopes             可以访问的域
     * @param expires_at         到期时间
     * @return 返回结果
     * @throws Exception
     */
    public String createOneImpersonationToken(String root_private_token, Integer user_id, String impersonation_name, ArrayList<ImpersonationTokenScopes> scopes, Date expires_at) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else if (impersonation_name == null || "".equals(impersonation_name)) {
            return "The impersonation_token_id is required.";
        } else if (scopes == null) {
            return "The scopes is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + user_id + "/impersonation_tokens");
            httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("name", impersonation_name));
            formparams.add(new BasicNameValuePair("scopes", scopes.toString()));
            if (expires_at != null && !"".equals(expires_at)) {
                formparams.add(new BasicNameValuePair("expires_at", new SimpleDateFormat("yyyy-MM-dd").format(expires_at)));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }


    /**
     * 删除用户的ImpersonationToken
     *
     * @param root_private_token     管理员的private_token
     * @param user_id                用户ID
     * @param impersonation_token_id ImpersonationToken的ID
     * @return 返回结果
     * @throws Exception
     */
    public String delOneImpersonationToken(String root_private_token, Integer user_id, Integer impersonation_token_id) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else if (impersonation_token_id == null || "".equals(impersonation_token_id)) {
            return "The impersonation_token_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(COMMON_URL + user_id + "/impersonation_tokens/" + impersonation_token_id);
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
    }

    /**
     * 获取用户的活动
     *
     * @param root_private_token 管理员的private_token
     * @return 返回结果
     * @throws Exception
     */
    public String getUserActivies(String root_private_token) throws Exception {
        return getUserActivies(root_private_token, null);
    }

    /**
     * 获取用户的活动
     *
     * @param root_private_token 管理员的private_token
     * @param from               从什么时间开始
     * @return 返回结果
     * @throws Exception
     */
    public String getUserActivies(String root_private_token, Date from) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/activities");
            httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (from != null && !"".equals(from)) {
                formparams.add(new BasicNameValuePair("from", new SimpleDateFormat("yyyy-MM-dd").format(from)));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            httpGet.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 获得用户的memberships
     *
     * @param root_private_token 管理员private_token
     * @param userId             用户ID
     * @return 返回结果
     * @throws Exception
     */
    public String getUserMemberships(String root_private_token, Integer userId) throws Exception {
        return getUserMemberships(root_private_token, userId, null);
    }

    /**
     * 获得用户的memberships
     *
     * @param root_private_token 管理员private_token
     * @param userId             用户ID
     * @param type               用户关系类型
     * @return 返回结果
     * @throws Exception
     */
    public String getUserMemberships(String root_private_token, Integer userId, UserMembershipsType type) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (userId == null) {
            return "The userId is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody(COMMON_URL + userId + "/memberships");
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


    /**
     * 查找邮箱信息
     *
     * @param private_token 用户的private_token
     * @return 返回邮箱信息
     * @throws Exception
     */
    public String getEmails(String private_token) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else {
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
    }

    /**
     * 查找指定邮箱
     *
     * @param private_token 用户的private_token
     * @param email_id      邮箱ID
     * @return 返回邮箱信息
     * @throws Exception
     */
    public String getOneEmail(String private_token, String email_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (email_id == null || "".equals(email_id)) {
            return "The email_id is required.";
        } else {
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
    }

    /**
     * 管理员获得指定用户的邮箱
     *
     * @param root_private_token 管理员的private_token
     * @param userId             用户ID
     * @return 返回邮箱信息
     * @throws Exception
     */
    public String getUserEmailsForAdmin(String root_private_token, String userId) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (userId == null || "".equals(userId)) {
            return "The userId is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COMMON_URL + userId + "/emails");
            httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            if (entity1 != null) {
                return EntityUtils.toString(entity1);
            } else {
                return Global.getPorpties("getUserEmailsFail");
            }
        }
    }

    /**
     * 添加一个邮箱
     *
     * @param private_token 用户的private_token
     * @param email         邮箱地址
     * @return 返回添加后的信息
     * @throws Exception
     */
    public String addEmail(String private_token, String email) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (email == null || "".equals(email)) {
            return "The email_id is required.";
        } else {
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
    }

    /**
     * 管理员为用户添加一个邮箱
     *
     * @param root_private_token 管理员的private_token
     * @param userId             用户ID
     * @param email              邮箱地址
     * @return 返回添加成功的信息
     * @throws Exception
     */
    public String addEmailForUser(String root_private_token, String userId, String email) throws Exception {
        return addEmailForUser(root_private_token, userId, email, false);
    }

    /**
     * 管理员为用户添加一个邮箱
     *
     * @param root_private_token 管理员的private_token
     * @param userId             用户ID
     * @param email              邮箱地址
     * @param skip_confirmation  是否跳过确认，默认为false
     * @return 返回添加成功的信息
     * @throws Exception
     */
    public String addEmailForUser(String root_private_token, String userId, String email, boolean skip_confirmation) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (userId == null || "".equals(userId)) {
            return "The userId is required.";
        } else if (email == null || "".equals(email)) {
            return "The email_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + userId + "/emails");
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
    }

    /**
     * 管理员为用户删除一个邮箱
     *
     * @param root_private_token 管理员的private_token
     * @param userId             用户的ID
     * @param email_id           用户邮箱的ID
     * @return 返回删除结果
     * @throws Exception
     */
    public String delEmailForUser(String root_private_token, String userId, String email_id) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (userId == null || "".equals(userId)) {
            return "The userId is required.";
        } else if (email_id == null || "".equals(email_id)) {
            return "The email_id is required.";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(COMMON_URL + userId + "/emails/" + email_id);
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
    }

    /**
     * 删除邮箱
     *
     * @param private_token 用户的private_token
     * @param email_id      邮箱的ID
     * @return 返回结果
     * @throws Exception
     */
    public String delEmail(String private_token, String email_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (email_id == null || "".equals(email_id)) {
            return "The email_id is required.";
        } else {
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
    }


    /**
     * 获取用户的GPG Keys
     *
     * @param private_token 用户的private_token
     * @return 返回GPG Keys信息
     * @throws Exception
     */
    public String getUserGPGKeys(String private_token) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required!";
        } else {
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
    }

    /**
     * 通过用户的ID获得用户的GPG Keys
     *
     * @param userId 用户ID
     * @return 返回GPG Keys
     * @throws Exception
     */
    public String getUserGPGKeysByUserId(String userId) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COMMON_URL + userId + "/gpg_keys");
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            if (entity1 != null) {
                return EntityUtils.toString(entity1);
            } else {
                return Global.getPorpties("getUserGPGKeysFail");
            }
        }
    }

    /**
     * 通过GPG Key ID 获得用户指定的GPG Key
     *
     * @param private_token 用户的private_token
     * @param keyId         指定的GPG KeyId
     * @return 返回GPG Key信息
     * @throws Exception
     */
    public String getUserGPGKeyByKeyId(String private_token, String keyId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The root_private_token is required!";
        } else if (keyId == null || "".equals(keyId)) {
            return "The keyId is required!";
        } else {
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
    }

    /**
     * 管理员获得指定用户的指定GPG Key
     *
     * @param root_private_token 管理员的private_token
     * @param userId             用户的ID
     * @param keyId              GPG Key的ID
     * @return 返回GPG Key的信息
     * @throws Exception
     */
    public String getUserGPGKeyByKeyIdForAdmin(String root_private_token, String userId, String keyId) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required!";
        } else if (userId == null || "".equals(userId)) {
            return "The userId is required!";
        } else if (keyId == null || "".equals(keyId)) {
            return "The keyId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COMMON_URL + userId + "/gpg_keys/" + keyId);
            httpGet.addHeader("PRIVATE-TOKEN", root_private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            if (entity1 != null) {
                return EntityUtils.toString(entity1);
            } else {
                return Global.getPorpties("getUserGPGKeysFail");
            }
        }
    }

    /**
     * 用户添加GPG Key
     *
     * @param private_token 用户的private_token
     * @param key           GPG Key信息
     * @return 返回添加结果
     * @throws Exception
     */
    public String addGPGKey(String private_token, String key) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required!";
        } else if (key == null || "".equals(key)) {
            return "The key is required!";
        } else {
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
    }


    /**
     * 管理员给用户添加一个GPG Key
     *
     * @param root_private_token 管理员的private_token
     * @param userId             用户的ID
     * @param key                GPG Key信息
     * @return 返回添加结果
     * @throws Exception
     */
    public String addGPGKeyForUser(String root_private_token, String userId, String key) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required!";
        } else if (userId == null || "".equals(userId)) {
            return "The userId is required!";
        } else if (key == null || "".equals(key)) {
            return "The key is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + userId + "/gpg_keys");
            httpPost.addHeader("PRIVATE-TOKEN", root_private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("key", key));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }

    /**
     * 管理员给用户删除一个 GPG Key
     *
     * @param root_private_token 管理员的private_token
     * @param userId             用户的ID
     * @param keyId              GPG Key的ID
     * @return 返回删除结果
     * @throws Exception
     */
    public String delGPGKeyForUser(String root_private_token, String userId, String keyId) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required!";
        } else if (userId == null || "".equals(userId)) {
            return "The userId is required!";
        } else if (keyId == null || "".equals(keyId)) {
            return "The keyId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(COMMON_URL + userId + "/gpg_keys/" + keyId);
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
    }

    /**
     * 删除一个GPG Key
     *
     * @param private_token 用户的private_token
     * @param keyId         要删除的GPG Key ID
     * @return 返回删除结果
     * @throws Exception
     */
    public String delGPGKey(String private_token, String keyId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required!";
        } else if (keyId == null || "".equals(keyId)) {
            return "The keyId is required!";
        } else {
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
    }

    /**
     * 管理员为指定用户删除SSHKey
     *
     * @param root_private_token 管理员private_token
     * @param userId             用户的ID
     * @param key_id             用户的SSH Key ID
     * @return 返回结果
     * @throws Exception
     */
    public String delSSHKeyForUser(String root_private_token, Integer userId, Integer key_id) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The private_token is required!";
        } else if (userId == null) {
            return "The userId is required!";
        } else if (key_id == null) {
            return "The key_id is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(COMMON_URL + userId + "/keys/" + key_id);
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
    }

    /**
     * 用户删除SSHKey
     *
     * @param private_token 用户private_token
     * @param key_id        用户的SSH Key ID
     * @return 返回结果
     * @throws Exception
     */
    public String delSSHKey(String private_token, String key_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required!";
        } else if (key_id == null || "".equals(key_id)) {
            return "The key_id is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/keys/" + key_id);
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
    }


    /**
     * 管理员为指定用户添加SSHKey
     *
     * @param root_private_token 管理员的private_token
     * @param userId             用户的ID
     * @param title              SSHKey的标题
     * @param key                SSHKey的内容 Must be RSA, DSA, ECDSA, or ED25519
     * @return 返回添加好的SSHKey
     * @throws Exception
     */
    public String addSSHKeyForUser(String root_private_token, String userId, String title, String key) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The private_token is required!";
        } else if (title == null || "".equals(title)) {
            return "The title is required!";
        } else if (key == null || "".equals(key)) {
            return "The key is required!";
        } else if (userId == null || "".equals(userId)) {
            return "The userId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(COMMON_URL + userId + "/keys");
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
    }

    /**
     * 为用户添加SSHKey
     *
     * @param private_token 用户的private_token
     * @param title         SSHKey的标题
     * @param key           SSHKey的内容 Must be RSA, DSA, ECDSA, or ED25519
     * @return 返回添加好的SSHKey
     * @throws Exception
     */
    public String addSSHKey(String private_token, String title, String key) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required!";
        } else if (title == null || "".equals(title)) {
            return "The title is required!";
        } else if (key == null || "".equals(key)) {
            return "The key is required!";
        } else {
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
    }

    /**
     * 获取用户的指定SSH Key
     *
     * @param private_token 用户的private_token
     * @param key_id        用户的SSH Key的ID
     * @return 获取用户的SSHKey信息
     * @throws Exception
     */
    public String getUserSSHKeyByKeyId(String private_token, Integer key_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required!";
        } else if (key_id == null) {
            return "The key_id is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user/keys/" + key_id);
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            if (entity1 != null) {
                return EntityUtils.toString(entity1);
            } else {
                return Global.getPorpties("getUserSSHKeysFail");
            }
        }
    }

    /**
     * 获取指定用户的SSH Keys
     *
     * @param id_or_username 用户的ID或者username
     * @return 获取用户的SSHKeys信息
     * @throws Exception
     */
    public String getUserSSHKeysByUserIdorUsername(String id_or_username) throws Exception {
        if (id_or_username == null || "".equals(id_or_username)) {
            return "The id_or_username is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COMMON_URL + id_or_username + "/keys");
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            if (entity1 != null) {
                return EntityUtils.toString(entity1);
            } else {
                return Global.getPorpties("getUserSSHKeysFail");
            }
        }
    }

    /**
     * 获取用户的SSH Keys
     *
     * @param private_token 用户的private_token
     * @return 获取用户的SSHKeys信息
     * @throws Exception
     */
    public String getUserSSHKeys(String private_token) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required!";
        } else {
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
    }

    /**
     * 设置用户的状态表情和状态信息
     *
     * @param private_token 用户的private_token
     * @param emoji         状态表情的index，可以在这里找:https://github.com/bonusly/gemojione/blob/master/config/index.json
     * @return 返回设置结果
     * @throws Exception
     */
    public String setUserStatusEmoji(String private_token, String emoji) throws Exception {
        return setUserStatus(private_token, emoji, null);
    }

    /**
     * 设置用户的状态表情和状态信息
     *
     * @param private_token 用户的private_token
     * @param message       状态信息
     * @return 返回设置结果
     * @throws Exception
     */
    public String setUserStatusMessage(String private_token, String message) throws Exception {
        return setUserStatus(private_token, null, message);
    }

    /**
     * 设置用户的状态表情和状态信息
     *
     * @param private_token 用户的private_token
     * @param emoji         状态表情的index，可以在这里找:https://github.com/bonusly/gemojione/blob/master/config/index.json
     * @param message       状态信息
     * @return 返回设置结果
     * @throws Exception
     */
    public String setUserStatus(String private_token, String emoji, String message) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required!";
        } else {
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
    }

    /**
     * 获取指定用户的状态
     *
     * @param id_or_username 用户的ID或者username
     * @return 用户的状态
     * @throws Exception
     */
    public String getUserStatusByUserIdOrUserName(String id_or_username) throws Exception {
        if (id_or_username == null || "".equals(id_or_username)) {
            return "The id_or_username is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COMMON_URL + id_or_username + "/status");
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            if (entity1 != null) {
                return EntityUtils.toString(entity1);
            } else {
                return Global.getPorpties("getUserStatusFail");
            }
        }
    }


    /**
     * 获取用户的状态
     *
     * @param private_token 用户的private_token
     * @return 用户的状态
     * @throws Exception
     */
    public String getUserStatus(String private_token) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required!";
        } else {
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
    }

    /**
     * Administrator gets currently authenticated user.
     *
     * @param root_private_token Administrator's private_token
     * @param pagination         分页信息，可以为null，默认page=1,per_page=20
     * @return 用户信息
     * @throws Exception
     */
    public String getCurrentUserForAdmin(String root_private_token, Pagination pagination) throws Exception {
        return getCurrentUserForAdmin(root_private_token, "", pagination);
    }

    /**
     * Administrator gets currently authenticated user.
     *
     * @param root_private_token Administrator's private_token
     * @param currentUserId      要获取的用户的ID
     * @param pagination         分页信息，可以为null，默认page=1,per_page=20
     * @return 用户信息
     * @throws Exception
     */
    public String getCurrentUserForAdmin(String root_private_token, String currentUserId, Pagination pagination) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user");
            httpGet.addHeader("PRIVATE-TOKEN", root_private_token);

            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (pagination != null) {
                formparams.add(new BasicNameValuePair("page", pagination.getPage().toString()));
                formparams.add(new BasicNameValuePair("per_page", pagination.getPer_page().toString()));
            }
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
    }


    /**
     * Gets currently authenticated user.
     *
     * @param private_token 获取的用户的private_token
     * @param pagination    分页信息，可以为null，默认page=1,per_page=20
     * @return 返回用户信息
     * @throws Exception
     */
    public String getCurrentUserForNormalUser(String private_token, Pagination pagination) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/user");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (pagination != null) {
                formparams.add(new BasicNameValuePair("page", pagination.getPage().toString()));
                formparams.add(new BasicNameValuePair("per_page", pagination.getPer_page().toString()));
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
    }

    /**
     * 获取所有用户
     *
     * @param private_token 需要提供private_token
     * @param pagination    分页信息，可以为null，默认page=1,per_page=20
     * @return 返回用户集合的json
     * @throws Exception
     */
    public String getAllUsers(String private_token, Pagination pagination) throws Exception {
        if (private_token != null && "".equals(private_token)) {
            return "The private_token is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGetWithBody httpGet = new HttpGetWithBody("http://" + Global.gitIP + ":" + Global.gitPort + "/api/v4/users");
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (pagination != null) {
                formparams.add(new BasicNameValuePair("page", pagination.getPage().toString()));
                formparams.add(new BasicNameValuePair("per_page", pagination.getPer_page().toString()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            httpGet.setEntity(entity);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        }
    }


    /**
     * 根据userId获取这个用户的信息
     *
     * @param private_token 需要提供private_token
     * @param userId        用户ID
     * @return
     * @throws IOException
     */
    public String getUserByUserId(String private_token, String userId) throws IOException {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required!";
        } else if (userId == null && "".equals(userId)) {
            return "The userId is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(COMMON_URL + userId);
            httpGet.addHeader("PRIVATE-TOKEN", private_token);
            CloseableHttpResponse response = httpClients.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpCode.OK.getCode()) {
                HttpEntity entity1 = response.getEntity();
                return EntityUtils.toString(entity1);
            } else {
                return Global.getPorpties("getUserFail");
            }
        }

    }


    /**
     * 添加用户
     *
     * @param user               用户的信息
     * @param root_private_token 管理员private_token
     * @return 返回创建好的用户信息
     */
    public String addUser(CreateUserParams user, String root_private_token) throws IOException {
        if (user.getName() == null || "".equals(user.getName())) {
            return "The user'name is required!";
        } else if (user.getUsername() == null || "".equals(user.getUsername())) {
            return "The user'username is required!";
        } else if (user.getEmail() == null || "".equals(user.getEmail())) {
            return "The user'email is required!";
        } else if (!user.getReset_password() && !user.getForce_random_password() && (user.getPassword() == null || "".equals(user.getPassword()))) {
            return "If reset_password and force_random_password are both false, then password is required!";
        } else {
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
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);

        }

    }


    /**
     * 根据用户ID修改用户信息
     *
     * @param user               新的用户信息 （暂不支持修改email）
     * @param userId             用户ID
     * @param root_private_token 管理员private_token
     * @return 返回修改后的结果
     * @throws Exception
     */
    public String modifyUserByUserId(CreateUserParams user, String userId, String root_private_token) throws Exception {
        if (userId == null && "".equals(userId)) {
            return "The userId is required!";
        } else if (user.getName() == null || "".equals(user.getName())) {
            return "The user'name is required!";
        } else if (user.getUsername() == null || "".equals(user.getUsername())) {
            return "The user'username is required!";
        } else if (user.getEmail() == null || "".equals(user.getEmail())) {
            return "The user'email is required!";
        } else if (!user.getReset_password() && !user.getForce_random_password() && (user.getPassword() == null || "".equals(user.getPassword()))) {
            return "If reset_password and force_random_password are both false, then password is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(COMMON_URL + userId);
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

    }

    /**
     * 删除用户的创建者信息
     *
     * @param userId             用户ID
     * @param provider           用户的创建者
     * @param root_private_token 管理员private_token
     * @return
     */
    public String deleteAuthenticationIdentityFromUser(String userId, String provider, String root_private_token) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required!";
        } else if (provider == null || "".equals(provider)) {
            return "The provider is required!";
        } else if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(COMMON_URL + userId + "/identities/" + provider);
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
    }

    /**
     * 根据用户ID删除用户
     *
     * @param root_private_token 管理员的private_token
     * @param userId             用户ID
     * @param hard_delete        强制删除,删除后用户信息保存在ghost账户
     * @return 返回删除结果
     * @throws Exception
     */
    public String delUserByUserId(String root_private_token, String userId, boolean hard_delete) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required!";
        } else if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required!";
        } else {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(COMMON_URL + userId);
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

    }

    /**
     * 根据用户ID删除用户
     *
     * @param userId             用户ID
     * @param root_private_token 管理员private_token
     * @return 返回删除结果
     * @throws Exception
     */
    public String delUserByUserId(String userId, String root_private_token) throws Exception {
        return delUserByUserId(userId, root_private_token, false);
    }
}
