package spring;

import java.util.Collection;

public class MemberListPrinter
{
    private MemberDao memberDao;
    private MemberPrinter printer;

    public MemberListPrinter(MemberDao memberDao, MemberPrinter memberPrinter)
    {
        this.memberDao = memberDao;
        this.printer = memberPrinter;
    }

    public void PrintAll()
    {
        Collection<Member> members = memberDao.selectAll();
        members.forEach(m -> printer.print(m));
    }
}
