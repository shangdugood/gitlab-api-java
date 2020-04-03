/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/30 11:00
 */

import com.css.gitapi.util.controller.UserController;
import com.css.gitapi.util.enums.ImpersonationTokenScopes;
import com.css.gitapi.util.enums.ImpersonationTokenState;
import com.css.gitapi.util.enums.UserMembershipsType;
import com.css.gitapi.util.model.CreateUserParams;
import com.css.gitapi.util.model.Global;
import org.junit.Test;

import java.util.ArrayList;


public class TestUsers {
    UserController userController = new UserController();

    @Test
    public void testGetAllUserByAdministrator() throws Exception {
        System.out.println(userController.getAllUser(Global.root_private_token));
    }

    @Test
    public void testGetAllUserByRegularAccount() throws Exception {
        System.out.println(userController.getAllUser(Global.regular_private_token));
    }

    @Test
    public void testAddUser() throws Exception {
        CreateUserParams gitLabUser = new CreateUserParams("测试2", "test2", "test9999@css.com.cn", "12345678");
        gitLabUser.setLinkedin("www.baidu.com");
        System.out.println(userController.addUser(gitLabUser, Global.root_private_token));
    }

    @Test
    public void testDelUser() throws Exception {
        System.out.println(userController.delUserById("20", Global.root_private_token, false));
    }

    @Test
    public void testModUser() throws Exception {
        CreateUserParams gitLabUser = new CreateUserParams("测试66611", "test8888", "test1010@css.com.cn", null);
        gitLabUser.setLinkedin("www.sina.com");
        System.out.println(userController.modifyUserById(gitLabUser, "21", Global.root_private_token));
    }


    @Test
    public void testDeleteAuthenticationIdentityFromUser() throws Exception {
        System.out.println(userController.deleteAuthenticationIdentityFromUser("19", "root", Global.root_private_token));
    }

    @Test
    public void testGetCurrentUsersForNormalAccount() throws Exception {
        System.out.println(userController.getCurrentUsersForNormalAccount(Global.regular_private_token));
    }

    @Test
    public void testGetCurrentUsersForAdmin() throws Exception {
        System.out.println(userController.getCurrentUsersForAdmin(Global.root_private_token, "24"));
    }

    @Test
    public void testGetUserStatus() throws Exception {
        System.out.println(userController.getUserStatus(Global.regular_private_token));
    }

    @Test
    public void testGetUserStatusByUserIdOrUsername() throws Exception {
        System.out.println(userController.getUserStatusByUserIdOrUserName("24"));
    }

    @Test
    public void testSetUserStatus() throws Exception {
        System.out.println(userController.setUserStatus(Global.regular_private_token, "100", "hahahahahah"));
    }

    @Test
    public void testGetUserSSHKeys() throws Exception {
        System.out.println(userController.getUserSSHKeys(Global.regular_private_token));
    }

    @Test
    public void testGetUserSSHKeysByUserIdorUsername() throws Exception {
        System.out.println(userController.getUserSSHKeysByUserIdorUsername("shangdu"));
    }

    @Test
    public void testGetUserSSHKeyByKeyId() throws Exception {
        System.out.println(userController.getUserSSHKeyByKeyId(Global.regular_private_token, 1));
    }


    @Test
    public void testAddSSHKey() throws Exception {
        System.out.println(userController.addSSHKey(Global.regular_private_token, "ABC", "34sldkfjsadofjosasdkf"));
    }

    @Test
    public void testAddSSHKeyForUser() throws Exception {
        System.out.println(userController.addSSHKeyForUser(Global.root_private_token, "2", "ABC", "34sldkfjsadofjosasdkf"));
    }

    @Test
    public void testDelSSHKey() throws Exception {
        System.out.println(userController.delSSHKey(Global.regular_private_token, "1"));
    }

    @Test
    public void testDelSSHKeyForUser() throws Exception {
        System.out.println(userController.delSSHKeyForUser(Global.root_private_token, "2", "2"));
    }


    @Test
    public void testGetUserGPGKeys() throws Exception {
        System.out.println(userController.getUserGPGKeys(Global.regular_private_token));
    }

    @Test
    public void testGetUserGPGKeysByUserId() throws Exception {
        System.out.println(userController.getUserGPGKeysByUserId("2"));
    }

    @Test
    public void testGetUserGPGKeyByKeyId() throws Exception {
        System.out.println(userController.getUserGPGKeyByKeyId(Global.regular_private_token, "1"));
    }


    @Test
    public void testGetUserGPGKeyByKeyIdForAdmin() throws Exception {
        System.out.println(userController.getUserGPGKeyByKeyIdForAdmin(Global.root_private_token, "2", "2"));
    }

    @Test
    public void testAddGPGKey() throws Exception {
        System.out.println(userController.addGPGKey(Global.regular_private_token, "1111"));
    }

    @Test
    public void testAddGPGKeyForUser() throws Exception {
        System.out.println(userController.addGPGKeyForUser(Global.root_private_token, "2", "11111"));
    }

    @Test
    public void testDelGPGKeyForUser() throws Exception {
        System.out.println(userController.delGPGKeyForUser(Global.root_private_token, "2", "2"));
    }

    @Test
    public void testDelGPGKey() throws Exception {
        System.out.println(userController.delGPGKey(Global.regular_private_token, "2"));
    }

    @Test
    public void testGetEmails() throws Exception {
        System.out.println(userController.getEmails(Global.regular_private_token));
    }

    @Test
    public void testGetOneEmail() throws Exception {
        System.out.println(userController.getOneEmail(Global.root_private_token, "2"));
    }

    @Test
    public void testGetUserEmailsForAdmin() throws Exception {
        System.out.println(userController.getUserEmailsForAdmin(Global.root_private_token, "2"));
    }

    @Test
    public void testAddEmail() throws Exception {
        System.out.println(userController.addEmail(Global.root_private_token, "sd@qq.com"));
    }

    @Test
    public void testAddEmailForUser() throws Exception {
        System.out.println(userController.addEmailForUser(Global.root_private_token, "2", "sd@qq.com", false));
    }

    @Test
    public void testDelEmailForUser() throws Exception {
        System.out.println(userController.delEmailForUser(Global.root_private_token, "2", "2"));
    }

    @Test
    public void testDelEmail() throws Exception {
        System.out.println(userController.delEmail(Global.regular_private_token, "2"));
    }

    @Test
    public void testBlockUser() throws Exception {
        System.out.println(userController.blockUser(Global.root_private_token, 2));
    }

    @Test
    public void testUnblockUser() throws Exception {
        System.out.println(userController.unblockUser(Global.root_private_token, 2));
    }

    @Test
    public void testDeactivateUser() throws Exception {
        System.out.println(userController.deactivateUser(Global.root_private_token, 2));
    }

    @Test
    public void testActiveUser() throws Exception {
        System.out.println(userController.activeUser(Global.root_private_token, 2));
    }

    @Test
    public void testGetUserImpersonationTokens() throws Exception {
        System.out.println(userController.getUserImpersonationTokens(Global.root_private_token, 2, ImpersonationTokenState.ALL));
    }

    @Test
    public void testGetUserOneImpersonationToken() throws Exception {
        System.out.println(userController.getUserOneImpersonationToken(Global.root_private_token, 2, 1));
    }

    @Test
    public void testCreateOneImpersonationToken() throws Exception {
        System.out.println(userController.createOneImpersonationToken(Global.root_private_token, 2, "abcd", new ArrayList<ImpersonationTokenScopes>(), "2020-05-05"));
    }

    @Test
    public void testDelOneImpersonationToken() throws Exception {
        System.out.println(userController.delOneImpersonationToken(Global.root_private_token, 2, 1));
    }

    @Test
    public void testGetUserActivies() throws Exception {
        System.out.println(userController.getUserActivies(Global.root_private_token, "2020-01-01"));
    }

    @Test
    public void testGetUserMemberships() throws Exception {
        System.out.println(userController.getUserMemberships(Global.root_private_token, 2, UserMembershipsType.NAMESPACE));
    }
}
