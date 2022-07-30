package xianzhan.hubu.service.base.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import xianzhan.hubu.service.base.ServiceBaseApplication;

import javax.annotation.Resource;
import java.util.HashSet;

@SpringJUnitConfig
@SpringBootTest(classes = ServiceBaseApplication.class)
public class IIdManagerTest {

    @Resource
    private IIdManager idManager;

    @Test
    public void testOrderId() {
        var set = new HashSet<Long>();
        for (var i = 0; i < 64; i++) {
            long orderId = idManager.orderId("test", "test");
            System.out.println(orderId);
            set.add(orderId);
        }
        System.out.println("size: " + set.size());
    }
}
