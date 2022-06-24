package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.FilterType;
import spring.MemberInfoPrinter;

import spring.*;

@Configuration
// 스캔 대상 페키지 = spring
@ComponentScan(basePackages = {"spring"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "spring\\..*Dao"))
public class AppCtx
{
    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1()
    {
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2()
    {
        return new MemberSummaryPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter()
    {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

    
}
