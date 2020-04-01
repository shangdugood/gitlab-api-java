package com.css.gitapi.util.service;

import com.css.gitapi.util.controller.ProjectController;
import com.css.gitapi.util.model.*;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/1 9:23
 */
public class ProjectService {
    ProjectController projectController = new ProjectController();

    /**
     * 获取指定项目的forks
     * @param your_private_token    你自己的private_token
     * @param project               项目信息（项目ID是必要的）
     * @return                      返回项目的fork的项目信息
     * @throws Exception
     */
    public String getProjectForks(String your_private_token, ListForkProjectParams project) throws Exception{
        if(your_private_token == null || "".equals(your_private_token)){
            return "Your private_token is required!";
        }else if(project.getId() == null || "".equals(project.getId())){
            return "GitLabForkProParams'id is required!";
        }else{
            return projectController.getProjectForks(your_private_token,project);
        }

    }

    /**
     * Fork一个项目到自己的git空间
     * @param your_private_token    自己的private_token
     * @param project               要fork的项目的信息
     * @return                      返回fork成功后的项目信息
     * @throws Exception
     */
    public String forkProject(String your_private_token, ForkProjectParams project) throws Exception{
        if(your_private_token == null || "".equals(your_private_token)){
            return "Your private_token is required!";
        }else if(project.getId() == null || "".equals(project.getId())){
            return "GitLabForkProParams'id is required!";
        }else{
            return projectController.forkProject(your_private_token,project);
        }
    }

    /**
     * 根据项目的ID修改项目信息
     *
     * @param projectId          项目ID
     * @param project            项目信息
     * @param your_private_token private_token
     * @return
     * @throws Exception
     */
    public String modifyProjectByProjectId(String projectId, CreateProjectParams project, String your_private_token) throws Exception {
        if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if ((project.getName() == null || "".equals(project.getName())) &&
                (project.getPath() == null || "".equals(project.getPath()))) {
            return "The path and name of the project provide at least one!";
        } else {
            return projectController.modifyProById(projectId, project, your_private_token);
        }

    }


    /**
     * 创建项目
     *
     * @param project               创建项目的参数
     * @param creator_private_token 创建者的private_token
     * @return 返回项目信息
     * @throws Exception
     */
    public String createProject(CreateProjectParams project, String creator_private_token) throws Exception {
        if (creator_private_token == null || "".equals(creator_private_token)) {
            return "The creator_private_token is required!";
        } else if ((project.getName() == null || "".equals(project.getName())) &&
                (project.getPath() == null || "".equals(project.getPath()))) {
            return "The path and name of the project provide at least one!";
        } else {
            return projectController.createProject(project, creator_private_token);
        }
    }

    /**
     * 为指定用户创建项目
     *
     * @param project 创建项目的参数
     * @param userId  指定用户的ID
     * @return 返回项目信息
     * @throws Exception
     */
    public String createProjectForUser(CreateProjectParams project, String userId) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        } else if ((project.getName() == null || "".equals(project.getName())) &&
                (project.getPath() == null || "".equals(project.getPath()))) {
            return "The path and name of the project provide at least one!";
        } else {
            return projectController.createProjectForUser(project, userId);
        }

    }

    /**
     * 获取所有项目
     *
     * @param params 获取项目的参数
     * @return 返回项目的详细信息
     * @throws Exception
     */
    public String getAllProjects(ListProjectParams params) throws Exception {
        return projectController.getAllProjects(params);
    }

    /**
     * 获取指定用户的项目
     *
     * @param userId 指定用户的ID
     * @param params 关于项目的参数
     * @return
     * @throws Exception
     */
    public String getUserProjects(String userId, ListProjectParams params) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        }else {
            return projectController.getUserProjects(userId, params);
        }
    }

    /**
     * 获取指定用户的星标项目
     *
     * @param userId 指定用户的ID
     * @param params 关于项目的参数
     * @return
     * @throws Exception
     */
    public String getUserStarredProjects(String userId, ListProjectParams params) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        }else {
            return projectController.getUserStarredProjects(userId, params);
        }

    }

    /**
     * 根据项目的ID获取项目的详细信息
     *
     * @param projectId 项目ID
     * @param params    项目的参数
     * @return
     * @throws Exception
     */
    public String getProjectById(String projectId, ListSingleProjectParams params) throws Exception {
        if (projectId == null || "".equals(projectId)) {
            return "The projectId is required";
        }else{
            return projectController.getProjectById(projectId, params);
        }
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId     项目的ID
     * @param search        成员的搜索条件，包括name,username
     * @param skip_users_id 不需要查找的用户的ID
     * @return
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String search, int[] skip_users_id) throws Exception {
        if (projectId == null || "".equals(projectId)) {
            return "The projectId is required";
        }else {
            return projectController.getProjectUsers(projectId, search, skip_users_id);
        }

    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId 项目的ID
     * @param search    成员的搜索条件，包括name,username
     * @return
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String search) throws Exception {
        return getProjectUsers(projectId, search, new int[0]);
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId     项目的ID
     * @param skip_users_id 不需要查找的用户的ID
     * @return
     * @throws Exception
     */
    public String getProjectUsers(String projectId, int[] skip_users_id) throws Exception {
        return getProjectUsers(projectId, "", skip_users_id);
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId 项目的ID
     * @return
     * @throws Exception
     */
    public String getProjectUsers(String projectId) throws Exception {
        return getProjectUsers(projectId, "", new int[0]);
    }


}
