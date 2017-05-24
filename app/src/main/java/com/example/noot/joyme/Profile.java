package com.example.noot.joyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    private Button btnEvent;
    private TextView textUsername;
    private FirebaseAuth mAuth;

    private ListView listView;
    private MyEventListAdapter eventListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        initInstances();
    }

    private void initInstances() {
        btnEvent = (Button) findViewById(R.id.btnEvent);
        textUsername = (TextView) findViewById(R.id.textUsername);
        listView = (ListView) findViewById(R.id.listView);
        eventListAdapter = new MyEventListAdapter();
        listView.setAdapter(eventListAdapter);

        updateUser();

        btnEvent.setOnClickListener(listener);
        Data.getInstance().getRef().addChildEventListener(childEventListener);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //send key to join page
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemId = Data.getInstance().getKeyPost().get(position);
                Intent mIntent = new Intent(Profile.this, Join.class);
                mIntent.putExtra("EXTRA_ITEM_KEY", itemId);
                startActivity(mIntent);
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnEvent:
                    startActivity(new Intent(Profile.this, EventMap.class));
                    break;
            }
        }
    };

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

    private void updateUser() {
        FirebaseUser user = mAuth.getCurrentUser();
        textUsername.setText(user.getEmail());
    }

}
