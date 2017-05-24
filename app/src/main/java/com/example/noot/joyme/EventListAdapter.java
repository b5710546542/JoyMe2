package com.example.noot.joyme;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Noot on 5/14/2017.
 */

public class EventListAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return Data.getInstance().getEventPost().size();
    }

    @Override
    public Object getItem(int position) {
        return Data.getInstance().getEventPost().get(position);
    }

    // Don't use this method
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventList event;
        if(convertView != null){
            event = (EventList) convertView;
        }else{
            event = new EventList(parent.getContext());
        }

        Post post = (Post) getItem(position);
        event.setTitleView("Title " + post.getTitle());
        event.setPlaceView("Place " + post.getPlace());
        event.setTimeView("Time " + post.getTime());
        event.setLimitNumberView("Member " + post.getCurrentNumberMember() + "/" + post.getMaxNumberMember());

        return event;
    }

}
