package com.example.multicameraapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SubscriberActivity extends AppCompatActivity {
    Button mStartDiscovery,mUnregisterSubscriber;
    int count=0;
    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final int CAMERA_REQUEST_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber);
        mStartDiscovery = (Button) findViewById(R.id.start_discovery);



        mStartDiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the alert dialog
                if(mStartDiscovery.getText().equals("START DISCOVERY")){

                    // Set up the alert builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(SubscriberActivity.this);
                    builder.setTitle("Available Devices");
                    // Add a checkbox list
                    String[] devices = {"Device 1 ", "Device 2", "Device 3"};
                    ArrayList<String> selecteditems = new ArrayList<>();

                    builder.setMultiChoiceItems(devices, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            // The user checked or unchecked a box
                            if(isChecked){
                                selecteditems.add(devices[which]);
                            }
                            else{
                                selecteditems.remove(devices[which]);
                            }
                        }
                    });
                    // Add OK and Cancel buttons
                    builder.setPositiveButton("Create Group", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // The user clicked OK
                            count = selecteditems.size();
                        }
                    });
                    builder.setNegativeButton("Cancel", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    mStartDiscovery.setText("Start Camera");

                }else if(mStartDiscovery.getText().equals("Start Camera")){
                    if (hasCameraPermission()) {

                        if(count==3){
                            enableCamera3();
                        }
                        else if(count==2){
                            enableCamera2();
                        }
                        else{
                            //Toast.makeText(SubsriberActivity.this,"count = "+count,Toast.LENGTH_SHORT).show();
                            enableCamera1();
                        }
                    } else {
                        requestPermission();
                    }
                }
            }
        });
        mUnregisterSubscriber = (Button) findViewById(R.id.unregister_subsrciber);
        mUnregisterSubscriber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartDiscovery.setText("START DISCOVERY");
            }
        });
    }


    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                CAMERA_PERMISSION,
                CAMERA_REQUEST_CODE
        );
    }
    private void enableCamera1() {
        Intent intent = new Intent(SubscriberActivity.this, CameraHostActivity1.class);
        startActivity(intent);
    }

    private void enableCamera2() {
        Intent intent = new Intent(SubscriberActivity.this, CameraHostActivity2.class);
        startActivity(intent);
    }
    private void enableCamera3() {
        Intent intent = new Intent(SubscriberActivity.this, CameraHostActivity3.class);
        startActivity(intent);
    }
}