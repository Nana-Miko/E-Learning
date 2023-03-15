package Util.useDatabase;

import database.JDBC.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataInsert {
    public static boolean insert(String notic){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        boolean finish = true;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);


            String sql = new StringBuffer()
                    .append("insert into black_board ")
                    .append("(text)")
                    .append("values(?)")
                    .toString();

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,notic);


            stmt.executeUpdate();


            conn.commit();
        } catch (SQLException e) {
            finish = false;
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DBUtil.close(conn, stmt);
        }
        return finish;
    }
}
