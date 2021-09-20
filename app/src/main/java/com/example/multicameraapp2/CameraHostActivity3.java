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

public class CameraHostActivity3 extends AppCompatActivity {
    MediaPlayer mp,mp2,mp3,mp1;
    int arr[]={1,2,3,4};
    TextView txt,txt1,txt2,txt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_host3);

        ArrayList<String> selectedItems = (ArrayList<String>) getIntent().getSerializableExtra("string");
        String[] a= selectedItems.toArray(new String[0]);
        Arrays.sort(a);
        String t1=a[0],t2=a[1],t3=a[2];
        txt = (TextView)findViewById(R.id.text);
        txt1 = (TextView)findViewById(R.id.text1);
        txt2 = (TextView)findViewById(R.id.text2);
        txt3 = (TextView)findViewById(R.id.text3);
        txt1.setText(t1);
        txt2.setText(t2);
        txt3.setText(t3);
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
        mp3 = MediaPlayer.create(this, R.raw.coca_cola);
        SurfaceView sv3 = (SurfaceView) findViewById(R.id.videoView3);
        SurfaceHolder holder3 = sv3.getHolder();
        holder3.addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceChanged(SurfaceHolder holder3, int format, int width, int height) { }

            @Override
            public void surfaceCreated(SurfaceHolder holder3) {
                mp3.setDisplay(holder3);
                mp3.start();
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder3) { }
        });
        sv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp;
                temp=arr[1];
                arr[1]=arr[0];
                arr[0]=temp;
                switch(arr[0]){
                    case 1:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.usain_bolt);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText("Me");
                        break;
                    case 2:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.danny_makaskil);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t1);
                        break;
                    case 3:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.fisica);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t2);
                        break;
                    case 4:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.coca_cola);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t3);
                        break;
                }
                switch (arr[1]){
                    case 1:
                        mp1.release();
                        mp1 = MediaPlayer.create(CameraHostActivity3.this, R.raw.usain_bolt);
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt1.setText("Me");
                        break;
                    case 2:
                        mp1.release();
                        mp1 = MediaPlayer.create(CameraHostActivity3.this, R.raw.danny_makaskil);
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt1.setText(t1);
                        break;
                    case 3:
                        mp1.release();
                        mp1 = MediaPlayer.create(CameraHostActivity3.this, R.raw.fisica);
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt1.setText(t2);
                        break;
                    case 4:
                        mp1.release();
                        mp1 = MediaPlayer.create(CameraHostActivity3.this, R.raw.coca_cola);
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt1.setText(t3);
                        break;
                }

            }
        });
        sv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp;
                temp=arr[2];
                arr[2]=arr[0];
                arr[0]=temp;
                switch(arr[0]){
                    case 1:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.usain_bolt);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText("Me");
                        break;
                    case 2:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.danny_makaskil);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t1);
                        break;
                    case 3:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.fisica);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t2);
                        break;
                    case 4:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.coca_cola);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t3);
                        break;
                }
                switch (arr[2]){
                    case 1:
                        mp2.release();
                        mp2 = MediaPlayer.create(CameraHostActivity3.this, R.raw.usain_bolt);
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt2.setText("Me");
                        break;
                    case 2:
                        mp2.release();
                        mp2 = MediaPlayer.create(CameraHostActivity3.this, R.raw.danny_makaskil);
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt2.setText(t1);
                        break;
                    case 3:
                        mp2.release();
                        mp2 = MediaPlayer.create(CameraHostActivity3.this, R.raw.fisica);
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt2.setText(t2);
                        break;
                    case 4:
                        mp2.release();
                        mp2 = MediaPlayer.create(CameraHostActivity3.this, R.raw.coca_cola);
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt2.setText(t3);
                        break;
                }
            }
        });
        sv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp;
                temp=arr[3];
                arr[3]=arr[0];
                arr[0]=temp;
                switch(arr[0]){
                    case 1:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.usain_bolt);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText("Me");
                        break;
                    case 2:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.danny_makaskil);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t1);
                        break;
                    case 3:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.fisica);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t2);
                        break;
                    case 4:
                        mp.release();
                        mp = MediaPlayer.create(CameraHostActivity3.this, R.raw.coca_cola);
                        mp.setDisplay(holder);
                        mp.start();
                        txt.setText(t3);
                        break;
                }
                switch (arr[3]){
                    case 1:
                        mp3.release();
                        mp3 = MediaPlayer.create(CameraHostActivity3.this, R.raw.usain_bolt);
                        mp3.setDisplay(holder3);
                        mp3.start();
                        txt3.setText("Me");
                        break;
                    case 2:
                        mp3.release();
                        mp3 = MediaPlayer.create(CameraHostActivity3.this, R.raw.danny_makaskil);
                        mp3.setDisplay(holder3);
                        mp3.start();
                        txt3.setText(t1);
                        break;
                    case 3:
                        mp3.release();
                        mp3 = MediaPlayer.create(CameraHostActivity3.this, R.raw.fisica);
                        mp3.setDisplay(holder3);
                        mp3.start();
                        txt3.setText(t2);
                        break;
                    case 4:
                        mp3.release();
                        mp3 = MediaPlayer.create(CameraHostActivity3.this, R.raw.coca_cola);
                        mp3.setDisplay(holder3);
                        mp3.start();
                        txt3.setText(t3);
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
        if(null != mp3) mp3.release();
        mp3 = null;
    }
}