package dao;

import domain.Location;
import domain.Result;
import newcomponents.DraggableButton;
import util.Calculate;
import view.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Business {
    private static Data data;

    private static Result result;

    private static HashMap<String, Object> tmp;

    public Business(Data data) {
        this.data = data;
    }

    public static JButton addLocation() {
        tmp = new AddLocationInterface(data).returnResult().getResult();
        if (!(Boolean) tmp.get("state"))
            return null;
        if (data.getLocations().containsKey(tmp.get("name")) || ((String) tmp.get("name")).length() == 0) {
            new ErrorInterface("该名字已存在或名字为空");
            return null;
        }
        if (Integer.parseInt((String) tmp.get("x")) < 0 || Integer.parseInt((String) tmp.get("y")) < 0 || Integer.parseInt((String) tmp.get("x")) > 668 || Integer.parseInt((String) tmp.get("y")) > 778) {
            new ErrorInterface("坐标越界");
            return null;
        }
        data.addLocation((String) tmp.get("name"), Integer.parseInt((String) tmp.get("x")), Integer.parseInt((String) tmp.get("y")), (String) tmp.get("describe"));
        data.getButtons().put((String) tmp.get("name"), new DraggableButton(data.getLocations().get(tmp.get("name")), data.getNp()));
        return data.getButtons().get((String) tmp.get("name"));
    }

    public static boolean deleteLocation() {
        tmp = new DeleteLocationInterface(data).returnResult().getResult();
        if (!(Boolean) tmp.get("state"))
            return false;
        if (!data.getLocations().containsKey((String) tmp.get("name")) || ((String) tmp.get("name")).length() == 0) {
            new ErrorInterface("该名字不存在或名字为空");
            return false;
        }
        data.deleteLocation((String) tmp.get("name"));
        data.getNp().repaint();
        return true;
    }

    public static boolean changeLocation() {
        tmp = new ChangeLocationInterface(data).returnResult().getResult();
        if (!(Boolean) tmp.get("state"))
            return false;
        if ((!data.getLocations().containsKey(tmp.get("name"))) || ((String) tmp.get("name")).length() == 0) {
            new ErrorInterface("该名字不存在或名字为空");
            return false;
        }
        if ((data.getLocations().containsKey(tmp.get("chedname"))&&!tmp.get("name").equals(tmp.get("chedname"))) || ((String) tmp.get("chedname")).length() == 0) {
            new ErrorInterface("该修改名字已存在或名字为空");
            return false;
        }
        if (Integer.parseInt((String) tmp.get("x")) < 0 || Integer.parseInt((String) tmp.get("y")) < 0 || Integer.parseInt((String) tmp.get("x")) > 668 || Integer.parseInt((String) tmp.get("y")) > 778) {
            new ErrorInterface("坐标越界");
            return false;
        }
        data.changeLocation((String) tmp.get("name"), (String) tmp.get("chedname"), Integer.parseInt((String) tmp.get("x")), Integer.parseInt((String) tmp.get("y")), (String) tmp.get("describe"));
        data.getNp().changeLocation(data.getButtons().get((String) tmp.get("chedname")));
        return true;
    }

    public static boolean addLine() {

        tmp = new AddLineInterface(data).returnResult().getResult();
        if (!(Boolean) tmp.get("state"))
            return false;
        for (int i = 0; i < data.getLines().size(); i++) {
            if ((data.getLines().get(i).Start.equals(tmp.get("Start")) && data.getLines().get(i).End.equals((String) tmp.get("End"))) || (data.getLines().get(i).Start.equals((String) tmp.get("End")) && data.getLines().get(i).End.equals((String) tmp.get("Start")))) {
                new ErrorInterface("该线已存在");
                return false;
            }
        }
        data.addLine((String) tmp.get("Start"), (String) tmp.get("End"));
        data.getLines().get(data.getLines().size() - 1).setCStart(data.getButtons().get((String) tmp.get("Start")));
        data.getLines().get(data.getLines().size() - 1).setCEnd(data.getButtons().get((String) tmp.get("End")));
        data.getNp().repaint();
        return true;
    }

    public static boolean deleteLine() {
        tmp = new DeleteLineInterface(data).returnResult().getResult();
        if (!(Boolean) tmp.get("state"))
            return false;
        for (int i = 0; i < data.getLines().size(); i++) {
            if ((data.getLines().get(i).Start.equals((String) tmp.get("Start")) && data.getLines().get(i).End.equals((String) tmp.get("End"))) || (data.getLines().get(i).Start.equals((String) tmp.get("End")) && data.getLines().get(i).End.equals((String) tmp.get("Start")))) {
                data.deleteLine((String) tmp.get("Start"), (String) tmp.get("End"));
                data.getNp().repaint();
                return true;
            }
        }
        new ErrorInterface("该线不存在");
        return false;
    }

    public static ArrayList<String> findShortest() {
        tmp = new FindShortestInteface(data).returnResult().getResult();
        if (!(Boolean) tmp.get("state"))
            return null;

        ArrayList<String> result = new ArrayList();

        HashMap<String, Integer> book = new HashMap();

        HashMap<Integer, String> opbook = new HashMap();

        HashMap<String, Location> Locations = data.getLocations();

        HashMap<Integer, HashMap<Integer, Integer>> nexts = new HashMap();

        int j = 0;
        for (String Key : Locations.keySet()) {
            book.put(Key, j++);
            opbook.put(book.get(Key), Key);
        }
        //初始化邻接表
        for (String Key : Locations.keySet()) {
            nexts.put(book.get(Key), new HashMap<Integer, Integer>());
            for (String Key1 : Locations.get(Key).getConnections()) {

                nexts.get(book.get(Key)).put(book.get(Key1), Calculate.getLength(Locations.get(Key), Locations.get(Key1)));

            }
        }

    //0是前驱，1是是否遍历过，2是最短路
        int[][] arr = new int[3][book.size()];
    //初始化数组
        Arrays.fill(arr[0], -1);
        Arrays.fill(arr[2], 999999);
        arr[2][book.get((String) tmp.get("Start"))]=0;
        for (int i = book.get((String) tmp.get("Start")), index = -1, min; i != -1 && i != book.get((String) tmp.get("End")); i = index) {

            index = -1;
            min = 999999;
            arr[1][i] = 1;

            for (int k = 0; k < book.size(); k++) {

                if (arr[1][k] == 1 || !nexts.get(i).containsKey(k)) {
                    continue;
                }
                if (arr[2][k] > arr[2][i] + nexts.get(i).get(k)) {
                    System.out.println("最小路更新"+opbook.get(i)+"->"+opbook.get(k)+"将最小值从"+arr[2][k]+"替换为"+(arr[2][i] + nexts.get(i).get(k)));
                    arr[2][k] = arr[2][i] + nexts.get(i).get(k);
                    arr[0][k] = i;
                }
            }
            for (int k = 0; k < book.size(); k++){
                if (min > arr[2][k]&&arr[1][k] != 1) {
                    min = arr[2][k];
                    index = k;
                }
            }
        }

        int i;
        for (i = book.get((String) tmp.get("End")); i != -1 && i != book.get((String) tmp.get("Start")); i = arr[0][i]) {
            result.add(opbook.get(arr[0][i]));
        }
        if (i == -1)
            return null;
        result.add(0,(String) tmp.get("End"));
        return result;
    }
}
