package org.androidtown.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class SecondActivity extends AppCompatActivity
{
    private String id;
    private Button myCalandal_button;
    private Button group_Calandal_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");


        myCalandal_button= (Button) findViewById(R.id.mycalandal_button);
        group_Calandal_button = (Button) findViewById(R.id.group_calandal_button);

        myCalandal_button.setText(id);
        group_Calandal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent test_activity_intent = new Intent(SecondActivity.this,Test_Activity.class); ;
                startActivity(test_activity_intent);
            }
        });



    }
}
