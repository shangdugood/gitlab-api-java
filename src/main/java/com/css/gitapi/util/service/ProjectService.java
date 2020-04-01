package com.css.gitapi.util.service;

import com.css.gitapi.util.controller.ProjectController;
import com.css.gitapi.util.model.GitLabListProParams;
import com.css.gitapi.util.model.GitLabPro;
import com.css.gitapi.util.model.GitLabSingleProParams;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/1 9:23
 */
public class ProjectService {
    ProjectController projectController = new ProjectController();

    /**
     * 创建项目
     * @param project   创建项目的参数
     * @param creator_private_token 创建者的private_token
     * @return          返回项目信息
     * @throws Exception
     */
    public String createProject(GitLabPro project,String creator_private_token) throws Exception{
        if("".equals(project.getName()) && "".equals(project.getPath())){
            return "The path and name of the project provide at least one!";
        }
        return projectController.createProject(project,creator_private_token);
    }

    /**
     * 为指定用户创建项目
     * @param project   创建项目的参数
     * @param userId    指定用户的ID
     * @return          返回项目信息
     * @throws Exception
     */
    public String createProjectForUser(GitLabPro project,String userId) throws Exception{
        if("".equals(project.getName()) && "".equals(project.getPath())){
            return "The path and name of the project provide at least one!";
        }
        return projectController.createProjectForUser(project,userId);
    }

    /**
     * 获取所有项目
     * @param params    获取项目的参数
     * @return          返回项目的详细信息
     * @throws Exception
     */
    public String getAllProjects(GitLabListProParams params) throws Exception {
        return projectController.getAllProjects(params);
    }

    /**
     * 获取指定用户的项目
     * @param userId    指定用户的ID
     * @param params    关于项目的参数
     * @return
     * @throws Exception
     */
    public String getUserProjects(String userId,GitLabListProParams params) throws Exception{
        return projectController.getUserProjects(userId,params);
    }

    /**
     * 获取指定用户的星标项目
     * @param userId    指定用户的ID
     * @param params    关于项目的参数
     * @return
     * @throws Exception
     */
    public String getUserStarredProjects(String userId,GitLabListProParams params) throws Exception{
        return projectController.getUserStarredProjects(userId,params);
    }

    /**
     * 根据项目的ID获取项目的详细信息
     * @param projectId 项目ID
     * @param params    项目的参数
     * @return
     * @throws Exception
     */
    public String getProjectById(String projectId, GitLabSingleProParams params) throws Exception{
        return projectController.getProjectById(projectId,params);
    }

    /**
     * 获取某个项目的成员
     * @param projectId     项目的ID
     * @param search        成员的搜索条件，包括name,username
     * @param skip_users_id 不需要查找的用户的ID
     * @return
     * @throws Exception
     */
    public String getProjectUsers(String projectId,String search,int[] skip_users_id) throws Exception{
        return projectController.getProjectUsers(projectId,search,skip_users_id);
    }

    /**
     * 获取某个项目的成员
     * @param projectId     项目的ID
     * @param search        成员的搜索条件，包括name,username
     * @return
     * @throws Exception
     */
    public String getProjectUsers(String projectId,String search) throws Exception{
        return projectController.getProjectUsers(projectId,search,new int[0]);
    }

    /**
     * 获取某个项目的成员
     * @param projectId     项目的ID
     * @param skip_users_id 不需要查找的用户的ID
     * @return
     * @throws Exception
     */
    public String getProjectUsers(String projectId,int[] skip_users_id) throws Exception{
        return projectController.getProjectUsers(projectId,"",skip_users_id);
    }

    /**
     * 获取某个项目的成员
     * @param projectId     项目的ID
     * @return
     * @throws Exception
     */
    public String getProjectUsers(String projectId) throws Exception{
        return projectController.getProjectUsers(projectId,"",new int[0]);
    }


}
