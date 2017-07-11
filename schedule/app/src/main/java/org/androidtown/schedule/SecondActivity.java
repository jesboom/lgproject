

package org.androidtown.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
