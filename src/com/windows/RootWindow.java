package com.windows;

import bean.Course;
import bean.Student;
import cn.hutool.core.io.FileUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class RootWindow extends JFrame {

    //操作面板
    JPanel consolePanel = new JPanel();
    //信息面板
    JPanel infPanel = new JPanel();

    //主操作面板
    JPanel mainOpePanel = new JPanel();
    //信息查询面板
    JPanel infoQueryPanel = new JPanel();
    //信息查询面板
    JPanel infoModifyPanel = new JPanel();
    //添加学生面板
    JPanel addStudentPanel = new JPanel();
    //添加课程面板
    JPanel addCoursePanel = new JPanel();

    //开始界面
    JPanel welcomPanel = new JPanel();
    //学生信息面板
    JPanel stuPanel = new JPanel();
    //课程面板
    JPanel coursePanel = new JPanel();

    //面板栈
    Stack<JPanel> opePanels = new Stack<>();
    Stack<JPanel> infPanels = new Stack<>();

    //边框
    TitledBorder consoleBorder = new TitledBorder("操作区");
    TitledBorder infBorder = new TitledBorder("信息展示区");

    //学生数据地址
    File stuDataPath = new File("src/datasrc/studentsData");
    //课程数据地址
    File courseDataPath = new File("src/datasrc/courseData");

    //成绩表数据
    Object rowData[][];
    Object headData[] = {"序号", "课程", "分数", "绩点", "班级排名"};

    //标题
    private final String title = "学生管理系统";
    //窗口宽高
    private final int WIDTH = 1200;
    private final int HEIGHT = 1400;

    //学生信息
    java.util.List<String> stuData;
    //课程信息
    java.util.List<String> courseData;

    //构造函数
    public RootWindow() throws HeadlessException {
        initData();
        initJFrame();
        initView();

        //设置显示
        setVisible(true);
    }

    //初始化数据
    private void initData() {
        //读取学生信息
        stuData = FileUtil.readUtf8Lines(stuDataPath);
        //读取课程信息
        courseData = FileUtil.readUtf8Lines(courseDataPath);


        //设置边框
        consoleBorder.setTitleFont(new Font(null, 1, 20));
        infBorder.setTitleFont(new Font(null, 1, 20));


        initStuPanel();
        initcoursePanel();
    }

    //设置学生信息面板
    private void initStuPanel() {

        //设置大小边界
        stuPanel.setBounds(0, 0, WIDTH - 20, 705);
        //设置边框
        TitledBorder tiBorder = new TitledBorder("基本信息");
        tiBorder.setTitleFont(new Font(null, 1, 20));
        stuPanel.setBorder(tiBorder);
        stuPanel.setLayout(null);

        //设置表格数据
        int row = stuData.size();
        Object rowData[][] = new Object[row][6];
        Object headData[] = {"序号", "学号", "姓名", "学院", "专业", "班级"};
        for (int i = 0; i < row; i++) {
            String[] split = stuData.get(i).split("&");
            rowData[i][0] = i + 1;    //编号
            for (int j = 0; j < 5; j++) {
                rowData[i][j+1] = split[j];
            }
        }

        //创建表格
        JTable jTable = new JTable(rowData, headData);
        //设置不可选中
        jTable.setEnabled(false);
        //表格字体
        jTable.setFont(new Font(null, 1, 20));
        //表头字体
        jTable.getTableHeader().setFont(new Font(null, 1, 20));
        //表格尺寸
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.setRowHeight(30);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(300);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(300);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(160);
        //滑动窗口
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(WIDTH - 50, 650));
        jScrollPane.setBounds(15, 35, WIDTH - 50, 650);
        stuPanel.add(jScrollPane);

    }

    //设置课程面板
    private void initcoursePanel() {

        //设置大小边界
        coursePanel.setBounds(0, 0, WIDTH - 20, 705);
        //设置边框
        TitledBorder tiBorder = new TitledBorder("课程查询");
        tiBorder.setTitleFont(new Font(null, 1, 20));
        coursePanel.setBorder(tiBorder);
        coursePanel.setLayout(null);

        int row = courseData.size();
        Object rowData[][] = new Object[row][4];
        Object headData[] = {"序号", "课程", "任课教师", "班级人数"};
        for (int i = 0; i < row; i++) {
            String[] split = courseData.get(i).split("&");
            rowData[i][0] = i + 1;    //编号
            for (int j = 0; j < 2; j++) {
                rowData[i][j+1] = split[j];
            }
            rowData[i][3] = split[2].split("#").length; //班级人数
        }


        //创建表格
        JTable jTable = new JTable(rowData, headData);
        //设置不可选中
        jTable.setEnabled(false);
        //表格字体
        jTable.setFont(new Font(null, 1, 40));
        //表头字体
        jTable.getTableHeader().setFont(new Font(null, 1, 40));
        //表格尺寸
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.setRowHeight(50);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(600);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        //滑动窗口
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(WIDTH - 50, 650));
        jScrollPane.setBounds(15, 35, WIDTH - 50, 650);
        coursePanel.add(jScrollPane);
    }

    //初始化图片
    private void initImage() {
        JLabel label = new JLabel(new ImageIcon("D:\\Codes\\Students Management System\\src\\imgresrc\\加拿大精灵岛.png"));
        label.setBounds(0, 0, WIDTH, 200);
        add(label);
    }

    //初始化视图布局
    private void initView() {
        initMenu();
        initImage();
        initPanel();
    }

    //初始化操作台
    private void initMainOpePanel() {

        //设置面板
        mainOpePanel.setBounds(0, 0, WIDTH - 20, 400);
        //面板边框
        mainOpePanel.setBorder(consoleBorder);
        mainOpePanel.setLayout(null);

        //查看所有学生信息
        JButton baseInfo = new JButton("学生信息");
        baseInfo.setFont(new Font(null, 1, 40));
//        baseInfo.setBounds(60, 140, 225, 120);
        baseInfo.setBounds(60, 140, 300, 120);
        baseInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //若当前正在展示基本信息界面则return
                if (!infPanels.isEmpty() && infPanels.peek() == stuPanel)
                    return;
                showDataPanel(stuPanel);
                System.out.println("学生信息");
            }
        });
        mainOpePanel.add(baseInfo);

        //查看所有课程信息
        JButton course = new JButton("课程信息");
        course.setFont(new Font(null, 1, 40));
//        course.setBounds(345, 140, 225, 120);
        course.setBounds(440, 140, 300, 120);
        course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //若当前正在展示基本信息界面则return
                if (!infPanels.isEmpty() && infPanels.peek() == coursePanel)
                    return;
                showDataPanel(coursePanel);
                System.out.println("课程");
            }
        });
        mainOpePanel.add(course);

        //信息检索
        JButton grades = new JButton("信息查询");
        grades.setFont(new Font(null, 1, 40));
//        grades.setBounds(630, 140, 225, 120);
        grades.setBounds(820, 140, 300, 120);
        grades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //若当前正在展示基本信息界面则return
                changeOpePanel(infoQueryPanel);
                System.out.println("成绩查询");
            }
        });
        mainOpePanel.add(grades);

        //信息修改
//        JButton change = new JButton("信息修改");
//        change.setFont(new Font(null, 1, 40));
//        change.setBounds(915, 140, 225, 120);
//        change.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //若当前正在展示基本信息界面则return
//                changeOpePanel(infoModifyPanel);
//                System.out.println("修改信息");
//            }
//        });
//        mainOpePanel.add(change);
    }

    //切换操作面板
    private void changeOpePanel(JPanel panel) {
        //如果有则关闭之前正在展示的界面
        if (!opePanels.isEmpty()) {
            opePanels.peek().setVisible(false);
        }
        opePanels.push(panel);
        panel.setVisible(true);
    }

    //展示界面
    private void showDataPanel(JPanel panel) {
        //如果有则关闭之前正在展示的界面
        if (!infPanels.isEmpty()) {
            infPanels.peek().setVisible(false);
            System.out.println(infPanels.peek().getName() + "被关闭");
            infPanels.pop();
        }
        infPanels.push(panel);
        panel.setVisible(true);
        System.out.println(panel.getName() + "被显示");
    }

    //初始化菜单
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("选项");
        JMenuItem exit = new JMenuItem("退出系统");
        JMenuItem reLogin = new JMenuItem("重新登录");

        menuBar.setPreferredSize(new Dimension(WIDTH, 35));
        menu.setFont(new Font(null, 1, 20));
        exit.setFont(new Font(null, 1, 20));
        reLogin.setFont(new Font(null, 1, 20));

        //退出系统
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //重新登录
        reLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    new LoginStart();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        menu.add(exit);
        menu.add(reLogin);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    //初始化框架
    private void initJFrame() {
        //设置宽高
        setSize(WIDTH, HEIGHT);
        //设置标题
        setTitle(title);
        //设置居中
        setLocationRelativeTo(null);
        //设置关闭模式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //关闭默认摆设
        setLayout(null);
        //设置大小不可变
        setResizable(false);
    }

    //初始化面板
    private void initPanel() {
        //设置面板
        initWelcomPanel();
        initMainOpePanel();
        initInfoQueryPanel();
        initInfoModifyPanel();
        initAddStudentPanel();
        consolePanel.setBounds(5, 210, WIDTH - 20, 400);
        infPanel.setBounds(5, 610, WIDTH - 20, 705);
        //设置默认布局
        consolePanel.setLayout(null);
        infPanel.setLayout(null);

        //设置子面板可见性
        welcomPanel.setVisible(true);
        stuPanel.setVisible(false);
        coursePanel.setVisible(false);

        mainOpePanel.setVisible(true);
        infoQueryPanel.setVisible(false);
        infoModifyPanel.setVisible(false);
        addStudentPanel.setVisible(false);

        //添加子面板
        consolePanel.add(mainOpePanel);
        consolePanel.add(infoQueryPanel);
        consolePanel.add(infoModifyPanel);
        consolePanel.add(addStudentPanel);

        infPanel.add(stuPanel);
        infPanel.add(coursePanel);
        infPanel.add(welcomPanel);


        opePanels.push(mainOpePanel);
        infPanels.push(welcomPanel);

        add(consolePanel);
        add(infPanel);
    }

    //欢迎界面
    private void initWelcomPanel() {
        //设置大小边界
        welcomPanel.setBounds(0, 0, WIDTH - 20, 705);
        //设置边框
        TitledBorder tiBorder = new TitledBorder("信息展示界面");
        tiBorder.setTitleFont(new Font(null, 1, 20));
        welcomPanel.setBorder(tiBorder);
        welcomPanel.setLayout(null);

        JLabel text = new JLabel("Welcome to the Student Management System!");
        text.setFont(new Font(null, 0, 50));
        text.setBounds(55, 300, WIDTH - 20, 100);
        welcomPanel.add(text);

    }

    //初始化信息查询面板
    private void initInfoQueryPanel() {
        //设置面板
        infoQueryPanel.setBounds(0, 0, WIDTH - 20, 400);
        //面板边框
        TitledBorder titledBorder = new TitledBorder("信息查询");
        titledBorder.setTitleFont(new Font(null, 1, 20));
        infoQueryPanel.setBorder(titledBorder);
        infoQueryPanel.setLayout(null);

        //返回按钮
        JButton back = new JButton("返回");
        back.setFont(new Font(null, 1, 30));
        back.setBounds(40, 40, 100, 50);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opePanelBack();
                System.out.println("返回");
            }
        });
        infoQueryPanel.add(back);

        //按学号筛选
        //文字
        JLabel idSiftText = new JLabel("查询学生成绩");
        idSiftText.setFont(new Font(null, 1, 25));
        idSiftText.setBounds(20, 150, 200,40);
        infoQueryPanel.add(idSiftText);
        //文本框
        JTextField idSifter = new JTextField();
        idSifter.setFont(new Font(null, 1, 30));
        idSifter.setBounds(200, 150, 400, 40);
        infoQueryPanel.add(idSifter);
        //筛选选项按钮
        JRadioButton idRdButton = new JRadioButton("按学号筛选",true);
        idRdButton.setFont(new Font(null, 1, 25));
        idRdButton.setBounds(620, 150,200,30);
        infoQueryPanel.add(idRdButton);

        //按课程筛选
        //文字
        JLabel courseSiftText = new JLabel("查询课程成绩");
        courseSiftText.setFont(new Font(null, 1, 25));
        courseSiftText.setBounds(20, 250, 200,40);
        infoQueryPanel.add(courseSiftText);
        //文本框
        JTextField courseSifter = new JTextField();
        courseSifter.setFont(new Font(null, 1, 30));
        courseSifter.setBounds(200, 250, 400, 40);
        infoQueryPanel.add(courseSifter);
        //筛选选项按钮
        JRadioButton courseRdButton = new JRadioButton("按课程筛选",false);
        courseRdButton.setFont(new Font(null, 1, 25));
        courseRdButton.setBounds(620, 250,200,30);
        infoQueryPanel.add(courseRdButton);

        //按钮组
        ButtonGroup group = new ButtonGroup();
        group.add(idRdButton);
        group.add(courseRdButton);


        //升序选项按钮
        JRadioButton greaterSortButton = new JRadioButton("升序",true);
        greaterSortButton.setFont(new Font(null, 1, 25));
        greaterSortButton.setBounds(900, 280,200,30);
        infoQueryPanel.add(greaterSortButton);

        //降序选项按钮
        JRadioButton lessSortBoutton = new JRadioButton("降序",false);
        lessSortBoutton.setFont(new Font(null, 1, 25));
        lessSortBoutton.setBounds(900, 330,200,30);
        infoQueryPanel.add(lessSortBoutton);

        //按钮组
        ButtonGroup group1 = new ButtonGroup();
        group1.add(greaterSortButton);
        group1.add(lessSortBoutton);

        //查询按钮
        JButton query = new JButton("查询");
        query.setFont(new Font(null, 1, 60));
        query.setBounds(900, 50, 200, 200);
        query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idRdButton.isSelected()){
                    queryById(idSifter.getText(), greaterSortButton.isSelected() ? Student.GREATER : Student.LESS);
                    System.out.println("查询学生");
                }else{
                    queryByCourse(courseSifter.getText(), greaterSortButton.isSelected() ? Course.GREATER : Course.LESS);
                    System.out.println("查询课程");
                }
            }
        });
        infoQueryPanel.add(query);
    }

    //初始化信息修改面板
    private void initInfoModifyPanel() {
        //设置面板
        infoModifyPanel.setBounds(0, 0, WIDTH - 20, 400);
        //面板边框
        TitledBorder titledBorder = new TitledBorder("信息修改");
        titledBorder.setTitleFont(new Font(null, 1, 20));
        infoModifyPanel.setBorder(titledBorder);
        infoModifyPanel.setLayout(null);

        //返回按钮
        JButton back = new JButton("返回");
        back.setFont(new Font(null, 1, 30));
        back.setBounds(40, 40, 100, 50);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opePanelBack();
                System.out.println("返回");
            }
        });
        infoModifyPanel.add(back);

        //添加学生按钮
        JButton addStudent = new JButton("添加学生");
        addStudent.setFont(new Font(null, 1, 50));
        addStudent.setBounds(120, 180, 400, 80);
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeOpePanel(addStudentPanel);
                System.out.println("添加学生");
            }
        });
        infoModifyPanel.add(addStudent);

        //返回按钮
        JButton addCourse = new JButton("添加课程");
        addCourse.setFont(new Font(null, 1, 50));
        addCourse.setBounds(640, 180, 400, 80);
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeOpePanel(addCoursePanel);
                System.out.println("添加课程");
            }
        });
        infoModifyPanel.add(addCourse);

    }

    //初始化添加学生面板
    private void initAddStudentPanel() {
        //设置面板
        addStudentPanel.setBounds(0, 0, WIDTH - 20, 400);
        //面板边框
        TitledBorder titledBorder = new TitledBorder("添加学生");
        titledBorder.setTitleFont(new Font(null, 1, 20));
        addStudentPanel.setBorder(titledBorder);
        addStudentPanel.setLayout(null);

        //返回按钮
        JButton back = new JButton("返回");
        back.setFont(new Font(null, 1, 30));
        back.setBounds(40, 40, 100, 50);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opePanelBack();
                System.out.println("返回");
            }
        });
        addStudentPanel.add(back);


    }

    //查询课程
    private void queryByCourse(String courseName, int sortPara) {
        //创建面板
        JPanel courseQueryPanel = new JPanel();
        //设置名字
        courseQueryPanel.setName("courseQueryPanel");
        //设置大小边界
        courseQueryPanel.setBounds(0, 0, WIDTH - 20, 705);
        //设置边框
        TitledBorder tiBorder = new TitledBorder("课程信息查询");
        tiBorder.setTitleFont(new Font(null, 1, 20));
        courseQueryPanel.setBorder(tiBorder);
        courseQueryPanel.setLayout(null);

        Course course = new Course(courseName);

        //课程名称
        JLabel name = new JLabel(course.name == null ? "暂无信息" : course.name);
        name.setFont(new Font(null, 1, 60));
        name.setBounds(30, 50, 500, 60);
        courseQueryPanel.add(name);
        //任课教师
        JLabel teacher = new JLabel(course.teacher == null ? "暂无信息" : course.teacher);
        teacher.setFont(new Font(null, 1, 30));
        teacher.setBounds(30, 120, 500, 40);
        courseQueryPanel.add(teacher);
        //班级人数
        JLabel number = new JLabel("班级人数：" + course.GetNumber());
        number.setFont(new Font(null, 0, 30));
        number.setBounds(30, 190, 500, 30);
        courseQueryPanel.add(number);

        //成绩数据
        int row = course.grades.size();
        Object headData[] = {"序号", "姓名", "分数", "绩点", "排名"};
        Object rowData[][] = course.getGradesRowData(sortPara);

        //成绩表格
        JTable jTable = new JTable(rowData, headData);
        //设置不可选中
        jTable.setEnabled(false);
        //表格字体
        jTable.setFont(new Font(null, 1, 20));
        //表头字体
        jTable.getTableHeader().setFont(new Font(null, 1, 20));
        //表格尺寸
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.setRowHeight(30);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(110);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(110);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(110);

        //滑动窗口
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(600, 400));
        jScrollPane.setBounds(560, 35, 600, 500);
        courseQueryPanel.add(jScrollPane);

        //平均分
        JLabel averGrades = new JLabel("平均分：" + new Double(course.GetAverageGrades()).toString());
        averGrades.setFont(new Font(null, 1, 30));
        averGrades.setBounds(560, 550, 500, 40);
        courseQueryPanel.add(averGrades);

        //平均绩点
        JLabel averPoint = new JLabel("平均绩点：" + new Double(course.GetAveragePoint()).toString());
        averPoint.setFont(new Font(null, 1, 30));
        averPoint.setBounds(560, 600, 500, 40);
        courseQueryPanel.add(averPoint);

        infPanel.add(courseQueryPanel);
        showDataPanel(courseQueryPanel);
    }

    //查询学生
    private void queryById(String id, int sortPara) {
        //创建面板
        JPanel idQueryPanel = new JPanel();
        //设置名字
        idQueryPanel.setName("idQueryPanel");
        //设置大小边界
        idQueryPanel.setBounds(0, 0, WIDTH - 20, 705);
        //设置边框
        TitledBorder tiBorder = new TitledBorder("学生信息查询");
        tiBorder.setTitleFont(new Font(null, 1, 20));
        idQueryPanel.setBorder(tiBorder);
        idQueryPanel.setLayout(null);

        //创建学生对象
        Student student = new Student(id);

        //姓名
        JLabel name = new JLabel(student.name == null ? "暂无信息" : student.name);
        name.setFont(new Font(null, 1, 60));
        name.setBounds(30, 50, 500, 60);
        idQueryPanel.add(name);
        //学号
        JLabel idLabel = new JLabel(student.id == null ? "暂无信息" : student.id);
        idLabel.setFont(new Font(null, 1, 30));
        idLabel.setBounds(30, 120, 500, 40);
        idQueryPanel.add(idLabel);

        //学院
        JLabel acdemy = new JLabel("所在学院：" + (student.academy == null ? "暂无信息" : student.academy));
        acdemy.setFont(new Font(null, 0, 30));
        acdemy.setBounds(30, 190, 500, 30);
        idQueryPanel.add(acdemy);
        //学专业院
        JLabel major = new JLabel("专业：" + (student.major == null ? "暂无信息" : student.major));
        major.setFont(new Font(null, 0, 30));
        major.setBounds(30, 230, 500, 30);
        idQueryPanel.add(major);
        //班级
        JLabel _class = new JLabel("班级：" + (student._class == null ? "暂无信息" : student._class));
        _class.setFont(new Font(null, 0, 30));
        _class.setBounds(30, 270, 500, 30);
        idQueryPanel.add(_class);


        //成绩数据
        int row = student.courses.size();
        Object headData[] = {"序号", "课程", "分数", "绩点", "班级排名"};
        Object rowData[][] = student.getGradesRowData(sortPara);

        //成绩表格
        JTable jTable = new JTable(rowData, headData);
        //设置不可选中
        jTable.setEnabled(false);
        //表格字体
        jTable.setFont(new Font(null, 1, 20));
        //表头字体
        jTable.getTableHeader().setFont(new Font(null, 1, 20));
        //表格尺寸
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.setRowHeight(30);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(110);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(110);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(110);

        //滑动窗口
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(600, 400));
        jScrollPane.setBounds(560, 35, 600, 500);
        idQueryPanel.add(jScrollPane);

        //平均分
        JLabel averGrades = new JLabel("平均分：" + new Double(student.GetAverGrades()).toString());
        averGrades.setFont(new Font(null, 1, 30));
        averGrades.setBounds(560, 550, 500, 40);
        idQueryPanel.add(averGrades);

        //平均绩点
        JLabel averPoint = new JLabel("平均绩点：" + new Double(student.GetAverPoint()).toString());
        averPoint.setFont(new Font(null, 1, 30));
        averPoint.setBounds(560, 600, 500, 40);
        idQueryPanel.add(averPoint);

        infPanel.add(idQueryPanel);
        showDataPanel(idQueryPanel);
    }

    //操作面板返回
    private void opePanelBack() {
        opePanels.peek().setVisible(false);
        opePanels.pop();
        opePanels.peek().setVisible(true);
    }
}
