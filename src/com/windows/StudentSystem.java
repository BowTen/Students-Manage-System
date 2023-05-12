package com.windows;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StudentSystem {
    public static void main(String[] args) throws IOException {
//        LoginStart loginWindow = new LoginStart();
        new JTableDemo();
    }
}

class JTableDemo extends JFrame{
    Object rowData[][] = {
            new Object[] {"张三", 20, "男"},
            new Object[] {"李四", 24, "女"},
            new Object[] {"王五", 22, "男"},
            new Object[] {"赵六", 19, "女"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"}
    };

    Object headData[] = {"姓名", "年龄", "性别"};

    public JTableDemo() throws HeadlessException {

        initFrame();
        initTable();

        setVisible(true);
    }

    private void initTable() {
        JTable jTable = new JTable(rowData, headData);
        jTable.setRowHeight(50);
        jTable.setFont(new Font(null, 1, 40));
        jTable.getTableHeader().setFont(new Font(null, 1, 40));
        jTable.getTableHeader().setPreferredSize(new Dimension(60,40));
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(100,30,600,400);
        add(jScrollPane);
    }

    private void initFrame() {
        setBounds(100,100,800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    }
}