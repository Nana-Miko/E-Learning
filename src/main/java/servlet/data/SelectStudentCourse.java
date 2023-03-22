package servlet.data;

import java.io.Serializable;


//  用于显示学生已经选择的课程
public class SelectStudentCourse implements Serializable {

    private int cId;
    private String cName;

    private String tName;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}
