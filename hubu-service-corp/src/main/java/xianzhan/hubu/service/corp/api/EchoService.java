package xianzhan.hubu.service.corp.api;

import org.springframework.stereotype.Service;

/**
 * EchoApi 实现类
 *
 * @author xianzhan
 */
@Service
public class EchoService implements EchoApi {
    @Override
    public String echo(String str) {
        return str;
    }
}
