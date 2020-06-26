package com.soundberry.hackers.soundberry;

/**
 * Created by Minwoo on 2016. 9. 23..
 */

public class Session {
    private static boolean LOGIN;
    private static String ID;
    private static String IOT_NUM;

    public static String getID() {
        return ID;
    }

    public static String getIotNum() {
        return IOT_NUM;
    }
}
