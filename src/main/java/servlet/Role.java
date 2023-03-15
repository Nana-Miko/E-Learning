package servlet;

public enum Role {
    STUDENT("学生",2),
    TEACHER("老师",1),
    ADMIN("管理员",0);

    private String name;
    private int value;

    Role(String name, int value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
