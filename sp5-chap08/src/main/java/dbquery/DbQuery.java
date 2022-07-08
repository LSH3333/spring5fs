package dbquery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DbQuery
{
    private DataSource dataSource;

    public DbQuery(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public int count() {
        Connection conn = null;

        try
        {
            conn = dataSource.getConnection(); // 커넥션풀에서 커넥션 갖고오도록 시도
            // try-with-resource 문, try 블록 벗어날시 자동으로 객체 close 실행됨
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("select count(*) from MEMBER"))
            {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            if (conn != null) // 최종적으로 커넥션이 있다면 닫는다
                try
                {
                    conn.close(); // 풀에 반환
                } catch (SQLException e) {}
        }
    }

}