import com.css.gitapi.util.controller.ProjectController;
import com.css.gitapi.util.model.GitLabListProParams;
import com.css.gitapi.util.model.GitLabPro;
import com.css.gitapi.util.model.GitLabSingleProParams;
import com.css.gitapi.util.model.Global;
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
        GitLabListProParams params = new GitLabListProParams();
        params.setSimple(false);
        System.out.println(projectController.getAllProjects(params));
    }

    @Test
    public void testGetUserProject() throws Exception {
        GitLabListProParams params = new GitLabListProParams();
        params.setSimple(false);
        System.out.println(projectController.getUserProjects("2",params));
    }

    @Test
    public void testGetUserStarredProject() throws Exception {
        GitLabListProParams params = new GitLabListProParams();
        params.setSimple(false);
        System.out.println(projectController.getUserStarredProjects("1",params));
    }

    @Test
    public void testGetProById() throws Exception {
        GitLabSingleProParams params = new GitLabSingleProParams();
        System.out.println(projectController.getProjectById("2",params));
    }
    @Test
    public void testCreateProject() throws Exception{
        GitLabPro project = new GitLabPro("","");
        if("".equals(project.getName()) && "".equals(project.getPath())){
            System.out.println("The path and name of the project provide at least one!");
            return;
        }
        System.out.println(projectController.createProject(project, Global.root_private_token));
    }

}
