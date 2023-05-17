package com.windows;

import cn.hutool.core.io.FileUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;

public class RootWindow extends JFrame {

    //操作面板
    JPanel consolePanel = new JPanel();
    //信息面板
    JPanel infPanel = new JPanel();

    //主操作面板
    JPanel mainOpePanel = new JPanel();
    //成绩查询面板
    JPanel gradesOpePanel = new JPanel();

    //学生信息面板
    JPanel stuPanel = new JPanel();
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

    Object rowData[][];
    Object headData[] = {"序号", "课程", "分数", "绩点", "班级排名"};
    private final String title = "学生管理系统";
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
        stuData = FileUtil.readUtf8Lines("D:\\Codes\\Students Management System\\src\\datasrc\\studentsData");
        //读取课程信息
        courseData = FileUtil.readUtf8Lines("D:\\Codes\\Students Management System\\src\\datasrc\\courseData");


        //设置边框
        consoleBorder.setTitleFont(new Font(null, 1, 20));
        infBorder.setTitleFont(new Font(null, 1, 20));


        initStuPanel();
        initGradesPanel();
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
        //表格字体
        jTable.setFont(new Font(null, 1, 20));
        //表头字体
        jTable.getTableHeader().setFont(new Font(null, 1, 20));
        //表格尺寸
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.setRowHeight(30);
        jTable.setPreferredSize(new Dimension(WIDTH - 70, 650));
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
        //表格字体
        jTable.setFont(new Font(null, 1, 20));
        //表头字体
        jTable.getTableHeader().setFont(new Font(null, 1, 20));
        //表格尺寸
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.setRowHeight(30);
        jTable.setPreferredSize(new Dimension(WIDTH - 70, 450));
        jTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        for (int i = 1; i < 4; i++) {
            jTable.getColumnModel().getColumn(i).setPreferredWidth(200);
        }
        //滑动窗口
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(WIDTH - 50, 450));
        jScrollPane.setBounds(15, 35, WIDTH - 50, 450);
        gradesPanel.add(jScrollPane);
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
        //表格字体
        jTable.setFont(new Font(null, 1, 40));
        //表头字体
        jTable.getTableHeader().setFont(new Font(null, 1, 40));
        //表格尺寸
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.setRowHeight(50);
        jTable.setPreferredSize(new Dimension(WIDTH - 70, 650));
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

        //功能：基本信息，成绩查询，课程查询，修改信息
        JButton baseInfo = new JButton("学生信息");
        baseInfo.setFont(new Font(null, 1, 40));
        baseInfo.setBounds(60, 140, 225, 120);
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

        JButton grades = new JButton("成绩查询");
        grades.setFont(new Font(null, 1, 40));
        grades.setBounds(345, 140, 225, 120);
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
        course.setBounds(630, 140, 225, 120);
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

        JButton change = new JButton("修改信息");
        change.setFont(new Font(null, 1, 40));
        change.setBounds(915, 140, 225, 120);
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //若当前正在展示基本信息界面则return
                changeOpePanel(gradesOpePanel);
                System.out.println("修改信息");
            }
        });
        mainOpePanel.add(change);
    }

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
        initMainOpePanel();
        initGradesOpePanel();
        consolePanel.setBounds(5, 210, WIDTH - 20, 400);
        infPanel.setBounds(5, 610, WIDTH - 20, 705);
        //设置默认布局
        consolePanel.setLayout(null);
        infPanel.setLayout(null);

        //设置子面板可见性
        stuPanel.setVisible(false);
        coursePanel.setVisible(false);
        mainOpePanel.setVisible(true);
        gradesOpePanel.setVisible(false);

        //添加子面板
        consolePanel.add(mainOpePanel);
        consolePanel.add(gradesOpePanel);
        infPanel.add(stuPanel);
        infPanel.add(coursePanel);

        opePanels.push(mainOpePanel);

        add(consolePanel);
        add(infPanel);

    }

    private void initGradesOpePanel() {
        //设置面板
        gradesOpePanel.setBounds(0, 0, WIDTH - 20, 400);
        //面板边框
        TitledBorder titledBorder = new TitledBorder("成绩查询");
        titledBorder.setTitleFont(new Font(null, 1, 20));
        gradesOpePanel.setBorder(titledBorder);
        gradesOpePanel.setLayout(null);

        //返回按钮
        JButton back = new JButton("返回");
        back.setFont(new Font(null, 1, 30));
        back.setBounds(40, 40, 100, 50);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //若当前正在展示基本信息界面则return
                opePanelBack();
                System.out.println("返回");
            }
        });
        gradesOpePanel.add(back);
    }

    private void opePanelBack() {
        opePanels.peek().setVisible(false);
        opePanels.pop();
        opePanels.peek().setVisible(true);
    }

    //表格模式
    private class MyTabelModel extends AbstractTableModel {

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
