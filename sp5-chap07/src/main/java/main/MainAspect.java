package main;

import chap07.Calculator;
import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppCtx.class);

        // cal은 Calculator 객체가 아닌 Spring에서 만든 $Proxy 객체
        // $Proxy는 Calculator 인터페이스를 상속받음
        Calculator cal = ctx.getBean("calculator", Calculator.class);
        long fiveFact = cal.factorial(5);
        System.out.println("cal.factorial(5) = " + fiveFact);
        System.out.println(cal.getClass().getName());

        ctx.close();

    }
}
