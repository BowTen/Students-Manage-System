package com.windows;

import bean.Student;
import cn.hutool.core.io.FileUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;

public class MainWindow extends JFrame {
    //操作面板
    JPanel consolePanel = new JPanel();
    //信息面板
    JPanel infPanel = new JPanel();

    //主操作面板
    JPanel mainOpePanel = new JPanel();
    //基本信息面板
    JPanel baseInfPanel = new JPanel();



    //面板栈
    Stack<JPanel> opePanels = new Stack<>();
    Stack<JPanel> infPanels = new Stack<>();

    //边框
    TitledBorder consoleBorder = new TitledBorder("操作区");
    TitledBorder infBorder = new TitledBorder("信息展示区");

    Object rowData[][] = {
            new Object[] {"张三", 20, "男"},
            new Object[] {"李四", 24, "女"},
            new Object[] {"王五", 22, "男"},
            new Object[] {"赵六", 19, "女"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"}
    };
    Object headData[] = {"姓名", "年龄", "性别"};
    private final int WIDTH = 1200;
    private final int HEIGHT = 1400;
    private final String title = "学生管理系统";

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
        List<String> stuData = FileUtil.readUtf8Lines("D:\\Codes\\Students Management System\\src\\datasrc\\studentsData");
        for (String s : stuData) {
            if(s.split("&")[0].equals(id)){
                String tmp[] = s.split("&");
                //初始化学生信息
                curUser = new Student(tmp[0], tmp[1], tmp[2],tmp[3],tmp[4]);
                String courses[] = tmp[5].split("#");
                //初始化课程
                for (String cours : courses) {

//                    curUser.courses.add(cours);
                }
                break;
            }
        }

        if(curUser == null)
            curUser = new Student(null,null,null,null,null);
        //设置边框
        consoleBorder.setTitleFont(new Font(null, 1, 20));
        infBorder.setTitleFont(new Font(null, 1, 20));
    }

    //初始化图片
    private void initImage() {
        JLabel label = new JLabel(new ImageIcon("D:\\Codes\\Students Management System\\src\\imgresrc\\加拿大精灵岛.png"));
        label.setBounds(0,0,WIDTH,200);
        add(label);
    }

    //初始化视图布局
    private void initView() {
        initMenu();
        initImage();
        initConsole();
//        initTable();
    }

    //初始化操作台
    private void initConsole() {
        opePanels.push(mainOpePanel);

        //设置面板
        mainOpePanel.setBounds(0,0,WIDTH-20,400);
        //面板边框
        mainOpePanel.setBorder(consoleBorder);
        mainOpePanel.setLayout(null);

        //功能：基本信息，成绩查询，课程查询
        JButton baseInfo = new JButton("基本信息");
        baseInfo.setFont(new Font(null, 1, 40));
        baseInfo.setBounds(60,140,300,120);
        baseInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                console.setVisible(false);
                //若当前正在展示基本信息界面则return
                if(!infPanels.isEmpty() && infPanels.peek() == baseInfPanel)
                    return;
                showBaseData();
                System.out.println(curUser);
                System.out.println("基础信息");
            }
        });
        mainOpePanel.add(baseInfo);

        JButton grades = new JButton("成绩查询");
        grades.setFont(new Font(null, 1, 40));
        grades.setBounds(440,140,300,120);
        grades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                console.setVisible(false);
//                show
                System.out.println("成绩");
            }
        });
        mainOpePanel.add(grades);

        JButton course = new JButton("课程查询");
        course.setFont(new Font(null, 1, 40));
        course.setBounds(820,140,300,120);
        course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                console.setVisible(false);
                System.out.println("课程");
            }
        });
        mainOpePanel.add(course);

        consolePanel.add(mainOpePanel);
    }

    //展示基本信息界面
    private void showBaseData() {
        infPanels.push(baseInfPanel);
        infPanel.add(baseInfPanel);

        //设置大小边界
        baseInfPanel.setBounds(0,0,WIDTH-20, 705);
        //设置边框
        TitledBorder tiBorder = new TitledBorder("基本信息");
        tiBorder.setTitleFont(new Font(null ,1, 20));
        baseInfPanel.setBorder(tiBorder);
        baseInfPanel.setLayout(null);

        //姓名
        JLabel name = new JLabel(curUser.name == null ? "暂无信息" : curUser.name);
        name.setFont(new Font(null, 1, 100));
        name.setBounds(30,50,1100,100);
        baseInfPanel.add(name);
        //学号
        JLabel id = new JLabel(curUser.id == null ? "暂无信息" : curUser.id);
        id.setFont(new Font(null, 1, 40));
        id.setBounds(30,160,1100,40);
        baseInfPanel.add(id);

        //学院
        JLabel acdemy = new JLabel("所在学院：" + (curUser.academy == null ? "暂无信息" : curUser.academy));
        acdemy.setFont(new Font(null, 0, 30));
        acdemy.setBounds(30,230,500,30);
        baseInfPanel.add(acdemy);
        //学专业院
        JLabel major = new JLabel("专业：" + (curUser.major == null ? "暂无信息" : curUser.major));
        major.setFont(new Font(null, 0, 30));
        major.setBounds(30,270,500,30);
        baseInfPanel.add(major);
        //班级
        JLabel _class = new JLabel("班级：" + (curUser._class == null ? "暂无信息" : curUser._class));
        _class.setFont(new Font(null, 0, 30));
        _class.setBounds(30, 310, 500, 30);
        baseInfPanel.add(_class);
    }

    //初始化菜单
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("选项");
        JMenuItem exit = new JMenuItem("退出系统");

        menuBar.setPreferredSize(new Dimension(WIDTH,35));
        menu.setFont(new Font(null, 1, 20));
        exit.setFont(new Font(null, 1, 20));

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(exit);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    //初始化表格
    private void initTable() {
        //添加面板
        JPanel jPanel = new JPanel();
        jPanel.setBounds(0,400,WIDTH-14,500);
        TitledBorder titledBorder = new TitledBorder("信息展示区");
        titledBorder.setTitleFont(new Font(null, 1, 20));
        jPanel.setBorder(titledBorder);
        add(jPanel);

        //添加表格
        JTable jTable = new JTable(new MyTabelModel());
        //表格字体
        jTable.setFont(new Font(null, 1, 40));
        //表格大小
        jTable.setSize(WIDTH-50,400);
        //表格行高
        jTable.setRowHeight(50);
        //表头
        jTable.getTableHeader().setFont(new Font(null, 1, 40));
        jTable.getTableHeader().setPreferredSize(new Dimension(60,50));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(400);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jPanel.add(jScrollPane);
    }

    //初始化框架
    private void initJFrame() {
        //设置宽高
        setSize(WIDTH,HEIGHT);
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
        //设置面板
        initPanel();
    }

    //初始化面板
    private void initPanel() {
        //设置面板
        consolePanel.setBounds(5,210,WIDTH-20,400);
        infPanel.setBounds(5, 610, WIDTH-20, 705);
        //设置默认布局
        consolePanel.setLayout(null);
        infPanel.setLayout(null);

        add(consolePanel);
        add(infPanel);
    }

    //表格模式
    private class MyTabelModel extends AbstractTableModel{

        @Override
        public int getRowCount() {
            return rowData.length;
        }

        @Override
        public int getColumnCount() {
            return headData.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return rowData[rowIndex][columnIndex];
        }
    }
}
