package view;

import dao.Data;
import domain.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteLineInterface extends JDInterface{
    private JComboBox jc1;
    private JComboBox jc2;
    private Data data;

    private JDialog jd;

    private Result result = new Result("state", "Start", "End");


    public DeleteLineInterface(Data data) {
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
        initJBox();
        initJButton();
        this.setVisible(true);

    }

    private void initJFrame() {
        this.setBounds(700, 200, 310, 200);
        this.setTitle("删除界面");
    }

    private void initJLabel() {
        JLabel jl1 = new JLabel("起点:");
        JLabel jl2 = new JLabel("终点:");

        jl1.setFont(new Font("宋体", Font.BOLD, 14));
        jl2.setFont(new Font("宋体", Font.BOLD, 14));

        jl1.setBounds(20, 35, 100, 20);
        jl2.setBounds(20, 95, 100, 20);


        this.add(jl1);
        this.add(jl2);
    }

    private void initJBox() {

        jc1 = new JComboBox();
        jc2 = new JComboBox();

        jc1.setBounds(60, 35, 120, 25);
        jc2.setBounds(60, 95, 120, 25);


        //组件的性质的设置
        for(String key:data.getLocations().keySet()){
            jc1.addItem(key);
        }
        for(String key:data.getLocations().keySet()){
            if(key.equals(jc1.getSelectedItem()))
                continue;
            jc2.addItem(key);
        }


        jc1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                jc2.removeAllItems();
                for(String key:data.getLocations().keySet()){
                    if(key.equals(jc1.getSelectedItem()))
                        continue;
                    jc2.addItem(key);
                }

            }
        });


        this.add(jc1);
        this.add(jc2);
    }

    private void initJButton() {
        JButton jb1 = new JButton("确定删除");

        jb1.setBounds(200, 37, 85, 80);
        jb1.setHorizontalAlignment(JButton.LEFT);

        jb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                result.getResult().put("state", new Boolean(true));
                result.getResult().put("Start", new String((String)jc1.getSelectedItem()));
                result.getResult().put("End", new String((String)jc2.getSelectedItem()));
                jd.dispose();
            }
        });

        this.add(jb1);
    }

    public Result returnResult() {
        return result;
    }
}
