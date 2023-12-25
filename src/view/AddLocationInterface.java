package view;

import dao.Data;
import domain.Result;
import util.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddLocationInterface extends JDInterface {

    private JTextComponent jt1;
    private JTextComponent jt2;
    private JTextComponent jt3;
    private JTextArea jt4;
    private Data data;

    private JDialog jd;

    private Result result = new Result("state", "name", "x", "y", "describe");


    public AddLocationInterface(Data data) {
        super(null,true);
        jd=this;
        this.data = data;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                result.getResult().put("state", new Boolean(false));
            }
        });
        initJFrame();
        initJLabel();
        initJText();
        initJButton();
        this.setVisible(true);

    }

    private void initJFrame() {
        this.setBounds(700, 200, 300, 400);
        this.setTitle("添加界面");
    }

    private void initJLabel() {
        JLabel jl1 = new JLabel("地点名字:");
        JLabel jl2 = new JLabel("横坐标:");
        JLabel jl3 = new JLabel("纵坐标:");
        JLabel jl4 = new JLabel("地点的简单介绍:");

        jl1.setFont(new Font("宋体", Font.BOLD, 14));
        jl2.setFont(new Font("宋体", Font.BOLD, 14));
        jl3.setFont(new Font("宋体", Font.BOLD, 14));
        jl4.setFont(new Font("宋体", Font.BOLD, 14));

        jl1.setBounds(30, 30, 500, 20);
        jl2.setBounds(30, 90, 100, 20);
        jl3.setBounds(150, 90, 100, 20);
        jl4.setBounds(30, 150, 120, 20);


        this.add(jl1);
        this.add(jl2);
        this.add(jl3);
        this.add(jl4);
    }

    private void initJText() {
        jt1 = new JTextField();
        jt2 = new JTextField();
        jt3 = new JTextField();
        jt4 = new JTextArea();

        jt1.setBounds(30, 55, 120, 28);
        jt2.setBounds(30, 115, 60, 28);
        jt3.setBounds(150, 115, 60, 28);
        jt4.setBounds(30, 175, 225, 105);

        //组件的性质的设置
        jt1.setDocument(new JTextFieldLimit(10));
        jt2.setDocument(new JTextFieldLimit(5));
        Filter.filter((JTextField) jt2);
        jt3.setDocument(new JTextFieldLimit(5));
        Filter.filter((JTextField) jt3);
        jt4.setDocument(new JTextFieldLimit(100));
        jt4.setLineWrap(true);
        jt4.setFont(new Font("宋体", Font.PLAIN, 20));

        this.add(jt1);
        this.add(jt2);
        this.add(jt3);
        this.add(jt4);
    }

    private void initJButton() {
        JButton jb1 = new JButton("确定添加");

        jb1.setBounds(90, 300, 100, 30);

        jb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                result.getResult().put("state", new Boolean(true));
                result.getResult().put("name", jt1.getText());
                result.getResult().put("x", new String(jt2.getText()));
                result.getResult().put("y", new String(jt3.getText()));
                result.getResult().put("describe",new String(jt4.getText()));
                jd.dispose();
            }
        });

        this.add(jb1);
    }

    public Result returnResult() {
        return result;
    }
}
