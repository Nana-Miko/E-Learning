package Util.useDatabase;

import database.JDBC.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataDelete {
    public static boolean delete(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        boolean finish = true;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);


            String sql = new StringBuffer()
                    .append("delete from user ")
                    .append("where id=?")
                    .toString();

            stmt = conn.prepareStatement(sql);

            stmt.setInt(1,id);

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
