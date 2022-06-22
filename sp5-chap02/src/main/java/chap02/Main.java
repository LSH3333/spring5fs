package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{

    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppContext.class);

        // getBean(빈 객체 이름, 빈 객체 타입)
        // greeter 메서드가 생성한 Greeter 객체 리턴
        Greeter g = ctx.getBean("greeter", Greeter.class);
        String msg = g.greet("스프링");
        System.out.println(msg);
        ctx.close();
    }

}