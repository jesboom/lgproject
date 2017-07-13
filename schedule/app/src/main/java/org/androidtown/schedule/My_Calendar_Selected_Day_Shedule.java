package org.androidtown.schedule;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.HashSet;

public class My_Calendar_Selected_Day_Shedule extends AppCompatActivity {
    private int year;
    private int mouth;
    private int day;
    private String uid;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__calendar__selected__day__shedule);
        ListView listView = (ListView) findViewById(R.id.my_calander_selected_day_schedule);

        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        year = intent.getIntExtra("year",0);
        mouth = intent.getIntExtra("month",0);
        day =intent.getIntExtra("day",0);
        //달력에서 년 월 일 받아옴

        Toast.makeText(this, uid + " :: " + year+": " + mouth+ " : "+ day,Toast.LENGTH_SHORT).show();
        // 제대로 받아 온지 테스트

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);
        // 어댑터 설정

        listView.setAdapter( adapter);
        // 리스트 뷰에 어댑터 셋

        databaseReference.child("Users").child(uid).child("schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashSet<CalendarDay> date = new HashSet<>();
                //넣기전에 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    String temp_key = snapshot.getKey();
                    Schedule temp_schedule = snapshot.getValue(Schedule.class);  // schedule 데이터를 가져오고

                    if(temp_schedule.getYear() == year && temp_schedule.getMounth() == mouth && temp_schedule.getDay() == day)
                    {
                        adapter.add(temp_schedule.getYear()+"."+temp_schedule.getMounth()
                                     +"."+temp_schedule.getDay()+ " Schedule : " + temp_key );
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}