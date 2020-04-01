package com.css.gitapi.util.model;

import java.io.IOException;
import java.io.InputStream;
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

    static {
        InputStream in = Global.class.getResourceAsStream("/config.properties");
        Properties pro = new Properties();
        try {
            pro.load(in);
            gitIP = pro.getProperty("ip");
            gitPort = pro.getProperty("port");
            root_private_token = pro.getProperty("root_private_token");
            regular_private_token = pro.getProperty("regular_private_token");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
