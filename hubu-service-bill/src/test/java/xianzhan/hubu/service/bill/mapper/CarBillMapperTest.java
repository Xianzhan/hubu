package xianzhan.hubu.service.bill.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xianzhan.hubu.service.bill.pj.entity.CarBillEntity;

import javax.annotation.Resource;
import java.util.List;

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
        for (int i = 0; i < 10; i++) {
            CarBillEntity billEntity = new CarBillEntity();
            billEntity.setCorpId("CorpId_" + i);
            billEntity.setSupplierCode("DIDI");
            billEntity.setOrderId(Long.toString(System.currentTimeMillis()));
            billEntity.setIsDel(0);

            int insert = carBillMapper.insert(billEntity);
            Assertions.assertEquals(1, insert);
            System.out.println(billEntity.getId());
        }
    }

    @Test
    public void testSelectById() {
        CarBillEntity bill = carBillMapper.selectById(1531297224031440897L);
        System.out.println(bill);
        Assertions.assertEquals("2330", bill.getOrderId());
    }

    @Test
    public void testSelectList() {
        List<CarBillEntity> billList = carBillMapper.selectList(Wrappers.<CarBillEntity>lambdaQuery()
                .ge(CarBillEntity::getCorpId, "CorpId_5")
        );
        billList.forEach(System.out::println);
    }
}
