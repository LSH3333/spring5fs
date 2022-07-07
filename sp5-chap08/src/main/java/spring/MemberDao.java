package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Member DB
public class MemberDao
{
    private static long nextId = 0;
    private Map<String, Member> map = new HashMap<>();

    private JdbcTemplate jdbcTemplate;

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

    public void insert(Member member)
    {

    }

    public void update(Member member)
    {

    }

    public Collection<Member> selectAll()
    {
        return null;
    }
}