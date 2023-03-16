package Util.useDatabase;

import database.JDBC.DBUtil;
import servlet.data.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                userInfo.setNo(res.getString("no"));
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

    public static TaskInfoList select(TaskInfoList taskInfoList) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = new StringBuffer()
                    .append("select * ")
                    .append("from task")
                    .toString();

            stmt = conn.prepareStatement(sql);
            res = stmt.executeQuery();

            while (res.next()) {
                TaskInfo taskInfo = new TaskInfo();
                taskInfo.setC_id(res.getInt("c_id"));
                taskInfo.setName(res.getString("name"));
                taskInfo.setA(res.getString("A"));
                taskInfo.setB(res.getString("B"));
                taskInfo.setC(res.getString("C"));
                taskInfo.setD(res.getString("D"));
                taskInfo.setRight(res.getString("right"));
                taskInfo.setTime(res.getLong("time"));
                taskInfoList.add(taskInfo);

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

        for (TaskInfo taskInfo :
                taskInfoList) {

            conn = null;
            stmt = null;
            res = null;

            try {
                conn = DBUtil.getConnection();
                conn.setAutoCommit(false);

                String sql = new StringBuffer()
                        .append("select * ")
                        .append("from task_do ")
                        .append("where c_id=? AND time=?")
                        .toString();

                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,taskInfo.getC_id());
                stmt.setLong(2,taskInfo.getTime());

                res = stmt.executeQuery();

                while (res.next()) {
                    TaskDoInfo taskDoInfo = new TaskDoInfo();
                    taskDoInfo.setScore(res.getString("score"));
                    taskDoInfo.setC_id(res.getInt("c_id"));
                    taskDoInfo.setS_id(res.getInt("s_id"));
                    taskDoInfo.setTime(res.getLong("time"));
                    taskDoInfo.setS_name(res.getString("s_name"));
                    taskDoInfo.setFinish_time(res.getLong("finish_time"));
                    taskInfo.getTaskDoInfoList().add(taskDoInfo);
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

        }




        return taskInfoList;
    }

    public static CourseInfoList select(CourseInfoList courseInfoList) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = new StringBuffer()
                    .append("select * ")
                    .append("from course")
                    .toString();

            stmt = conn.prepareStatement(sql);
            res = stmt.executeQuery();

            while (res.next()) {
                CourseInfo courseInfo = new CourseInfo();
                courseInfo.setId(res.getInt("id"));
                courseInfo.setT_id(res.getInt("t_id"));
                courseInfo.setName(res.getString("name"));
                courseInfo.setScore(res.getInt("score"));

                courseInfoList.add(courseInfo);

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

        for (Object obj :
                courseInfoList) {
            CourseInfo courseInfo = (CourseInfo) obj;

            conn = null;
            stmt = null;
            res = null;

            try {
                conn = DBUtil.getConnection();
                conn.setAutoCommit(false);

                String sql = new StringBuffer()
                        .append("select Count(*) ")
                        .append("from course_select ")
                        .append("where c_id=?")
                        .toString();

                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,courseInfo.getId());

                res = stmt.executeQuery();

                while (res.next()) {
                    courseInfo.setCount(res.getInt("Count(*)"));
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

        }




        return courseInfoList;
    }

    public static String select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        String notic = "";

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = new StringBuffer()
                    .append("select * ")
                    .append("from black_board ")
                    .append("order by id DESC")
                    .toString();

            stmt = conn.prepareStatement(sql);
            res = stmt.executeQuery();
            res.next();
            notic = res.getString("text");

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
        return notic;
    }





}
