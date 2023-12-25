package domain;

import java.util.HashMap;

public class Result {
    private HashMap<String, Object> result=new HashMap<>();

    public Result(String... object) {
        for (String tmp : object) {
        result.put(tmp,null);
        }
    }
    public HashMap<String, Object> getResult(){
        return result;
    }
}
