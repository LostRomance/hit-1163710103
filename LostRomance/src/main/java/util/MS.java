package util;

import java.sql.Timestamp;

public class MS {

    public static void begin(String ip){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        System.out.println("<<");
        System.out.println(time);
        System.out.println("ip:"+ip);

    }
    public static void end(String json){
        System.out.println(json);
        System.out.println(">>");
    }

}
