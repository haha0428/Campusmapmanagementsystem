package dao;

import domain.Line;
import domain.Location;
import domain.User;
import newcomponents.DraggableButton;
import newcomponents.NewPanel;
import util.Link;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    private ArrayList<Line> Lines = new ArrayList();

    private HashMap<String, Location> Locations = new HashMap();

    private HashMap<String, User> Users = new HashMap();

    private HashMap<String, DraggableButton> Buttons = new HashMap();

    private NewPanel np;

    public ArrayList<Line> getLines() {
        return Lines;
    }

    public HashMap<String, Location> getLocations() {
        return Locations;
    }

    public HashMap<String, User> getUsers() {
        return Users;
    }

    public HashMap<String, DraggableButton> getButtons() {
        return Buttons;
    }

    public NewPanel getNp() {
        return np;
    }

    public Data() {
        //初始化地点
        List<Map<String, Object>> maps = Link.queryMap("select *from location");
        for (int i = 0; i < maps.size(); i++) {
            Locations.put((String) (maps.get(i).get("name")), new Location((String) (maps.get(i).get("name")), (Integer) (maps.get(i).get("x")), (Integer) (maps.get(i).get("y")), (String) (maps.get(i).get("description"))));
        }

        //初始化不同地点之间的道路
        maps = Link.queryMap("select *from line");
        for (int i = 0; i < maps.size(); i++) {
            Lines.add(new Line((String) (maps.get(i).get("start")), (String) (maps.get(i).get("end"))));
            Locations.get(Lines.get(i).Start).addConnection(Lines.get(i).End);
            Locations.get(Lines.get(i).End).addConnection(Lines.get(i).Start);
        }

        //初始化用户
        maps = Link.queryMap("select *from user");
        for (int i = 0; i < maps.size(); i++) {
            Users.put((String) (maps.get(i).get("name")), new User(((String) (maps.get(i).get("name"))), (String) (maps.get(i).get("password"))));
        }

        //初始化画板
        this.np = new NewPanel(this);

        //初始化地点
        for (String tmp : Locations.keySet()) {
            Buttons.put(tmp, new DraggableButton(Locations.get(tmp), np));
        }

        //让线与按钮绑定
        Line tmp;
        for (int i = 0; i < Lines.size(); i++) {
            tmp = Lines.get(i);
            if (Buttons.containsKey(tmp.Start)) {
                tmp.setCStart(Buttons.get(tmp.Start));
            }
            if (Buttons.containsKey(tmp.End)) {
                tmp.setCEnd(Buttons.get(tmp.End));
            }
        }

    }

    public int addLocation(String name, int x, int y, String describe) {
        Locations.put(name, new Location(name, x, y, describe));
        return Link.updata("INSERT INTO location (name, x, y, description) VALUES (?, ?, ?, ?)", name, x, y, describe);
    }

    public int deleteLocation(String name) {
        Locations.remove(name);
        //在data中删除地点
        Container container = this.getButtons().get(name).getParent();
        container.remove(this.getButtons().get(name));
        this.getButtons().remove(name);
        //删除按钮
        this.deleteLine(name);
        //删除对应的线
        for (String Key : Locations.keySet()) {
            Locations.get(Key).removeConnection(name);
        }
        //在别的地点中删除对应的关系
        return Link.updata("delete from location where name = ?", name);
    }

    public int changeLocation(String name, String chedname, int x, int y, String describe) {
        //修改地点
        Location tmp = Locations.get(name);
        Locations.remove(name);
        tmp.setName(chedname);
        tmp.setCoord(x, y);
        tmp.setDescribe(describe);
        Locations.put(chedname, tmp);
        //修改按钮
        DraggableButton tmp1 = Buttons.get(name);
        Buttons.remove(name);
        tmp1.setLocation(x + 230, y + 20);
        tmp1.setName(chedname);
        tmp1.setText(chedname);
        Buttons.put(chedname, tmp1);
        //修改线
        for (int i = 0; i < Lines.size(); i++) {
            if (Lines.get(i).Start.equals(name))
                Lines.get(i).Start = chedname;
            else if (Lines.get(i).End.equals(name))
                Lines.get(i).End = chedname;
        }
        //修改关系
        for (String Key : Locations.keySet()) {
            if (Locations.get(Key).getConnections().contains(name)) {
                Locations.get(Key).getConnections().remove(name);
                Locations.get(Key).addConnection(chedname);
            }
        }

        //修改数据库中的表关系
        Link.updata("update line set start = ? where start = ?", chedname, name);
        Link.updata("update line set end = ? where end = ?", chedname, name);
        return Link.updata("update location set name = ?,x = ?,y = ?,description = ? where name = ?", chedname, x, y, describe, name);
    }

    public int addLine(String Start, String End) {
        //增添线
        Lines.add(new Line(Start, End));
        //增添关系
        Locations.get(Start).addConnection(End);
        Locations.get(End).addConnection(Start);
        return Link.updata("insert into line(start,end) values (?,?)", Start, End);
    }

    public int deleteLine(String Start, String End) {
        //删除线
        for (int i = Lines.size() - 1; i >= 0; i--) {
            if ((Lines.get(i).Start.equals(Start) && Lines.get(i).End.equals(End)) || (Lines.get(i).Start.equals(End) && Lines.get(i).End.equals(Start))) {
                Lines.remove(i);
            }
        }
        //删除关系
        for (String Key : Locations.keySet()) {
            if (Locations.get(Key).getConnections().contains(Start))
                Locations.get(Key).getConnections().remove(Start);
            else if (Locations.get(Key).getConnections().contains(End))
                Locations.get(Key).getConnections().remove(End);
        }
        return Link.updata("delete from line where (start=? and end=?) or (start=? and end=?)", Start, End, End, Start);
    }

    public int deleteLine(String name) {
        for (int i = Lines.size() - 1; i >= 0; i--) {
            if (Lines.get(i).Start.equals(name) || Lines.get(i).End.equals(name)) {
                Lines.remove(i);
            }
        }
        for (String Key : Locations.keySet()) {
            if (Locations.get(Key).getConnections().contains(name))
                Locations.get(Key).getConnections().remove(name);
        }
        return Link.updata("delete from line where (Start=?) or ( End=?)", name, name);

    }

    public void addUser() {

    }
}
