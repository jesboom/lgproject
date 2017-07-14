package org.androidtown.schedule;

import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
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

public class Group_Calendar_Activity extends AppCompatActivity
{
    ArrayList<String> group_members_uids = new ArrayList<>();
    MaterialCalendarView materialCalendarView;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private String uid;
    private String gid;
    private  int color;
    private int position;
    private ArrayList< ArrayList<CalendarDay>> array_hash_set;
    public  HashSet<CalendarDay> date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group__calendar_);

        Intent intent = getIntent();
        gid = intent.getStringExtra("gid");
        uid = intent.getStringExtra("uid");


        Toast.makeText(this, uid + ":::!!!!!!!!!!!!!!!!!::: " + gid, Toast.LENGTH_LONG).show();

        materialCalendarView = (MaterialCalendarView) findViewById(R.id.group_calander);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2014, 0, 1))
                .setMaximumDate(CalendarDay.from(2020, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        CalendarDay calendarDay = new CalendarDay(2017, 6, 13);
        CalendarDay calendarDay2 = new CalendarDay(2017, 6, 11);
        HashSet<CalendarDay> date = new HashSet<>();
        date.add(calendarDay);
        date.add(calendarDay2);

        // materialCalendarView.addDecorator(new MyCustomDecorator(Color.RED,4, date));
        //구현한 Decorator(색, 그룹에서 몇번째 사람, 점찍을 날짜)

        //달력 날자 선택시 스케쥴 화면 넘어감 (아래)
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(Group_Calendar_Activity.this, "" + date.getYear() + ":" + date.getMonth() + ":" + date.getDay(), Toast.LENGTH_SHORT).show();
                //테스트 하기위해

                Intent selected_Day_Shedule_intent = new Intent(Group_Calendar_Activity.this, My_Calendar_Selected_Day_Shedule.class);
                ;
                selected_Day_Shedule_intent.putExtra("uid", uid);
                selected_Day_Shedule_intent.putExtra("year", date.getYear());
                selected_Day_Shedule_intent.putExtra("month", date.getMonth());
                selected_Day_Shedule_intent.putExtra("day", date.getDay());
                startActivity(selected_Day_Shedule_intent);
                //년 월 일 넘겨 줘서 새 액티비티에서 검색하게 하자 (위)

            }
        });
        //gid로  group안의 멤버 검색
        databaseReference.child("Groups").child(gid).child("member").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                group_members_uids.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String temp_id = snapshot.getKey() + "";
                    group_members_uids.add(temp_id);
                }
                array_hash_set = new ArrayList<ArrayList<CalendarDay>>();

                for(int i =0; i< group_members_uids.size(); i++)
                {
                  //  Toast.makeText(Group_Calendar_Activity.this, "group_members_uids: " + group_members_uids.get(i), Toast.LENGTH_SHORT).show();

                    databaseReference.child("Users").child(group_members_uids.get(i)).child("schedule").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot)
                        {
                            ArrayList<CalendarDay> date = new ArrayList<>();

                            for(DataSnapshot snapshot : dataSnapshot.getChildren())
                            {
                                Schedule temp_schedule = snapshot.getValue(Schedule.class);  // schedule 데이터를 가져오고

                                CalendarDay calendarDay2 = CalendarDay.from(temp_schedule.getYear(),
                                        temp_schedule.getMounth(),
                                        temp_schedule.getDay());
                                //가져온 데이터로 날자를 지정하고

                                date.add(calendarDay2);
                                //어레이 리스트에 저장.
                            }
                            array_hash_set.add(date);
                            //어레이 리스트의 어레이 리스트에 날자 저장

                            for(int i = 0; i< array_hash_set.size() ; i++)
                            {
                                //사람 수랑 받은 데이터 수가 일치할때 그린다.
                                 if(array_hash_set.size() == group_members_uids.size())
                                 {
                                     //저장된 날자들로 점을 그린다!.
                                     switch (i)
                                     {
                                         case 0:
                                             materialCalendarView.addDecorator(new MyCustomDecorator(Color.RED, i+1, array_hash_set.get(i)));
                                             break;
                                         case 1:
                                             materialCalendarView.addDecorator(new MyCustomDecorator(Color.BLUE, i+1,  array_hash_set.get(i)));
                                             break;
                                         case 2:
                                             materialCalendarView.addDecorator(new MyCustomDecorator(Color.CYAN, i+2,  array_hash_set.get(i)));
                                             break;
                                         case 3:
                                             materialCalendarView.addDecorator(new MyCustomDecorator(Color.GREEN, i+3,  array_hash_set.get(i)));
                                             break;
                                         case 4:
                                             materialCalendarView.addDecorator(new MyCustomDecorator(Color.MAGENTA, i+4,  array_hash_set.get(i)));
                                     }
                                 }
                            }
                            //materialCalendarView.addDecorator(new EventDecorator1(Color.BLUE, date));
                           // materialCalendarView.addDecorator(new MyCustomDecorator(color ,position, date));
                            //저장된 날자들로 점을 그린다!.
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });

                   // Toast.makeText(Group_Calendar_Activity.this, "asdfsadf adfas color is" + Color.RED+"",Toast.LENGTH_LONG).show();
                }

              //  Toast.makeText(Group_Calendar_Activity.this, "Enddddddddddddddddddddddddddd", Toast.LENGTH_SHORT).show();
            }
            //materialCalendarView.addDecorator(new MyCustomDecorator(Color.RED,4, date));
            /*
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
            */
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        /*
        Toast.makeText(this, "start sleep", Toast.LENGTH_SHORT);
        SystemClock.sleep(5000);
        Toast.makeText(this, "start second sleep", Toast.LENGTH_SHORT);
        SystemClock.sleep(5000);
        Toast.makeText(this, "end sleep", Toast.LENGTH_SHORT);
        for(int j =0 ; j< array_hash_set.size(); j++)
        {
            for(int k =0 ; j< array_hash_set.get(k).size(); k++)
            {
                Toast.makeText(Group_Calendar_Activity.this, ""+array_hash_set.get(j).get(k), Toast.LENGTH_SHORT).show();
            }
        }
        */
    }
}
