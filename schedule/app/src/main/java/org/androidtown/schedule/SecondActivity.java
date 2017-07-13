

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
    private String uid;
    private String userName;
    private Button myCalandal_button;
    private Button group_Calandal_button;
    private Button id_setting_button;
    private Button logout_button;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        uid = user.getUid()+"";

        userName = "user" + new Random().nextInt(10000);

        myCalandal_button= (Button) findViewById(R.id.mycalandal_button);
        group_Calandal_button = (Button) findViewById(R.id.group_calandal_button);
        id_setting_button= (Button) findViewById(R.id.id_setting_button);
        logout_button = (Button) findViewById(R.id.logout_button);

        myCalandal_button.setText(uid);

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(SecondActivity.this, LoginActivity.class));
            }
        });
        myCalandal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent my_calendar_intent = new Intent(SecondActivity.this,My_Calendar_Activity.class); ;
                my_calendar_intent.putExtra("id",uid);
                startActivity(my_calendar_intent);
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
                setting_activity_intent.putExtra("id",uid);
                startActivity(setting_activity_intent);
            }
        });
        Groups groups = new Groups(true);
    }
}
