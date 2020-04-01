package com.css.gitapi.util.service;

import com.css.gitapi.util.controller.ServerController;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/1 15:10
 */
public class ServerService {

    ServerController serverController = new ServerController();
    /**
     * 测试gitlab服务是否可以连通
     */
    public boolean testConnect() throws Exception {
        return serverController.isConnect();
    }
}
