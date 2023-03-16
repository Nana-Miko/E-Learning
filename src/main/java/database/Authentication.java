package database;

import database.JDBC.DBUtil;
import servlet.Role;
import servlet.data.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {

    public String no;
    public String psw;
    public Role role;
    public String name;
    public UserInfo userInfo = new UserInfo();

    public Authentication(String no, String psw) {
        this.no = no;
        this.psw = psw;
        userInfo.setNo(no);
        userInfo.setPsw(psw);
    }

    public boolean insert(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        boolean finish = true;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = new StringBuffer()
                    .append("insert into user ")
                    .append("(no,psw,role,name)")
                    .append("values (?,?,?,?)")
                    .toString();

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, this.no);
            stmt.setString(2,this.psw);
            stmt.setInt(3,this.role.getValue());
            stmt.setString(4,this.getName());


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

    public boolean validating(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        boolean fina = false;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = new StringBuffer()
                    .append("select * ")
                    .append("from user ")
                    .append("where no=?")
                    .toString();

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, this.no);

            res = stmt.executeQuery();

            if (res.next()){
                if (res.getString("psw").equals(this.psw)){
                    fina = true;
                    switch (res.getInt("role")){
                        case 0:this.role = Role.ADMIN;break;
                        case 1:this.role = Role.TEACHER;break;
                        case 2:this.role = Role.STUDENT;break;
                    }
                    this.name = res.getString("name");
                }
                userInfo.setName(res.getString("name"));
                userInfo.setId(res.getInt("id"));
                userInfo.setRole(res.getInt("role"));
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

        return fina;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}
