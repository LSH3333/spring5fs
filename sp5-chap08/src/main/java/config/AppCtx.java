package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.MemberDao;

@Configuration
public class AppCtx
{
    @Bean(destroyMethod = "close") // close 메소드는 커넥션 풀에 보관된 Connection을 닫는다
    public DataSource dataSource()
    {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver"); // Driver 클래스 지정
        ds.setUrl("jdbc:mysql://localhost:3306/spring5fs?characterEncoding=utf8");
        ds.setUsername("spring5");
        ds.setPassword("spring5");
        ds.setInitialSize(2); // 커넥션풀을 2개 만들어 놓는다
        ds.setMaxActive(10); // 활성 상태 가능한 최대 커넥션 개수 10
        // 10초 주기로 유휴 커넥션이 유효한지 여부 검사, 최소 유휴 시간 3분으로 지정
        ds.setTestWhileIdle(true); // 유휴 커넥션 검사 true
        ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3); // 최소 유휴 시간 3분 설정
        ds.setTimeBetweenEvictionRunsMillis(10 * 1000); // 10초 주기로
        return ds;
    }

    @Bean
    public MemberDao memberDao()
    {
        // dataSource 빈 객체를 받는다
        return new MemberDao(dataSource());
    }
}
