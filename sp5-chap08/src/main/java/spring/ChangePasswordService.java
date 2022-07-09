package spring;


import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public class ChangePasswordService
{
    private MemberDao memberDao;

    // 이 메소드 내 쿼리는 모두 같은 트랜잭션에서 수행된다
    @Transactional
    public void changePassword(String email, String oldPwd, String newPwd)
    {
        // memberDao.selectByEmail(), memberDao.ChangePassword() 는 같은 트랜잭션에서 처리됨
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