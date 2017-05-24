package com.example.noot.joyme;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Noot on 5/21/2017.
 */

public class EventMember {

    private String author;
    private int idMember = 1;
    private Map<String, Object> memberUpdate = new HashMap<String, Object>();;

    public EventMember(String author){
        addMember(author);
    }

    public void addMember(String member){

        String keymember = idMember+"";
        memberUpdate.put(keymember,member);
        idMember++;
    }

    public Map<String, Object> getMemberUpdate(){
        return memberUpdate;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}
