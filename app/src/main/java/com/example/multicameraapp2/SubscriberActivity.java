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

        ArrayList<String> selecteditems = new ArrayList<>();

        mStartDiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the alert dialog

                if(mStartDiscovery.getText().equals("START DISCOVERY")){
                    selecteditems.clear();
                    // Set up the alert builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(SubscriberActivity.this);
                    builder.setTitle("Nearby Devices");
                    // Add a checkbox list
                    String[] devices = {"Device 1", "Device 2", "Device 3"};

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
                            mStartDiscovery.setText("START CAMERA");
                        }
                    });
                    builder.setNegativeButton("Cancel", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    setButtonSize(dialog);

                }else if(mStartDiscovery.getText().equals("START CAMERA")){
                    if (hasCameraPermission()) {

                        if(count==3){

                            Intent intent = new Intent(SubscriberActivity.this, CameraHostActivity3.class);
                            intent.putExtra("string",selecteditems);
                            startActivity(intent);
                        }
                        else if(count==2){
                            Intent intent = new Intent(SubscriberActivity.this, CameraHostActivity2.class);
                            intent.putExtra("string",selecteditems);
                            startActivity(intent);
                        }
                        else if(count==1){
                            Intent intent = new Intent(SubscriberActivity.this, CameraHostActivity1.class);
                            intent.putExtra("string",selecteditems);
                            startActivity(intent);
                        }
                        else{
                            mStartDiscovery.setText("START DISCOVERY");
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

    private void setButtonSize(AlertDialog dialog) {
        Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button button1 = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        int i = getResources().getColor(R.color.blue);
        button.setTextColor(i);
        button1.setTextColor(i);
        int height = getResources().getDimensionPixelSize(R.dimen.alertdialog_button_height);
        int width  = getResources().getDimensionPixelSize(R.dimen.alertdialog_button_width);
        button.setHeight(height);
        button.setWidth(width);
        button1.setHeight(height);
        button1.setWidth(width);
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
}