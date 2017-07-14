package org.androidtown.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.util.Iterator;

public class Test_Activity extends AppCompatActivity
{


    private FirebaseAuth firebaseAuth;
    //private DatabaseReference databaseReference;

    private String id;
    private String get_groupId_text_toString;
    private String userNames ;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
   // private FirebaseDatabase firebaseDatabasetwo = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    //private DatabaseReference databaseReferencetwo = firebaseDatabase.getReference();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listItems = new ArrayList<String>();
    private EditText editText;
    private TextView chat_conversation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        get_groupId_text_toString = intent.getStringExtra("get_groupId_text_toString");
        id = intent.getStringExtra("id");
        Toast.makeText(this, "get : " + id, Toast.LENGTH_SHORT).show();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //userName=databaseReference.child("Users").child(id).child("name").get;
                //user.getEmail();

        ListView listView = (ListView) findViewById(R.id.test_listView);
        editText = (EditText) findViewById(R.id.test_editText);
        Button sendButton = (Button) findViewById(R.id.test_button);
        chat_conversation = (TextView) findViewById(R.id.textView);

      //  userName =
        //userName = "user" + new Random().nextInt(10000);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1);

        listView.setAdapter( adapter);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);


        databaseReference.child("Users").child(id).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String userNicnkname = ""+dataSnapshot.getValue();
                userNames = userNicnkname;

                Toast.makeText(Test_Activity.this,"this is user name: " + userNames,Toast.LENGTH_LONG);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
/*
        databaseReference.child("Users").child(id).addValueEventListener(new EventListener() {  // message는 child의 이벤트를 수신합니다.

        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            //ChatData chatData = dataSnapshot.getValue(ChatData.class);  // chatData를 가져오고

            for(DataSnapshot snapshot : dataSnapshot.getChildren())
            {
                if(dataSnapshot.getKey()+"" == "name")
                {
                    String userNicnkname = ""+dataSnapshot.getValue();
                    userNames = userNicnkname;
                }
            }
            Toast.makeText(Test_Activity.this,"this is user name: " + userNames,Toast.LENGTH_LONG);

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

*/


        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ChatData chatData = new ChatData(userNames , editText.getText().toString());  // 유저 이름과 메세지로 chatData 만들기

               // User userData = new User();
                //M1 m1 = new M1("1","2","123");

               // Schedule schedule = new Schedule("fad");
              //  Groups groups = new Groups(true);
              //  User user = new User("idd",groups,schedule);
               // ArrayList<User> users_Arrya = new ArrayList<User>();
             //   users_Arrya.add(user);

               // Users users = new Users(users_Arrya);

                databaseReference.child("Groups").child(get_groupId_text_toString).child("chat_Room").push().setValue(chatData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
              //  databaseReference.child("message").child("TTTTTT").setValue(users);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기

                editText.setText("");
            }
        });
    /*
        databaseReference.child("message").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);  // chatData를 가져오고
                adapter.add(chatData.getUserName()+":"+chatData.getMessage());
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
    */

        databaseReference.child("Users").child("ohji1006@").child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.add(dataSnapshot.getKey() + ":" + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

/*
        databaseReference.child("Users").child("ohji1006@").child("groups").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
*/
/*
        databaseReference.child("Users").child("ohji1006@").child("groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users users = dataSnapshot.getValue(Users.class);  // chatData를 가져오고
                //adapter.add(users.getUsers() + ":");
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    adapter.add(snapshot.getValue()+"");
                   // adapter.add(snapshot.getKey() + ":" + snapshot.getValue());
                    //adapter.clear();
                }
>>>>>>> d52361a5a5a2f27a690a256fc050702fc2ccf53a
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
/*
        databaseReference.child("Groups").child(get_groupId_text_toString).child("chat_Room").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);  // chatData를 가져오고
            //    String nickname = dataSnapshot.getValue(String.class);
                adapter.add(chatData.getUserName()+" : "+chatData.getMessage());
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
        */
    }
}
