package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter
{
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberPrinter printer;

    public void printMemberInfo(String email)
    {
        Member member = memberDao.selectByEmail(email);
        // setter로 의존 객체를 주입 받은 이후에만 정보 출력 가능
        if(member == null)
        {
            System.out.println("데이터 없음\n");
            return;
        }
        printer.print(member);
        System.out.println();
    }

    public void setMemberDao(MemberDao memberDao)
    {
        this.memberDao = memberDao;
    }

    public void setPrinter(MemberPrinter printer)
    {
        this.printer = printer;
    }
}
