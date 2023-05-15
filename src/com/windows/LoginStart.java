package com.windows;

import bean.Student;
import cn.hutool.core.io.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginStart extends JFrame implements ActionListener {
    //标题
    String title = "学生管理系统登陆界面";

    //窗口宽度
    final int WIDTH = 800;

    //窗口高度
    final int HEIGHT = 1200;

    //定义按钮
    private JButton login = new JButton("登陆");

    //定义文本输入框
    JTextField userNameText = new JTextField();
    JPasswordField passwordText = new JPasswordField();

    private ArrayList<Student> allUsers = new ArrayList<Student>();

    //构造方法
    public LoginStart() throws IOException {
        initJFrame();
        initView();
        initImage();
        //设置显示
        setVisible(true);

        initData();
    }

    private void initData() throws IOException {
        List<String> userInfo = FileUtil.readUtf8Lines("D:\\Codes\\Students Management System\\src\\datasrc\\userinfo.txt");
        for (String s : userInfo) {
            String[] splits = s.split("&");
            allUsers.add(new Student(splits[0].split("=")[1],splits[1].split("=")[1]));
        }
    }

    private void initView() {

        //定义文字提示
        JLabel titleLab = new JLabel(title);
        JLabel userNameLab = new JLabel("学号：");
        JLabel passwordLab = new JLabel("密码：");
        //设置字体大小
        titleLab.setFont(new Font(null, 1, 62));
        userNameLab.setFont(new Font(null, 1, 40));
        passwordLab.setFont(new Font(null, 1, 40));
        //设置字体颜色
        titleLab.setForeground(new Color(100,200,0));
        userNameLab.setForeground(new Color(0,200,200));
        passwordLab.setForeground(new Color(0,200,200));
        //设置字体位置
        titleLab.setBounds(55,100,1000,80);
        userNameLab.setBounds(50,220,200,40);
        passwordLab.setBounds(50,320,200,40);

        getContentPane().add(titleLab);
        getContentPane().add(userNameLab);
        getContentPane().add(passwordLab);


        //设置输入字体大小
        userNameText.setFont(new Font(null, 1, 30));
        passwordText.setFont(new Font(null, 1, 30));
        //设置文本框位置
        userNameText.setBounds(200,220,500,40);
        passwordText.setBounds(200,320,500,40);
        //添加文本框
        getContentPane().add(userNameText);
        getContentPane().add(passwordText);

        //设置按钮字体大小
        login.setFont(new Font(null, 1, 60));
        //设置按钮字体颜色
        login.setForeground(new Color(0,100,200));
        //设置按钮字体位置
        login.setBounds(200,900, 400, 60);
        //添加按钮
        getContentPane().add(login);

        //添加事件
        login.addActionListener(this);
    }

    private void initImage() {
        JLabel bgJLabel = new JLabel(new ImageIcon("D:\\Codes\\Students Management System\\src\\imgresrc\\法国吕衲峰.png"));
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
        //取消默认居中方式
        setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == login){//登陆按钮
            String identityInput = userNameText.getText();
            String passwordInput = passwordText.getText();
            if(identityInput.length() == 0){
                JOptionPane.showMessageDialog(null,"学号不能为空");
                return;
            }
            if(passwordInput.length() == 0){
                JOptionPane.showMessageDialog(null,"密码不能为空");
                return;
            }
            if(allUsers.contains(new Student(identityInput,passwordInput))){
                MainWindow mainWindow = new MainWindow(identityInput);
                return;
            }else{
                JOptionPane.showMessageDialog(null,"用户名或密码错误");
                return;
            }
        }
    }
}
