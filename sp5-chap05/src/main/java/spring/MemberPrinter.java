package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.time.format.DateTimeFormatter;

public class MemberPrinter
{
    private DateTimeFormatter dateTimeFormatter;

    public void print(Member member)
    {
        // DateTimeFormatter 객체가 없어도 printer 메소드는 정상작동한다
        if(dateTimeFormatter == null)
        {
            System.out.printf(
                    "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                    member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
        }
        else
        {
            System.out.printf(
                    "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                    member.getId(), member.getEmail(), member.getName(),
                    dateTimeFormatter.format(member.getRegisterDateTime()));
        }
    }

    // @Autowired 는 자동주입에 필요한 빈 객체가 없으면 익셉션을 발생시킴
    @Autowired
    public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter)
    {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
