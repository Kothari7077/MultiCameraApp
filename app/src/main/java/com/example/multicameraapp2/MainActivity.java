package com.example.multicameraapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button msubscriber,mcaster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msubscriber = (Button) findViewById(R.id.button);
        mcaster = (Button) findViewById(R.id.button2);

        msubscriber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subscriberIntent = new Intent(MainActivity.this,SubscriberActivity.class);
                startActivity(subscriberIntent);
            }
        });


        mcaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent casterIntent = new Intent(MainActivity.this,CasterActivity.class);
                startActivity(casterIntent);
            }
        });
    }


}