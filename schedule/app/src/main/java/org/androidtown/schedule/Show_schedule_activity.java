package org.androidtown.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Show_schedule_activity extends AppCompatActivity {



    private TextView show_shedule_title;
    private TextView show_shedule_month;
    private TextView show_shedule_day;
    private TextView show_shedule_hour;
    private TextView show_shedule_minute;
    private TextView show_shedule_body;

    private Schedule schedule;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schedule_activity);

        Intent intent = getIntent();
        schedule =(Schedule) getIntent().getSerializableExtra("schedule");


        show_shedule_title = (TextView) findViewById(R.id.s_shedule_Title);
        show_shedule_month =(TextView)findViewById(R.id.s_shedule_Month);
        show_shedule_day= (TextView)findViewById(R.id.s_shedule_Day);
        show_shedule_hour = (TextView)findViewById(R.id.s_shedule_Hour);
        show_shedule_minute = (TextView)findViewById(R.id.s_shedule_Minute);
        show_shedule_body = (TextView)findViewById(R.id.s_shedule_Body);

        show_shedule_title.setText(schedule.getTitle());
        show_shedule_month.setText("Mounth : "+ schedule.getMounth()+"");
        show_shedule_day.setText("Day : "+schedule.getDay()+"");
        show_shedule_hour.setText("Hour : "+schedule.getHour()+"");
        show_shedule_minute.setText("Minute : "+schedule.getMinute()+"");
        show_shedule_body.setText(schedule.getBody());
    }
}
