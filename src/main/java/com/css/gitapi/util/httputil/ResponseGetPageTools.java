package com.css.gitapi.util.httputil;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/9 16:02
 */
public class ResponseGetPageTools {
    public static String getPageInfoByReponse(CloseableHttpResponse response) {
        JSONObject result = new JSONObject();
        result.put("X-Total", response.getHeaders("X-Total")[0].getValue());
        result.put("X-Total-Pages", response.getHeaders("X-Total-Pages")[0].getValue());
        result.put("X-Per-Page", response.getHeaders("X-Per-Page")[0].getValue());
        result.put("X-Page", response.getHeaders("X-Page")[0].getValue());
        result.put("X-Prev-Page", response.getHeaders("X-Prev-Page")[0].getValue());
        result.put("X-Next-Page", response.getHeaders("X-Next-Page")[0].getValue());
        return result.toJSONString();
    }
}
