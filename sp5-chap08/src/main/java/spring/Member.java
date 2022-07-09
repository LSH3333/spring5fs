package spring;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Member
{
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime registerDateTime;

    public Member(String email, String password, String name, LocalDateTime regDateTime)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDateTime = regDateTime;
    }

    void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return this.id;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getName()
    {
        return this.name;
    }

    public LocalDateTime getRegisterDateTime()
    {
        return this.registerDateTime;
    }

    // 비밀번호 변경
    public void ChangePassword(String oldPassWord, String newPassWord)
    {
        if(!password.equals(oldPassWord)) // 비밀번호 맞는지 확인
            throw new WrongIdPasswordException();
        this.password = newPassWord; // 맞다면 새로운 비밀번호 할당
    }
    
}