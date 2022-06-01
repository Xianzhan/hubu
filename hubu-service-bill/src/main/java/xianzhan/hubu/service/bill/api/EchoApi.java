package xianzhan.hubu.service.bill.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xianzhan.hubu.base.pj.model.Response;
import xianzhan.hubu.service.corp.service.IEcho;

/**
 * Echo 服务客户端
 *
 * @author xianzhan
 * @since 2022-01-22
 */
@FeignClient("hubu-service-corp")
public interface EchoApi extends IEcho {
    /**
     * 将参数返回
     *
     * @param str 参数
     * @return 参数
     */
    @GetMapping("/echo/{str}")
    @Override
    Response<String> echo(@PathVariable("str") String str);
}
