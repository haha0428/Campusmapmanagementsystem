package view;

import javax.swing.*;
import java.awt.*;

public class JDInterface extends JDialog {
    public JDInterface(Dialog owner, boolean modal) {
        super(owner, modal);
        InitSetting();
    }

    private void InitSetting() {
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
    }
}
