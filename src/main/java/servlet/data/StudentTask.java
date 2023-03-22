package servlet.data;

import java.io.Serializable;


// 考题内容
public class StudentTask implements Serializable {
    private String cName;
    private String qName;
    private String qA;
    private String qB;
    private String qC;
    private String qD;
    private String qRight;
    private int cId;
    private long time;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }



    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getqName() {
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }

    public String getqA() {
        return qA;
    }

    public void setqA(String qA) {
        this.qA = qA;
    }

    public String getqB() {
        return qB;
    }

    public void setqB(String qB) {
        this.qB = qB;
    }

    public String getqC() {
        return qC;
    }

    public void setqC(String qC) {
        this.qC = qC;
    }

    public String getqD() {
        return qD;
    }

    public void setqD(String qD) {
        this.qD = qD;
    }

    public String getqRight() {
        return qRight;
    }

    public void setqRight(String qRight) {
        this.qRight = qRight;
    }
}
