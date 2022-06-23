package config;

import jdk.nashorn.internal.runtime.Version;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import spring.MemberInfoPrinter;

import spring.*;

@Configuration
public class AppCtx
{
    @Bean
    public MemberDao memberDao()
    {
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegSvc()
    {
        return new MemberRegisterService();
    }

    @Bean
    public ChangePasswordService changePwdSvc()
    {
        // ChangePasswordService 객체가 의존하는 MemberDao 객체에 @Autowired 애노테이션
        ChangePasswordService pwdSvc = new ChangePasswordService();
        return pwdSvc;
    }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1()
    {
        return new MemberPrinter();
    }

    @Bean
    public MemberPrinter memberPrinter2()
    {
        return new MemberPrinter();
    }

    @Bean
    public MemberListPrinter listPrinter()
    {
        return new MemberListPrinter();
    }

    @Bean
    public MemberInfoPrinter infoPrinter()
    {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        // @Autowired
//        infoPrinter.setMemberDao(memberDao());
//        infoPrinter.setPrinter(memberPrinter());
        return infoPrinter;
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
