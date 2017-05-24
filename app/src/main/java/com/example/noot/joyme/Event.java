package com.example.noot.joyme;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Event extends AppCompatActivity {

    private Button btnUpdate;
    private EditText edtTitle, edtPlace, edtTime, edtLimitMember;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mAuth = FirebaseAuth.getInstance();

        initInstances();
    }

    private void initInstances() {
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtPlace = (EditText) findViewById(R.id.edtPlace);
        edtTime = (EditText) findViewById(R.id.edtTime);
        edtLimitMember = (EditText) findViewById(R.id.edtLimitMember);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String place = edtPlace.getText().toString();
                String time = edtTime.getText().toString();
                String maxMember = edtLimitMember.getText().toString();
                int limit = Integer.parseInt(maxMember);
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(place) && !TextUtils.isEmpty(time) && !TextUtils.isEmpty(maxMember))
                    if (limit > 1) {
                        createEvent(title, place, time, limit);
                        finish();
                        startActivity(new Intent(Event.this, Profile.class));
                    } else {
                        Toast.makeText(getApplicationContext(),"Member must more than 2",Toast.LENGTH_SHORT).show();
                    }
                else {
                    Toast.makeText(getApplicationContext(),"Please fill in form",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createEvent(String title, String place, String time, int maxMember){
        UserInfo user = mAuth.getCurrentUser();
        DatabaseReference postsRef = Data.getInstance().getRef();
        DatabaseReference newPostRef = postsRef.push();
        Post post = new Post(user.getEmail(), title, place, time, maxMember);

        newPostRef.updateChildren(post.getPost());
        newPostRef.setValue(post.getPost(),
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError != null){
                            Toast.makeText(getApplicationContext(),"FAIL",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Event created",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
