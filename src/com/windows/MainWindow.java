package com.windows;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final int WIDTH = 1600;
    private final int HEIGHT = 1200;
    private final String title = "学生管理系统";

    public MainWindow() throws HeadlessException {
        initJFrame();
        initView();
        initImage();

        //设置显示
        setVisible(true);
    }

    private void initImage() {

    }

    private void initView() {

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
}
