package org.androidtown.schedule;


import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
=======
import android.support.annotation.NonNull;
>>>>>>> jihun
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
<<<<<<< HEAD
=======
import android.view.View;
>>>>>>> jihun
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

<<<<<<< HEAD

public class SecondActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;

=======
public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
>>>>>>> jihun
    private String uid;
    private String userName;
    private String gid;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;



    @Override
<<<<<<< HEAD
    protected void onCreate(Bundle savedInstanceState) {
=======
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
>>>>>>> jihun
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
<<<<<<< HEAD


=======
>>>>>>> jihun

        FirebaseUser user = firebaseAuth.getCurrentUser();
        uid = user.getUid()+"";

        userName = "user" + new Random().nextInt(10000);

        databaseReference.child("Users").child(uid).child("groups").addValueEventListener(new ValueEventListener() {
            @Override
<<<<<<< HEAD
            public void onClick(View view) {

                if(gid != null) {
                    Intent show_groups_activity_intent = new Intent(SecondActivity.this, Show_groups_Activity.class);
                    show_groups_activity_intent.putExtra("uid", uid);
                    //show_groups_activity_intent.putExtra("gid", gid);
                    startActivity(show_groups_activity_intent);
=======
            public void onDataChange(DataSnapshot dataSnapshot) {
                //넣기전에 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    gid = snapshot.getKey()+"";
                    Toast.makeText(SecondActivity.this,"get gid : "+ gid , Toast.LENGTH_SHORT).show();
>>>>>>> dong
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
<<<<<<< HEAD
=======
        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //지움
                Intent test_activity_intent = new Intent(SecondActivity.this,Test_Activity.class); ;
                test_activity_intent.putExtra("id",uid);
                startActivity(test_activity_intent);
            }
        });
        id_setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
>>>>>>> jihun


<<<<<<< HEAD
=======
        //나중에 그룹이 여러개가 되면 수정해야함!!!!!!!!!
        databaseReference.child("Users").child(uid).child("groups").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //넣기전에 초기화
                    for(DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        gid = snapshot.getKey()+"";
                        Toast.makeText(SecondActivity.this,"get gid : "+ gid ,Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
        });

>>>>>>> jihun
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
<<<<<<< HEAD

=======
>>>>>>> jihun
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){

            case R.id.nav_home:
                Intent h = new Intent(SecondActivity.this , SecondActivity.class);
                startActivity(h);
                break;
            case R.id.nav_my_calendar:
                Intent m = new Intent(SecondActivity.this , My_Calendar_Activity.class);
                startActivity(m);
                break;
            case R.id.nav_group_calendar:
                Intent g = new Intent(SecondActivity.this , Show_groups_Activity.class);
                g.putExtra("uid", uid);
                startActivity(g);
                break;
            case R.id.nav_setting:
<<<<<<< HEAD
                Intent s = new Intent(SecondActivity.this , SettingActivity.class);
=======
                Intent s = new Intent(SecondActivity.this ,SettingActivity.class);
                s.putExtra("uid", uid);
>>>>>>> jihun
                startActivity(s);
                break;
            case R.id.nav_logout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(SecondActivity.this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
