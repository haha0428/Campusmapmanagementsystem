package view;


import dao.Data;
import domain.Result;
import service.SLog;
import util.JTextFieldLimit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginInterface extends JDInterface {

    private Data data;

    private Container JContentPane = this.getContentPane();

    private String name = null;

    private String password = null;

    private String repassword = null;

    private JTextField jt1;//用户名栏

    private JPasswordField jt2;//密码栏

    private JPasswordField jt3;//确认密码栏

    private Result result=new Result("state");

    private JDialog jd=this;

    /*登录页面*/
    /*登录页面*/
    /*登录页面*/


    public LoginInterface(Data data) {
        super(null, true);
        this.data=data;
        initJFrame();
        initJLabel();
        initButton();
        this.setVisible(true);
    }
    private void initJFrame() {
        this.setBounds(600, 200, 500, 500);
        this.setTitle("登录demo");
    }

    private void initJLabel() {
        JLabel jl1 = new JLabel("用户登录");
        jl1.setFont(new Font("黑体", Font.BOLD, 24));
        jl1.setBounds(190, 1, 200, 50);
        this.add(jl1);


        JLabel jl2 = new JLabel("用户名");
        jl2.setFont(new Font("黑体", Font.BOLD, 18));
        jl2.setBounds(50, 80, 200, 30);
        this.add(jl2);


        JLabel jl3 = new JLabel("密  码");
        jl3.setFont(new Font("黑体", Font.BOLD, 18));
        jl3.setBounds(50, 180, 200, 30);
        this.add(jl3);


        this.jt1 = new JTextField();//ID
        jt1.setBounds(150, 80, 200, 30);
        jt1.setDocument(new JTextFieldLimit(20));
        this.add(jt1);


        this.jt2 = new JPasswordField();//pass
        jt2.setBounds(150, 180, 200, 30);
        jt2.setDocument(new JTextFieldLimit(20));
        this.add(jt2);
    }

    private void initButton() {
        JButton bu1 = new JButton("注册");
        bu1.setBounds(50, 300, 80, 30);
        bu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Component component : JContentPane.getComponents()) {
                    JContentPane.remove(component);
                }
                /*初始化切换界面*/


                initReJFrame();
                initReJLabel();
                initReButton();


                JContentPane.repaint();
            }
        });
        this.add(bu1);

        ///////////////////////////////////////////
        JButton bu2 = new JButton("确定");
        bu2.setBounds(300, 300, 80, 30);
        bu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name = jt1.getText();
                password = jt2.getText();
                if (SLog.Log(name, password)) {
//                    new BusinessInterface(data);
                    result.getResult().put("state", new Boolean(true));
                    jd.dispose();
                    System.out.println("正确");
                } else {
                    new ErrorInterface("密码或用户名错误，点击确定后重试");
                }
            }
        });
        this.add(bu2);
        //与数据库进行比较


        //成功进入业务页面；失败继续登录
    }


    /*注册页面*/
    /*注册页面*/
    /*注册页面*/


    private void initReJFrame() {
        this.setBounds(600, 200, 500, 500);
        this.setTitle("注册demo");
    }

    private void initReJLabel() {
        JLabel jl1 = new JLabel("用户注册");
        jl1.setFont(new Font("黑体", Font.BOLD, 24));
        jl1.setBounds(190, 1, 200, 50);
        this.add(jl1);


        JLabel jl2 = new JLabel("用户名");
        jl2.setFont(new Font("黑体", Font.BOLD, 18));
        jl2.setBounds(50, 80, 200, 30);
        this.add(jl2);


        JLabel jl3 = new JLabel("密  码");
        jl3.setFont(new Font("黑体", Font.BOLD, 18));
        jl3.setBounds(50, 150, 200, 30);
        this.add(jl3);


        JLabel jl4 = new JLabel("确认密码");
        jl4.setFont(new Font("黑体", Font.BOLD, 18));
        jl4.setBounds(50, 220, 200, 30);
        this.add(jl4);


        this.jt1 = new JTextField();//ID
        jt1.setBounds(150, 80, 200, 30);
        jt1.setDocument(new JTextFieldLimit(20));
        this.add(jt1);


        this.jt2 = new JPasswordField();//password
        jt2.setBounds(150, 150, 200, 30);
        jt2.setDocument(new JTextFieldLimit(20));
        this.add(jt2);


        this.jt3 = new JPasswordField();//repassword
        jt3.setBounds(150, 220, 200, 30);
        jt3.setDocument(new JTextFieldLimit(20));
        this.add(jt3);
    }

    private void initReButton() {
        JButton bu1 = new JButton("返回");
        bu1.setBounds(50, 300, 80, 30);
        bu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Component component : JContentPane.getComponents()) {
                    JContentPane.remove(component);
                }
                /*初始化切换界面*/


                initJFrame();
                initJLabel();
                initButton();


                JContentPane.repaint();
            }
        });
        this.add(bu1);


        ///////////////////////////////////////////
        JButton bu2 = new JButton("确定");
        bu2.setBounds(300, 300, 80, 30);
        bu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name = jt1.getText();
                password = jt2.getText();
                repassword = jt3.getText();
                if (!password.equals(repassword)) {
                    new ErrorInterface("两次密码不一致，请重新输入");
                } else if (SLog.Register(name, password, repassword)){
                    System.out.println(SLog.addUser(name,password));
                    result.getResult().put("state", new Boolean(true));
                    jd.dispose();
                    new ErrorInterface("注册成功");
                }else{
                    new ErrorInterface("该用户名已存在，请重新输入");
                }
            }
        });
        this.add(bu2);
        //与数据库进行比较

        //成功进入业务页面；失败继续登录
    }
    public Result returnResult() {
        return result;
    }
}

