package newcomponents;

import domain.Location;
import view.BusinessInterface;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DraggableButton extends JButton {

    private JButton im = this;
    private Dimension size;

    private Point pp;
    private static NewPanel mp;

    public static BusinessInterface bi;

    public DraggableButton(Location location, NewPanel mp) {
        super(location.getName());
        this.setBorder(new LineBorder(Color.BLACK, 1, true));
        this.size = mp.getSize();
        this.pp = mp.getLocation();
        this.setBounds(location.getCoord()[0] + 230, location.getCoord()[1] + 20, 72, 27);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bi.viewLocation(im.getText());
            }
        });

//        this.addMouseMotionListener(new MouseMotionAdapter() {
//            private int lastX;
//            private int lastY;
//
//            public void mouseMoved(MouseEvent e) {
//                lastX = e.getXOnScreen();
//                lastY = e.getYOnScreen();
//                Point location = im.getLocation();
//            }
//
//            public void mouseDragged(MouseEvent e) {
//                int currentX = e.getXOnScreen();
//                int currentY = e.getYOnScreen();
//                int frx = pp.x + size.width - im.getSize().width;
//                int fry = pp.y + size.height - im.getSize().height;
//                Point location = im.getLocation();
//                int lx = location.x + currentX - lastX;
//                int ly = location.y + currentY - lastY;
//                if (lx < pp.x + 2)
//                    lx = pp.x + 2;
//                if (lx > frx - 2)
//                    lx = frx - 2;
//                if (ly > fry - 2)
//                    ly = fry - 2;
//                if (ly < pp.y + 2)
//                    ly = pp.y + 2;
//                im.setLocation(lx, ly);
//                mp.changeLocation(im);
//                lastX = currentX;
//                lastY = currentY;
//            }
//        });
    }


}
