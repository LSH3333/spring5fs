package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

// Member DB
public class MemberDao
{

    private JdbcTemplate jdbcTemplate;

    // jdbcTemplate 객체는 DataSource를 받아 생성된다
    public MemberDao(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email)
    {
        // List<T> query(String sql, RowMapper<T> rowMapper, Object... args)
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?",
                // 임의 클래스를 이용해 RowMapper 객체 전달
                new RowMapper<Member>()
                {
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException
                    {
                        Member member = new Member(
                                rs.getString("EMAIL"),
                                rs.getString("PASSWORD"),
                                rs.getString("NAME"),
                                rs.getTimestamp("REGDATE").toLocalDateTime());
                        member.setId(rs.getLong("ID"));
                        return member;
                    }
                }
                , email
        );

        return results.isEmpty() ? null : results.get(0);
    }

    // Member Insert
    // update 메소드에 PreparedStatementCreator 전달
    public void insert(final Member member)
    {
        KeyHolder keyHolder = new GeneratedKeyHolder(); // update 메소드에 KeyHolder 객체 전달
        jdbcTemplate.update(new PreparedStatementCreator()
        {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException
            {
                PreparedStatement pstmt = connection.prepareStatement(
                        "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " + "values(?,?,?,?)",
                        new String[]{"ID"} // 자동증가 칼럼 ID
                );
                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
                return pstmt;
            }
        }, keyHolder); // end of update method

        Number keyValue = keyHolder.getKey(); // 자동생성된 키 값을 KeyHolder에 저장
        member.setId(keyValue.longValue());
    }

    public void update(Member member)
    {
        // update(String sql, Object...args)
        jdbcTemplate.update("update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
                member.getName(), member.getPassword(), member.getEmail());
    }

    public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER",
				(ResultSet rs, int rowNum) -> {
					Member member = new Member(
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					member.setId(rs.getLong("ID"));
					return member;
				});
		return results;
	}

    public int count()
    {
        // queryForObject(String sql, Class<T> requiredType)
        // 쿼리 실행 결과가 한 행일때 사용 가능
        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from MEMBER", Integer.class);
        return count;
    }

//    public void queryForObjEx1()
//    {
//        Member member = jdbcTemplate.queryForObject
//                (
//                "select * from MEMBER where ID = ?", // MEMBER 테이블에서 특정 ID인 컬럼 가져옴
//                new RowMapper<Member>()
//                {
//                    @Override
//                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException
//                    {
//                        Member member = new Member(
//                                rs.getString("EMAIL"),
//                                rs.getString("PASSWORD"),
//                                rs.getString("NAME"),
//                                rs.getTimestamp("REGDATE").toLocalDateTime());
//                                member.setId(rs.getLong("ID"));
//                        return member;
//                    }
//                }
//                , 100 // ID = 100인 컬럼
//                );
//    }
//
//    public void preparedStatementCreatorEx()
//    {
//        Member member;
//        jdbcTemplate.update(
//                // update에 PreparedStatementCreator 객체 전달 (임시 클래스)
//                new PreparedStatementCreator()
//                {
//                    @Override
//                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException
//                    {
//                        PreparedStatement pstmt = connection.prepareStatement(
//                                "insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE) values (?,?,?,?)");
//
//                                // 인덱스 파라미터의 값 설정
//                                pstmt.setString(1, member.getEmail());
//                                pstmt.setString(2, member.getPassword());
//                                pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
//                                // 생성한 PreparedStatement 객체 리턴
//                                return pstmt;
//                    }
//                }
//        );
//    }
}