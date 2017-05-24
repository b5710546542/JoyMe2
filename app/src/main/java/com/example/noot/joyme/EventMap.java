package com.example.noot.joyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class EventMap extends AppCompatActivity {

    Button btnCreateEvent;
    ListView allEventList;
    private EventListAdapter eventListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_map);
        initInstances();
    }

    private void initInstances() {
        btnCreateEvent = (Button) findViewById(R.id.btnCreateEvent);
        allEventList = (ListView) findViewById(R.id.allEventList);

        eventListAdapter = new EventListAdapter();
        allEventList.setAdapter(eventListAdapter);
        allEventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //send key to join page
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemId = Data.getInstance().getKeyPost().get(position);
                Intent mIntent = new Intent(EventMap.this, Join.class);
                mIntent.putExtra("EXTRA_ITEM_KEY", itemId);
                startActivity(mIntent);
            }
        });

        Data.getInstance().getRef().addChildEventListener(childEventListener);

        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(EventMap.this,Event.class));
            }
        });
    }

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
            Data.getInstance().updateData(dataSnapshot);
            eventListAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            Data.getInstance().updateData(dataSnapshot);
            eventListAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Data.getInstance().removeData(dataSnapshot);
            eventListAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            Data.getInstance().updateData(dataSnapshot);
            eventListAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }

    };
}
