package com.css.gitapi.util.service;

import com.css.gitapi.util.controller.ProjectController;
import com.css.gitapi.util.enums.ProListOrderBy;
import com.css.gitapi.util.enums.SortMethod;
import com.css.gitapi.util.enums.UserAccessLevel;
import com.css.gitapi.util.model.*;

import java.util.Date;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020-04-03 11:07:24
 */
public class ProjectService {
    ProjectController projectController = new ProjectController();

    /**
     * 下载一个项目的快照
     *
     * @param root_private_token 管理员的private_token
     * @param projectId          项目的ID
     * @return tar格式的文件流
     * @throws Exception
     */
    public String downloadSnapshot(String root_private_token, String projectId) throws Exception {
        return downloadSnapshot(root_private_token, projectId, false);
    }

    /**
     * 下载一个项目的快照
     *
     * @param root_private_token 管理员的private_token
     * @param projectId          项目的ID
     * @param wiki               是否下载wiki页
     * @return tar格式的文件流
     * @throws Exception
     */
    public String downloadSnapshot(String root_private_token, String projectId, boolean wiki) throws Exception {
        if (root_private_token == null || "".equals(root_private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.downloadSnapshot(root_private_token, projectId, wiki);
        }
    }

    /**
     * 给一个项目设置一个新的命名空间
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID
     * @param namespace     The ID or path of the namespace to transfer to project to
     * @return
     * @throws Exception
     */
    public String transerProjectToNewNamespace(String private_token, String projectId, String namespace) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (namespace == null || "".equals(namespace)) {
            return "The namespace is required!";
        } else {
            return projectController.transferProjectToNewNamespace(private_token, projectId, namespace);
        }
    }

    /**
     * 删除项目的推送规则(GitLab必须为STARTER版本才可以使用)
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或者URL路径
     * @return 返回删除结果
     * @throws Exception
     */
    public String delPorjectPushRules(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.delProjectPushRules(private_token, projectId);
        }
    }

    /**
     * 修改项目的推送规则(GitLab必须为STARTER版本才可以使用)
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param params        推送规则的参数
     * @return 返回推送规则结果
     * @throws Exception
     */
    public String modPorjectPushRules(String private_token, AddProjectPushRuleParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The params'id is required!";
        } else {
            return projectController.modProjectPushRules(private_token, params);
        }
    }

    /**
     * 添加项目的推送规则(GitLab必须为STARTER版本才可以使用)
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param params        推送规则的参数
     * @return 返回推送规则结果
     * @throws Exception
     */
    public String addPorjectPushRules(String private_token, AddProjectPushRuleParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The params'id is required!";
        } else {
            return projectController.addProjectPushRules(private_token, params);
        }
    }

    /**
     * 查找项目的推送规则(GitLab必须为STARTER版本才可以使用)
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID
     * @return 返回推送规则结果
     * @throws Exception
     */
    public String getPorjectPushRules(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.getProjectPushRules(private_token, projectId);
        }
    }

    /**
     * 对一个项目执行housekeeping
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID或者NAMESPACE/PROJECT_NAME
     * @return 返回执行结果
     * @throws Exception
     */
    public String houseKeepingProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.houseKeepingProject(private_token, projectId);
        }
    }

    /**
     * 根据项目名称查找项目，经测试，需要至少三个连续字符匹配。
     * eg:项目名称：abcd，查找条件可以为:abcd,abc,bcd
     * 查找范围：用户参与的项目以及public的项目
     *
     * @param private_token 用户的private_token
     * @param proName       搜索条件，必须
     * @return 返回项目集合
     * @throws Exception
     */
    public String searchProjectByName(String private_token, String proName) throws Exception {
        return searchProjectByName(private_token, proName, null, null);
    }

    /**
     * 根据项目名称查找项目，经测试，需要至少三个连续字符匹配。
     * eg:项目名称：abcd，查找条件可以为:abcd,abc,bcd
     * 查找范围：用户参与的项目以及public的项目
     *
     * @param private_token 用户的private_token
     * @param proName       搜索条件，必须
     * @param order_by      排序条件，非必须
     * @return 返回项目集合
     * @throws Exception
     */
    public String searchProjectByName(String private_token, String proName, ProListOrderBy order_by) throws Exception {
        return searchProjectByName(private_token, proName, order_by, null);
    }

    /**
     * 根据项目名称查找项目，经测试，需要至少三个连续字符匹配。
     * eg:项目名称：abcd，查找条件可以为:abcd,abc,bcd
     * 查找范围：用户参与的项目以及public的项目
     *
     * @param private_token 用户的private_token
     * @param proName       搜索条件，必须
     * @param sort          排序规则，非必须
     * @return 返回项目集合
     * @throws Exception
     */
    public String searchProjectByName(String private_token, String proName, SortMethod sort) throws Exception {
        return searchProjectByName(private_token, proName, null, sort);
    }

    /**
     * 根据项目名称查找项目，经测试，需要至少三个连续字符匹配。
     * eg:项目名称：abcd，查找条件可以为:abcd,abc,bcd
     * 查找范围：用户参与的项目以及public的项目
     *
     * @param private_token 用户的private_token
     * @param proName       搜索条件，必须
     * @param order_by      排序条件，非必须
     * @param sort          排序规则，非必须
     * @return 返回项目集合
     * @throws Exception
     */
    public String searchProjectByName(String private_token, String proName, ProListOrderBy order_by, SortMethod sort) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (proName == null || "".equals(proName)) {
            return "The proName is required!";
        } else {
            return projectController.searchProjectByName(private_token, proName, order_by, sort);
        }
    }

    /**
     * 删除项目的fork
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID或者URL路径
     * @return 返回删除结果
     * @throws Exception
     */
    public String delExistProjectFork(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.delExistFork(private_token, projectId);
        }
    }


    /**
     * 将一个项目fork到另一个项目
     *
     * @param private_token 管理员或者新项目的private_token
     * @param projectId     新项目的ID或者URL路径
     * @param fork_from_id  fork的项目的ID
     * @return 返回新的项目的信息
     * @throws Exception
     */
    public String createForkBetweenExistProjects(String private_token, String projectId, Integer fork_from_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (fork_from_id == null) {
            return "The fork_from_id is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.createForkBetweenExistProject(private_token, projectId, fork_from_id);
        }
    }

    /**
     * 删除项目的hook
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或者URL路径
     * @param hook_id       hook的ID
     * @return 返回成功/失败
     * @throws Exception
     */
    public String delProjectHook(String private_token, String projectId, Integer hook_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (hook_id == null) {
            return "The hook_id is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.delProjectHook(private_token, projectId, hook_id);
        }
    }

    /**
     * 修改项目的hook的信息
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param hook_id       要修改的hook的ID
     * @param params        修改后的hook的信息
     * @return 返回修改后的hook的信息
     * @throws Exception
     */
    public String modifyProjectHook(String private_token, Integer hook_id, CreateProjectHookParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (hook_id == null) {
            return "The hook_id is required!";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The params'id is required!";
        } else if (params.getUrl() == null || "".equals(params.getUrl())) {
            return "The params'url is required!";
        } else {
            return projectController.modifyProjectHook(private_token, hook_id, params);
        }
    }

    /**
     * 为项目添加hook
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param params        添加hook的参数
     * @return 返回添加后的hook信息
     * @throws Exception
     */
    public String addProjectHook(String private_token, CreateProjectHookParams params) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (params.getId() == null || "".equals(params.getId())) {
            return "The params'id is required!";
        } else if (params.getUrl() == null || "".equals(params.getUrl())) {
            return "The params'url is required!";
        } else {
            return projectController.addProjectHook(private_token, params);
        }
    }

    /**
     * 获得指定项目的指定hook
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID或者URL路径
     * @param hook_id       hook的ID
     * @return 返回hook
     * @throws Exception
     */
    public String getProjectHook(String private_token, String projectId, Integer hook_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (hook_id == null) {
            return "The hook_id is required!";
        } else {
            return projectController.getProjectHook(private_token, projectId, hook_id);
        }
    }

    /**
     * 获得指定项目的hooks
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID或者URL路径
     * @return 返回hooks集合
     * @throws Exception
     */
    public String getProjectHooks(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.getProjectHooks(private_token, projectId);
        }
    }

    /**
     * 停止与群组分享该项目
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目的ID或URL路径
     * @param group_id      群组ID
     * @return 返回结果：成功/失败
     * @throws Exception
     */
    public String stopShareProjectWithGroup(String private_token, String projectId, Integer group_id) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (group_id == null) {
            return "The group_id is required!";
        } else {
            return projectController.stopShareProjectWithGroup(private_token, projectId, group_id);
        }
    }

    /**
     * 将项目与指定的群组共享
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或者URL路径
     * @param group_id      群组ID
     * @param group_access  群组在这个项目中的级别
     * @return 返回共享结果
     * @throws Exception
     */
    public String shareProjectWithGroup(String private_token, String projectId, Integer group_id, UserAccessLevel group_access) throws Exception {
        return shareProjectWithGroup(private_token, projectId, group_id, group_access, null);
    }

    /**
     * 将项目与指定的群组共享
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或者URL路径
     * @param group_id      群组ID
     * @param group_access  群组在这个项目中的级别
     * @param expires_at    共享到期时间
     * @return 返回共享结果
     * @throws Exception
     */
    public String shareProjectWithGroup(String private_token, String projectId, Integer group_id, UserAccessLevel group_access, Date expires_at) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (group_id == null) {
            return "The group_id is required!";
        } else if (group_access == null) {
            return "The group_access is required!";
        } else {
            return projectController.shareProjectWithGroup(private_token, projectId, group_id, group_access, expires_at);
        }
    }

    /**
     * 恢复已删除的项目
     * gitlab v12.6 PREMIUM版本及之后PREMIUM版本，可以使用该方法
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID
     * @return 项目的详细信息
     * @throws Exception
     */
    public String restoreProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.restoreProject(private_token, projectId);
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
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID
     * @return 删除成功/失败
     * @throws Exception
     */
    public String delProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.delProject(private_token, projectId);
        }
    }

    /**
     * 解打包一个项目
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或URL路径
     * @return 返回项目的详细信息
     * @throws Exception
     */
    public String unArchiveProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.unArchiveProject(private_token, projectId);
        }
    }

    /**
     * 打包一个项目
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     项目ID或URL路径
     * @return 返回项目的详细信息
     * @throws Exception
     */
    public String archiveProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.archiveProject(private_token, projectId);
        }
    }

    /**
     * 获取项目所用语言占比
     *
     * @param projectId     项目ID
     * @param private_token 管理员或者项目创建者的private_token
     * @return 返回语言占比的json格式字符串
     * @throws Exception
     */
    public String getProjectLanguagePercentage(String projectId, String private_token) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.getLanguagesPercentage(private_token, projectId);
        }
    }

    /**
     * 获取指定项目的星标者
     *
     * @param projectId     项目ID或者URL路径
     * @param private_token 管理员或者项目创建者的private_token
     * @return 用户信息的集合
     * @throws Exception
     */
    public String getProjectStarrers(String projectId, String private_token) throws Exception {
        return projectController.getProjectStarrers(private_token, projectId, "");
    }

    /**
     * 获取指定项目的星标者
     *
     * @param projectId       项目ID或者URL路径
     * @param private_token   管理员或者项目创建者的private_token
     * @param searchCondition 查询条件，包括用户的名称，用户名，邮箱
     * @return 用户信息的集合
     * @throws Exception
     */
    public String getProjectStarrers(String projectId, String private_token, String searchCondition) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.getProjectStarrers(private_token, projectId, searchCondition);
        }
    }

    /**
     * 取消星标一个项目
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     需要星标的项目的ID或者URL路径
     * @return 取消星标项目的信息
     * @throws Exception
     */
    public String unStarProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.unStarProject(private_token, projectId);
        }
    }


    /**
     * 星标一个项目
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param projectId     需要星标的项目的ID或者URL路径
     * @return 星标项目的信息
     * @throws Exception
     */
    public String starProject(String private_token, String projectId) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else {
            return projectController.starProject(private_token, projectId);
        }
    }

    /**
     * 获取指定项目的forks
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param project       项目信息（项目ID是必要的）
     * @return 返回项目的fork的项目信息
     * @throws Exception
     */
    public String getProjectForks(String private_token, ListForkProjectParams project) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (project.getId() == null || "".equals(project.getId())) {
            return "GitLabForkProParams'id is required!";
        } else {
            return projectController.getProjectForks(private_token, project);
        }

    }

    /**
     * Fork一个项目到自己的git空间
     *
     * @param private_token 管理员或者项目创建者的private_token
     * @param project       要fork的项目的信息
     * @return 返回fork成功后的项目信息
     * @throws Exception
     */
    public String forkProject(String private_token, ForkProjectParams project) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if (project.getId() == null || "".equals(project.getId())) {
            return "GitLabForkProParams'id is required!";
        } else {
            return projectController.forkProject(private_token, project);
        }
    }

    /**
     * 根据项目的ID修改项目信息
     *
     * @param projectId     项目ID或项目URL路径
     * @param project       项目信息
     * @param private_token 管理员或者项目创建者的private_token
     * @return 项目修改后的信息
     * @throws Exception
     */
    public String modifyProjectByProjectId(String projectId, CreateProjectParams project, String private_token) throws Exception {
        if (projectId == null || "".equals(projectId)) {
            return "The projectId is required!";
        } else if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required!";
        } else if ((project.getName() == null || "".equals(project.getName())) &&
                (project.getPath() == null || "".equals(project.getPath()))) {
            return "The path and name of the project provide at least one!";
        } else {
            return projectController.modifyProById(projectId, project, private_token);
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
     * @param project       创建项目的参数
     * @param private_token 管理员的private_token
     * @param userId        指定用户的ID
     * @return 返回项目信息
     * @throws Exception
     */
    public String createProjectForUser(CreateProjectParams project, String private_token, String userId) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        } else if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required";
        } else if ((project.getName() == null || "".equals(project.getName())) &&
                (project.getPath() == null || "".equals(project.getPath()))) {
            return "The path and name of the project provide at least one!";
        } else {
            return projectController.createProjectForUser(project, private_token, userId);
        }

    }

    /**
     * 获取所有项目
     *
     * @param params        获取项目的参数
     * @param private_token 管理员或者项目参与者的private_token
     * @return 项目的信息
     * @throws Exception
     */
    public String getAllProjects(ListProjectParams params, String private_token) throws Exception {
        if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required";
        }
        return projectController.getAllProjects(params, private_token);
    }

    /**
     * 获取指定用户的项目
     *
     * @param userId        指定用户的ID
     * @param private_token 管理员的private_token
     * @param params        关于项目的参数
     * @return 项目的信息
     * @throws Exception
     */
    public String getUserProjects(String userId, String private_token, ListProjectParams params) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        } else if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required";
        } else {
            return projectController.getUserProjects(userId, private_token, params);
        }
    }

    /**
     * 获取指定用户的星标项目
     *
     * @param userId        指定用户的ID
     * @param private_token 管理员的private_token
     * @param params        关于项目的参数
     * @return 项目的集合
     * @throws Exception
     */
    public String getUserStarredProjects(String userId, String private_token, ListProjectParams params) throws Exception {
        if (userId == null || "".equals(userId)) {
            return "The userId is required";
        } else if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required";
        } else {
            return projectController.getUserStarredProjects(userId, private_token, params);
        }

    }

    /**
     * 根据项目的ID获取项目的详细信息
     *
     * @param projectId     项目ID或者URL路径
     * @param private_token 管理员或者项目参与者的private_token
     * @param params        项目的参数
     * @return 项目的信息
     * @throws Exception
     */
    public String getProjectById(String projectId, String private_token, ListSingleProjectParams params) throws Exception {
        if (projectId == null || "".equals(projectId)) {
            return "The projectId is required";
        } else if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required";
        } else {
            return projectController.getProjectById(projectId, private_token, params);
        }
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId     项目的ID或者URL路径
     * @param private_token 管理员或者项目创建者的private_token
     * @param search        成员的搜索条件，包括name,username
     * @param skip_users_id 不需要查找的用户的ID
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String private_token, String search, int[] skip_users_id) throws Exception {
        if (projectId == null || "".equals(projectId)) {
            return "The projectId is required";
        } else if (private_token == null || "".equals(private_token)) {
            return "Your private_token is required";
        } else {
            return projectController.getProjectUsers(projectId, private_token, search, skip_users_id);
        }

    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId     项目的ID或者URL路径
     * @param private_token 管理员或者项目创建者的private_token
     * @param search        成员的搜索条件，包括name,username
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String private_token, String search) throws Exception {
        return getProjectUsers(projectId, private_token, search, new int[0]);
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId     项目的ID或者URL路径
     * @param private_token 管理员或者项目创建者的private_token
     * @param skip_users_id 不需要查找的用户的ID
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String private_token, int[] skip_users_id) throws Exception {
        return getProjectUsers(projectId, private_token, "", skip_users_id);
    }

    /**
     * 获取某个项目的成员
     *
     * @param projectId     项目的ID或者URL路径
     * @param private_token 管理员或者项目创建者的private_token
     * @return 用户的集合
     * @throws Exception
     */
    public String getProjectUsers(String projectId, String private_token) throws Exception {
        return getProjectUsers(projectId, private_token, "", new int[0]);
    }


}
