package com.css.gitapi.util.controller;

import com.css.gitapi.util.model.Global;
import com.css.gitapi.util.enums.HttpCode;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 11:02
 */
public class ServerController {
    public boolean isConnect() throws IOException {
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
