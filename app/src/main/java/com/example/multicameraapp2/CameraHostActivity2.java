package com.example.multicameraapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class CameraHostActivity2 extends AppCompatActivity  {
    MediaPlayer mp,mp1,mp2;
    int count=0,count1=0;
    TextView txt,txt1,txt2;
    int arr[]={1,2,3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_host2);
        ArrayList<String> selectedItems = (ArrayList<String>) getIntent().getSerializableExtra("string");
        String[] a= selectedItems.toArray(new String[0]);
        Arrays.sort(a);
        String t1=a[0],t2=a[1];
        txt = (TextView) findViewById(R.id.text);
        txt1 =(TextView) findViewById(R.id.text1);
        txt2 =(TextView) findViewById(R.id.text2);

        txt1.setText(t1);
        txt2.setText(t2);
        mp = MediaPlayer.create(this, R.raw.usain_bolt);
        SurfaceView sv = (SurfaceView) findViewById(R.id.videoViewMain);
        SurfaceHolder holder = sv.getHolder();
        holder.addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mp.setDisplay(holder);
                mp.start();
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) { }
        });

        mp1 = MediaPlayer.create(this, R.raw.danny_makaskil);
        SurfaceView sv1 = (SurfaceView) findViewById(R.id.videoView1);
        SurfaceHolder holder1 = sv1.getHolder();
        holder1.addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceChanged(SurfaceHolder holder1, int format, int width, int height) { }

            @Override
            public void surfaceCreated(SurfaceHolder holder1) {
                mp1.setDisplay(holder1);
                mp1.start();
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder1) { }
        });

        mp2 = MediaPlayer.create(this, R.raw.fisica);
        SurfaceView sv2 = (SurfaceView) findViewById(R.id.videoView2);
        SurfaceHolder holder2 = sv2.getHolder();
        holder2.addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceChanged(SurfaceHolder holder2, int format, int width, int height) { }

            @Override
            public void surfaceCreated(SurfaceHolder holder2) {
                mp2.setDisplay(holder2);
                mp2.start();
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder2) { }
        });
        sv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp;
                temp=arr[0];
                arr[0]=arr[1];
                arr[1]=temp;
                switch (arr[0]){
                    case 1:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity2.this, R.raw.usain_bolt);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText("Me");
                        break;
                    case 2:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity2.this, R.raw.danny_makaskil);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t1);
                        break;
                    case 3:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity2.this, R.raw.fisica);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t2);
                        break;
                }
                switch(arr[1]){
                    case 1:
                        mp1.release();
                        mp1 = MediaPlayer.create(CameraHostActivity2.this, R.raw.usain_bolt);
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt1.setText("Me");
                        break;
                    case 2:
                        mp1.release();
                        mp1 = MediaPlayer.create(CameraHostActivity2.this, R.raw.danny_makaskil);
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt1.setText(t1);
                        break;
                    case 3:
                        mp1.release();
                        mp1 = MediaPlayer.create(CameraHostActivity2.this, R.raw.fisica);
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt1.setText(t2);
                        break;
                }
            }
        });
        sv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp1;
                temp1=arr[0];
                arr[0]=arr[2];
                arr[2]=temp1;
                switch(arr[0]){
                    case 1:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity2.this, R.raw.usain_bolt);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText("Me");
                        break;
                    case 2:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity2.this, R.raw.danny_makaskil);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t1);
                        break;
                    case 3:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity2.this, R.raw.fisica);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t2);
                        break;
                }
                switch(arr[2]){
                    case 1:
                        mp2.release();
                        mp2 = MediaPlayer.create(CameraHostActivity2.this, R.raw.usain_bolt);
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt2.setText("Me");
                        break;
                    case 2:
                        mp2.release();
                        mp2 = MediaPlayer.create(CameraHostActivity2.this, R.raw.danny_makaskil);
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt2.setText(t1);
                        break;
                    case 3:
                        mp2.release();
                        mp2 = MediaPlayer.create(CameraHostActivity2.this, R.raw.fisica);
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt2.setText(t2);
                        break;
                }
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(null != mp) mp.release();
        mp = null;
        if(null != mp1) mp1.release();
        mp1 = null;
        if(null != mp2) mp2.release();
        mp2 = null;
    }

}