package com.css.gitapi.model;

import java.io.*;
import java.util.Properties;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/30 14:43
 */
public class Global {
    public static String gitIP;
    public static String gitPort;
    public static String root_private_token;
    public static String regular_private_token;
    private static Properties pro;

    static {
        InputStream inputStream = Global.class.getResourceAsStream("/gitlab_config.properties");
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        try {
            pro = new Properties();
            pro.load(bf);
            gitIP = pro.getProperty("ip");
            gitPort = pro.getProperty("port");
            root_private_token = pro.getProperty("root_private_token");
            regular_private_token = pro.getProperty("regular_private_token");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getPorpties(String key) throws UnsupportedEncodingException {
        return (String) pro.get(key);
    }
}
