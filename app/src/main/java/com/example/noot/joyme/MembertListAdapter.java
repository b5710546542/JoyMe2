package com.example.noot.joyme;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Noot on 5/14/2017.
 */

public class MembertListAdapter extends BaseAdapter {

    private int keyEvent;

    public int getKeyEvent() {
        return keyEvent;
    }

    public void setKeyEvent(int keyEvent) {
        this.keyEvent = keyEvent;
    }

    // Don't use this method
    @Override
    public int getCount() {
        return Data.getInstance().getEventPost().get(keyEvent).getMembers().size();
    }

    // Don't use this method
    @Override
    public Object getItem(int position) {
        return Data.getInstance().getEventPost().get(keyEvent).getMembers().get(position);
    }

    // Don't use this method
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MembertList membertList;
        if(convertView != null){
            membertList = (MembertList) convertView;
        }else{
            membertList = new MembertList(parent.getContext());
        }

        String member = (String) getItem(position);
        membertList.setMemberView(member);

        return membertList;
    }

}
