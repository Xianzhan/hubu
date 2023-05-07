package xianzhan.hubu.service.corp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xianzhan.hubu.base.pj.model.Response;
import xianzhan.hubu.service.corp.pj.vo.CorpPageInVO;
import xianzhan.hubu.service.corp.pj.vo.CorpPageOutVO;
import xianzhan.hubu.service.corp.service.ICorpService;

/**
 * @author xianzhan
 * @since 2022-06-03
 */
@Slf4j
@RestController
@RequestMapping("/")
public class CorpController {

    @Autowired
    private ICorpService corpService;

    @GetMapping("/page")
    public Response<CorpPageOutVO> page(CorpPageInVO inVO) {
        return corpService.page(inVO);
    }
}
