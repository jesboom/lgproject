package org.androidtown.schedule;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.HashSet;

public class My_Calendar_Selected_Day_Shedule extends AppCompatActivity {
    private int year;
    private int mouth;
    private int day;
    private String uid;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private ArrayAdapter<String> adapter;
    private ArrayList<Schedule> schedule_ArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__calendar__selected__day__shedule);
        ListView listView = (ListView) findViewById(R.id.my_calander_selected_day_schedule);

        Intent intent = getIntent();
        schedule_ArrayList =(ArrayList<Schedule>) getIntent().getSerializableExtra("schedule_ArrayList");
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(My_Calendar_Selected_Day_Shedule.this,"position: "+position+", id:"+ id,Toast.LENGTH_SHORT).show();

                Intent show_schedule_intent = new Intent(My_Calendar_Selected_Day_Shedule.this, Show_schedule_activity.class);

                show_schedule_intent.putExtra("schedule", schedule_ArrayList.get(position) );
                startActivity(show_schedule_intent);
            }
        });
        // 리스트 뷰에 어댑터 셋

        for(Schedule schedules :schedule_ArrayList )
        {
            int temp_year=schedules.getYear();
            int temp_mount= schedules.getMounth();
            int temp_day = schedules.getDay();

            if(temp_year== year && temp_mount== mouth && temp_day== day)
            {
                temp_mount = temp_mount+1;
                adapter.add(temp_year+"."+temp_mount
                        +"."+temp_day + " :: Schedule Title :: " +schedules.getTitle() );
            }
        }


        /* 이전 데이타셋에서의 리스너
        databaseReference.child("Users").child(uid).child("schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<CalendarDay> date = new ArrayList<>();
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
        */
    }
}
