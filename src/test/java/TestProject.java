import com.css.gitapi.util.controller.ProjectController;
import com.css.gitapi.util.enums.UserAccessLevel;
import com.css.gitapi.util.model.*;
import org.junit.Test;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 16:51
 */
public class TestProject {
    ProjectController projectController = new ProjectController();

    @Test
    public void testGetAllProjects() throws Exception {
        ListProjectParams params = new ListProjectParams();
        params.setSimple(false);
        System.out.println(projectController.getAllProjects(params, Global.root_private_token));
    }

    @Test
    public void testGetUserProject() throws Exception {
        ListProjectParams params = new ListProjectParams();
        params.setSimple(false);
        System.out.println(projectController.getUserProjects("2", Global.root_private_token, params));
    }

    @Test
    public void testGetUserStarredProject() throws Exception {
        ListProjectParams params = new ListProjectParams();
        params.setSimple(false);
        System.out.println(projectController.getUserStarredProjects("1", Global.root_private_token, params));
    }

    @Test
    public void testGetProById() throws Exception {
        ListSingleProjectParams params = new ListSingleProjectParams();
        System.out.println(projectController.getProjectById("2", Global.root_private_token, params));
    }

    @Test
    public void testCreateProject() throws Exception {
        CreateProjectParams project = new CreateProjectParams("", "");
        if ("".equals(project.getName()) && "".equals(project.getPath())) {
            System.out.println("The path and name of the project provide at least one!");
            return;
        }
        System.out.println(projectController.createProject(project, Global.root_private_token));
    }

    @Test
    public void testCreateProjectForUser() throws Exception {
        CreateProjectParams project = new CreateProjectParams("test2", "tset2");
        if ("".equals(project.getName()) && "".equals(project.getPath())) {
            System.out.println("The path and name of the project provide at least one!");
            return;
        }
        System.out.println(projectController.createProjectForUser(project, Global.root_private_token, "2"));
    }

    @Test
    public void testModifyProById() throws Exception {
        CreateProjectParams project = new CreateProjectParams("3333", "3333");
        if ("".equals(project.getName()) && "".equals(project.getPath())) {
            System.out.println("The path and name of the project provide at least one!");
            return;
        }
        System.out.println(projectController.modifyProById("7", project, Global.regular_private_token));
    }

    @Test
    public void testForkProject() throws Exception {
        ForkProjectParams project = new ForkProjectParams("4");
        if ("".equals(project.getId())) {
            System.out.println("The id is required!");
            return;
        }
        System.out.println(projectController.forkProject(Global.regular_private_token, project));
    }

    @Test
    public void testListProjectForks() throws Exception {
        ListForkProjectParams project = new ListForkProjectParams("4");
        if ("".equals(project.getId())) {
            System.out.println("The id is required!");
            return;
        }
        System.out.println(projectController.getProjectForks(Global.regular_private_token, project));
    }

    @Test
    public void testStarProject() throws Exception {
        System.out.println(projectController.starProject(Global.regular_private_token, "4"));
    }

    @Test
    public void testUnStarProject() throws Exception {
        System.out.println(projectController.unStarProject(Global.regular_private_token, "4"));
    }

    @Test
    public void testGetProjectStarrers() throws Exception {
        System.out.println(projectController.getProjectStarrers(Global.regular_private_token, "2", ""));
    }

    @Test
    public void testGetProjectLanguagePercentages() throws Exception {
        System.out.println(projectController.getLanguagesPercentage(Global.regular_private_token, "2"));
    }

    @Test
    public void testArchiveProject() throws Exception {
        System.out.println(projectController.archiveProject(Global.regular_private_token, "8"));
    }

    @Test
    public void testUnArchiveProject() throws Exception {
        System.out.println(projectController.unArchiveProject(Global.regular_private_token, "8"));
    }

    @Test
    public void testDelProjects() throws Exception {
        System.out.println(projectController.delProject(Global.regular_private_token, "2"));
    }

    @Test
    public void testRestoreProjects() throws Exception {
        System.out.println(projectController.restoreProject(Global.root_private_token, "2"));
    }

    /**
     * 未测试通过
     * {"error":"file is invalid"}
     *
     * @throws Exception
     */
    @Test
    public void testUploadFileToProject() throws Exception {
        System.out.println(projectController.uploadFileToProject(Global.root_private_token, "6", "D:\\1.png"));
    }

    @Test
    public void testShareProjectWithGroup() throws Exception {
        System.out.println(projectController.shareProjectWithGroup(Global.root_private_token, "6", 22, UserAccessLevel.DEVELOPER, null));
    }

    @Test
    public void testStopShareProjectWithGroup() throws Exception {
        System.out.println(projectController.stopShareProjectWithGroup(Global.root_private_token, "6", 22));
    }

    @Test
    public void testGetProjectHooks() throws Exception {
        System.out.println(projectController.getProjectHooks(Global.root_private_token, "6"));
    }

    @Test
    public void testGetProjectHook() throws Exception {
        System.out.println(projectController.getProjectHook(Global.regular_private_token, "6", 22));
    }

    @Test
    public void testAddProjectHook() throws Exception {
        System.out.println(projectController.addProjectHook(Global.regular_private_token, new CreateProjectHookParams("6", "")));
    }

    @Test
    public void testModifyProjectHook() throws Exception {
        System.out.println(projectController.modifyProjectHook(Global.regular_private_token, 0, new CreateProjectHookParams("6", "")));
    }

    @Test
    public void testDelProjectHook() throws Exception {
        System.out.println(projectController.delProjectHook(Global.root_private_token, "5", 22));
    }
}
