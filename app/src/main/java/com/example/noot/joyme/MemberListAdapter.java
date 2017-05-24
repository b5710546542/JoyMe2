package com.example.noot.joyme;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Noot on 5/14/2017.
 */

public class MemberListAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return Data.getInstance().getEventMembers().size();
    }

    @Override
    public Object getItem(int position) {
        return Data.getInstance().getEventMembers().get(position);
    }

    // Don't use this method
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MemberList memberList;
        if(convertView != null){
            memberList = (MemberList) convertView;
        }else{
            memberList = new MemberList(parent.getContext());
        }

        Post post = (Post) getItem(position);
//        event.setTitleView("Title " + post.getTitle());
//        event.setPlaceView("Place " + post.getPlace());
//        event.setTimeView("Time " + post.getTime());
//        event.setLimitNumberView("Member " + post.getMaxNumberMember()+"");

        return memberList;
    }

}
