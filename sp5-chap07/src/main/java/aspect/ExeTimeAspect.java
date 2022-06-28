package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

// Aspect 로 사용할 클래스
@Aspect
@Order(2) // 두번째로 적용
public class ExeTimeAspect
{
    // 공통 기능을 적용할 Pointcut
    @Pointcut("execution(public * chap07..*(..))")
    private void publicTarget() {}

    // 공통 기능을 구현한 메소드에 @Around
    // @Pointcut 에 이 메소드를 적용한다
    // 즉 chap07 패키지나 그 하위 패키지에 속한 빈 객체의 public 메서드에 @Around가 붙은 measure 메소드를 적용한다
    // 이 메소드가 실행되는 시점은 factorial() 메소드가 실행될때
    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable
    {
        long start = System.nanoTime(); // 시작 시간
        try
        {
            // ProceedingJoinPoint.proceed() 메소드는 실제 대상 객체의 메소드 호출함
            Object result = joinPoint.proceed(); // RecCalculator.factorial()
            return result;
        }
        finally
        {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    sig.getName(), Arrays.toString(joinPoint.getArgs()),
                    (finish-start));
        }
    }

}
