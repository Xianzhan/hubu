package xianzhan.hubu.service.corp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xianzhan.hubu.base.pj.model.Response;
import xianzhan.hubu.service.corp.service.IEcho;

/**
 * Echo 控制器
 *
 * @author xianzhan
 */
@Slf4j
@RequestMapping("/echo")
@RestController
public class EchoController {

    @Autowired
    private IEcho echo;

    @GetMapping("/{str}")
    public Response<String> echo(@PathVariable("str") String str) {
        return echo.echo(str);
    }
}


