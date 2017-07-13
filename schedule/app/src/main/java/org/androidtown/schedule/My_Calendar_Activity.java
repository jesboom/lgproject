package org.androidtown.schedule;

import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.HashSet;

public class My_Calendar_Activity extends AppCompatActivity {

    ArrayList<Schedule> schedule_ArrayList;
    MaterialCalendarView materialCalendarView;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__calendar_);

        Intent intent = getIntent();
        uid = intent.getStringExtra("id");
        Toast.makeText(this, uid+"",Toast.LENGTH_SHORT).show();
        materialCalendarView = (MaterialCalendarView)findViewById(R.id.my_calander);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2014, 0, 1))
                .setMaximumDate(CalendarDay.from(2020, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        CalendarDay calendarDay = new CalendarDay(2017,6,13);
        CalendarDay calendarDay2 = new CalendarDay(2017,6,11);
        HashSet<CalendarDay> date = new HashSet<>();
        date.add(calendarDay);
        date.add(calendarDay2);

        materialCalendarView.addDecorator(new MyCustomDecorator(Color.RED,4, date));
        //구현한 Decorator(색, 그룹에서 몇번째 사람, 점찍을 날짜)

        //달력 날자 선택시 스케쥴 화면 넘어감 (아래)
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(My_Calendar_Activity.this, ""+date.getYear()+":"+date.getMonth()+":"+date.getDay(),Toast.LENGTH_SHORT).show();
                //테스트 하기위해

                Intent selected_Day_Shedule_intent = new Intent(My_Calendar_Activity.this, My_Calendar_Selected_Day_Shedule.class); ;
                selected_Day_Shedule_intent.putExtra("uid",uid);
                selected_Day_Shedule_intent.putExtra("year",date.getYear());
                selected_Day_Shedule_intent.putExtra("month",date.getMonth());
                selected_Day_Shedule_intent.putExtra("day",date.getDay());
                startActivity(selected_Day_Shedule_intent);
                //년 월 일 넘겨 줘서 새 액티비티에서 검색하게 하자 (위)

            }
        });

        databaseReference.child("Users").child(uid).child("schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashSet<CalendarDay> date = new HashSet<>();
                //넣기전에 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Schedule temp_schedule = snapshot.getValue(Schedule.class);  // schedule 데이터를 가져오고

                    CalendarDay calendarDay2 = CalendarDay.from(temp_schedule.getYear(),
                                                                 temp_schedule.getMounth(),
                                                                 temp_schedule.getDay());
                    //getNew Instance (위)
                    // 캘린더 년, 달, 일 설정후 HashSet에 넣는다.
                   // schedule_ArrayList.add(temp_schedule);
                    date.add(calendarDay2);
                }
                materialCalendarView.addDecorator(new EventDecorator1(Color.BLUE, date));
                //HashSet에 넣어진 CalendarDay 를 기반으로 달력에 점을 그린다.
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
