package com.css.gitapi.util.service;

import com.css.gitapi.util.controller.ProjectController;
import com.css.gitapi.util.enums.UserAccessLevel;
import com.css.gitapi.util.model.*;

import java.util.Date;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/1 9:23
 */
public class ProjectService {
    ProjectController projectController = new ProjectController();


    /**
     * 删除项目的hook
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          项目ID或者URL路径
     * @param hook_id            hook的ID
     * @return 返回成功/失败
     * @throws Exception
     */
    public String delProjectHook(String your_private_token, String projectId, Integer hook_id) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (hook_id == null) {
            return "The hook_id is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.delProjectHook(your_private_token, projectId, hook_id);
        }
    }

    /**
     * 修改项目的hook的信息
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param hook_id            要修改的hook的ID
     * @param params             修改后的hook的信息
     * @return 返回修改后的hook的信息
     * @throws Exception
     */
    public String modifyProjectHook(String your_private_token, Integer hook_id, CreateProjectHookParams params) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (hook_id == null) {
            return "The hook_id is required!";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The params'id is required!";
        } else if (params.getUrl() == null || "".equals(params.getUrl())) {
            return "The params'url is required!";
        } else {
            return projectController.modifyProjectHook(your_private_token, hook_id, params);
        }
    }

    /**
     * 为项目添加hook
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param params             添加hook的参数
     * @return 返回添加后的hook信息
     * @throws Exception
     */
    public String addProjectHook(String your_private_token, CreateProjectHookParams params) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The params'id is required!";
        } else if (params.getUrl() == null || "".equals(params.getUrl())) {
            return "The params'url is required!";
        } else {
            return projectController.addProjectHook(your_private_token, params);
        }
    }

    /**
     * 获得指定项目的指定hook
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          项目的ID或者URL路径
     * @param hook_id            hook的ID
     * @return 返回hook
     * @throws Exception
     */
    public String getProjectHook(String your_private_token, String projectId, Integer hook_id) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (hook_id == null) {
            return "The hook_id is required!";
        } else {
            return projectController.getProjectHook(your_private_token, projectId, hook_id);
        }
    }

    /**
     * 获得指定项目的hooks
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          项目的ID或者URL路径
     * @return 返回hooks集合
     * @throws Exception
     */
    public String getProjectHooks(String your_private_token, String projectId) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.getProjectHooks(your_private_token, projectId);
        }
    }

    /**
     * 停止与群组分享该项目
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          项目的ID或URL路径
     * @param group_id           群组ID
     * @return 返回结果：成功/失败
     * @throws Exception
     */
    public String stopShareProjectWithGroup(String your_private_token, String projectId, Integer group_id) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (group_id == null) {
            return "The group_id is required!";
        } else {
            return projectController.stopShareProjectWithGroup(your_private_token, projectId, group_id);
        }
    }

    /**
     * 将项目与指定的群组共享
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          项目ID或者URL路径
     * @param group_id           群组ID
     * @param group_access       群组在这个项目中的级别
     * @return 返回共享结果
     * @throws Exception
     */
    public String shareProjectWithGroup(String your_private_token, String projectId, Integer group_id, UserAccessLevel group_access) throws Exception {
        return shareProjectWithGroup(your_private_token, projectId, group_id, group_access, null);
    }

    /**
     * 将项目与指定的群组共享
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          项目ID或者URL路径
     * @param group_id           群组ID
     * @param group_access       群组在这个项目中的级别
     * @param expires_at         共享到期时间
     * @return 返回共享结果
     * @throws Exception
     */
    public String shareProjectWithGroup(String your_private_token, String projectId, Integer group_id, UserAccessLevel group_access, Date expires_at) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (group_id == null) {
            return "The group_id is required!";
        } else if (group_access == null) {
            return "The group_access is required!";
        } else {
            return projectController.shareProjectWithGroup(your_private_token, projectId, group_id, group_access, expires_at);
        }
    }

    /**
     * 恢复已删除的项目
     * gitlab v12.6 PREMIUM版本及之后PREMIUM版本，可以使用该方法
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          项目ID
     * @return 项目的详细信息
     * @throws Exception
     */
    public String restoreProject(String your_private_token, String projectId) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.restoreProject(your_private_token, projectId);
        }
    }


    /**
     * 删除项目
     * gitlab v12.6版本之前，该方法会删除项目及与该项目所有有关的关系
     * gitlab v12.6 PREMIUM版本及之后PREMIUM版本，该方法会标记项目为删除，在一定期限内可以恢复，期限可以由管理员设置
     * To change this period:
     * <p>
     * 1、Select the desired option.
     * 2、Click Save changes.
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          项目ID
     * @return 删除成功/失败
     * @throws Exception
     */
    public String delProject(String your_private_token, String projectId) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.delProject(your_private_token, projectId);
        }
    }

    /**
     * 解打包一个项目
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          项目ID或URL路径
     * @return 返回项目的详细信息
     * @throws Exception
     */
    public String unArchiveProject(String your_private_token, String projectId) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.unArchiveProject(your_private_token, projectId);
        }
    }

    /**
     * 打包一个项目
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          项目ID或URL路径
     * @return 返回项目的详细信息
     * @throws Exception
     */
    public String archiveProject(String your_private_token, String projectId) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.archiveProject(your_private_token, projectId);
        }
    }

    /**
     * 获取项目所用语言占比
     *
     * @param projectId          项目ID
     * @param your_private_token 管理员或者项目创建者的private_token
     * @return 返回语言占比的json格式字符串
     * @throws Exception
     */
    public String getProjectLanguagePercentage(String projectId, String your_private_token) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.getLanguagesPercentage(your_private_token, projectId);
        }
    }

    /**
     * 获取指定项目的星标者
     *
     * @param projectId          项目ID或者URL路径
     * @param your_private_token 管理员或者项目创建者的private_token
     * @return 用户信息的集合
     * @throws Exception
     */
    public String getProjectStarrers(String projectId, String your_private_token) throws Exception {
        return projectController.getProjectStarrers(your_private_token, projectId, "");
    }

    /**
     * 获取指定项目的星标者
     *
     * @param projectId          项目ID或者URL路径
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param searchCondition    查询条件，包括用户的名称，用户名，邮箱
     * @return 用户信息的集合
     * @throws Exception
     */
    public String getProjectStarrers(String projectId, String your_private_token, String searchCondition) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.getProjectStarrers(your_private_token, projectId, searchCondition);
        }
    }

    /**
     * 取消星标一个项目
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          需要星标的项目的ID或者URL路径
     * @return 取消星标项目的信息
     * @throws Exception
     */
    public String unStarProject(String your_private_token, String projectId) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.unStarProject(your_private_token, projectId);
        }
    }


    /**
     * 星标一个项目
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param projectId          需要星标的项目的ID或者URL路径
     * @return 星标项目的信息
     * @throws Exception
     */
    public String starProject(String your_private_token, String projectId) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.starProject(your_private_token, projectId);
        }
    }

    /**
     * 获取指定项目的forks
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param project            项目信息（项目ID是必要的）
     * @return 返回项目的fork的项目信息
     * @throws Exception
     */
    public String getProjectForks(String your_private_token, ListForkProjectParams project) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (project.getId() == null || "".equals(project.getId())) {
            return "GitLabForkProParams'id is required!";
        } else {
            return projectController.getProjectForks(your_private_token, project);
        }

    }

    /**
     * Fork一个项目到自己的git空间
     *
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param project            要fork的项目的信息
     * @return 返回fork成功后的项目信息
     * @throws Exception
     */
    public String forkProject(String your_private_token, ForkProjectParams project) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required!";
        } else if (project.getId() == null || "".equals(project.getId())) {
            return "GitLabForkProParams'id is required!";
        } else {
            return projectController.forkProject(your_private_token, project);
        }
    }

    /**
     * 根据项目的ID修改项目信息
     *
     * @param projectId          项目ID或项目URL路径
     * @param project            项目信息
     * @param your_private_token 管理员或者项目创建者的private_token
     * @return 项目修改后的信息
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
     * @param project            创建项目的参数
     * @param your_private_token 管理员的private_token
     * @param userId             指定用户的ID
     * @return 返回项目信息
     * @throws Exception
     */
    public String createProjectForUser(CreateProjectParams project, String your_private_token, String userId) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        } else if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required";
        } else if ((project.getName() == null || "".equals(project.getName())) &&
                (project.getPath() == null || "".equals(project.getPath()))) {
            return "The path and name of the project provide at least one!";
        } else {
            return projectController.createProjectForUser(project, your_private_token, userId);
        }

    }

    /**
     * 获取所有项目
     *
     * @param params             获取项目的参数
     * @param your_private_token 管理员或者项目参与者的private_token
     * @return 项目的信息
     * @throws Exception
     */
    public String getAllProjects(ListProjectParams params, String your_private_token) throws Exception {
        if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required";
        }
        return projectController.getAllProjects(params, your_private_token);
    }

    /**
     * 获取指定用户的项目
     *
     * @param userId             指定用户的ID
     * @param your_private_token 管理员的private_token
     * @param params             关于项目的参数
     * @return 项目的信息
     * @throws Exception
     */
    public String getUserProjects(String userId, String your_private_token, ListProjectParams params) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        } else if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required";
        } else {
            return projectController.getUserProjects(userId, your_private_token, params);
        }
    }

    /**
     * 获取指定用户的星标项目
     *
     * @param userId             指定用户的ID
     * @param your_private_token 管理员的private_token
     * @param params             关于项目的参数
     * @return 项目的集合
     * @throws Exception
     */
    public String getUserStarredProjects(String userId, String your_private_token, ListProjectParams params) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        } else if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required";
        } else {
            return projectController.getUserStarredProjects(userId, your_private_token, params);
        }

    }

    /**
     * 根据项目的ID获取项目的详细信息
     *
     * @param projectId          项目ID或者URL路径
     * @param your_private_token 管理员或者项目参与者的private_token
     * @param params             项目的参数
     * @return 项目的信息
     * @throws Exception
     */
    public String getProjectById(String projectId, String your_private_token, ListSingleProjectParams params) throws Exception {
        if (projectId == null || "".equals(projectId)) {
            return "The projectId is required";
        } else if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required";
        } else {
            return projectController.getProjectById(projectId, your_private_token, params);
        }
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId          项目的ID或者URL路径
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param search             成员的搜索条件，包括name,username
     * @param skip_users_id      不需要查找的用户的ID
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String your_private_token, String search, int[] skip_users_id) throws Exception {
        if (projectId == null || "".equals(projectId)) {
            return "The projectId is required";
        } else if (your_private_token == null || "".equals(your_private_token)) {
            return "Your private_token is required";
        } else {
            return projectController.getProjectUsers(projectId, your_private_token, search, skip_users_id);
        }

    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId          项目的ID或者URL路径
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param search             成员的搜索条件，包括name,username
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String your_private_token, String search) throws Exception {
        return getProjectUsers(projectId, your_private_token, search, new int[0]);
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId          项目的ID或者URL路径
     * @param your_private_token 管理员或者项目创建者的private_token
     * @param skip_users_id      不需要查找的用户的ID
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String your_private_token, int[] skip_users_id) throws Exception {
        return getProjectUsers(projectId, your_private_token, "", skip_users_id);
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId          项目的ID或者URL路径
     * @param your_private_token 管理员或者项目创建者的private_token
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String your_private_token) throws Exception {
        return getProjectUsers(projectId, your_private_token, "", new int[0]);
    }


}
