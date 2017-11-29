package com.example.anhkhoa.testbuttonlike;

import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CompletionException;

import AdapterRecycler.AdapterRecyclerView;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef,myRefLikeuser;
    RecyclerView recyclerView;
    ArrayList<Comment>commentArrayList;
    AdapterRecyclerView adapterRecyclerView;
    int bitlike=0;
    String iduserlike="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iduserlike=getIntent().getStringExtra("iduser");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Comment");
        myRefLikeuser=database.getReference("Likeuser");
        addControl();
        addEvent();
        adapterRecyclerView.notifyDataSetChanged();
        recyclerView.setAdapter(adapterRecyclerView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(getIntent());
    }

    private void addEvent() {
          myRef.addChildEventListener(new ChildEventListener() {
              @Override
              public void onChildAdded(final DataSnapshot dataSnapshot2, String s) {
                  Comment comment= dataSnapshot2.getValue(Comment.class);
                  commentArrayList.add(comment);
                  adapterRecyclerView.notifyDataSetChanged();
                  Toast.makeText(MainActivity.this, "for 1", Toast.LENGTH_SHORT).show();

              }

              @Override
              public void onChildChanged(DataSnapshot dataSnapshot, String s) {

              }

              @Override
              public void onChildRemoved(DataSnapshot dataSnapshot) {

              }

              @Override
              public void onChildMoved(DataSnapshot dataSnapshot, String s) {

              }

              @Override
              public void onCancelled(DatabaseError databaseError) {

              }
          });

    }


    private void addControl() {
        recyclerView=findViewById(R.id.recyclerlike);
        commentArrayList=new ArrayList<>();

        adapterRecyclerView=new AdapterRecyclerView(commentArrayList,getApplicationContext(),iduserlike);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

    }
}
