package com.css.gitapi.util.service;

import com.css.gitapi.util.controller.ServerController;
import com.css.gitapi.util.controller.UserController;
import com.css.gitapi.util.model.GitLabUser;
import com.css.gitapi.util.enums.Identify;

import java.io.IOException;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/30 14:35
 */
public class UserService {
    UserController userController = new UserController();


    /**
     * 测试gitlab服务是否可以连通
     */
    public boolean testConnect() throws Exception {
        ServerController sta = new ServerController();
        return sta.isConnect();
    }

    /**
     * 获取所有用户
     *
     * @return 返回用户集合的json
     * @throws Exception
     */
    public String getAllUsers(Identify idt) throws Exception {
        return userController.getAllUser(idt);
    }


    /**
     * 根据userId获取这个用户的信息
     *
     * @param idt    身份
     * @param userId 用户ID
     * @return
     * @throws IOException
     */
    public String getUserByUserId(Identify idt, String userId) throws IOException {
        return userController.getUserByUserId(idt, userId);
    }


    /**
     * 添加用户
     *
     * @return 返回创建好的用户信息
     */
    public String addUser(GitLabUser user) throws IOException {
        return userController.addUser(user);
    }

    /**
     * 根据用户ID删除用户
     *
     * @param userId 用户ID
     * @return 返回删除结果
     * @throws Exception
     */
    public boolean delUserByUserId(String userId) throws Exception {
        return userController.delUserById(userId);
    }

    /**
     * 根据用户ID删除用户
     *
     * @param userId      用户ID
     * @param hard_delete 强制删除,删除后用户信息保存在ghost账户
     * @return 返回删除结果
     * @throws Exception
     */
    public boolean delUserByUserId(String userId, boolean hard_delete) throws Exception {
        return userController.delUserById(userId, hard_delete);
    }

    /**
     * 根据用户ID修改用户信息
     *
     * @param user   新的用户信息 （暂不支持修改email）
     * @param userId 用户ID
     * @return 返回修改后的结果
     * @throws Exception
     */
    public String modifyUserByUserId(GitLabUser user, String userId) throws Exception {
        return userController.modifyUserById(user, userId);
    }

    /**
     * 删除用户的创建者信息
     *
     * @param userId   用户ID
     * @param provider 用户的创建者
     * @return
     */
    public boolean DeleteAuthenticationIdentityFromUser(String userId, String provider) throws Exception {
        return userController.deleteAuthenticationIdentityFromUser(userId, provider);
    }

}
