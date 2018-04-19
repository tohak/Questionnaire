package com.Konovalov;


import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private Map<String, String> db = new HashMap<>();
    private static DataBase stat = new DataBase();

    public DataBase() {
    }

    public static DataBase getInstance() {
        return stat;
    }

    public synchronized boolean addPerson(String name, String lastName) {

        if (db.containsKey(name) )
            return true;
        else {
            db.put(name, lastName);
        }
        return false;
    }
}
