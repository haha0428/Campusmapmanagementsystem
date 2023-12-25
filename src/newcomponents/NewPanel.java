package newcomponents;

import dao.Data;
import domain.Line;
import domain.Location;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class NewPanel extends JPanel {
    public  Data data;
    private ArrayList<Line> Lines;



    public NewPanel(Data data) {
        this.data=data;
        this.Lines=data.getLines();
        this.setBounds(230,20,740,805);
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        repaint();
    }
    public void changeLocation(Component e) {
        //true为操作起点,false为操作终点
        Point la = e.getLocation();
        Dimension d = e.getSize();
        for (Line tmp : Lines) {
            if (e==tmp.CStart) {
                tmp.StartX = la.x + d.width / 2;
                tmp.StartY = la.y + d.height / 2;
            } else if(e==tmp.CEnd){
                tmp.EndX = la.x + d.width / 2;
                tmp.EndY = la.y + d.height / 2;
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for (Line tmp : Lines) {
            g2d.drawLine(tmp.StartX-230, tmp.StartY-20, tmp.EndX-230, tmp.EndY-20);
            }
    }

    public void addLine(Line e){
        Lines.add(e);
    }

}
