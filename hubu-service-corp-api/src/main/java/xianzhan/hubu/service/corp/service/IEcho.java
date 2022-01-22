package xianzhan.hubu.service.corp.service;

import xianzhan.hubu.base.model.Response;

/**
 * 响应服务
 *
 * @author xianzhan
 */
public interface IEcho {

    /**
     * 将参数返回
     *
     * @param str 参数
     * @return 参数
     */
     Response<String> echo(String str);
}
