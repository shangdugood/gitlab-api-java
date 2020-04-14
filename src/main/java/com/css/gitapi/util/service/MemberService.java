package com.css.gitapi.util.service;

import com.css.gitapi.util.controller.MemberController;
import com.css.gitapi.util.model.AddMemberParams;
import com.css.gitapi.util.model.GetMemberParams;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/14 15:54
 */
public class MemberService {
    MemberController memberController = new MemberController();

    /**
     * 删除群组中的成员
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL路径
     * @param user_id       要删除的成员的ID
     * @return
     * @throws Exception
     */
    public String delMemberFromGroup(String private_token, String group_id, Integer user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (user_id == null) {
            return "The user_id is required.";
        } else {
            return memberController.delMemberfromGroup(private_token, group_id, user_id);
        }
    }

    /**
     * 删除项目中的成员
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL路径
     * @param user_id       要删除的成员的ID
     * @return
     * @throws Exception
     */
    public String delMemberFromProject(String private_token, String project_id, Integer user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else if (user_id == null) {
            return "The user_id is required.";
        } else {
            return memberController.delMemberfromProject(private_token, project_id, user_id);
        }
    }

    /**
     * 修改项目中的成员
     *
     * @param private_token 用户的private_token
     * @param params        修改的成员的参数信息
     * @return
     * @throws Exception
     */
    public String modifyMemberOfGroup(String private_token, AddMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The project_id is required.";
        } else if (params.getUser_id() == null) {
            return "The params'user_id is required.";
        } else if (params.getAccess_level() == null) {
            return "The params'access_level is required.";
        } else {
            return memberController.modifyMemberOfGroup(private_token, params);
        }
    }

    /**
     * 修改项目中的成员
     *
     * @param private_token 用户的private_token
     * @param params        修改的成员的参数信息
     * @return
     * @throws Exception
     */
    public String modifyMemberOfProject(String private_token, AddMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The project_id is required.";
        } else if (params.getUser_id() == null) {
            return "The params'user_id is required.";
        } else if (params.getAccess_level() == null) {
            return "The params'access_level is required.";
        } else {
            return memberController.modifyMemberOfProject(private_token, params);
        }
    }

    /**
     * 向项目中添加成员
     *
     * @param private_token 用户的private_token
     * @param params        添加的成员的参数信息
     * @return
     * @throws Exception
     */
    public String addMemberToProject(String private_token, AddMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The project_id is required.";
        } else if (params.getUser_id() == null) {
            return "The params'user_id is required.";
        } else if (params.getAccess_level() == null) {
            return "The params'access_level is required.";
        } else {
            return memberController.addMemberToProject(private_token, params);
        }
    }

    /**
     * 向群组中添加成员
     *
     * @param private_token 用户的private_token
     * @param params        添加的成员的参数信息
     * @return
     * @throws Exception
     */
    public String addMemberToGroup(String private_token, AddMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The group_id is required.";
        } else if (params.getUser_id() == null) {
            return "The params'user_id is required.";
        } else if (params.getAccess_level() == null) {
            return "The params'access_level is required.";
        } else {
            return memberController.addMemberToGroup(private_token, params);
        }
    }

    /**
     * 查询项目的成员(指定单个成员,包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @param user_id       指定的用户ID
     * @return
     * @throws Exception
     */
    public String getOneProjectMemberWithInherited(String private_token, String project_id, String user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            return memberController.getOneProjectMemberWithInherited(private_token, project_id, user_id);
        }
    }

    /**
     * 查询群组的成员(指定单个成员,包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL
     * @param user_id       指定的用户ID
     * @return
     * @throws Exception
     */
    public String getOneGroupMemberWithInherited(String private_token, String group_id, String user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            return memberController.getOneGroupMemberWithInherited(private_token, group_id, user_id);
        }
    }

    /**
     * 查询项目的成员(指定单个成员)
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @param user_id       指定的用户ID
     * @return
     * @throws Exception
     */
    public String getOneProjectMember(String private_token, String project_id, String user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            return memberController.getOneProjectMember(private_token, project_id, user_id);
        }
    }

    /**
     * 查询群组的成员(指定单个成员)
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL
     * @param user_id       指定的用户ID
     * @return
     * @throws Exception
     */
    public String getOneGroupMember(String private_token, String group_id, String user_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else if (user_id == null || "".equals(user_id)) {
            return "The user_id is required.";
        } else {
            return memberController.getOneGroupMember(private_token, group_id, user_id);
        }
    }

    /**
     * 查询群组的成员(包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param group_id      项目的ID或者URL
     * @return
     * @throws Exception
     */
    public String getGroupMembersWithInherited(String private_token, String group_id) throws Exception {
        return getGroupMembersWithInherited(private_token, group_id, null);
    }

    /**
     * 查询群组的成员(包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param group_id      项目的ID或者URL
     * @param params        获取成员所需的参数
     * @return
     * @throws Exception
     */
    public String getGroupMembersWithInherited(String private_token, String group_id, GetMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else {
            return memberController.getGroupMembersWithInherited(private_token, group_id, params);
        }
    }

    /**
     * 查询项目的成员(包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @return
     * @throws Exception
     */
    public String getProjectMembersWithInherited(String private_token, String project_id) throws Exception {
        return getProjectMembersWithInherited(private_token, project_id, null);
    }

    /**
     * 查询项目的成员(包括Inherited成员)
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @param params        获取成员所需的参数
     * @return
     * @throws Exception
     */
    public String getProjectMembersWithInherited(String private_token, String project_id, GetMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else {
            return memberController.getProjectMembersWithInherited(private_token, project_id, params);
        }
    }

    /**
     * 查询群组的成员
     *
     * @param private_token 用户的private_token
     * @param group_id      项目的ID或者URL
     * @return
     * @throws Exception
     */
    public String getGroupMembers(String private_token, String group_id) throws Exception {
        return getGroupMembers(private_token, group_id, null);
    }

    /**
     * 查询群组的成员
     *
     * @param private_token 用户的private_token
     * @param group_id      群组的ID或者URL
     * @param params        获取成员所需的参数
     * @return
     * @throws Exception
     */
    public String getGroupMembers(String private_token, String group_id, GetMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (group_id == null || "".equals(group_id)) {
            return "The group_id is required.";
        } else {
            return memberController.getGroupMembers(private_token, group_id, params);
        }
    }

    /**
     * 查询项目的成员
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @return
     * @throws Exception
     */
    public String getProjectMembers(String private_token, String project_id) throws Exception {
        return getProjectMembers(private_token, project_id, null);
    }

    /**
     * 查询项目的成员
     *
     * @param private_token 用户的private_token
     * @param project_id    项目的ID或者URL
     * @param params        获取成员所需的参数
     * @return
     * @throws Exception
     */
    public String getProjectMembers(String private_token, String project_id, GetMemberParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "The private_token is required.";
        } else if (project_id == null || "".equals(project_id)) {
            return "The project_id is required.";
        } else {
            return memberController.getProjectMembers(private_token, project_id, params);
        }
    }
}
