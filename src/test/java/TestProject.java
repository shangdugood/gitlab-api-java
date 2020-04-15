import com.css.gitapi.enums.ProListOrderBy;
import com.css.gitapi.enums.SortMethod;
import com.css.gitapi.enums.UserAccessLevel;
import com.css.gitapi.model.*;
import com.css.gitapi.service.ProjectService;
import org.junit.Test;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 16:51
 */
public class TestProject {
    ProjectService service = new ProjectService();

    @Test
    public void testGetAllProjects() throws Exception {
        ListProjectParams params = new ListProjectParams();
        params.setSimple(false);
        System.out.println(service.getAllProjects(params, Global.root_private_token));
    }

    @Test
    public void testGetUserProject() throws Exception {
        ListProjectParams params = new ListProjectParams();
        params.setSimple(false);
        System.out.println(service.getUserProjects("2", Global.root_private_token, params));
    }

    @Test
    public void testGetUserStarredProject() throws Exception {
        ListProjectParams params = new ListProjectParams();
        params.setSimple(false);
        System.out.println(service.getUserStarredProjects("1", Global.root_private_token, params));
    }

    @Test
    public void testGetProById() throws Exception {
        ListSingleProjectParams params = new ListSingleProjectParams();
        System.out.println(service.getProjectById("2", Global.root_private_token, params));
    }

    @Test
    public void testCreateProject() throws Exception {
        CreateProjectParams project = new CreateProjectParams("abcdefg", "");
        if ("".equals(project.getName()) && "".equals(project.getPath())) {
            System.out.println("The path and name of the project provide at least one!");
            return;
        }
        System.out.println(service.createProject(project, Global.root_private_token));
    }

    @Test
    public void testCreateProjectForUser() throws Exception {
        CreateProjectParams project = new CreateProjectParams("test2", "tset2");
        if ("".equals(project.getName()) && "".equals(project.getPath())) {
            System.out.println("The path and name of the project provide at least one!");
            return;
        }
        System.out.println(service.createProjectForUser(project, Global.root_private_token, "2"));
    }

    @Test
    public void testModifyProById() throws Exception {
        CreateProjectParams project = new CreateProjectParams("3333", "3333");
        if ("".equals(project.getName()) && "".equals(project.getPath())) {
            System.out.println("The path and name of the project provide at least one!");
            return;
        }
        System.out.println(service.modifyProjectByProjectId("7", project, Global.regular_private_token));
    }

    @Test
    public void testForkProject() throws Exception {
        ForkProjectParams project = new ForkProjectParams("4");
        if ("".equals(project.getId())) {
            System.out.println("The id is required!");
            return;
        }
        System.out.println(service.forkProject(Global.regular_private_token, project));
    }

    @Test
    public void testListProjectForks() throws Exception {
        ListForkProjectParams project = new ListForkProjectParams("4");
        if ("".equals(project.getId())) {
            System.out.println("The id is required!");
            return;
        }
        System.out.println(service.getProjectForks(Global.regular_private_token, project));
    }

    @Test
    public void testStarProject() throws Exception {
        System.out.println(service.starProject(Global.regular_private_token, "4"));
    }

    @Test
    public void testUnStarProject() throws Exception {
        System.out.println(service.unStarProject(Global.regular_private_token, "4"));
    }

    @Test
    public void testGetProjectStarrers() throws Exception {
        System.out.println(service.getProjectStarrers(Global.regular_private_token, "2", "", null));
    }

    @Test
    public void testGetProjectLanguagePercentages() throws Exception {
        System.out.println(service.getProjectLanguagePercentage(Global.regular_private_token, "2"));
    }

    @Test
    public void testArchiveProject() throws Exception {
        System.out.println(service.archiveProject(Global.regular_private_token, "8"));
    }

    @Test
    public void testUnArchiveProject() throws Exception {
        System.out.println(service.unArchiveProject(Global.regular_private_token, "8"));
    }

    @Test
    public void testDelProjects() throws Exception {
        System.out.println(service.delProject(Global.regular_private_token, "2"));
    }

    @Test
    public void testRestoreProjects() throws Exception {
        System.out.println(service.restoreProject(Global.root_private_token, "2"));
    }

    @Test
    public void testShareProjectWithGroup() throws Exception {
        System.out.println(service.shareProjectWithGroup(Global.root_private_token, "6", 22, UserAccessLevel.DEVELOPER, null));
    }

    @Test
    public void testStopShareProjectWithGroup() throws Exception {
        System.out.println(service.stopShareProjectWithGroup(Global.root_private_token, "6", 22));
    }

    @Test
    public void testGetProjectHooks() throws Exception {
        System.out.println(service.getProjectHooks(Global.root_private_token, "6"));
    }

    @Test
    public void testGetProjectHook() throws Exception {
        System.out.println(service.getProjectHook(Global.regular_private_token, "6", 22));
    }

    @Test
    public void testAddProjectHook() throws Exception {
        System.out.println(service.addProjectHook(Global.regular_private_token, new CreateProjectHookParams("6", "")));
    }

    @Test
    public void testModifyProjectHook() throws Exception {
        System.out.println(service.modifyProjectHook(Global.regular_private_token, 0, new CreateProjectHookParams("6", "")));
    }

    @Test
    public void testDelProjectHook() throws Exception {
        System.out.println(service.delProjectHook(Global.root_private_token, "5", 22));
    }

    @Test
    public void testCreateForkBetweenExistProject() throws Exception {
        System.out.println(service.createForkBetweenExistProjects(Global.regular_private_token, "22", 22));
    }

    @Test
    public void testDelExistFork() throws Exception {
        System.out.println(service.delExistProjectFork(Global.regular_private_token, "22"));
    }

    @Test
    public void testSearchProByName() throws Exception {
        System.out.println(service.searchProjectByName(Global.root_private_token, "efg", ProListOrderBy.CREATED_TIME, SortMethod.ASC));
    }

    @Test
    public void testHouseKeepingProject() throws Exception {
        System.out.println(service.houseKeepingProject(Global.root_private_token, "7"));
    }

    @Test
    public void testGetProjectPushRules() throws Exception {
        System.out.println(service.getPorjectPushRules(Global.root_private_token, "abcdefg"));
    }

    @Test
    public void testAddProjectPushRules() throws Exception {
        AddProjectPushRuleParams project = new AddProjectPushRuleParams("2");
        project.setAuthor_email_regex("@css.com.cn$");
        System.out.println(service.addPorjectPushRules(Global.root_private_token, project));
    }

    @Test
    public void testModProjectPushRules() throws Exception {
        AddProjectPushRuleParams project = new AddProjectPushRuleParams("2");
        project.setAuthor_email_regex("@css.com.cn$");
        System.out.println(service.modPorjectPushRules(Global.root_private_token, project));
    }

    @Test
    public void testDelProjectPushRules() throws Exception {
        System.out.println(service.delPorjectPushRules(Global.root_private_token, "9"));
    }

    @Test
    public void testTranserProjectToNewNamespace() throws Exception {
        System.out.println(service.transerProjectToNewNamespace(Global.root_private_token, "9", "8"));
    }


    @Test
    public void testDownloadSnapshot() throws Exception {
        System.out.println(service.downloadSnapshot(Global.regular_private_token, "7", false));
    }
}

