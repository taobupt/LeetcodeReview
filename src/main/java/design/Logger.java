package design;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tao on 4/19/17.
 */
public class Logger {

    private Map<String,Integer> map=null;
    /** Initialize your data structure here. */
    public Logger() {
        map=new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(!map.containsKey(message)){
            map.put(message,timestamp);
            return true;
        }else{
            if(timestamp-map.get(message)<10)
                return false;
            else{
                map.put(message,timestamp);
                return  true;
            }
        }
    }
}
