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

public class MainWindow extends JFrame {
    JPanel console = new JPanel();
    JPanel baseDataPanel = new JPanel();
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

    private Student curUser;

    public MainWindow(String id) throws HeadlessException {
        initData(id);
        initJFrame();
        initView();


        //设置显示
        setVisible(true);
    }

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
                    curUser.courses.add(cours);
                }
                break;
            }
        }
    }

    private void initImage() {
        JLabel label = new JLabel(new ImageIcon("D:\\Codes\\Students Management System\\src\\imgresrc\\加拿大精灵岛.png"));
        label.setBounds(0,0,WIDTH,200);
        add(label);
    }

    private void initView() {
        initMenu();
        initImage();
        initConsole();
//        initTable();
    }

    private void initConsole() {
        //设置面板
        console.setBounds(5,220,WIDTH-30,400);
        //面板边框
        TitledBorder titledBorder = new TitledBorder("操作区");
        titledBorder.setTitleFont(new Font(null, 1, 20));
        console.setBorder(titledBorder);
        console.setLayout(null);

        //功能：基本信息，成绩查询，课程查询
        JButton baseInfo = new JButton("基本信息");
        baseInfo.setFont(new Font(null, 1, 40));
        baseInfo.setBounds(60,140,300,120);
        baseInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                console.setVisible(false);
                System.out.println(curUser);
                System.out.println("基础信息");
            }
        });
        console.add(baseInfo);

        JButton grades = new JButton("成绩查询");
        grades.setFont(new Font(null, 1, 40));
        grades.setBounds(440,140,300,120);
        grades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                console.setVisible(false);
                System.out.println("成绩");
            }
        });
        console.add(grades);


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
        console.add(course);

        add(console);
    }

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
//        jScrollPane.setSize(WIDTH-50,400);
        jPanel.add(jScrollPane);
    }

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
    }

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
