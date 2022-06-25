package Spring;

public class Client2
{
    private String host;

    public void setHost(String host)
    {
        this.host = host;
    }
    // 빈 객체 초기화시 실행
    public void connect()
    {
        System.out.println("Client2.connect() 실행");
    }

    public void send()
    {
        System.out.println("Client2.send() to " + host);
    }
    // 빈 객체 소멸시 실행
    public void close()
    {
        System.out.println("Client2.close() 실행");
    }
}
