package xianzhan.hubu.service.base.manager.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xianzhan.hubu.base.util.IdUtil;
import xianzhan.hubu.base.util.StringUtil;
import xianzhan.hubu.service.base.manager.IIdManager;
import xianzhan.hubu.service.base.manager.IMemoryManager;

@Component
public class IdManager implements IIdManager {

    @Autowired
    private IMemoryManager memoryManager;

    @Override
    public long orderId(String corpId, String employeeId) {
        long incr = memoryManager.incr("hubu:orderId:" + StringUtil.hash(corpId) + ":" + StringUtil.hash(employeeId));
        return IdUtil.orderId(corpId, employeeId, incr);
    }
}
