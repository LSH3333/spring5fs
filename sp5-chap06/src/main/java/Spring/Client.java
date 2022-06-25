package Spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// Bean 객체의 생성,소멸
public class Client implements InitializingBean, DisposableBean
{
    private String host;

    public void setHost(String host)
    {
        this.host = host;
    }

    // InitializingBean 인터페이스에 존재, 빈 초기화시 실행
    @Override
    public void afterPropertiesSet() throws Exception
    {
        System.out.println("Client.afterPropertiesSet() 실행");
    }

    public void send()
    {
        System.out.println("Client.send() to " + host);
    }

    @Override
    public void destroy() throws Exception
    {
        System.out.println("Client.destroy() 실행");
    }

}
