package service;

import dao.Log;
import util.Link;

public class SLog {


    public static boolean Log(String name,String password){
        return Log.Log(name,password);
    }

    public static boolean Register(String name,String password,String repassword){
        return Log.Register(name, password,repassword);
    }

    public static int addUser(String name,String password){
        return Log.addUser(name,password);
    }
    private SLog() {
    }
}
