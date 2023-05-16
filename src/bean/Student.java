package bean;

import java.util.ArrayList;

public class Student{
    private String passWord = null;

    //学号
    public String id = null;
    //姓名
    public String name = null;
    //学院
    public String academy = null;
    //专业
    public String major = null;
    //班级
    public String _class = null;

    public ArrayList<Course> courses = new ArrayList<Course>();

    //构造方法
    public Student(String id, String name, String academy, String major, String _class) {
        this.id = id;
        this.name = name;
        this.academy = academy;
        this.major = major;
        this._class = _class;
    }


    //默认构造
    public Student() {
    }

    //有参构造
    public Student(String id, String passWord) {
        this.id = id;
        this.passWord = passWord;
    }

    //重写equals
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != Student.class)
            return false;
        Student s = (Student)obj;
        return id.equals(s.id)&&passWord.equals(s.passWord);
    }

    //重写toString
    @Override
    public String toString() {
        String ret = id + "&" + name + "&" + major + "&" + _class;
        for (Course cours : courses) {
            ret += "#" + cours.toString();
        }
        return ret;
    }
}
