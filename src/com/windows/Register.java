package com.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Register extends JFrame implements MouseListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 1200;
    private final String title = "学生管理系统账号注册";

    //定义按钮
    JButton registerBut = new JButton("注册");

    public Register() throws HeadlessException {
        initJFrame();
        initView();
        initImage();

        //设置显示
        setVisible(true);
    }

    private void initView() {
        //定义文字提示
        JLabel userName = new   JLabel("  用户名:");
        JLabel password = new   JLabel("    密码:");
        JLabel repassword = new JLabel("确认密码:");
        //设置文字样式大小
        userName.setFont(new Font(null, 1, 40));
        password.setFont(new Font(null, 1, 40));
        repassword.setFont(new Font(null, 1, 40));
        //设置字体颜色
        userName.setForeground(new Color(0,250,0));
        password.setForeground(new Color(0,250,0));
        repassword.setForeground(new Color(0,250,0));
        //设置字体位置
        userName.setBounds(45,150,300,40);
        password.setBounds(65,250,300,40);
        repassword.setBounds(30,350,300,40);
        //添加字体
        getContentPane().add(userName);
        getContentPane().add(password);
        getContentPane().add(repassword);

        //定义文本框
        JTextField userNameText = new JTextField();
        JPasswordField passwordText = new JPasswordField();
        JPasswordField repasswordText = new JPasswordField();
        //设置文本框字体大小
        userNameText.setFont(new Font(null, 1, 30));
        passwordText.setFont(new Font(null, 1, 30));
        repasswordText.setFont(new Font(null, 1, 30));
        //设置文本框位置
        userNameText.setBounds(220, 150, 450, 40);
        passwordText.setBounds(220, 250, 450, 40);
        repasswordText.setBounds(220, 350, 450, 40);
        //添加文本框
        getContentPane().add(userNameText);
        getContentPane().add(passwordText);
        getContentPane().add(repasswordText);

        //设置按钮字体样式大小
        registerBut.setFont(new Font(null, 1, 60));
        //设置按钮字体颜色
        registerBut.setForeground(new Color(255, 207, 207));
        //设置按钮位置大小
        registerBut.setBounds(100,900,600,100);
        //添加按钮
        getContentPane().add(registerBut);

        registerBut.addMouseListener(this);
    }


    private void initImage() {
        JLabel bgJLabel = new JLabel(new ImageIcon("src/imgresrc/xiao.png"));
        //设置图片位置，宽高
        bgJLabel.setBounds(0,0,WIDTH, HEIGHT);
        getContentPane().add(bgJLabel);
    }

    private void initJFrame() {
        //设置宽高
        setSize(WIDTH, HEIGHT);
        //设置标题
        setTitle(title);
        //设置居中
        setLocationRelativeTo(null);
        //设置关闭模式
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //大小不可变
        setResizable(false);
        //取消默认摆设方式
        setLayout(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
