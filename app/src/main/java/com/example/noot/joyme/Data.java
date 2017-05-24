package com.example.noot.joyme;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.util.ArrayList;


public class Data {

    private static Data instance;
    private ArrayList<Post> myEventPost = new ArrayList<Post>();
    private ArrayList<String> myKeyPost = new ArrayList<String>();
    private ArrayList<Post> eventPost = new ArrayList<Post>();
    private ArrayList<String> keyPost = new ArrayList<String>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/events/posts");

    public static Data getInstance() {
        if (instance == null)
            instance = new Data();
        return instance;
    }

    private Context mContext;

    private Data() {
        mContext = Contextor.getInstance().getContext();
    }

    public void insertPost(Post post){
        eventPost.add(post);
    }

    public ArrayList<Post> getEventPost() {
        return eventPost;
    }

    public void insertMyPost(Post post){
        myEventPost.add(post);
    }

    public ArrayList<Post> getMyEventPost() {
        return myEventPost;
    }

    public void insertKeyPost(String key){
        keyPost.add(key);
    }

    public void insertMyKeyPost(String key){
        myKeyPost.add(key);
    }

    public ArrayList<String> getKeyPost() {
        return keyPost;
    }

    public void replaceData(int i, Post post){
        eventPost.set(i, post);
    }

    public void replaceMyData(int i, Post post){
         myEventPost.set(i, post);
    }

    public DatabaseReference getRef() {
        return ref;
    }

    public void updateData(DataSnapshot dataSnapshot){
        Post newPost = dataSnapshot.getValue(Post.class);
        UserInfo userInfo = mAuth.getCurrentUser();
        boolean isMyKey = newPost.getMembers().contains(userInfo.getEmail());
        // update key and item
        if (!keyPost.contains(dataSnapshot.getKey())){
            insertPost(newPost);
            insertKeyPost(dataSnapshot.getKey());
            if (isMyKey){
                insertMyPost(newPost);
                insertMyKeyPost(dataSnapshot.getKey());
            }
        }else {
            int index = keyPost.indexOf(dataSnapshot.getKey());
            replaceData(index, newPost);

            if (isMyKey) {
                int myIndex = myKeyPost.indexOf(dataSnapshot.getKey());
                replaceMyData(myIndex, newPost);
            }
        }
    }

    public void removeData(DataSnapshot dataSnapshot){
        int index = keyPost.indexOf(dataSnapshot.getKey());
        keyPost.remove(index);
        eventPost.remove(index);

        int myIndex = myKeyPost.indexOf(dataSnapshot.getKey());
        if (myKeyPost.contains(dataSnapshot.getKey())){
            myKeyPost.remove(myIndex);
            myEventPost.remove(myIndex);
        }
    }

}
