package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// Aspect 로 사용할 클래스
@Aspect
public class ExeTimeAspect
{
    // 공통 기능을 적용할 Pointcut
    @Pointcut("execution(public * chap07..*(..))")
    private void publicTarget() {}

    // 공통 기능을 구현한 메소드에 @Around
    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable
    {
        long start = System.nanoTime();
        try
        {
            Object result = joinPoint.proceed();
            return result;
        }
        finally
        {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
        }
    }

}
