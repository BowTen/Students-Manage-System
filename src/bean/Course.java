package bean;

import java.util.*;

public class Course {
    public String name;
    public String teacher;
    public Map<String, Double> students = new HashMap<String,Double>();
    public ArrayList<Double> grades = new ArrayList<Double>();

    //构造方法
    public Course(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    //初始化成绩列表
    public void InitGrades(){
        for (Double value : students.values()) {
            grades.add(value);
        }
        Comparator<Double> comparator = Collections.reverseOrder();
        Collections.sort(grades, comparator);
    }

    //获取成绩
    public Double GetGrades(String name){
        return students.get(name);
    }

    //获取排名
    public int GetRank(String name){
        return grades.indexOf(students.get(name)) + 1;
    }
}
