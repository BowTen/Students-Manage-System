package bean;

import cn.hutool.core.io.FileUtil;

import java.math.BigDecimal;
import java.util.*;

public class Course {
    private double averageGrades = 0;
    private double averagePoint = 0;
    public String name;
    public String teacher;
    public Map<String, Double> students = new HashMap<String,Double>();
    public ArrayList<Double> grades = new ArrayList<Double>();

    //构造方法
    public Course(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    //构造方法
    public Course(String courseName){
        //读入数据
        List<String> allCourse = FileUtil.readUtf8Lines("D:\\Codes\\Students Management System\\src\\datasrc\\courseData");
        for (String s1 : allCourse) {
            if (s1.split("&")[0].equals(courseName)) {
                String[] split = s1.split("&");
                name = split[0];
                teacher = split[1];
                String[] students = split[2].split("#");
                for (String student : students) {
                    String[] nameAndgrades = student.split("@");
                    this.students.put(nameAndgrades[0], Double.parseDouble(nameAndgrades[1]));
                }
                InitGrades();
                break;
            }
        }
    }

    //初始化成绩列表
    public void InitGrades(){
        for (Double value : students.values()) {
            grades.add(value);
            averageGrades += value;
        }
        Comparator<Double> comparator = Collections.reverseOrder();
        Collections.sort(grades, comparator);
        averageGrades /= GetNumber();
        averagePoint = new BigDecimal(averageGrades >= 60.0 ? ((averageGrades - 60.0) / 10.0) + 1.0 : 0).setScale(2,BigDecimal.ROUND_UP).doubleValue();
    }

    //获取成绩
    public Double GetGrades(String name){
        return students.get(name);
    }

    //获取绩点
    public Double GetPoint(String name){
        double grades = GetGrades(name);
        if(grades == 0)
            return 0.0;
        return new BigDecimal(grades >= 60.0 ? ((grades - 60.0) / 10.0) + 1.0 : 0).setScale(2,BigDecimal.ROUND_UP).doubleValue();
    }

    //获取课程平均分
    public Double GetAverageGrades(){
        return averageGrades;
    }

    //获取课程平均绩点
    public Double GetAveragePoint(){
        return averagePoint;
    }

    //获取班级人数
    public int GetNumber(){
        return grades.size();
    }

    //获取排名
    public int GetRank(String name){
        return grades.indexOf(students.get(name)) + 1;
    }

    //获取成绩表行数据
    public Object[][] getGradesRowData(){
        int row = grades.size();
        Object[][] rowData = new Object[row][5];
        int id = 0;
        for (Map.Entry<String, Double> entry : students.entrySet()) {
            rowData[id][0] = id+1;
            rowData[id][1] = entry.getKey();
            rowData[id][2] = entry.getValue();
            rowData[id][3] = GetPoint(entry.getKey());
            rowData[id][4] = GetRank(entry.getKey());
            id++;
        }
        return rowData;
    }
}
