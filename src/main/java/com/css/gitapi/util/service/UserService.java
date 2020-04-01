package com.css.gitapi.util.service;

import com.css.gitapi.util.controller.UserController;
import com.css.gitapi.util.model.CreateUserParams;

import java.io.IOException;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/30 14:35
 */
public class UserService {
    UserController userController = new UserController();

    /**
     * 获取所有用户
     *
     * @param your_private_token 需要提供private_token
     * @return 返回用户集合的json
     * @throws Exception
     */
    public String getAllUsers(String your_private_token) throws Exception {
        if (your_private_token != null && !"".equals(your_private_token)) {
            return userController.getAllUser(your_private_token);
        } else {
            return "The your_private_token is required!";
        }
    }


    /**
     * 根据userId获取这个用户的信息
     *
     * @param your_private_token 需要提供private_token
     * @param userId             用户ID
     * @return
     * @throws IOException
     */
    public String getUserByUserId(String your_private_token, String userId) throws IOException {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "The your_private_token is required!";
        } else if (userId == null && "".equals(userId)) {
            return "The userId is required!";
        } else{
            return userController.getUserByUserId(your_private_token, userId);
        }

    }


    /**
     * 添加用户
     *
     * @return 返回创建好的用户信息
     */
    public String addUser(CreateUserParams user) throws IOException {
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
        if (userId != null && !"".equals(userId)) {
            return userController.delUserById(userId);
        } else {
            return false;
        }

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
        if (userId != null && !"".equals(userId)) {
            return userController.delUserById(userId, hard_delete);
        } else {
            return false;
        }

    }

    /**
     * 根据用户ID修改用户信息
     *
     * @param user   新的用户信息 （暂不支持修改email）
     * @param userId 用户ID
     * @return 返回修改后的结果
     * @throws Exception
     */
    public String modifyUserByUserId(CreateUserParams user, String userId) throws Exception {
        if (userId != null && !"".equals(userId)) {
            return userController.modifyUserById(user, userId);
        } else {
            return "The userId is required!";
        }

    }

    /**
     * 删除用户的创建者信息
     *
     * @param userId   用户ID
     * @param provider 用户的创建者
     * @return
     */
    public boolean DeleteAuthenticationIdentityFromUser(String userId, String provider) throws Exception {
        if (userId != null && !"".equals(userId) && provider != null && !"".equals(provider)) {
            return userController.deleteAuthenticationIdentityFromUser(userId, provider);
        } else {
            System.out.println("The userId and provider are required!");
            return false;
        }
    }

}
