package Util.useDatabase;

import database.JDBC.DBUtil;
import servlet.admin.data.UserInfo;
import servlet.admin.data.UserInfoList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DataSelect {

    public static UserInfoList select(UserInfoList userinfos) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = new StringBuffer()
                    .append("select * ")
                    .append("from user")
                    .toString();

            stmt = conn.prepareStatement(sql);
            res = stmt.executeQuery();

            while (res.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(res.getInt("id"));
                userInfo.setNo(res.getInt("no"));
                userInfo.setPsw(res.getString("psw"));
                userInfo.setName(res.getString("name"));
                userInfo.setRole(res.getInt("role"));

                userinfos.add(userInfo);

            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DBUtil.close(conn, stmt);
        }
        return userinfos;
    }




}
