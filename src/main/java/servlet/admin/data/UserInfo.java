package servlet.admin.data;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private int id;
    private int no;
    private String psw;
    private String name;
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        switch (role){
            case 0:return "管理员";
            case 1:return "老师";
            case 2:return "学生";
        }
        return "";
    }

    public void setRole(int role) {
        this.role = role;
    }
}
