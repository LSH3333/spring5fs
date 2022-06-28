package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Order(1) // 첫번째로 적용
public class CacheAspect
{
    private Map<Long, Object> cache = new HashMap<>();

    @Pointcut("execution(public * chap07..*(long))")
    public void cacheTarget() {}

    // num없으면 추가, 있으면 리턴
    @Around("cacheTarget()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Long num = (Long)joinPoint.getArgs()[0];
        if(cache.containsKey(num))
        {
            System.out.printf("CacheAspect: Cache에서 구함 [%d]\n", num);
            return cache.get(num);
        }

        Object result = joinPoint.proceed(); // 핵심 기능 실행
        cache.put(num, result);
        System.out.printf("CacheAspect: Cache에 추가 [%d]\n", num);
        return result;
    }
}
