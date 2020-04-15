import com.css.gitapi.enums.UserAccessLevel;
import com.css.gitapi.model.AddMemberParams;
import com.css.gitapi.model.GetMemberParams;
import com.css.gitapi.model.Global;
import com.css.gitapi.service.MemberService;
import org.junit.Test;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/14 9:19
 */
public class TestMember {
    MemberService service = new MemberService();

    @Test
    public void testGetProjectMembers() throws Exception {
        System.out.println(service.getProjectMembers(Global.regular_private_token, "11", new GetMemberParams()));
    }

    @Test
    public void testGetGroupMembers() throws Exception {
        System.out.println(service.getGroupMembers(Global.regular_private_token, "11", new GetMemberParams()));
    }

    @Test
    public void testGetProjectMembersWithInherited() throws Exception {
        System.out.println(service.getProjectMembersWithInherited(Global.regular_private_token, "11", new GetMemberParams()));
    }

    @Test
    public void testGetGroupMembersWithInherited() throws Exception {
        System.out.println(service.getGroupMembersWithInherited(Global.regular_private_token, "23", new GetMemberParams()));
    }

    @Test
    public void testGetOneGroupMember() throws Exception {
        System.out.println(service.getOneGroupMember(Global.root_private_token, "23", "2"));
    }

    @Test
    public void testGetOneProjectMember() throws Exception {
        System.out.println(service.getOneProjectMember(Global.root_private_token, "11", "2"));
    }

    @Test
    public void testGetOneGroupMemberWithInherited() throws Exception {
        System.out.println(service.getOneGroupMemberWithInherited(Global.root_private_token, "23", "2"));
    }

    @Test
    public void testGetOneProjectMemberWithInherited() throws Exception {
        System.out.println(service.getOneProjectMemberWithInherited(Global.root_private_token, "11", "2"));
    }

    @Test
    public void testAddMemberToGroup() throws Exception {
        System.out.println(service.addMemberToGroup(Global.root_private_token, new AddMemberParams("23", 24, UserAccessLevel.DEVELOPER)));
    }

    @Test
    public void testAddMemberToProject() throws Exception {
        System.out.println(service.addMemberToProject(Global.root_private_token, new AddMemberParams("23", 2, UserAccessLevel.DEVELOPER)));
    }

    @Test
    public void testmodifyMemberToGroup() throws Exception {
        System.out.println(service.modifyMemberOfGroup(Global.root_private_token, new AddMemberParams("23", 22, UserAccessLevel.DEVELOPER)));
    }

    @Test
    public void testmodifyMemberToProject() throws Exception {
        System.out.println(service.modifyMemberOfProject(Global.root_private_token, new AddMemberParams("23", 2, UserAccessLevel.DEVELOPER)));
    }

    @Test
    public void testDelMemberFromGroup() throws Exception {
        System.out.println(service.delMemberFromGroup(Global.root_private_token, "23", 24));
    }

    @Test
    public void testDelMemberFromProject() throws Exception {
        System.out.println(service.delMemberFromProject(Global.root_private_token, "23", 22));
    }
}
