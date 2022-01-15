package xianzhan.hubu.service.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * http 请求参数、返回值日志记录
 *
 * @author xianzhan
 * @since 2022-01-15
 */
@Slf4j
@Aspect
public abstract class AbstractHttpLogAop {

    /**
     * 切面，记录 controller 包下的所有公共方法的执行参数和返回结果
     */
    @Pointcut("execution(public * xianzhan.hubu..controller.*.*(..))")
    public void controller() {

    }

    @Around("controller()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        log.info("Http - req: signature: {}, args: {}", signature, Arrays.toString(args));

        Object result = joinPoint.proceed();
        log.info("Http - res: result: {}", result);
        return result;
    }
}
