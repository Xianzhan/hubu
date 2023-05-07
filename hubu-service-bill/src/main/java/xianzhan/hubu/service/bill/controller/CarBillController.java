package xianzhan.hubu.service.bill.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xianzhan.hubu.base.pj.model.Response;
import xianzhan.hubu.service.bill.api.CorpApi;
import xianzhan.hubu.service.bill.pj.vo.CarBillPageInVO;
import xianzhan.hubu.service.bill.pj.vo.CarBillPageOutVO;
import xianzhan.hubu.service.bill.service.ICarBillService;
import xianzhan.hubu.service.corp.pj.vo.CorpPage;
import xianzhan.hubu.service.corp.pj.vo.CorpPageOutVO;

import java.util.List;

/**
 * @author xianzhan
 * @since 2022-06-03
 */
@Slf4j
@RestController
@RequestMapping("/car")
public class CarBillController {

    @Autowired
    private CorpApi         corpApi;
    @Autowired
    private ICarBillService carBillService;

    @GetMapping("/page")
    public Response<CarBillPageOutVO> page(CarBillPageInVO inVO) {
        Response<CorpPageOutVO> corpResponse = corpApi.page(1, Integer.MAX_VALUE);
        if (!corpResponse.areOk()) {
            log.error("Car - page: 查询企业错误. err: {}", corpResponse.getMessage());
            return Response.err(corpResponse.getMessage());
        }
        CorpPageOutVO corpData = corpResponse.getData();
        List<String> corpIdList = corpData.getPage().stream()
                .map(CorpPage::getCorpId)
                .toList();
        inVO.setCorpIdList(corpIdList);

        return carBillService.page(inVO);
    }
}
