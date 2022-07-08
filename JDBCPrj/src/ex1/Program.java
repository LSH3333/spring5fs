package ex1;

import java.sql.*;

public class Program
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:@localhost:1522/xepdb1"; // 포트번호 1522
        String sql = "SELECT * FROM NOTICE"; // NOTICE 테이블에서 모든 컬럼 얻어옴
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, "LSH", "1234");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next())
        {
            int id = rs.getInt("ID");
            String title = rs.getString("TITLE");
            String writerId = rs.getString("WRITER_ID");
            Date regDate = rs.getDate("REGDATE");
            String content = rs.getString("CONTENT");
            int hit = rs.getInt("hit");

            System.out.printf("id : %d, title:%s, writerId:%s, regDate:%s, content:%s, hit:%d\n",
                    id, title, writerId, regDate, content, hit);
        }

        rs.close();
        st.close();
        con.close();
    }

}


