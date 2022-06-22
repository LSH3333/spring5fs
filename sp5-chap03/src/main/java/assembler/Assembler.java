package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler
{
    private MemberDao memberDao;
    private MemberRegisterService regSvc;
    private ChangePasswordService pwdSvc;

    public Assembler()
    {
        memberDao = new MemberDao();
        regSvc = new MemberRegisterService(memberDao); // 생성자를 통한 객체 주입
        pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);
    }

    public MemberDao getMemberDao() { return this.memberDao; }
    public MemberRegisterService getMemberRegisterService() { return this.regSvc; }
    public ChangePasswordService getChangePasswordService() { return this.pwdSvc; }
}
