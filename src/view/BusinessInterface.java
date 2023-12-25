package view;

import dao.Data;
import domain.Location;
import newcomponents.DraggableButton;
import newcomponents.NewPanel;
import service.SBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class BusinessInterface extends Interface {

    private Data data;
    private HashMap<String, Location> Locations;

    private HashMap<String, DraggableButton> Buttons = new HashMap();

    private NewPanel np;

    private JFrame jf;

    private JTextField jt1;

    private JTextField jt2;

    private JTextField jt3;

    private JTextArea jt4;

    private boolean power;

    public BusinessInterface(Data data) {
        super();
        DraggableButton.bi = this;
        this.jf = this;
        this.data = data;
        this.Locations = data.getLocations();
        this.Buttons = data.getButtons();
        this.np = data.getNp();
        initJFrame();
        initJMenuBar();
        initButton();
        initPanel();
        initDraggableButton();
        initImage();
        initJTextField();

        this.setVisible(true);

    }

    private void initJFrame() {
        this.setBounds(100, 100, 1000, 920);
        this.setTitle("业务界面");

    }

    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu jmenu1 = new JMenu("功能");


        JMenuItem jMenuItem1 = new JMenuItem("获得管理员权限");
        JMenuItem jMenuItem2 = new JMenuItem("退出管理员模式");


        jMenuBar.add(jmenu1);

        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if((Boolean) new LoginInterface(data).returnResult().getResult().get("state")){
                    power=true;
                }
            }
        });
        jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            power=false;
            }
        });

        jmenu1.add(jMenuItem1);
        jmenu1.add(jMenuItem2);


        this.setJMenuBar(jMenuBar);//设置菜单
    }

    private void initButton() {
        ArrayList<JButton> arrayList = new ArrayList();

        JButton jtb1 = new JButton("添加地点");
        jtb1.setBounds(25, 30, 170, 45);
        jtb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (power) {
                    JButton tmp = SBusiness.addLocation();
                    if (tmp != null) {
                        jf.add(tmp, 0);
                        jf.repaint();
                        np.repaint();
                        np.changeLocation(tmp);
                    }
                } else {
                    new ErrorInterface("未拥有此权限");
                }
            }
        });
        this.add(jtb1);


        JButton jtb2 = new JButton("删除地点");
        jtb2.setBounds(25, 30 + 1 * 80, 170, 45);
        jtb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (power) {
                    if (SBusiness.deleteLocation()) {
                        jf.repaint();
                        np.repaint();
                    }
                } else {
                    new ErrorInterface("未拥有此权限");
                }
            }
        });
        this.add(jtb2);


        JButton jtb3 = new JButton("修改地点");
        jtb3.setBounds(25, 30 + 2 * 80, 170, 45);
        jtb3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (power) {
                    SBusiness.changeLocation();
                    jf.repaint();
                    np.repaint();
                } else {
                    new ErrorInterface("未拥有此权限");
                }
            }

        });
        this.add(jtb3);


        JButton jtb4 = new JButton("增加路径");
        jtb4.setBounds(25, 30 + 3 * 80, 170, 45);
        jtb4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (power) {
                    SBusiness.addLine();
                    jf.repaint();
                    np.repaint();
                } else {
                    new ErrorInterface("未拥有此权限");
                }
            }
        });
        this.add(jtb4);


        JButton jtb5 = new JButton("删除路径");
        jtb5.setBounds(25, 30 + 4 * 80, 170, 45);
        jtb5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (power) {
                    SBusiness.deleteLine();
                    jf.repaint();
                    np.repaint();
                } else {
                    new ErrorInterface("未拥有此权限");
                }
            }
        });
        this.add(jtb5);


        JButton jtb6 = new JButton("查找最小路");
        jtb6.setBounds(25, 30 + 5 * 80, 170, 45);
        jtb6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<String> tmp = SBusiness.findShortest();
                if (tmp != null) {
                    StringBuilder o = new StringBuilder();
                    for (int i = tmp.size() - 1; i >= 1; i--) {
                        o.append(tmp.get(i) + " -> ");
                    }
                    o.append(tmp.get(0));
                    jt1.setText("");
                    jt2.setText("");
                    jt3.setText("");
                    jt4.setText(o.toString());
                } else {
                    jt1.setText("");
                    jt2.setText("");
                    jt3.setText("");
                    jt4.setText("");
                }
            }
        });
        this.add(jtb6);

    }


    private void initPanel() {
        this.add(np);
    }

    //初始化背景图
    private void initImage() {
        JLabel jl = new JLabel(new ImageIcon("素材/image/back.jpg"));
        jl.setBounds(0, 0, 1900, 1267);
        this.getContentPane().add(jl);
    }

    private void initDraggableButton() {
        for (String tmp : Buttons.keySet()) {
            this.add(Buttons.get(tmp));
        }
    }

    private void initJTextField() {
        jt1 = new JTextField();
        jt2 = new JTextField();
        jt3 = new JTextField();
        jt4 = new JTextArea();

        jt1.setBounds(25, 550, 160, 30);
        jt2.setBounds(25, 600, 70, 30);
        jt3.setBounds(115, 600, 70, 30);
        jt4.setBounds(25, 650, 160, 175);

        jt1.setEditable(false);
        jt2.setEditable(false);
        jt3.setEditable(false);
        jt4.setEditable(false);
        jt4.setLineWrap(true);

        this.add(jt1, 0);
        this.add(jt2, 0);
        this.add(jt3, 0);
        this.add(jt4, 0);

    }

    public void viewLocation(String name) {
        jt1.setText(name);
        jt2.setText(Integer.toString(Locations.get(name).x));
        jt3.setText(Integer.toString(Locations.get(name).y));
        jt4.setText(Locations.get(name).getDescribe());
    }
}
