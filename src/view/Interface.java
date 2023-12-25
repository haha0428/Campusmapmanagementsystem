package view;

import javax.swing.*;

public class Interface extends JFrame {
    public Interface(){
        InitSetting();
    }
    private void InitSetting() {
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);
        this.setResizable(false);
    }
}
