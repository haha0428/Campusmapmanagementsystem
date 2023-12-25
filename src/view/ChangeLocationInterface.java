package view;


import dao.Data;
import domain.Location;
import domain.Result;
import util.Filter;
import util.JTextFieldLimit;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class ChangeLocationInterface extends JDInterface {

    private JTextComponent jt;
    private JTextComponent jt1;
    private JTextComponent jt2;
    private JTextComponent jt3;
    private JTextArea jt4;
    private Data data;

    private JDialog jd;

    private Result result = new Result("state", "name","chedname","x","y","describe");

    private HashMap<String, Location> Locations;

    private String name;

    public BusinessInterface bi;


    public ChangeLocationInterface(Data data) {
        super(null, true);

        this.data = data;
        jd=this;
        this.Locations=data.getLocations();
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
        this.setBounds(700, 200, 300, 520);
        this.setTitle("删除界面");
    }

    private void initJLabel() {
        JLabel jl=new JLabel("输入你要改为的名字:");
        JLabel jl0 = new JLabel("请输入你要修改地点的名字");
        JLabel jl1 = new JLabel("地点名字:");
        JLabel jl2 = new JLabel("横坐标:");
        JLabel jl3 = new JLabel("纵坐标:");
        JLabel jl4 = new JLabel("地点的简单介绍:");

        jl.setFont(new Font("宋体", Font.BOLD, 14));
        jl0.setFont(new Font("宋体", Font.BOLD, 14));
        jl1.setFont(new Font("宋体", Font.BOLD, 14));
        jl2.setFont(new Font("宋体", Font.BOLD, 14));
        jl3.setFont(new Font("宋体", Font.BOLD, 14));
        jl4.setFont(new Font("宋体", Font.BOLD, 14));

        jl.setBounds(30, 150, 150, 20);
        jl0.setBounds(30, 35, 200, 20);
        jl1.setBounds(30, 80, 500, 20);
        jl2.setBounds(30, 210, 100, 20);
        jl3.setBounds(150, 210, 100, 20);
        jl4.setBounds(30, 270, 120, 20);

        this.add(jl);
        this.add(jl0);
        this.add(jl1);
        this.add(jl2);
        this.add(jl3);
        this.add(jl4);
    }

    private void initJText() {
        jt =new JTextField();
        jt1 = new JTextField();
        jt2 = new JTextField();
        jt3 = new JTextField();
        jt4 = new JTextArea();

        jt.setBounds(30, 175, 120, 28);
        jt1.setBounds(100, 75, 120, 28);
        jt2.setBounds(30, 235, 60, 28);
        jt3.setBounds(150, 235, 60, 28);
        jt4.setBounds(30, 305, 225, 105);

        //组件的性质的设置
        jt.setDocument(new JTextFieldLimit(10));
        jt1.setDocument(new JTextFieldLimit(10));
        jt2.setDocument(new JTextFieldLimit(5));
        Filter.filter((JTextField) jt2);
        jt3.setDocument(new JTextFieldLimit(5));
        Filter.filter((JTextField) jt3);
        jt4.setDocument(new JTextFieldLimit(100));
        jt4.setLineWrap(true);
        jt4.setFont(new Font("宋体", Font.PLAIN, 15));

        this.add(jt);
        this.add(jt1);
        this.add(jt2);
        this.add(jt3);
        this.add(jt4);
    }

    private void initJButton() {
        JButton jb1 = new JButton("确定修改");
        JButton jb2 = new JButton("查询");

        jb1.setBounds(90, 420, 100, 30);
        jb2.setBounds(100,110,120,20);

        jb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                result.getResult().put("state", new Boolean(true));
                result.getResult().put("name", new String(jt1.getText()));
                result.getResult().put("chedname", new String(jt.getText()));
                result.getResult().put("x", new String(jt2.getText()));
                result.getResult().put("y", new String(jt3.getText()));
                result.getResult().put("describe", new String(jt4.getText()));
                jd.dispose();
            }
        });
        jb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name= jt1.getText();
                jt.setText(name);
                jt2.setText(String.valueOf(Locations.get(name).getCoord()[0]));
                jt3.setText(String.valueOf(Locations.get(name).getCoord()[1]));
                jt4.setText(Locations.get(name).getDescribe());
            }
        });

        this.add(jb1);
        this.add(jb2);
    }

    public Result returnResult() {
        return result;
    }
}

