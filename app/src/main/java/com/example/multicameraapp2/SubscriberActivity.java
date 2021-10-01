package com.example.multicameraapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Host");

        ArrayList<String> selecteditems = new ArrayList<>();

        mStartDiscovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show the alert dialog

                if(mStartDiscovery.getText().equals("Scan Nearby Devices")){
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
                            if(count>0) {
                                mStartDiscovery.setText("Start Camera");
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    setButtonSize(dialog);

                }else if(mStartDiscovery.getText().equals("Start Camera")){
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
                            mStartDiscovery.setText("Scan Nearby Devices");
                        }
                    } else {
                        requestPermission();
                    }
                }
            }
        });

    }

    private void setButtonSize(AlertDialog dialog) {
        Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button button1 = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.weight = 10;
        button.setLayoutParams(layoutParams);
        button1.setLayoutParams(layoutParams);
        int i = getResources().getColor(R.color.blue);
        button.setTextColor(i);
        button1.setTextColor(i);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_host_main, menu);
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
    public void doThis(MenuItem item){
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SubscriberActivity.this);
        builder.setTitle("Want to Unregister Host ?");
        // Add OK and Cancel buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(SubscriberActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        setButtonSize(dialog);
    }
}