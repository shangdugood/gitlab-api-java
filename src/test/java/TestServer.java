import com.css.gitapi.util.controller.ServerController;
import org.junit.Test;

import java.io.IOException;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/3/31 9:20
 */
public class TestServer {
    ServerController serverController = new ServerController();

    @Test
    public void testConnect() throws IOException {
        System.out.println(serverController.isConnect());
    }
}
