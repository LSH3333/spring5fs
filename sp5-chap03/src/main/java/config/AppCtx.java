package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import spring.ChangePasswordService;
import spring.Member;
import spring.MemberDao;
import spring.MemberRegisterService;

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

}
