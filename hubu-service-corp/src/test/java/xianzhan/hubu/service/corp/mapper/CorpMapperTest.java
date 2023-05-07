package xianzhan.hubu.service.corp.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xianzhan.hubu.base.constant.HubuConst;
import xianzhan.hubu.service.corp.pj.entity.CorpEntity;

/**
 * @author xianzhan
 * @since 2022-06-01
 */
@SpringBootTest
public class CorpMapperTest {

    @Autowired
    private CorpMapper corpMapper;

    @Test
    public void testInsert() {
        CorpEntity corp = new CorpEntity();
        corp.setCorpId("testInsert");
        corp.setCorpName("测试");
        corp.setIsDel(HubuConst.IS_DEL_N);

        int insert = corpMapper.insert(corp);
        Assertions.assertEquals(1, insert);
    }
}
