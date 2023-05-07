package xianzhan.hubu.service.corp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xianzhan.hubu.base.pj.model.Response;
import xianzhan.hubu.service.corp.pj.vo.CorpPageInVO;
import xianzhan.hubu.service.corp.pj.vo.CorpPageOutVO;

/**
 * @author xianzhan
 * @since 2022-06-01
 */
@SpringBootTest
public class ICorpServiceTest {

    @Autowired
    private ICorpService corpService;

    @Test
    public void testPage() {
        CorpPageInVO inVO = new CorpPageInVO();
        inVO.setPageIndex(1);
        inVO.setPageSize(10);

        Response<CorpPageOutVO> response = corpService.page(inVO);
        Assertions.assertTrue(response.areOk());

        CorpPageOutVO data = response.getData();
        data.getPage().forEach(System.out::println);
    }
}
