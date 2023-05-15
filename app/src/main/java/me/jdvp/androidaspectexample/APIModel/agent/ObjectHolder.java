package me.jdvp.androidaspectexample.APIModel.agent;

import java.util.ArrayList;

public class ObjectHolder {
    private static ArrayList<AttendanceResponse> objectList;

    public static ArrayList<AttendanceResponse> getObjectList() {
        return objectList;
    }

    public static void setObjectList(ArrayList<AttendanceResponse> list) {
        objectList = list;
    }
}
