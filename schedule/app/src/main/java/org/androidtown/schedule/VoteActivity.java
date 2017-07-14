package org.androidtown.schedule;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import android.content.Intent;

//import android.content.Intent;

/**
 * Created by Ei Seok on 2017-07-14.
 */

public class VoteActivity extends AppCompatActivity {

    private Button add_vote_Button;
    private Button send_shedule_Button;



    private FirebaseAuth firebaseAuth;
    //private DatabaseReference databaseReference;

    private String id;

    private String userNames ;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    // private FirebaseDatabase firebaseDatabasetwo = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    //private DatabaseReference databaseReferencetwo = firebaseDatabase.getReference();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listItems = new ArrayList<String>();
    private EditText editText;
    private TextView chat_conversation;

    private Button sendButton, voteButton, votelistButton;
    private String year, month, day, hour, minute;
    private DatePickerDialog datePickerDialog;
    private AlertDialog.Builder buider;
    private String shedule_title;
    private String shedule_body;
    private View dialogView;
    private String get_groupId_text_toString;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);


        Intent intent = getIntent();
        get_groupId_text_toString = intent.getStringExtra("get_groupId_text_toString");
       // month = intent.getStringExtra("month");


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //userName=databaseReference.child("Users").child(id).child("name").get;
        //user.getEmail();

        ListView listView = (ListView) findViewById(R.id.vote_listView);
        editText = (EditText) findViewById(R.id.test_editText);
        sendButton = (Button) findViewById(R.id.test_button);
        voteButton = (Button) findViewById(R.id.vote_button);
        votelistButton = (Button) findViewById(R.id.votelist_button);
        chat_conversation = (TextView) findViewById(R.id.textView);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);

        listView.setAdapter( adapter);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);


        databaseReference.child("Groups").child(get_groupId_text_toString).child("vote_Room").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    VoteData voteData = snapshot.getValue(VoteData.class);
                    adapter.add(voteData.getshedule_title()+" : "+voteData.getshedule_body());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        
    }


}
