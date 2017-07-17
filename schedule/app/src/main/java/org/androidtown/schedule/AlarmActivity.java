package org.androidtown.schedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/**
 * Created by Ei Seok on 2017-07-17.
 */

public class AlarmActivity extends BroadcastReceiver {
    //private AlertDialog.Builder buider;
    private String shedule_title;
    private String shedule_body;
    private View dialogView;

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"ALARM .......",Toast.LENGTH_LONG).show();

        // 다이얼로그 바디
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        // 메세지
        alert_confirm.setMessage("기본 다이얼로그 입니다.");
        // 확인 버튼 리스너
        alert_confirm.setPositiveButton("확인", null);
        // 다이얼로그 생성
        AlertDialog alert = alert_confirm.create();

        // 아이콘
       // alert.setIcon(R.drawable.ic_launcher);
        // 다이얼로그 타이틀
        alert.setTitle("제목");
        // 다이얼로그 보기
        alert.show();


        출처: http://taehyun71.tistory.com/4 [코딩하는 블로그]



        Vibrator v = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        v.vibrate(10000);



    }
}
