package servlet.data;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class MessageInfo implements Serializable {
    private int id;
    private int t_id;
    private int s_id;
    private String message;
    private int send;
    private String t_name;
    private String s_name;

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public boolean isSelf(HttpServletRequest request){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        return userInfo.getRoleReal() == send;
    }

    public boolean isMyT_id(HttpServletRequest request){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        return userInfo.getId() == t_id;
    }

    public boolean isMyS_id(HttpServletRequest request){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        return userInfo.getId() == s_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }
}
