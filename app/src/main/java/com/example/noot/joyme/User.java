package com.example.noot.joyme;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Noot on 5/15/2017.
 */

public class User {

    private String author;
    private Map<String, Object> userUpdate = new HashMap<String, Object>();
    private ArrayList<String> friendList = new ArrayList<String>();
    private ArrayList<String> eventList = new ArrayList<String>();

    public User(){

    }

    public User(String author) {
        userUpdate.put("author",author);
        userUpdate.put("friend",friendList);
        userUpdate.put("event",eventList);
    }

    public Map<String, Object> getUserUpdate(){
        return userUpdate;
    }

    public ArrayList<String> getFriendList(){
        return friendList;
    }

    public ArrayList<String> getEventList(){
        return eventList;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}