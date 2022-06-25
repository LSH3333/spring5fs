package Main;

import Config.AppCtx;
import Spring.Client;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;

public class main
{
    public static void main(String[] args) throws IOException
    {
        AbstractApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppCtx.class);

        Client client = ctx.getBean(Client.class);
        client.send();

        // 없을시 destroy 실행되지 않음
        ctx.close();
    }
}
