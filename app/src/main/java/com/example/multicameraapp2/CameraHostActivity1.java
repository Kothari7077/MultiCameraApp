package com.example.multicameraapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

public class CameraHostActivity1 extends AppCompatActivity{

    MediaPlayer mp,mp1;
    MediaPlayer temp,temp1;
    int count=0;
    TextView txt,txt1;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_host1);
        txt = (TextView) findViewById(R.id.text);
        txt1 =(TextView) findViewById(R.id.text1);

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
           public void surfaceDestroyed(SurfaceHolder holder) {
           }
       });
       mp1 = MediaPlayer.create(this,R.raw.danny_makaskil);
       SurfaceView sv1 = (SurfaceView) findViewById(R.id.videoView1);
       SurfaceHolder holder1 = sv1.getHolder();
       holder1.addCallback(new SurfaceHolder.Callback(){
           @Override
           public void surfaceChanged(SurfaceHolder holder1, int format, int width, int height) {

           }

           @Override
           public void surfaceCreated(SurfaceHolder holder1) {
               mp1.setDisplay(holder1);
               mp1.start();
           }
           @Override
           public void surfaceDestroyed(SurfaceHolder holder1) {

           }
       });
       sv1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(count%2==0) {
                   mp1.release();
                   mp1 = MediaPlayer.create(CameraHostActivity1.this, R.raw.usain_bolt);
                   mp1.setDisplay(holder1);
                   mp1.start();
                   txt1.setText("Me");
                   mp.release();
                   mp = MediaPlayer.create(CameraHostActivity1.this, R.raw.danny_makaskil);
                   mp.setDisplay(holder);
                   mp.start();
                   txt.setText("Device 1");
               }
               else{
                   mp1.release();
                   mp1 = MediaPlayer.create(CameraHostActivity1.this, R.raw.danny_makaskil);
                   //mp1=temp;
                   mp1.setDisplay(holder1);
                   mp1.start();
                   txt1.setText("Device 1");
                   mp.release();
                   mp = MediaPlayer.create(CameraHostActivity1.this, R.raw.usain_bolt);
                   //mp=temp1;
                   mp.setDisplay(holder);
                   mp.start();
                   txt.setText("Me");
               }
               count++;
           }
       });
    }
    @Override
    protected void onPause(){
        super.onPause();

        if(null != mp) mp.release();
        if(null != mp1) mp1.release();
    }

}