package com.example.noot.joyme;


import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Noot on 5/15/2017.
 */

public class Post {

    private String author;
    private String title;
    private String place;
    private String time;
    private int maxNumberMember;
    private int currentNumberMember;
    private Map<String, Object> postUpdate = new HashMap<String, Object>();
    private ArrayList<String> members = new ArrayList<String>();
//    private Map<String, Object> memberUpdate = new HashMap<String, Object>();

    public Post() {

    }

    public Post(String author, String title, String place, String time, int maxNumberMember) {
        currentNumberMember = 1;
        postUpdate.put("author", author);
        postUpdate.put("title", title);
        postUpdate.put("place", place);
        postUpdate.put("time", time);
        postUpdate.put("maxNumberMember", maxNumberMember);
        postUpdate.put("currentNumberMember", currentNumberMember);
        members.add(author);
        postUpdate.put("members", members);
    }

    public void addMember(String member, String key) {
        currentNumberMember++;
        if (currentNumberMember <= maxNumberMember) {
//            postUpdate.put("currentNumberMember", currentNumberMember);
            members.add(member);
//            postUpdate.put("members",members);
            try {
                Data.getInstance().getRef().child(key).child("currentNumberMember").setValue(currentNumberMember);
                Data.getInstance().getRef().child(key).child("members").setValue(members);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeMember(String member, String key) {
        currentNumberMember--;
        members.remove(member);
        try {
            Data.getInstance().getRef().child(key).child("currentNumberMember").setValue(currentNumberMember);
            Data.getInstance().getRef().child(key).child("members").setValue(members);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map<String, Object> getPost() {
        return postUpdate;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public String getAuthor() {
        return author;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxNumberMember() {
        return maxNumberMember;
    }

    public int getCurrentNumberMember() {
        return currentNumberMember;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMaxNumberMember(int maxNumberMember) {
        this.maxNumberMember = maxNumberMember;
    }

}