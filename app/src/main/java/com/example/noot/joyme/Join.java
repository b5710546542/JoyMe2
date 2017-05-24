package com.example.noot.joyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

import java.util.ArrayList;

public class Join extends AppCompatActivity {

    private TextView ownerJoin, titleJoin, placeJoin, timeJoin, maxmemberJoin;
    private ListView listJoin;
    private String key;
    private int index;
    private UserInfo userInfo;
    private Button btnJoin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        key = getIntent().getExtras().getString("EXTRA_ITEM_KEY");
        index = Data.getInstance().getKeyPost().indexOf(key);
        mAuth = FirebaseAuth.getInstance();
        userInfo = mAuth.getCurrentUser();
        initInstances();
    }

    private void initInstances() {
        ownerJoin = (TextView) findViewById(R.id.ownerView);
        titleJoin = (TextView) findViewById(R.id.titleJoin);
        placeJoin = (TextView) findViewById(R.id.placeJoin);
        timeJoin = (TextView) findViewById(R.id.timeJoin);
        maxmemberJoin = (TextView) findViewById(R.id.maxMemberJoin);
        listJoin = (ListView) findViewById(R.id.listJoin);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        setUpJoin();

        MembertListAdapter membertListAdapter = new MembertListAdapter();
        membertListAdapter.setKeyEvent(index);
        listJoin.setAdapter(membertListAdapter);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnJoin.getText().toString().equals("OUT")){
                    Data.getInstance().getEventPost().get(index).removeMember(userInfo.getEmail(),key);
                    setUpJoin();
                    Toast.makeText(Join.this,"You get out of this group",Toast.LENGTH_SHORT).show();
                } else {
                    Data.getInstance().getEventPost().get(index).addMember(userInfo.getEmail(),key);
                    setUpJoin();
                    Toast.makeText(Join.this,"You join this group",Toast.LENGTH_SHORT).show();
                }

                finish();
                startActivity(new Intent(Join.this,Profile.class));
            }
        });

        setInitInfo();
    }

    private void setUpJoin(){
        if (Data.getInstance().getEventPost().get(index).getMembers().contains(userInfo.getEmail())){
            btnJoin.setText("OUT");
        }else{
            btnJoin.setText("JOIN");
        }
    }

    private void setInitInfo (){
        int id = Data.getInstance().getKeyPost().indexOf(key);
        Post post = Data.getInstance().getEventPost().get(id);

        ownerJoin.setText("Owner " + post.getAuthor());
        titleJoin.setText("Title " + post.getTitle());
        placeJoin.setText("Place " + post.getPlace());
        timeJoin.setText("Time " + post.getTime());
        maxmemberJoin.setText("Member " + post.getCurrentNumberMember() + "/" + post.getMaxNumberMember());
    }

}
