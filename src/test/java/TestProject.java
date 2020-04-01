import com.css.gitapi.util.controller.ProjectController;
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
        System.out.println(projectController.getAllProjects(params));
    }

    @Test
    public void testGetUserProject() throws Exception {
        ListProjectParams params = new ListProjectParams();
        params.setSimple(false);
        System.out.println(projectController.getUserProjects("2",params));
    }

    @Test
    public void testGetUserStarredProject() throws Exception {
        ListProjectParams params = new ListProjectParams();
        params.setSimple(false);
        System.out.println(projectController.getUserStarredProjects("1",params));
    }

    @Test
    public void testGetProById() throws Exception {
        ListSingleProjectParams params = new ListSingleProjectParams();
        System.out.println(projectController.getProjectById("2",params));
    }
    @Test
    public void testCreateProject() throws Exception{
        CreateProjectParams project = new CreateProjectParams("","");
        if("".equals(project.getName()) && "".equals(project.getPath())){
            System.out.println("The path and name of the project provide at least one!");
            return;
        }
        System.out.println(projectController.createProject(project, Global.root_private_token));
    }

    @Test
    public void testCreateProjectForUser() throws Exception{
        CreateProjectParams project = new CreateProjectParams("test2","tset2");
        if("".equals(project.getName()) && "".equals(project.getPath())){
            System.out.println("The path and name of the project provide at least one!");
            return;
        }
        System.out.println(projectController.createProjectForUser(project,"2"));
    }

    @Test
    public void testModifyProById() throws Exception{
        CreateProjectParams project = new CreateProjectParams("3333","3333");
        if("".equals(project.getName()) && "".equals(project.getPath())){
            System.out.println("The path and name of the project provide at least one!");
            return;
        }
        System.out.println(projectController.modifyProById("7",project,Global.regular_private_token));
    }

    @Test
    public void testForkProject() throws Exception{
        ForkProjectParams project = new ForkProjectParams("4");
        if("".equals(project.getId())){
            System.out.println("The id is required!");
            return;
        }
        System.out.println(projectController.forkProject(Global.regular_private_token,project));
    }

    @Test
    public void testListProjectForks() throws Exception{
        ListForkProjectParams project = new ListForkProjectParams("4");
        if("".equals(project.getId())){
            System.out.println("The id is required!");
            return;
        }
        System.out.println(projectController.getProjectForks(Global.regular_private_token,project));
    }
}
