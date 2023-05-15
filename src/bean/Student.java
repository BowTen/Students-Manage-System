package bean;

import java.util.ArrayList;

public class Student{
    private String id = null;
    private String passWord = null;

    //姓名
    public String name = null;
    //学院
    public String academy = null;
    //专业
    public String major = null;

    public Student(String id, String name, String academy, String major, String _class) {
        this.id = id;
        this.name = name;
        this.academy = academy;
        this.major = major;
        this._class = _class;
    }

    //班级
    public String _class = null;

    public ArrayList<String> courses = new ArrayList<String>();

    public Student() {
    }

    public Student(String id, String passWord) {
        this.id = id;
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != Student.class)
            return false;
        Student s = (Student)obj;
        return id.equals(s.id)&&passWord.equals(s.passWord);
    }

    @Override
    public String toString() {
        String ret = id + "&" + name + "&" + major + "&" + _class;
        for (String cours : courses) {
            ret += "#" + cours;
        }
        return ret;
    }
}
