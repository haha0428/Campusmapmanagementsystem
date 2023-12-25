package domain;

import java.awt.*;

public class Line {
    public int StartX;

    public int StartY;

    public int EndX;

    public int EndY;

    public String Start;

    public String End;

    public Component CStart;

    public Component CEnd;


    public Line(String Start, String End) {
        this.Start = Start;
        this.End = End;
    }

    public Line(Component CStart, Component CEnd) {
//        changeStart(Start);
//        changeEnd(End);
        this.CStart = CStart;
        this.CEnd = CEnd;
        this.StartX = CStart.getLocation().x + CEnd.getSize().width / 2;
        this.StartY = CStart.getLocation().y + CEnd.getSize().height / 2;
        this.EndX = CEnd.getLocation().x + CEnd.getSize().width / 2;
        this.EndY = CEnd.getLocation().y + CEnd.getSize().height / 2;
    }


    public void setLocation(int StartX, int StartY, int EndX, int EndY) {
        this.StartX = StartX;
        this.StartY = StartY;
        this.EndX = EndX;
        this.EndY = EndY;
    }

    public void changeStart(String Start) {
        this.Start = Start;
    }

    public void changeEnd(String End) {
        this.End = End;
    }

    public void setCStart(Component CStart) {
        this.CStart = CStart;
        this.StartX = CStart.getX() + CStart.getSize().width / 2;
        this.StartY = CStart.getY() + CStart.getSize().height / 2;
    }

    public void setCEnd(Component CEnd) {
        this.CEnd = CEnd;
        this.EndX = CEnd.getX() + CEnd.getSize().width / 2;
        this.EndY = CEnd.getY() + CEnd.getSize().height / 2;
    }
}
