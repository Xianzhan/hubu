package xianzhan.hubu.service.bill.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xianzhan.hubu.service.bill.pj.entity.CarBillEntity;

import javax.annotation.Resource;

/**
 * https://github.com/apache/shardingsphere/tree/master/examples
 *
 * @author xianzhan
 * @since 2022-05-30
 */
@SpringBootTest
public class CarBillMapperTest {

    @Resource
    private CarBillMapper carBillMapper;

    @Test
    public void testInsert() {
        CarBillEntity bill = new CarBillEntity();
        bill.setCorpId("test");
        bill.setSupplierCode("test");
        bill.setOrderId("test");
        bill.setIsDel(0);

        int insert = carBillMapper.insert(bill);
        Assertions.assertEquals(1, insert);
    }
}
