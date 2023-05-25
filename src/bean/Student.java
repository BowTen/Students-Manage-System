package bean;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
    //课程
    public ArrayList<Course> courses = new ArrayList<Course>();

    //课程数据地址
    File courseDataPath = new File("src/datasrc/courseData");
    //学生数据地址
    File stuDataPath = new File("src/datasrc/studentsData");


    //排序参数
    //升序
    public static final int GREATER = 1;
    //降序
    public static final int LESS = 0;

    //构造方法
    public Student(String id, String name, String academy, String major, String _class) {
        this.id = id;
        this.name = name;
        this.academy = academy;
        this.major = major;
        this._class = _class;
    }

    //构造方法
    public Student(String id){
        //读取数据
        List<String> stuData = FileUtil.readUtf8Lines(stuDataPath);
        for (String s : stuData) {
            if (s.split("&")[0].equals(id)) {
                String tmp[] = s.split("&");
                this.id = tmp[0];
                name = tmp[1];
                academy = tmp[2];
                major = tmp[3];
                _class = tmp[4];
                String coursesData[] = tmp[5].split("#");
                //初始化课程
                List<String> allCourse = FileUtil.readUtf8Lines(courseDataPath);
                for (String cours : coursesData) {
                    courses.add(new Course(cours));
                }
                break;
            }
        }
    }

    public Student(String id, String passWord) {
        this.passWord = passWord;
        this.id = id;
    }

    //默认构造
    public Student() {
    }

    //获取成绩表格行数据
    public Object[][] getGradesRowData(int sortPara){
        int row = courses.size();
        Object[][] rowData = new Object[row][5];
        for (int i = 0; i < row; i++) {
            rowData[i][1] = courses.get(i).name;
            rowData[i][2] = courses.get(i).GetGrades(name);
            rowData[i][3] = courses.get(i).GetPoint(name);
            rowData[i][4] = courses.get(i).GetRank(name);
        }

        if(sortPara == 1)
            greaterSort(rowData);
        else
            lessSort(rowData);

        for (int i = 0; i < row; i++) {
            rowData[i][0] = i + 1;
        }

        return rowData;
    }

    private void lessSort(Object[][] rowData) {
        Arrays.sort(rowData, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                Double e1 = new Double(o1[2].toString());
                Double e2 = new Double(o2[2].toString());
                if(e1 > e2)
                    return -1;
                else if(e1 == e2)
                    return 0;
                return 1;
            }
        });
    }

    private void greaterSort(Object[][] rowData) {
        Arrays.sort(rowData, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                Double e1 = new Double(o1[2].toString());
                Double e2 = new Double(o2[2].toString());
                if(e1 < e2)
                    return -1;
                else if(e1 == e2)
                    return 0;
                return 1;
            }
        });
    }

    //获取平均分
    public double GetAverGrades(){
        double ret = 0;
        for (Course cours : courses) {
            ret += cours.GetGrades(name);
        }
        if(ret == 0)
            return 0;
        ret /= courses.size();
        return new BigDecimal(ret).setScale(2,BigDecimal.ROUND_UP).doubleValue();
    }

    //获取平均绩点
    public double GetAverPoint(){
        double grades = GetAverGrades();
        if(grades == 0)
            return 0;
        return new BigDecimal(grades >= 60.0 ? ((grades - 60.0) / 10.0) + 1.0 : 0).setScale(2,BigDecimal.ROUND_UP).doubleValue();
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
