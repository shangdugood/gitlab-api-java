/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/30 11:00
 */

import com.css.gitapi.enums.ImpersonationTokenScopes;
import com.css.gitapi.enums.ImpersonationTokenState;
import com.css.gitapi.enums.UserMembershipsType;
import com.css.gitapi.model.CreateUserParams;
import com.css.gitapi.model.Global;
import com.css.gitapi.service.UserService;
import org.junit.Test;

import java.util.ArrayList;


public class TestUsers {
    UserService userService = new UserService();

    @Test
    public void testGetAllUserByAdministrator() throws Exception {
        System.out.println(userService.getAllUsers(Global.root_private_token, null));
    }

    @Test
    public void testGetAllUserByRegularAccount() throws Exception {
        System.out.println(userService.getAllUsers(Global.regular_private_token, null));
    }

    @Test
    public void testAddUser() throws Exception {
        CreateUserParams gitLabUser = new CreateUserParams("测试2", "test2", "test9999@css.com.cn", "12345678");
        gitLabUser.setLinkedin("www.baidu.com");
        System.out.println(userService.addUser(gitLabUser, Global.root_private_token));
    }

    @Test
    public void testDelUser() throws Exception {
        System.out.println(userService.delUserByUserId("20", Global.root_private_token, false));
    }

    @Test
    public void testModUser() throws Exception {
        CreateUserParams gitLabUser = new CreateUserParams("测试66611", "test8888", "test1010@css.com.cn", null);
        gitLabUser.setLinkedin("www.sina.com");
        System.out.println(userService.modifyUserByUserId(gitLabUser, "21", Global.root_private_token));
    }


    @Test
    public void testDeleteAuthenticationIdentityFromUser() throws Exception {
        System.out.println(userService.deleteAuthenticationIdentityFromUser("19", "root", Global.root_private_token));
    }

    @Test
    public void testGetCurrentUsersForNormalUser() throws Exception {
        System.out.println(userService.getCurrentUserForNormalUser(Global.regular_private_token, null));
    }

    @Test
    public void testGetCurrentUsersForAdmin() throws Exception {
        System.out.println(userService.getCurrentUserForAdmin(Global.root_private_token, "24", null));
    }

    @Test
    public void testGetUserStatus() throws Exception {
        System.out.println(userService.getUserStatus(Global.regular_private_token));
    }

    @Test
    public void testGetUserStatusByUserIdOrUsername() throws Exception {
        System.out.println(userService.getUserStatusByUserIdOrUserName("24"));
    }

    @Test
    public void testSetUserStatus() throws Exception {
        System.out.println(userService.setUserStatus(Global.regular_private_token, "100", "hahahahahah"));
    }

    @Test
    public void testGetUserSSHKeys() throws Exception {
        System.out.println(userService.getUserSSHKeys(Global.regular_private_token));
    }

    @Test
    public void testGetUserSSHKeysByUserIdorUsername() throws Exception {
        System.out.println(userService.getUserSSHKeysByUserIdorUsername("shangdu"));
    }

    @Test
    public void testGetUserSSHKeyByKeyId() throws Exception {
        System.out.println(userService.getUserSSHKeyByKeyId(Global.regular_private_token, 1));
    }


    @Test
    public void testAddSSHKey() throws Exception {
        System.out.println(userService.addSSHKey(Global.regular_private_token, "ABC", "34sldkfjsadofjosasdkf"));
    }

    @Test
    public void testAddSSHKeyForUser() throws Exception {
        System.out.println(userService.addSSHKeyForUser(Global.root_private_token, "2", "ABC", "34sldkfjsadofjosasdkf"));
    }

    @Test
    public void testDelSSHKey() throws Exception {
        System.out.println(userService.delSSHKey(Global.regular_private_token, "1"));
    }

    @Test
    public void testDelSSHKeyForUser() throws Exception {
        System.out.println(userService.delSSHKeyForUser(Global.root_private_token, 2, 2));
    }


    @Test
    public void testGetUserGPGKeys() throws Exception {
        System.out.println(userService.getUserGPGKeys(Global.regular_private_token));
    }

    @Test
    public void testGetUserGPGKeysByUserId() throws Exception {
        System.out.println(userService.getUserGPGKeysByUserId("2"));
    }

    @Test
    public void testGetUserGPGKeyByKeyId() throws Exception {
        System.out.println(userService.getUserGPGKeyByKeyId(Global.regular_private_token, "1"));
    }


    @Test
    public void testGetUserGPGKeyByKeyIdForAdmin() throws Exception {
        System.out.println(userService.getUserGPGKeyByKeyIdForAdmin(Global.root_private_token, "2", "2"));
    }

    @Test
    public void testAddGPGKey() throws Exception {
        System.out.println(userService.addGPGKey(Global.regular_private_token, "1111"));
    }

    @Test
    public void testAddGPGKeyForUser() throws Exception {
        System.out.println(userService.addGPGKeyForUser(Global.root_private_token, "2", "11111"));
    }

    @Test
    public void testDelGPGKeyForUser() throws Exception {
        System.out.println(userService.delGPGKeyForUser(Global.root_private_token, "2", "2"));
    }

    @Test
    public void testDelGPGKey() throws Exception {
        System.out.println(userService.delGPGKey(Global.regular_private_token, "2"));
    }

    @Test
    public void testGetEmails() throws Exception {
        System.out.println(userService.getEmails(Global.regular_private_token));
    }

    @Test
    public void testGetOneEmail() throws Exception {
        System.out.println(userService.getOneEmail(Global.root_private_token, "2"));
    }

    @Test
    public void testGetUserEmailsForAdmin() throws Exception {
        System.out.println(userService.getUserEmailsForAdmin(Global.root_private_token, "2"));
    }

    @Test
    public void testAddEmail() throws Exception {
        System.out.println(userService.addEmail(Global.root_private_token, "sd@qq.com"));
    }

    @Test
    public void testAddEmailForUser() throws Exception {
        System.out.println(userService.addEmailForUser(Global.root_private_token, "2", "sd@qq.com", false));
    }

    @Test
    public void testDelEmailForUser() throws Exception {
        System.out.println(userService.delEmailForUser(Global.root_private_token, "2", "2"));
    }

    @Test
    public void testDelEmail() throws Exception {
        System.out.println(userService.delEmail(Global.regular_private_token, "2"));
    }

    @Test
    public void testBlockUser() throws Exception {
        System.out.println(userService.blockUser(Global.root_private_token, 2));
    }

    @Test
    public void testUnblockUser() throws Exception {
        System.out.println(userService.unblockUser(Global.root_private_token, 2));
    }

    @Test
    public void testDeactivateUser() throws Exception {
        System.out.println(userService.deactivateUser(Global.root_private_token, 2));
    }

    @Test
    public void testActiveUser() throws Exception {
        System.out.println(userService.activeUser(Global.root_private_token, 2));
    }

    @Test
    public void testGetUserImpersonationTokens() throws Exception {
        System.out.println(userService.getUserImpersonationTokens(Global.root_private_token, 2, ImpersonationTokenState.ALL));
    }

    @Test
    public void testGetUserOneImpersonationToken() throws Exception {
        System.out.println(userService.getUserOneImpersonationToken(Global.root_private_token, 2, 1));
    }

    @Test
    public void testCreateOneImpersonationToken() throws Exception {
        System.out.println(userService.createOneImpersonationToken(Global.root_private_token, 2, "abcd", new ArrayList<ImpersonationTokenScopes>()));
    }

    @Test
    public void testDelOneImpersonationToken() throws Exception {
        System.out.println(userService.delOneImpersonationToken(Global.root_private_token, 2, 1));
    }

    @Test
    public void testGetUserActivies() throws Exception {
        System.out.println(userService.getUserActivies(Global.root_private_token));
    }

    @Test
    public void testGetUserMemberships() throws Exception {
        System.out.println(userService.getUserMemberships(Global.root_private_token, 2, UserMembershipsType.NAMESPACE));
    }
}
