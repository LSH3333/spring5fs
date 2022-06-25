package Config;


import Spring.Client;
import Spring.Client2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppCtx
{
    @Bean
    @Scope("prototype")
    public Client client()
    {
        Client client = new Client();
        client.setHost("host");
        return client;
    }

    @Bean(initMethod = "connect", destroyMethod = "close")
    public Client2 client2()
    {
        Client2 client2 = new Client2();
        client2.setHost("host");
        return client2;
    }


}
