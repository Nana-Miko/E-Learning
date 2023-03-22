package servlet.data;

import java.util.ArrayList;
import java.util.List;


// 存储每次测试的所有题目
public class StudentTaskList extends ArrayList<StudentTask> {
    public StudentTaskList() {
    }


    public boolean add(StudentTask o) {
        return super.add(o);
    }
}
