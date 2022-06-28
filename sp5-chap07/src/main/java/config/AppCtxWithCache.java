package config;

import aspect.CacheAspect;
import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppCtxWithCache
{
    // CacheAspect 먼저
    @Bean
    public CacheAspect cacheAspect() {return new CacheAspect();}
    // ExeAspect 그 다음
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
