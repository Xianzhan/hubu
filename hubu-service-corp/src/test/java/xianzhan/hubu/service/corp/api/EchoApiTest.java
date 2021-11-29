package xianzhan.hubu.service.corp.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class EchoApiTest {

    @Resource
    private EchoApi echoApi;

    @Test
    public void testEcho() {
        String str = "echo";
        String echo = echoApi.echo(str);
        Assertions.assertEquals(str, echo);
    }
}
