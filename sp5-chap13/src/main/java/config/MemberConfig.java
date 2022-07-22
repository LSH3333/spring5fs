package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

// DataSource, 트랜잭션, 서비스 클래스, DAO 클래스 설정 파일 
@Configuration
@EnableTransactionManagement
public class MemberConfig 
{
	@Bean(destroyMethod="close")
	public DataSource dataSource() 
	{
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/spring5fs?characterEncoding=utf8");
		ds.setUsername("spring5");
        ds.setPassword("1234");
        ds.setInitialSize(2); // 커넥션풀을 2개 만들어 놓는다
        ds.setMaxActive(10); // 활성 상태 가능한 최대 커넥션 개수 10
        ds.setMaxIdle(10);
        // 10초 주기로 유휴 커넥션이 유효한지 여부 검사, 최소 유휴 시간 3분으로 지정
        ds.setTestWhileIdle(true); // 유휴 커넥션 검사 true
        ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3); // 최소 유휴 시간 3분 설정
        ds.setTimeBetweenEvictionRunsMillis(10 * 1000); // 10초 주기로
        return ds;
	}
	
	@Bean 
	public PlatformTransactionManager transactionManager() 
	{
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource()); // DataSource를 이용해 트랜잭션 연동에 사용할 DataSource를 지정함.
		return tm;
	}
	
	@Bean 
	public MemberDao memberDao() 
	{
		return new MemberDao(dataSource());
	}
	
	@Bean 
	public MemberRegisterService memberRegSvc() 
	{
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
    public ChangePasswordService changePwdSvc()
    {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao()); // 주입
        return pwdSvc;
    }
}
