package com.css.gitapi.service;

import com.css.gitapi.enums.HttpCode;
import com.css.gitapi.model.Global;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/1 15:10
 */
public class ServerService {


    /**
     * 测试gitlab服务是否可以连通
     */
    public boolean isConnect() {
        try {
            CloseableHttpClient httpClients = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://" + Global.gitIP + ":" + Global.gitPort + "/api/session");

            CloseableHttpResponse response = httpClients.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpCode.OK.getCode()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
