package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Spring 설정 클래스 지정
public class AppContext
{
    @Bean // 이 메소드가 생성한 객체를 스프링이 관리하는 '빈 객체'로 등록
    public Greeter greeter()
    {
        Greeter g = new Greeter();
        g.setFormat("%s, 안녕하세요!");
        return g;
    }

}