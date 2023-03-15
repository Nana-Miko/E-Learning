package database;

import database.JDBC.DBUtil;
import servlet.Role;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Authentication {

    public int no;
    public String psw;
    public Role role;
    public String name;

    public Authentication(int no, String psw) {
        this.no = no;
        this.psw = psw;
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

            stmt.setInt(1, this.no);
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

            stmt.setInt(1, this.no);

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
