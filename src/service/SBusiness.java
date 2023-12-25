package service;

import dao.Business;

import javax.swing.*;
import java.util.ArrayList;

public class SBusiness {
    public static JButton addLocation() {
        return Business.addLocation();
    }

    public static boolean deleteLocation() {
        return Business.deleteLocation();
    }

    public static boolean changeLocation() {
        return Business.changeLocation();
    }

    public static boolean addLine() {
        return Business.addLine();
    }

    public static boolean deleteLine() {
        return Business.deleteLine();
    }

    public static ArrayList<String> findShortest() {
        return Business.findShortest();
    }
}
