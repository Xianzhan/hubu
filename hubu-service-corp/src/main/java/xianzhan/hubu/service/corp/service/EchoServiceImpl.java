package xianzhan.hubu.service.corp.service;

import org.springframework.stereotype.Service;
import xianzhan.hubu.base.model.Response;

/**
 * EchoApi 实现类
 *
 * @author xianzhan
 */
@Service
public class EchoServiceImpl implements IEcho {
    @Override
    public Response<String> echo(String str) {
        return Response.ok(str);
    }
}
