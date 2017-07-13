package org.androidtown.schedule;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends AppCompatActivity
{

    private String id;
    private EditText groupId_text;
    private EditText shedule_text;
    private Button send_groupId_Button;
    private Button send_shedule_Button;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private int year, month, day, hour, minute;
    private DatePickerDialog datePickerDialog;
    private AlertDialog.Builder buider;
    private String shedule_title;
    private String shedule_body;
    private View dialogView;
    private String get_groupId_text_toString;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        groupId_text = (EditText)findViewById(R.id.Group_ID_EditText);

        send_groupId_Button = (Button) findViewById(R.id.Enter_Group_ID_Button);
        send_shedule_Button = (Button) findViewById(R.id.Enter_Your_Shedule_Button);

        send_groupId_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //databaseReference.child("Users").child(id).child("groups").child(groupId_text.getText().toString()).setValue(true);

                get_groupId_text_toString = groupId_text.getText().toString();

                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/Users/" + id + "/groups/" + get_groupId_text_toString , true);
                childUpdates.put("/Groups/" + get_groupId_text_toString + "/member/" + id, true);

                Intent groupchat_activity_intend = new Intent(SettingActivity.this,Test_Activity.class);
                groupchat_activity_intend.putExtra("get_groupId_text_toString",get_groupId_text_toString);
                //groupchat_activity_intend.putExtra("get_groupId_text_toString",get_groupId_text_toString);
                groupchat_activity_intend.putExtra("id",id);
                startActivity(groupchat_activity_intend);


                databaseReference.updateChildren(childUpdates);
            }
        });
        send_shedule_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
              //  datePickerDialog = new DatePickerDialog(SettingActivity.this, SettingActivity.this, 2013, 10, 23);
               // datePickerDialog.show();
                datePickerDialog = new DatePickerDialog(SettingActivity.this,listener,2017,6,12);
                datePickerDialog.show();

            }
        });


    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int temp_year, int temp_monthOfYear, int temp_dayOfMonth) {

            year= temp_year;
            month = temp_monthOfYear;
            day = temp_dayOfMonth;
            Toast toast = Toast.makeText(SettingActivity.this, "year: " + year+ ", month: " + month+ " , day: "+ day,Toast.LENGTH_SHORT);
            toast.show();

            LayoutInflater inflater=getLayoutInflater();
            dialogView= inflater.inflate(R.layout.dialog_add_schedule, null);

            buider = new AlertDialog.Builder(SettingActivity.this);
            buider.setTitle("Let make Schedule");
            buider.setView(dialogView);
            buider.setPositiveButton("Registe", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText shedule_title_edit = (EditText)dialogView.findViewById(R.id.schedule_name);
                    EditText shedule_body_edit = (EditText)dialogView.findViewById(R.id.schedule_body);

                    shedule_title = shedule_title_edit.getText().toString();
                    shedule_body = shedule_body_edit.getText().toString();

                    Toast toast2 = Toast.makeText(SettingActivity.this,"title: " + shedule_title + ", body: " + shedule_body ,Toast.LENGTH_SHORT);
                    toast2.show();

                    Schedule schedule = new Schedule(shedule_body, year,month, day);
                    databaseReference.child("Users").child(id).child("schedule").child(shedule_body).setValue(schedule);

                }
            });
            buider.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog dialog = buider.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

        }
    };
}
