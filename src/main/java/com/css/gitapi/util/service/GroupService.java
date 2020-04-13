package com.css.gitapi.util.service;

import com.css.gitapi.util.controller.GroupController;
import com.css.gitapi.util.model.CreateGroupHookParams;
import com.css.gitapi.util.model.CreateGroupParams;
import com.css.gitapi.util.model.ListGroupProjectParams;
import com.css.gitapi.util.model.ListGroupsParams;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/9 11:43
 */
public class GroupService {
    GroupController groupController = new GroupController();

    /**
     * 删除群组的hook
     *
     * @param private_token 用户的private_token
     * @param hook_id       要删除的hook的id
     * @return
     * @throws Exception
     */
    public String modifyGroupHook(String private_token, String group_id, Integer hook_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (hook_id == null || "".equals(hook_id)) {
            return "The hook_id is required.";
        } else {
            return groupController.delGroupHook(private_token, group_id, hook_id);
        }
    }

    /**
     * 修改群组的hook
     *
     * @param private_token 用户的private_token
     * @param hook_id       要修改的hook的ID
     * @param params        添加hook的参数，其中group_id和hook_url是必须的
     * @return
     * @throws Exception
     */
    public String modifyGroupHook(String private_token, Integer hook_id, CreateGroupHookParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (hook_id == null || "".equals(hook_id)) {
            return "The hook_id is required.";
        } else if (params.getGroup_id() == null || "".equals(params.getGroup_id())) {
            return "The params's group_id is required.";
        } else if (params.getHook_url() == null || "".equals(params.getHook_url())) {
            return "The params's hook_url is required.";
        } else {
            return groupController.modifyGroupHook(private_token, hook_id, params);
        }
    }

    /**
     * 为群组添加hook
     *
     * @param private_token 用户的private_token
     * @param params        添加hook的参数，其中group_id和hook_url是必须的
     * @return
     * @throws Exception
     */
    public String addGroupHook(String private_token, CreateGroupHookParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (params.getGroup_id() == null || "".equals(params.getGroup_id())) {
            return "The params's group_id is required.";
        } else if (params.getHook_url() == null || "".equals(params.getHook_url())) {
            return "The params's hook_url is required.";
        } else {
            return groupController.addGroupHook(private_token, params);
        }
    }

    /**
     * 查询群组指定的hook
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL路径
     * @param hook_id       指定的hook的ID
     * @return
     * @throws Exception
     */
    public String getGroupHook(String private_token, String group_id, Integer hook_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (hook_id == null || "".equals(hook_id)) {
            return "The hook_id is required.";
        } else {
            return groupController.getGroupHook(private_token, group_id, hook_id);
        }
    }

    /**
     * 查询群组的hooks
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL路径
     * @return
     * @throws Exception
     */
    public String getGroupHooks(String private_token, String group_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else {
            return groupController.getGroupHooks(private_token, group_id);
        }
    }

    /**
     * 搜索群组,关键字的长度至少为连续3位
     * 可以查到用户自己的讨论组以及其他公共讨论组
     *
     * @param private_token 用户的private_token
     * @param key_word      关键字
     * @return
     * @throws Exception
     */
    public String searchGroup(String private_token, String key_word) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (key_word == null || "".equals(key_word)) {
            return "The key_word is required.";
        } else {
            return groupController.searchGroup(private_token, key_word);
        }
    }

    /**
     * 删除群组
     *
     * @param private_token 群组拥有者的或者管理员的private_token
     * @param group_id      要删除的群组的ID或者URL路径
     * @return
     * @throws Exception
     */
    public String updateGroup(String private_token, String group_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null) {
            return "The group_id is required.";
        } else {
            return groupController.delGroup(private_token, group_id);
        }
    }

    /**
     * 修改群组
     *
     * @param private_token 群组拥有者的或者管理员的private_token
     * @param group_id      要修改的群组的ID
     * @param groupParams   群组的信息，其中name和path是必须的
     * @return
     * @throws Exception
     */
    public String updateGroup(String private_token, Integer group_id, CreateGroupParams groupParams) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null) {
            return "The group_id is required.";
        } else {
            return groupController.updateGroup(private_token, group_id, groupParams);
        }
    }

    /**
     * 将一个项目转移到一个群组中
     * Transfer a project to the Group namespace.
     * Available only to instance administrators,
     * although an alternative API endpoint is available which does not require instance administrator access.
     * Transferring projects may fail when tagged packages exist in the project's repository.
     *
     * @param root_private_token 管理员的private_token
     * @param group_id           群组的ID或者URL路径
     * @param project_id         项目的ID或者URL路径
     * @return
     * @throws Exception
     */
    public String createGroup(String root_private_token, String group_id, String project_id) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "The root_private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else {
            return groupController.transferProjectToGroup(root_private_token, group_id, project_id);
        }
    }

    /**
     * 创建群组
     *
     * @param private_token 用户的private_token
     * @param groupParams   群组的信息，其中name和path是必须的
     * @return
     * @throws Exception
     */
    public String createGroup(String private_token, CreateGroupParams groupParams) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (groupParams.getName() == null || "".equals(groupParams.getName())) {
            return "The groupParams's name is required.";
        } else if (groupParams.getPath() == null || "".equals(groupParams.getPath())) {
            return "The groupParams's path is required.";
        } else {
            return groupController.createGroup(private_token, groupParams);
        }
    }

    /**
     * 查找群组的细节信息
     * Get all details of a group.
     * This endpoint can be accessed without authentication if the group is publicly accessible.
     * In case the user that requests is admin of the group, it will return the runners_token for the group too.
     *
     * @param private_token 用户的private_token
     * @param group_id      群组ID或者URL地址
     * @return
     * @throws Exception
     */
    public String getGroupDetails(String private_token, String group_id) throws Exception {
        return getGroupDetails(private_token, group_id, false, false);
    }

    /**
     * 查找群组的细节信息
     * Get all details of a group.
     * This endpoint can be accessed without authentication if the group is publicly accessible.
     * In case the user that requests is admin of the group, it will return the runners_token for the group too.
     *
     * @param private_token          用户的private_token
     * @param group_id               群组ID或者URL地址
     * @param with_custom_attributes 结果是否携带custom_attributes
     * @return
     * @throws Exception
     */
    public String getGroupDetails(String private_token, boolean with_custom_attributes, String group_id) throws Exception {
        return getGroupDetails(private_token, group_id, with_custom_attributes, false);
    }

    /**
     * 查找群组的细节信息
     * Get all details of a group.
     * This endpoint can be accessed without authentication if the group is publicly accessible.
     * In case the user that requests is admin of the group, it will return the runners_token for the group too.
     *
     * @param private_token 用户的private_token
     * @param group_id      群组ID或者URL地址
     * @param with_projects 结果是否携带群组的项目信息
     * @return
     * @throws Exception
     */
    public String getGroupDetails(String private_token, String group_id, boolean with_projects) throws Exception {
        return getGroupDetails(private_token, group_id, false, with_projects);
    }

    /**
     * 查找群组的细节信息
     * Get all details of a group.
     * This endpoint can be accessed without authentication if the group is publicly accessible.
     * In case the user that requests is admin of the group, it will return the runners_token for the group too.
     *
     * @param private_token          用户的private_token
     * @param group_id               群组ID或者URL地址
     * @param with_custom_attributes 结果是否携带custom_attributes
     * @param with_projects          结果是否携带群组的项目信息
     * @return
     * @throws Exception
     */
    public String getGroupDetails(String private_token, String group_id, boolean with_custom_attributes, boolean with_projects) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else {
            return groupController.getGroupDetails(private_token, group_id, with_custom_attributes, with_projects);
        }
    }

    /**
     * 查找群组的项目
     * Get a list of projects in this group.
     * When accessed without authentication, only public projects are returned.
     * By default, this request returns 20 results at a time because the API results are paginated.
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL
     * @param params        查找群组的条件
     * @return
     * @throws Exception
     */
    public String getGroupProjects(String private_token, String group_id, ListGroupProjectParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else {
            return groupController.getGroupProjects(private_token, group_id, params);
        }
    }

    /**
     * 查找一个群组的子群组
     * Get a list of visible direct subgroups in this group.
     * When accessed without authentication, only public groups are returned.
     * By default, this request returns 20 results at a time because the API results are paginated.
     *
     * @param private_token   用户的private_token
     * @param parent_group_id 父群组的ID或者URL
     * @param params          查找群组的条件
     * @return
     * @throws Exception
     */
    public String getSubGroups(String private_token, String parent_group_id, ListGroupsParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (parent_group_id == null || "".equals(parent_group_id)) {
            return "The parent_group_id is required.";
        } else {
            return groupController.getSubGroups(private_token, parent_group_id, params);
        }
    }

    /**
     * 查找所有群组
     * Get a list of visible groups for the authenticated user.
     * When accessed without authentication, only public groups are returned.
     * By default, this request returns 20 results at a time because the API results are paginated.
     *
     * @param private_token 用户的private_token
     * @param params        搜索群组的条件
     * @return
     * @throws Exception
     */
    public String getGroups(String private_token, ListGroupsParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else {
            return groupController.getGroups(private_token, params);
        }
    }


}
