package servlet.data;

import java.io.Serializable;

public class TaskInfo implements Serializable {
    private int c_id;
    private String name;
    private String A;
    private String B;
    private String C;
    private String D;
    private String right;
    private long time;
    private TaskDoInfoList taskDoInfoList = new TaskDoInfoList();

    public TaskDoInfoList getTaskDoInfoList() {
        return taskDoInfoList;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
