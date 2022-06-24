package spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordService
{
    @Autowired
    private MemberDao memberDao;

    public void changePassword(String email, String oldPwd, String newPwd)
    {
        Member member = memberDao.selectByEmail(email);
        if(member == null)
            throw new MemberNotFoundException();

        member.ChangePassword(oldPwd, newPwd);

        memberDao.update(member);
    }

    // 의존하는 MemberDao 전달받아 주입
    public void setMemberDao(MemberDao memberDao)
    {
        this.memberDao = memberDao;
    }

}