package com.Konovalov;





import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private Map<String, String> db = new HashMap<>();
private Map<String,Integer> countVoice=new HashMap<>();
private static Statistic state=new Statistic();

    public Statistic() {
    }

    static Statistic getInstance(){
        return state;
    }
    synchronized boolean addPerson(String name, String lastName) {
        if (db.containsKey(name) )
            return true;
        else {
            db.put(name, lastName);
        }
        return false;
    }

    synchronized void addVoice(String voice){
        if (countVoice.containsKey(voice)) countVoice.put(voice, countVoice.get(voice) + 1);
        else countVoice.put(voice, 1);
    }

    public  synchronized String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Voice:").append("<br>");
        countVoice.forEach((key, value)->
        sb.append(key).append("\t").append(value).append("<br>"));
        return sb.toString();
    }
}
