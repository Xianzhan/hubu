package xianzhan.hubu.service.bill.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xianzhan.hubu.base.model.Response;
import xianzhan.hubu.service.bill.api.EchoApi;

import javax.annotation.Resource;

/**
 * Echo 控制器
 *
 * @author xianzhan
 */
@Slf4j
@RequestMapping("/echo")
@RestController
public class EchoController {

    @Resource
    private EchoApi echoApi;

    @GetMapping("/{str}")
    public Response<String> echo(@PathVariable("str") String str) {
        return echoApi.echo(str);
    }
}

