package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ErrorInterface extends Interface{
    private JFrame tmp=this;
    private ErrorInterface(){
    }
    public ErrorInterface(String Error){
        super();

        initJFrame();
        initJLabel(Error);
        initJButtom();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initJFrame() {
        this.setBounds(600, 200, 600, 200);
        this.setTitle("错误提醒");
    }

    private void initJLabel(String Error){
//        new JLabel(Error);
        JLabel jl=new JLabel(Error);
        jl.setFont(new Font("黑体", Font.BOLD, 14));
        jl.setBounds(20,20,600,30);
        this.add(jl);
    }


    private void initJButtom(){
        JButton jb=new JButton("确定");
        jb.setFont(new Font("黑体", Font.BOLD, 15));
        jb.setBounds(240,100,80,30);
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tmp.dispose();
            }
        });
        this.add(jb);
    }
}
