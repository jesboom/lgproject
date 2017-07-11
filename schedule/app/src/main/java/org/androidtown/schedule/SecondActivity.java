

package org.androidtown.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
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

    private String userName ;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listItems=new ArrayList<String>();
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);


        ListView listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        Button sendButton = (Button) findViewById(R.id.button);

        userName = "user" + new Random().nextInt(10000);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);

        listView.setAdapter( adapter);


        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ChatData chatData = new ChatData(userName, editText.getText().toString(),10);  // 유저 이름과 메세지로 chatData 만들기

                User userData = new User();
                M1 m1 = new M1("1","2","123");

                Calander calander = new Calander(m1);
                Groups groups = new Groups(true);
                User user = new User("idd","nananma",groups,calander);
                Users users = new Users(user);

                //databaseReference.child("message").push().setValue(chatData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
                databaseReference.child("message").push().setValue(users);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기

                editText.setText("");
            }
        });

        databaseReference.child("message").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //ChatData chatData = dataSnapshot.getValue(ChatData.class);  // chatData를 가져오고
                //adapter.add(chatData.getUserName()+":"+chatData.getMessage());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
