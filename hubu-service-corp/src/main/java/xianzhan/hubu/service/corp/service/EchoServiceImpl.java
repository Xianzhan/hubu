package xianzhan.hubu.service.corp.service;

import org.springframework.stereotype.Service;

/**
 * EchoApi 实现类
 *
 * @author xianzhan
 */
@Service
public class EchoServiceImpl implements IEcho {
    @Override
    public String echo(String str) {
        return str;
    }
}
