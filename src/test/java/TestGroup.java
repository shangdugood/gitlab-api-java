import com.css.gitapi.util.controller.GroupController;
import com.css.gitapi.util.enums.SortMethod;
import com.css.gitapi.util.model.*;
import org.junit.Test;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/9 9:47
 */
public class TestGroup {
    GroupController groupController = new GroupController();

    Pagination pagination = new Pagination();

    @Test
    public void testGetGroups() throws Exception {
        ListGroupsParams params = new ListGroupsParams();
        params.setSort(SortMethod.ASC);
        System.out.println(groupController.getGroups(Global.root_private_token, params));
    }

    @Test
    public void testGetSubGroups() throws Exception {
        ListGroupsParams params = new ListGroupsParams();
        params.setSort(SortMethod.ASC);
        System.out.println(groupController.getSubGroups(Global.root_private_token, "26", params));
    }

    @Test
    public void testGetGroupProjects() throws Exception {
        ListGroupProjectParams params = new ListGroupProjectParams();
        params.setSimple(true);
        System.out.println(groupController.getGroupProjects(Global.root_private_token, "28", params));
    }

    @Test
    public void testGetGroupDetails() throws Exception {
        System.out.println(groupController.getGroupDetails(Global.root_private_token, "28", true, true));
    }

    @Test
    public void testCreateGroup() throws Exception {
        CreateGroupParams params = new CreateGroupParams("testCreate2", "testCreate2");
        params.setEmails_disabled(false);
        System.out.println(groupController.createGroup(Global.root_private_token, params));
    }

    @Test
    public void testTransferProToGroup() throws Exception {
        System.out.println(groupController.transferProjectToGroup(Global.root_private_token, "23", "7"));
    }

    @Test
    public void testUpdateGroup() throws Exception {
        CreateGroupParams params = new CreateGroupParams("testCreate2", "testCreate2");
        params.setEmails_disabled(false);
        System.out.println(groupController.updateGroup(Global.root_private_token, 30, params));
    }

    @Test
    public void testDeleteGroup() throws Exception {
        System.out.println(groupController.delGroup(Global.root_private_token, "30"));
    }

    @Test
    public void testSearchGroup() throws Exception {
        System.out.println(groupController.searchGroup(Global.root_private_token, "te"));
    }

    @Test
    public void testGetGroupHooks() throws Exception {
        System.out.println(groupController.getGroupHooks(Global.root_private_token, "32"));
    }

    @Test
    public void testGetGroupHook() throws Exception {
        System.out.println(groupController.getGroupHook(Global.root_private_token, "32", 1));
    }

}


