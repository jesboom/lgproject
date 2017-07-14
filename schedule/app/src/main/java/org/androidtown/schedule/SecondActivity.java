

package org.androidtown.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class SecondActivity extends AppCompatActivity
{
    private String uid;
    private String userName;
    private String gid;
    private Button myCalandal_button;
    private Button group_Calandal_button;
    private Button test_button;
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
        test_button = (Button) findViewById(R.id.test_button);
        id_setting_button= (Button) findViewById(R.id.id_setting_button);
        logout_button = (Button) findViewById(R.id.logout_button);
        group_Calandal_button = (Button) findViewById(R.id.Group_Calendar_Button);

        myCalandal_button.setText(uid);

        group_Calandal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(gid != null) {
                    Intent group_calendar_activity_intent = new Intent(SecondActivity.this, Group_Calendar_Activity.class);
                    group_calendar_activity_intent.putExtra("uid", uid);
                    group_calendar_activity_intent.putExtra("gid", gid);
                    startActivity(group_calendar_activity_intent);
                }
            }
        });
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
        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent test_activity_intent = new Intent(SecondActivity.this,Test_Activity.class); ;
                test_activity_intent.putExtra("id",uid);
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

        //나중에 그룹이 여러개가 되면 수정해야함!!!!!!!!!
        databaseReference.child("Users").child(uid).child("groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //넣기전에 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    gid = snapshot.getKey()+"";
                    Toast.makeText(SecondActivity.this,"get gid : "+ gid ,Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
