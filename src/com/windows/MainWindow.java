package com.windows;

import bean.Student;
import cn.hutool.core.io.FileUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class MainWindow extends JFrame {
    //操作面板
    JPanel consolePanel = new JPanel();
    //信息面板
    JPanel infPanel = new JPanel();

    //开始界面
    JPanel welcomPanel = new JPanel();
    //主操作面板
    JPanel mainOpePanel = new JPanel();
    //修改密码面板
    JPanel editPasswdPanel = new JPanel();

    //基本信息面板
    JPanel baseInfPanel = new JPanel();
    //成绩面板
    JPanel gradesPanel = new JPanel();
    //课程面板
    JPanel coursePanel = new JPanel();

    //面板栈
    Stack<JPanel> opePanels = new Stack<>();
    Stack<JPanel> infPanels = new Stack<>();

    //边框
    TitledBorder consoleBorder = new TitledBorder("操作区");
    TitledBorder infBorder = new TitledBorder("信息展示区");

    //用户信息文件地址
    public String userDataPath = "datasrc/userinfo.txt";

    Object rowData[][];
    Object headData[] = {"序号", "课程", "分数", "绩点", "班级排名"};
    private final String title = "学生管理系统";
    private final int WIDTH = 1200;
    private final int HEIGHT = 1400;

    //当前学生
    private Student curUser;

    //构造函数
    public MainWindow(String id) throws HeadlessException {
        initData(id);
        initJFrame();
        initView();

        //设置显示
        setVisible(true);
    }

    //初始化数据
    private void initData(String id) {
        //初始化学生信息
        curUser = new Student(id);

        rowData = curUser.getGradesRowData(Student.LESS);
//        if (curUser == null)
//            curUser = new Student();
//        int row = curUser.courses.size();
//        rowData = new Object[row][5];
//        for (int i = 0; i < row; i++) {
//            rowData[i][0] = i + 1;
//            rowData[i][1] = curUser.courses.get(i).name;
//            Double grades = curUser.courses.get(i).GetGrades(curUser.name);
//            if (grades == null) {
//                rowData[i][2] = "暂无数据";
//                rowData[i][3] = "暂无数据";
//                rowData[i][4] = "暂无数据";
//            } else {
//                //计算绩点并保留两位小数
//                Double point = new BigDecimal(grades >= 60.0 ? ((grades - 60.0) / 10.0) + 1.0 : 0).setScale(2, BigDecimal.ROUND_UP).doubleValue();
//                rowData[i][2] = grades;
//                rowData[i][3] = point;
//                rowData[i][4] = curUser.courses.get(i).GetRank(curUser.name);
//            }
//        }

        if (curUser == null)
            curUser = new Student(null, null, null, null, null);
        //设置边框
        consoleBorder.setTitleFont(new Font(null, 1, 20));
        infBorder.setTitleFont(new Font(null, 1, 20));


        initBaseInfPanel();
        initGradesPanel();
        initcoursePanel();
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

    //设置基础信息面板
    private void initBaseInfPanel() {

        //设置大小边界
        baseInfPanel.setBounds(0, 0, WIDTH - 20, 705);
        //设置边框
        TitledBorder tiBorder = new TitledBorder("基本信息");
        tiBorder.setTitleFont(new Font(null, 1, 20));
        baseInfPanel.setBorder(tiBorder);
        baseInfPanel.setLayout(null);

        //姓名
        JLabel name = new JLabel(curUser.name == null ? "暂无信息" : curUser.name);
        name.setFont(new Font(null, 1, 100));
        name.setBounds(30, 50, 1100, 100);
        baseInfPanel.add(name);
        //学号
        JLabel id = new JLabel(curUser.id == null ? "暂无信息" : curUser.id);
        id.setFont(new Font(null, 1, 40));
        id.setBounds(30, 160, 1100, 40);
        baseInfPanel.add(id);

        //学院
        JLabel acdemy = new JLabel("所在学院：" + (curUser.academy == null ? "暂无信息" : curUser.academy));
        acdemy.setFont(new Font(null, 0, 30));
        acdemy.setBounds(30, 230, 500, 30);
        baseInfPanel.add(acdemy);
        //学专业院
        JLabel major = new JLabel("专业：" + (curUser.major == null ? "暂无信息" : curUser.major));
        major.setFont(new Font(null, 0, 30));
        major.setBounds(30, 270, 500, 30);
        baseInfPanel.add(major);
        //班级
        JLabel _class = new JLabel("班级：" + (curUser._class == null ? "暂无信息" : curUser._class));
        _class.setFont(new Font(null, 0, 30));
        _class.setBounds(30, 310, 500, 30);
        baseInfPanel.add(_class);
    }

    //设置成绩面板
    private void initGradesPanel() {
        //设置大小边界
        gradesPanel.setBounds(0, 0, WIDTH - 20, 705);
        //设置边框
        TitledBorder tiBorder = new TitledBorder("成绩查询");
        tiBorder.setTitleFont(new Font(null, 1, 20));
        gradesPanel.setBorder(tiBorder);
        gradesPanel.setLayout(null);

        //创建表格
        JTable jTable = new JTable(rowData, headData);
        //设置不可选中
        jTable.setEnabled(false);
        //表格字体
        jTable.setFont(new Font(null, 1, 20));
        //表头字体
        jTable.getTableHeader().setFont(new Font(null, 1, 30));
        //表格尺寸
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.setRowHeight(40);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(330);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(220);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(200);
        //滑动窗口
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(WIDTH - 50, 450));
        jScrollPane.setBounds(15, 35, WIDTH - 50, 450);
        gradesPanel.add(jScrollPane);

        //平均分
        JLabel averGrades = new JLabel("平均分：" + new Double(curUser.GetAverGrades()).toString());
        averGrades.setFont(new Font(null, 1, 40));
        averGrades.setBounds(50, 600, 500, 50);

        //平均绩点
        JLabel averPoint = new JLabel("平均绩点：" + new Double(curUser.GetAverPoint()).toString());
        averPoint.setFont(new Font(null, 1, 40));
        averPoint.setBounds(550, 600, 500, 50);

        gradesPanel.add(averGrades);
        gradesPanel.add(averPoint);
    }

    //设置课程面板
    private void initcoursePanel() {
        int row = curUser.courses.size();
        Object rowData[][] = new Object[row][3];
        Object headData[] = {"序号", "课程", "任课教师"};
        for (int i = 0; i < row; i++) {
            rowData[i][0] = i + 1;
            rowData[i][1] = curUser.courses.get(i).name;
            rowData[i][2] = curUser.courses.get(i).teacher;
        }

        //设置大小边界
        coursePanel.setBounds(0, 0, WIDTH - 20, 705);
        //设置边框
        TitledBorder tiBorder = new TitledBorder("课程查询");
        tiBorder.setTitleFont(new Font(null, 1, 20));
        coursePanel.setBorder(tiBorder);
        coursePanel.setLayout(null);

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
        jTable.getColumnModel().getColumn(1).setPreferredWidth(530);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(500);
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
        opePanels.push(mainOpePanel);

        //设置面板
        mainOpePanel.setBounds(0, 0, WIDTH - 20, 400);
        //面板边框
        mainOpePanel.setBorder(consoleBorder);
        mainOpePanel.setLayout(null);

        //功能：基本信息，成绩查询，课程查询
        JButton baseInfo = new JButton("基本信息");
        baseInfo.setFont(new Font(null, 1, 40));
        baseInfo.setBounds(60, 140, 300, 120);
        baseInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //若当前正在展示基本信息界面则return
                if (!infPanels.isEmpty() && infPanels.peek() == baseInfPanel)
                    return;
                showDataPanel(baseInfPanel);
                System.out.println("基础信息");
            }
        });
        mainOpePanel.add(baseInfo);

        JButton grades = new JButton("成绩查询");
        grades.setFont(new Font(null, 1, 40));
        grades.setBounds(440, 140, 300, 120);
        grades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //若当前正在展示基本信息界面则return
                if (!infPanels.isEmpty() && infPanels.peek() == gradesPanel)
                    return;
                showDataPanel(gradesPanel);
                System.out.println("成绩");
            }
        });
        mainOpePanel.add(grades);

        JButton course = new JButton("课程查询");
        course.setFont(new Font(null, 1, 40));
        course.setBounds(820, 140, 300, 120);
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
    }

    //展示界面
    private void showDataPanel(JPanel panel) {
        //如果有则关闭之前正在展示的界面
        if (!infPanels.isEmpty()) {
            infPanels.peek().setVisible(false);
            infPanels.pop();
        }
        infPanels.push(panel);
        panel.setVisible(true);
    }

    //初始化菜单
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("选项");
        JMenuItem exit = new JMenuItem("退出系统");
        JMenuItem reLogin = new JMenuItem("重新登录");
        JMenuItem editPasswd = new JMenuItem("修改密码");

        menuBar.setPreferredSize(new Dimension(WIDTH, 35));
        menu.setFont(new Font(null, 1, 20));
        exit.setFont(new Font(null, 1, 20));
        reLogin.setFont(new Font(null, 1, 20));
        editPasswd.setFont(new Font(null, 1, 20));

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

        //修改密码
        editPasswd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeOpePanel(editPasswdPanel);
                System.out.println("修改密码");
            }
        });

        menu.add(exit);
        menu.add(reLogin);
        menu.add(editPasswd);
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
        initMainOpePanel();
        initWelcomPanel();
        initEditPasswdPanel();
        consolePanel.setBounds(5, 210, WIDTH - 20, 400);
        infPanel.setBounds(5, 610, WIDTH - 20, 705);
        //设置默认布局
        consolePanel.setLayout(null);
        infPanel.setLayout(null);

        mainOpePanel.setVisible(true);
        editPasswdPanel.setVisible(false);

        welcomPanel.setVisible(true);
        gradesPanel.setVisible(false);
        baseInfPanel.setVisible(false);
        coursePanel.setVisible(false);

        consolePanel.add(mainOpePanel);
        consolePanel.add(editPasswdPanel);

        infPanel.add(welcomPanel);
        infPanel.add(gradesPanel);
        infPanel.add(baseInfPanel);
        infPanel.add(coursePanel);

        opePanels.push(mainOpePanel);

        infPanels.push(welcomPanel);

        add(consolePanel);
        add(infPanel);
    }

    //初始化修改密码面板
    private void initEditPasswdPanel() {
        //设置面板
        editPasswdPanel.setBounds(0, 0, WIDTH - 20, 400);
        //面板边框
        TitledBorder titledBorder = new TitledBorder("修改密码");
        titledBorder.setTitleFont(new Font(null, 1, 20));
        editPasswdPanel.setBorder(titledBorder);
        editPasswdPanel.setLayout(null);

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
        editPasswdPanel.add(back);

        //旧密码
        JLabel oldPasswdText = new JLabel("旧密码");
        oldPasswdText.setFont(new Font(null, 1, 25));
        oldPasswdText.setBounds(20, 150, 200, 40);
        editPasswdPanel.add(oldPasswdText);
        //密码框
        JPasswordField oldPasswdInput = new JPasswordField();
        oldPasswdInput.setFont(new Font(null, 1, 30));
        oldPasswdInput.setBounds(200, 150, 500, 40);
        editPasswdPanel.add(oldPasswdInput);

        //新密码
        JLabel newPasswdText = new JLabel("新密码");
        newPasswdText.setFont(new Font(null, 1, 25));
        newPasswdText.setBounds(20, 220, 200, 40);
        editPasswdPanel.add(newPasswdText);
        //密码框
        JPasswordField newPasswdInput = new JPasswordField();
        newPasswdInput.setFont(new Font(null, 1, 30));
        newPasswdInput.setBounds(200, 220, 500, 40);
        editPasswdPanel.add(newPasswdInput);

        //确认新密码
        JLabel newPasswdComfirmText = new JLabel("确认新密码");
        newPasswdComfirmText.setFont(new Font(null, 1, 25));
        newPasswdComfirmText.setBounds(20, 290, 200, 40);
        editPasswdPanel.add(newPasswdComfirmText);
        //密码框
        JPasswordField newPasswdComfirmInput = new JPasswordField();
        newPasswdComfirmInput.setFont(new Font(null, 1, 30));
        newPasswdComfirmInput.setBounds(200, 290, 500, 40);
        editPasswdPanel.add(newPasswdComfirmInput);

        //修改按钮
        JButton edit = new JButton("修改");
        edit.setFont(new Font(null, 1, 60));
        edit.setBounds(900, 50, 200, 300);
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(oldPasswdInput.getPassword().length == 0){
                    JOptionPane.showMessageDialog(null,"请输入旧密码");
                    return;
                }else if(newPasswdInput.getPassword().length == 0){
                    JOptionPane.showMessageDialog(null,"请输入新密码");
                    return;
                }else if(newPasswdComfirmInput.getPassword().length == 0){
                    JOptionPane.showMessageDialog(null,"请确认新密码");
                    return;
                }
                if(checkOldPasswd(new String(oldPasswdInput.getPassword()))){
                    if(new String(newPasswdInput.getPassword()).equals(new String(newPasswdComfirmInput.getPassword()))){
                        if(new String(newPasswdInput.getPassword()).equals(new String(oldPasswdInput.getPassword()))){
                            JOptionPane.showMessageDialog(null,"新密码不能与旧密码相同");
                            return;
                        }
                        List<String> utf8Lines = FileUtil.readUtf8Lines(userDataPath);
                        for (int i = 0; i < utf8Lines.size(); i++) {
                            if(utf8Lines.get(i).split("&")[0].equals(curUser.id)){
                                utf8Lines.set(i, curUser.id + "&" + new String(newPasswdInput.getPassword()));
                                FileUtil.writeUtf8Lines(utf8Lines, userDataPath);
                                JOptionPane.showMessageDialog(null,"修改成功！");
                                opePanelBack();
                                oldPasswdInput.setText("");
                                newPasswdInput.setText("");
                                newPasswdComfirmInput.setText("");
                                System.out.println("修改成功");
                                break;
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"请确认新密码");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"旧密码错误");
                }
                    System.out.println("修改");
            }

            private boolean checkOldPasswd(String password) {
                for (String readUtf8Line : FileUtil.readUtf8Lines(userDataPath)) {
                    if(readUtf8Line.split("&")[0].equals(curUser.id)){
                        return password.equals(readUtf8Line.split("&")[1]);
                    }
                }
                return false;
            }
        });
        editPasswdPanel.add(edit);
    }

    //返回上级操作面板
    private void opePanelBack() {
        opePanels.peek().setVisible(false);
        opePanels.pop();
        opePanels.peek().setVisible(true);
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
}
