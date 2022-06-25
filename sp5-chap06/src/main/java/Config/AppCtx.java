package Config;


import Spring.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx
{
    @Bean
    public Client client()
    {
        Client client = new Client();
        client.setHost("host");
        return client;
    }
}
