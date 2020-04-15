import com.css.gitapi.service.ServerService;
import org.junit.Test;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 9:20
 */
public class TestServer {
    ServerService serverService = new ServerService();

    @Test
    public void testConnect() {
        System.out.println(serverService.isConnect());
    }
}
