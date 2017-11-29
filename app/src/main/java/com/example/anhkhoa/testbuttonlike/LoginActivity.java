package com.example.anhkhoa.testbuttonlike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText edtgmail,edtpass;
    Button btnnhap;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");
       addControl();
       edtgmail.setText("ceo.goverment@gmail.com");
       edtpass.setText("123");
       addEvent();
    }

    private void addEvent() {
        btnnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               myRef.addChildEventListener(new ChildEventListener() {
                   @Override
                   public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                       User user =dataSnapshot.getValue(User.class);
                       String iduser="";
                        if(user.getEmail().toString().equalsIgnoreCase(edtgmail.getText().toString())){
                            iduser=user.getIduser();
                            Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("iduser",iduser);
                            startActivity(intent);
                            finish();
                        }
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
        });
    }

    private void addControl() {
        edtgmail=findViewById(R.id.edtgmail1);
        edtpass=findViewById(R.id.edtpass1);
        btnnhap=findViewById(R.id.btnlogin);
    }


}
