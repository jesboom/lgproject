

package org.androidtown.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class SecondActivity extends AppCompatActivity
{
    private String id;
    private String userName;
    private Button myCalandal_button;
    private Button group_Calandal_button;
    private Button id_setting_button;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Intent intent = getIntent();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        id = user.getUid()+"";
        //id = firebaseAuth.getCurrentUser().getUid()+"";
        userName = "user" + new Random().nextInt(10000);

        myCalandal_button= (Button) findViewById(R.id.mycalandal_button);
        group_Calandal_button = (Button) findViewById(R.id.group_calandal_button);
        id_setting_button= (Button) findViewById(R.id.id_setting_button);

        myCalandal_button.setText(id);

        myCalandal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        group_Calandal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent test_activity_intent = new Intent(SecondActivity.this,Test_Activity.class); ;

                startActivity(test_activity_intent);
            }
        });
        id_setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent setting_activity_intent = new Intent(SecondActivity.this,SettingActivity.class);
                setting_activity_intent.putExtra("id",id);
                startActivity(setting_activity_intent);
            }
        });

        //Schedule schedule = new Schedule()
        Groups groups = new Groups(true);
      //  User user = new User(userName,groups,schedule);

        //databaseReference.child("Users").child(id).setValue(user);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기



    }
}
