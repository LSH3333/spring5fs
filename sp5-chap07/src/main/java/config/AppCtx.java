package config;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;

@Configuration
@EnableAspectJAutoProxy
public class AppCtx
{
    @Bean
    public ExeTimeAspect exeTimeAspect()
    {
        return new ExeTimeAspect();
    }

    // ExeTimeAspect 의 measure 메소드가 적용됨
    @Bean
    public Calculator calculator()
    {
        return new RecCalculator();
    }
}
