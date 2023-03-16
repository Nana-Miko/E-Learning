package Util.useDatabase;

import database.JDBC.DBUtil;
import servlet.data.CourseInfo;
import servlet.data.TaskInfo;

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
    public static boolean insert(CourseInfo courseInfo){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        boolean finish = true;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);


            String sql = new StringBuffer()
                    .append("insert into course ")
                    .append("(name,t_id,score)")
                    .append("values(?,?,?)")
                    .toString();

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,courseInfo.getName());
            stmt.setInt(2,courseInfo.getT_id());
            stmt.setInt(3,courseInfo.getScore());


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

    public static boolean insert(TaskInfo taskInfo){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        boolean finish = true;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);


            String sql = new StringBuffer()
                    .append("insert into task ")
                    .append("(c_id,name,A,B,C,D,`right`,time) ")
                    .append("values(?,?,?,?,?,?,?,?)")
                    .toString();

            stmt = conn.prepareStatement(sql);

            stmt.setInt(1,taskInfo.getC_id());
            stmt.setString(2,taskInfo.getName());
            stmt.setString(3,taskInfo.getA());
            stmt.setString(4,taskInfo.getB());
            stmt.setString(5,taskInfo.getC());
            stmt.setString(6,taskInfo.getD());
            stmt.setString(7,taskInfo.getRight());
            stmt.setLong(8,taskInfo.getTime());


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
