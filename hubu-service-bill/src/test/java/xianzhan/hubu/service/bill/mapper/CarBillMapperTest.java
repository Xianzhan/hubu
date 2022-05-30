package xianzhan.hubu.service.bill.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xianzhan.hubu.service.bill.pj.entity.CarBillEntity;

import javax.annotation.Resource;

/**
 * @author xianzhan
 * @since 2022-05-30
 */
@SpringBootTest
public class CarBillMapperTest {

    @Resource
    private CarBillMapper carBillMapper;

    @Test
    public void testInsert() {
        CarBillEntity billEntity = new CarBillEntity();
        billEntity.setCorpId("test");
        billEntity.setSupplierCode("test");
        billEntity.setOrderId("test");
        billEntity.setIsDel(0);

        int insert = carBillMapper.insert(billEntity);
        Assertions.assertEquals(1, insert);
    }
}
