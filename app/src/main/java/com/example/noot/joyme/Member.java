package com.example.noot.joyme;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Noot on 5/15/2017.
 */

public class Member {

    private String eventKey;
    private Map<String, Object> memberUpdate = new HashMap<String, Object>();;

    public Member(){

    }

    public Member(String author) {
        memberUpdate.put("author",author);
    }

    public Map<String, Object> getMemberUpdate(){
        return memberUpdate;
    }

    public String getAuthor() {
        return eventKey;
    }

    public void setAuthor(String eventKey) {
        this.eventKey = eventKey;
    }


}