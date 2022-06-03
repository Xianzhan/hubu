package xianzhan.hubu.service.bill.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xianzhan.hubu.base.pj.model.Response;
import xianzhan.hubu.service.corp.pj.vo.CorpPageInVO;
import xianzhan.hubu.service.corp.pj.vo.CorpPageOutVO;

/**
 * 企业服务客户端
 *
 * @author xianzhan
 * @since 2022-06-03
 */
@FeignClient("hubu-service-corp")
public interface CorpApi {

    /**
     * 分页查询企业
     *
     * @return 企业信息
     * @see xianzhan.hubu.service.corp.service.ICorpService#page(CorpPageInVO)
     */
    @GetMapping("/page")
    Response<CorpPageOutVO> page(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);
}
