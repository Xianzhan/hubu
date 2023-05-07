package xianzhan.hubu.service.corp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xianzhan.hubu.base.pj.model.Response;

@SpringBootTest
public class IEchoTest {

    @Autowired
    private IEcho echo;

    @Test
    public void testEcho() {
        String str = "echo";
        Response<String> ret = echo.echo(str);
        Assertions.assertTrue(ret.areOk());
        Assertions.assertEquals(str, ret.getData());
    }
}
