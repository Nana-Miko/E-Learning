package Util.useDatabase;

import database.JDBC.DBUtil;
import servlet.admin.data.UserInfo;
import servlet.admin.data.UserInfoList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataUpdate {

    public static boolean update(UserInfo userInfo) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        boolean finish = true;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);


            String sql = new StringBuffer()
                    .append("update user ")
                    .append("set no=?,name=?,psw=?,role=? ")
                    .append("where id=?")
                    .toString();

            stmt = conn.prepareStatement(sql);

            stmt.setInt(1,userInfo.getNo());
            stmt.setString(2, userInfo.getName());
            stmt.setString(3, userInfo.getPsw());
            stmt.setInt(4,userInfo.getRoleReal());
            stmt.setInt(5,userInfo.getId());


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
