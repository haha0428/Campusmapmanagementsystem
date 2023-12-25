package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Location {
    private String name;

    public  int x;

    public  int y;

    private String describe;

    private HashSet<String> connections=new HashSet<>();

    public Location(String name, Integer x, Integer y, String describe) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.describe = describe;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int[] getCoord(){
        return new int[]{x,y};
    }

    public void setCoord(int x,int y){
        this.x=x;
        this.y=y;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe){
        this.describe=describe;
    }
    public void addConnection(String name){
        connections.add(name);
    }

    public void removeConnection(String name) {
        connections.remove(name);
    }

    public HashSet<String> getConnections() {
        return connections;
    }
}
