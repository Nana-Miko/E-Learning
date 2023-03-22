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

    public static MessageInfoList select(MessageInfoList messages) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = new StringBuffer()
                    .append("SELECT * ")
                    .append("FROM message")
                    .toString();

            stmt = conn.prepareStatement(sql);
            res = stmt.executeQuery();

            while (res.next()) {
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setId(res.getInt("id"));
                messageInfo.setT_id(res.getInt("t_id"));
                messageInfo.setS_id(res.getInt("s_id"));
                messageInfo.setMessage(res.getString("message"));
                messageInfo.setSend(res.getInt("send"));

                messages.add(messageInfo);
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

        for (MessageInfo messageInfo :
                messages) {

            for (Object obj:
                    DataSelect.select(new UserInfoList())) {
                UserInfo userInfo = (UserInfo) obj;
                if (userInfo.getId()== messageInfo.getS_id()){
                    messageInfo.setS_name(userInfo.getName());
                }
                if (userInfo.getId()== messageInfo.getT_id()){
                    messageInfo.setT_name(userInfo.getName());
                }



            }
        }
        return messages;

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

    // 根据学号查询出所选课程信息
    public static SelectStudentCourseList select(int Sid,SelectStudentCourseList sscList){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = new StringBuffer()
                    .append(" select distinct s_id,c_id,C.name,T.name ")
                    .append(" from course_select  s ")
                    .append(" JOIN course C ON s.c_id = C.id ")
                    .append(" JOIN user T ON C.t_id = T.id ")
                    .append( "WHERE s.s_id = ? ")
                    .toString();

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,Sid);
            res = stmt.executeQuery();




            while (res.next()) {

                SelectStudentCourse ssc = new SelectStudentCourse();

                ssc.setcId(res.getInt("c_id")) ;
                ssc.setcName(res.getString("C.name"));
                ssc.settName(res.getString("T.name"));
                sscList.add(ssc);
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
        return sscList;
    }



    public static StudentTaskList select(long time,StudentTaskList studentTasks) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = new StringBuffer()
                    .append("select time,c.name, task.name, A, B, C, D,c_id, `right` ")
                    .append("from task ")
                    .append("JOIN course c on task.c_id = c.id ")
                    .append("where time = ? ")
                    .toString();

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,time);
            res = stmt.executeQuery();

            while (res.next()) {
                StudentTask ST = new StudentTask();
                ST.setTime(res.getLong("time"));
                ST.setcId(res.getInt("task.c_id"));
                ST.setcName(res.getString("c.name"));
                ST.setqName(res.getString("task.name"));
                ST.setqA(res.getString("A"));
                ST.setqB(res.getString("B"));
                ST.setqC(res.getString("C"));
                ST.setqD(res.getString("D"));
                ST.setqRight(res.getString("right"));
                studentTasks.add(ST);

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


        return studentTasks;
    }


    // 查询所有没被选择的课程数据
    public static NotCourseInfoList select(int Sid,NotCourseInfoList noSelect){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = new StringBuffer()
                    .append(" select * ")
                    .append(" from course ")
                    .append(" where id not in ")
                    .append(" (select c_id ")
                    .append(" from course_select ")
                    .append(" where s_id = ? ) ")
                    .toString();

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,Sid);
            res = stmt.executeQuery();




            while (res.next()) {

                NotCourseInfo ssc = new NotCourseInfo();

                ssc.setId(res.getInt("id")); ;
                ssc.setName(res.getString("name"));
                ssc.setT_id(res.getInt("t_id"));
                ssc.setScore(res.getInt("score"));
                noSelect.add(ssc);
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
        return noSelect;
    }

    public static TaskDoInfo select(int sid,long time,TaskDoInfo tdi){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = new StringBuffer()
                    .append(" select * ")
                    .append(" from task_do ")
                    .append(" where time = ? and s_id = ?  ")
                    .toString();

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,time);
            stmt.setLong(2,sid);
            res = stmt.executeQuery();



            res.next();
            tdi.setS_id(res.getInt("s_id"));
            tdi.setS_name(res.getString("s_name"));
            tdi.setC_id(res.getInt("c_id"));
            tdi.setScore(res.getString("score"));
            tdi.setTime(res.getLong("time"));
            tdi.setFinish_time(res.getLong("finish_time"));


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
        return tdi;
    }


}
