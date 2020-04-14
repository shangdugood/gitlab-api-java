import com.css.gitapi.util.controller.MemberController;
import com.css.gitapi.util.enums.UserAccessLevel;
import com.css.gitapi.util.model.AddMemberParams;
import com.css.gitapi.util.model.GetMemberParams;
import com.css.gitapi.util.model.Global;
import org.junit.Test;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/14 9:19
 */
public class TestMember {
    MemberController memberController = new MemberController();

    @Test
    public void testGetProjectMembers() throws Exception {
        System.out.println(memberController.getProjectMembers(Global.regular_private_token, "11", new GetMemberParams()));
    }

    @Test
    public void testGetGroupMembers() throws Exception {
        System.out.println(memberController.getGroupMembers(Global.regular_private_token, "11", new GetMemberParams()));
    }

    @Test
    public void testGetProjectMembersWithInherited() throws Exception {
        System.out.println(memberController.getProjectMembersWithInherited(Global.regular_private_token, "11", new GetMemberParams()));
    }

    @Test
    public void testGetGroupMembersWithInherited() throws Exception {
        System.out.println(memberController.getGroupMembersWithInherited(Global.regular_private_token, "23", new GetMemberParams()));
    }

    @Test
    public void testGetOneGroupMember() throws Exception {
        System.out.println(memberController.getOneGroupMember(Global.root_private_token, "23", "2"));
    }

    @Test
    public void testGetOneProjectMember() throws Exception {
        System.out.println(memberController.getOneProjectMember(Global.root_private_token, "11", "2"));
    }

    @Test
    public void testGetOneGroupMemberWithInherited() throws Exception {
        System.out.println(memberController.getOneGroupMemberWithInherited(Global.root_private_token, "23", "2"));
    }

    @Test
    public void testGetOneProjectMemberWithInherited() throws Exception {
        System.out.println(memberController.getOneProjectMemberWithInherited(Global.root_private_token, "11", "2"));
    }

    @Test
    public void testAddMemberToGroup() throws Exception {
        System.out.println(memberController.addMemberToGroup(Global.root_private_token, new AddMemberParams("23", 24, UserAccessLevel.DEVELOPER)));
    }

    @Test
    public void testAddMemberToProject() throws Exception {
        System.out.println(memberController.addMemberToProject(Global.root_private_token, new AddMemberParams("23", 2, UserAccessLevel.DEVELOPER)));
    }

    @Test
    public void testmodifyMemberToGroup() throws Exception {
        System.out.println(memberController.modifyMemberOfGroup(Global.root_private_token, new AddMemberParams("23", 22, UserAccessLevel.DEVELOPER)));
    }

    @Test
    public void testmodifyMemberToProject() throws Exception {
        System.out.println(memberController.modifyMemberOfProject(Global.root_private_token, new AddMemberParams("23", 2, UserAccessLevel.DEVELOPER)));
    }

    @Test
    public void testDelMemberFromGroup() throws Exception {
        System.out.println(memberController.delMemberfromGroup(Global.root_private_token, "23", 24));
    }

    @Test
    public void testDelMemberFromProject() throws Exception {
        System.out.println(memberController.delMemberfromProject(Global.root_private_token, "23", 22));
    }
}
