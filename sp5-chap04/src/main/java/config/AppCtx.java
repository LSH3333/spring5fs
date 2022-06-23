package config;

import jdk.nashorn.internal.runtime.Version;
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
        return new MemberRegisterService(memberDao()); // DI
    }

    @Bean
    public ChangePasswordService changePwdSvc()
    {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao());
        return pwdSvc;
    }

    @Bean
    public MemberPrinter memberPrinter()
    {
        return new MemberPrinter();
    }

    @Bean
    public MemberListPrinter listPrinter()
    {
        return new MemberListPrinter(memberDao(), memberPrinter());
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
