package com.example.multicameraapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CameraHostActivity3 extends AppCompatActivity implements SurfaceHolder.Callback, Handler.Callback{
    MediaPlayer mp,mp2,mp3,mp1;
    int arr[]={1,2,3,4};
    int camera_count=0;
    TextView txt,txt1,txt2,txt3;
    static final String TAG = "CamTest";
    static final int MY_PERMISSIONS_REQUEST_CAMERA = 1242;
    private static final int MSG_CAMERA_OPENED = 1;
    private static final int MSG_SURFACE_READY = 2;
    private final Handler mHandler = new Handler(this);
    SurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;
    CameraManager mCameraManager;
    String[] mCameraIDsList;
    CameraDevice.StateCallback mCameraStateCB;
    CameraDevice mCameraDevice;
    CameraCaptureSession mCaptureSession;
    boolean mSurfaceCreated = true;
    boolean mIsCameraConfigured = false;
    private Surface mCameraSurface = null;
    private Surface mClientSurface1 = null;
    private Surface mClientSurface2 = null;
    private Surface mClientSurface3 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_host3);

        ArrayList<String> selectedItems = (ArrayList<String>) getIntent().getSerializableExtra("string");
        String[] a= selectedItems.toArray(new String[0]);
        Arrays.sort(a);
        String t1=a[0],t2=a[1],t3=a[2];
        txt = findViewById(R.id.text);
        txt1 =findViewById(R.id.text1);
        txt2 = findViewById(R.id.text2);
        txt3 = findViewById(R.id.text3);
        txt1.setText(t1);
        txt2.setText(t2);
        txt3.setText(t3);
        ImageView mImageView = findViewById(R.id.imageView1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Host");

        this.mSurfaceView =  findViewById(R.id.videoViewMain);
        this.mSurfaceHolder = this.mSurfaceView.getHolder();
        this.mSurfaceHolder.addCallback(this);
        this.mCameraManager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraIDsList = this.mCameraManager.getCameraIdList();
            for (String id : mCameraIDsList) {
                Log.v(TAG, "CameraID: " + id);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        mCameraStateCB = new CameraDevice.StateCallback() {
            @Override
            public void onOpened(CameraDevice camera) {
                mCameraDevice = camera;
                mHandler.sendEmptyMessage(MSG_CAMERA_OPENED);
                //check for swap camera here
            }

            @Override
            public void onDisconnected(CameraDevice camera) {
                Toast.makeText(getApplicationContext(), "onDisconnected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(CameraDevice camera, int error) {
                Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();
            }
        };

        mp1 = MediaPlayer.create(this, R.raw.galaxy_watxh4);
        SurfaceView sv1 = (SurfaceView) findViewById(R.id.videoView1);
        SurfaceHolder holder1 = sv1.getHolder();
        holder1.addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceChanged(SurfaceHolder holder1, int format, int width, int height) {
                mClientSurface1=holder1.getSurface();
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder1) {
                mp1.setDisplay(holder1);
                mp1.start();
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder1) { }
        });
        mp2 = MediaPlayer.create(this, R.raw.galaxy_z_fold3);
        SurfaceView sv2 = findViewById(R.id.videoView2);
        SurfaceHolder holder2 = sv2.getHolder();
        holder2.addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceChanged(SurfaceHolder holder2, int format, int width, int height) {
                mClientSurface2 = holder2.getSurface();
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder2) {
                mp2.setDisplay(holder2);
                mp2.start();
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder2) { }
        });
        mp3 = MediaPlayer.create(this, R.raw.galaxy_buds2);
        SurfaceView sv3 = findViewById(R.id.videoView3);
        SurfaceHolder holder3 = sv3.getHolder();
        holder3.addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceChanged(SurfaceHolder holder3, int format, int width, int height) {
                mClientSurface3 = holder3.getSurface();
            }

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
                int temp,temp1;
                temp=arr[0];
                temp1=arr[1];
                if(arr[0]==1){
                    mImageView.setVisibility(View.INVISIBLE);
                }
                arr[1]=arr[0];
                arr[0]=temp1;
                if(arr[0]==1){
                    mImageView.setVisibility(View.VISIBLE);
                }
                switch(arr[0]){
                    case 1:
                        if(temp==2){
                            mp1.release();
                        }
                        else if(temp==3){
                            mp2.release();
                        }
                        else{
                            mp3.release();
                        }
                        mCameraDevice.close();
                        mSurfaceView = findViewById(R.id.videoViewMain);
                        mSurfaceHolder = mSurfaceView.getHolder();
                        mSurfaceHolder.addCallback(CameraHostActivity3.this);
                        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        mCameraStateCB = new CameraDevice.StateCallback() {
                            @Override
                            public void onOpened(CameraDevice camera) {
                                mCameraDevice = camera;
                                //mHandler.sendEmptyMessage(MSG_CAMERA_OPENED);
                                configureCamera(mCameraSurface);
                            }
                            @Override
                            public void onDisconnected(CameraDevice camera) {
                            }
                            @Override
                            public void onError(CameraDevice camera, int error) {
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(CameraHostActivity3.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        try {
                            if(camera_count%2==0){
                                mCameraManager.openCamera(mCameraIDsList[0], mCameraStateCB, new Handler());
                            }
                            else {
                                mCameraManager.openCamera(mCameraIDsList[1], mCameraStateCB, new Handler());
                            }
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        txt.setText("Me");
                        break;
                    case 2:
                        if(temp==1){
                            mCameraDevice.close();
                        }
                        else if(temp==3){
                            mp2.release();
                        }
                        else{
                            mp3.release();
                        }
                        mp1.release();
                        mp1 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_watxh4);
                        SurfaceView sv1 =  findViewById(R.id.videoViewMain);
                        SurfaceHolder holder1 = sv1.getHolder();
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt.setText(t1);
                        break;
                    case 3:
                        if(temp==1){
                            mCameraDevice.close();
                        }
                        else if(temp==2){
                            mp1.release();
                        }
                        else{
                            mp3.release();
                        }
                        mp2.release();
                        mp2 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_z_fold3);
                        SurfaceView sv2 =  findViewById(R.id.videoViewMain);
                        SurfaceHolder holder2 = sv2.getHolder();
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt.setText(t2);
                        break;
                    case 4:
                        if(temp==1){
                            mCameraDevice.close();
                        }
                        else if(temp==2){
                            mp1.release();
                        }
                        else{
                            mp2.release();
                        }
                        mp3.release();
                        mp3 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_buds2);
                        SurfaceView sv3 =  findViewById(R.id.videoViewMain);
                        SurfaceHolder holder3 = sv3.getHolder();
                        mp3.setDisplay(holder3);
                        mp3.start();
                        txt.setText(t3);
                        break;
                }
                switch (arr[1]){
                    case 1:
                        mSurfaceView = findViewById(R.id.videoView1);
                        mSurfaceHolder = mSurfaceView.getHolder();
                        mSurfaceHolder.addCallback(CameraHostActivity3.this);
                        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        mCameraStateCB = new CameraDevice.StateCallback() {
                            @Override
                            public void onOpened(CameraDevice camera) {
                                mCameraDevice = camera;
                                //mHandler.sendEmptyMessage(MSG_CAMERA_OPENED);
                                configureCamera(mClientSurface1);
                            }
                            @Override
                            public void onDisconnected(CameraDevice camera) {
                            }
                            @Override
                            public void onError(CameraDevice camera, int error) {
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(CameraHostActivity3.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        try {
                            if(camera_count%2==0){
                                mCameraManager.openCamera(mCameraIDsList[0], mCameraStateCB, new Handler());
                            }
                            else {
                                mCameraManager.openCamera(mCameraIDsList[1], mCameraStateCB, new Handler());
                            }
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        txt1.setText("Me");
                        break;
                    case 2:
                        mp1 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_watxh4);
                        SurfaceView sv1 =  findViewById(R.id.videoView1);
                        SurfaceHolder holder1 = sv1.getHolder();
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt1.setText(t1);
                        break;
                    case 3:
                        mp2 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_z_fold3);
                        SurfaceView sv2 =  findViewById(R.id.videoView1);
                        SurfaceHolder holder2 = sv2.getHolder();
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt1.setText(t2);
                        break;
                    case 4:
                        mp3 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_buds2);
                        SurfaceView sv3 =  findViewById(R.id.videoView1);
                        SurfaceHolder holder3 = sv3.getHolder();
                        mp3.setDisplay(holder3);
                        mp3.start();
                        txt1.setText(t3);
                        break;
                }

            }
        });
        sv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp,temp1;
                temp=arr[0];
                temp1=arr[2];
                if(arr[0]==1){
                    mImageView.setVisibility(View.INVISIBLE);
                }
                arr[2]=arr[0];
                arr[0]=temp1;
                if(arr[0]==1){
                    mImageView.setVisibility(View.VISIBLE);
                }
                switch(arr[0]){
                    case 1:
                        if(temp==2){
                            mp1.release();
                        }
                        else if(temp==3){
                            mp2.release();
                        }
                        else{
                            mp3.release();
                        }
                        mCameraDevice.close();
                        mSurfaceView = findViewById(R.id.videoViewMain);
                        mSurfaceHolder = mSurfaceView.getHolder();
                        mSurfaceHolder.addCallback(CameraHostActivity3.this);
                        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        mCameraStateCB = new CameraDevice.StateCallback() {
                            @Override
                            public void onOpened(CameraDevice camera) {
                                mCameraDevice = camera;
                                //mHandler.sendEmptyMessage(MSG_CAMERA_OPENED);
                                configureCamera(mCameraSurface);
                            }
                            @Override
                            public void onDisconnected(CameraDevice camera) {
                            }
                            @Override
                            public void onError(CameraDevice camera, int error) {
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(CameraHostActivity3.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        try {
                            if(camera_count%2==0){
                                mCameraManager.openCamera(mCameraIDsList[0], mCameraStateCB, new Handler());
                            }
                            else {
                                mCameraManager.openCamera(mCameraIDsList[1], mCameraStateCB, new Handler());
                            }
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        txt.setText("Me");
                        break;
                    case 2:
                        if(temp==1){
                            mCameraDevice.close();
                        }
                        else if(temp==3){
                            mp2.release();
                        }
                        else{
                            mp3.release();
                        }
                        mp1.release();
                        mp1 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_watxh4);
                        SurfaceView sv1 =  findViewById(R.id.videoViewMain);
                        SurfaceHolder holder1 = sv1.getHolder();
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt.setText(t1);
                        break;
                    case 3:
                        if(temp==1){
                            mCameraDevice.close();
                        }
                        else if(temp==2){
                            mp1.release();
                        }
                        else{
                            mp3.release();
                        }
                        mp2.release();
                        mp2 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_z_fold3);
                        SurfaceView sv2 =  findViewById(R.id.videoViewMain);
                        SurfaceHolder holder2 = sv2.getHolder();
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt.setText(t2);
                        break;
                    case 4:
                        if(temp==1){
                            mCameraDevice.close();
                        }
                        else if(temp==2){
                            mp1.release();
                        }
                        else{
                            mp2.release();
                        }
                        mp3.release();
                        mp3 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_buds2);
                        SurfaceView sv3 =  findViewById(R.id.videoViewMain);
                        SurfaceHolder holder3 = sv3.getHolder();
                        mp3.setDisplay(holder3);
                        mp3.start();
                        txt.setText(t3);
                        break;
                }
                switch (arr[2]){
                    case 1:

                        mSurfaceView = findViewById(R.id.videoView2);
                        mSurfaceHolder = mSurfaceView.getHolder();
                        mSurfaceHolder.addCallback(CameraHostActivity3.this);
                        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        mCameraStateCB = new CameraDevice.StateCallback() {
                            @Override
                            public void onOpened(CameraDevice camera) {
                                mCameraDevice = camera;
                                //mHandler.sendEmptyMessage(MSG_CAMERA_OPENED);
                                configureCamera(mClientSurface2);
                            }
                            @Override
                            public void onDisconnected(CameraDevice camera) {
                            }
                            @Override
                            public void onError(CameraDevice camera, int error) {
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(CameraHostActivity3.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        try {
                            if(camera_count%2==0){
                                mCameraManager.openCamera(mCameraIDsList[0], mCameraStateCB, new Handler());
                            }
                            else {
                                mCameraManager.openCamera(mCameraIDsList[1], mCameraStateCB, new Handler());
                            }
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        txt2.setText("Me");
                        break;
                    case 2:

                        mp1 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_watxh4);
                        SurfaceView sv1 =  findViewById(R.id.videoView2);
                        SurfaceHolder holder1 = sv1.getHolder();
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt2.setText(t1);
                        break;
                    case 3:

                        mp2 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_z_fold3);
                        SurfaceView sv2 =  findViewById(R.id.videoView2);
                        SurfaceHolder holder2 = sv2.getHolder();
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt2.setText(t2);
                        break;
                    case 4:
                        mp3 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_buds2);
                        SurfaceView sv3 =  findViewById(R.id.videoView2);
                        SurfaceHolder holder3 = sv3.getHolder();
                        mp3.setDisplay(holder3);
                        mp3.start();
                        txt2.setText(t3);
                        break;
                }
            }
        });
        sv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp,temp1;
                temp=arr[0];
                temp1=arr[3];
                if(arr[0]==1){
                    mImageView.setVisibility(View.INVISIBLE);
                }
                arr[3]=arr[0];
                arr[0]=temp1;
                if(arr[0]==1){
                    mImageView.setVisibility(View.VISIBLE);
                }
                switch(arr[0]){
                    case 1:
                        if(temp==2){
                            mp1.release();
                        }
                        else if(temp==3){
                            mp2.release();
                        }
                        else{
                            mp3.release();
                        }
                        mCameraDevice.close();
                        mSurfaceView = findViewById(R.id.videoViewMain);
                        mSurfaceHolder = mSurfaceView.getHolder();
                        mSurfaceHolder.addCallback(CameraHostActivity3.this);
                        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        mCameraStateCB = new CameraDevice.StateCallback() {
                            @Override
                            public void onOpened(CameraDevice camera) {
                                mCameraDevice = camera;
                                //mHandler.sendEmptyMessage(MSG_CAMERA_OPENED);
                                configureCamera(mCameraSurface);
                            }
                            @Override
                            public void onDisconnected(CameraDevice camera) {
                            }
                            @Override
                            public void onError(CameraDevice camera, int error) {
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(CameraHostActivity3.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        try {
                            if(camera_count%2==0){
                                mCameraManager.openCamera(mCameraIDsList[0], mCameraStateCB, new Handler());
                            }
                            else {
                                mCameraManager.openCamera(mCameraIDsList[1], mCameraStateCB, new Handler());
                            }
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        txt.setText("Me");
                        break;
                    case 2:
                        if(temp==1){
                            mCameraDevice.close();
                        }
                        else if(temp==3){
                            mp2.release();
                        }
                        else{
                            mp3.release();
                        }
                        mp1.release();
                        mp1 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_watxh4);
                        SurfaceView sv1 =  findViewById(R.id.videoViewMain);
                        SurfaceHolder holder1 = sv1.getHolder();
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt.setText(t1);
                        break;
                    case 3:
                        if(temp==1){
                            mCameraDevice.close();
                        }
                        else if(temp==2){
                            mp1.release();
                        }
                        else{
                            mp3.release();
                        }
                        mp2.release();
                        mp2 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_z_fold3);
                        SurfaceView sv2 =  findViewById(R.id.videoViewMain);
                        SurfaceHolder holder2 = sv2.getHolder();
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt.setText(t2);
                        break;
                    case 4:
                        if(temp==1){
                            mCameraDevice.close();
                        }
                        else if(temp==2){
                            mp1.release();
                        }
                        else{
                            mp2.release();
                        }
                        mp3.release();
                        mp3 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_buds2);
                        SurfaceView sv3 =  findViewById(R.id.videoViewMain);
                        SurfaceHolder holder3 = sv3.getHolder();
                        mp3.setDisplay(holder3);
                        mp3.start();
                        txt.setText(t3);
                        break;
                }
                switch (arr[3]){
                    case 1:
                        mSurfaceView = findViewById(R.id.videoView3);
                        mSurfaceHolder = mSurfaceView.getHolder();
                        mSurfaceHolder.addCallback(CameraHostActivity3.this);
                        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        mCameraStateCB = new CameraDevice.StateCallback() {
                            @Override
                            public void onOpened(CameraDevice camera) {
                                mCameraDevice = camera;
                                //mHandler.sendEmptyMessage(MSG_CAMERA_OPENED);
                                configureCamera(mClientSurface3);
                            }
                            @Override
                            public void onDisconnected(CameraDevice camera) {
                            }
                            @Override
                            public void onError(CameraDevice camera, int error) {
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(CameraHostActivity3.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        try {
                            if(camera_count%2==0){
                                mCameraManager.openCamera(mCameraIDsList[0], mCameraStateCB, new Handler());
                            }
                            else {
                                mCameraManager.openCamera(mCameraIDsList[1], mCameraStateCB, new Handler());
                            }
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        txt3.setText("Me");
                        break;
                    case 2:

                        mp1 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_watxh4);
                        SurfaceView sv1 =  findViewById(R.id.videoView3);
                        SurfaceHolder holder1 = sv1.getHolder();
                        mp1.setDisplay(holder1);
                        mp1.start();
                        txt3.setText(t1);
                        break;
                    case 3:
                        mp2 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_z_fold3);
                        SurfaceView sv2 =  findViewById(R.id.videoView3);
                        SurfaceHolder holder2 = sv2.getHolder();
                        mp2.setDisplay(holder2);
                        mp2.start();
                        txt3.setText(t2);
                        break;
                    case 4:
                        mp3 = MediaPlayer.create(CameraHostActivity3.this, R.raw.galaxy_buds2);
                        SurfaceView sv3 =  findViewById(R.id.videoView3);
                        SurfaceHolder holder3 = sv3.getHolder();
                        mp3.setDisplay(holder3);
                        mp3.start();
                        txt3.setText(t3);
                        break;
                }
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr[0]==1){
                    if(camera_count%2==0){
                        mCameraDevice.close();
                        mSurfaceView = findViewById(R.id.videoViewMain);
                        mSurfaceHolder = mSurfaceView.getHolder();
                        mSurfaceHolder.addCallback(CameraHostActivity3.this);
                        try {
                            mCameraIDsList = mCameraManager.getCameraIdList();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        mCameraStateCB = new CameraDevice.StateCallback() {
                            @Override
                            public void onOpened(CameraDevice camera) {
                                mCameraDevice = camera;
                                configureCamera();
                            }

                            @Override
                            public void onDisconnected(CameraDevice camera) {
                            }

                            @Override
                            public void onError(CameraDevice camera, int error) {
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(CameraHostActivity3.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        try {
                            mCameraManager.openCamera(mCameraIDsList[1], mCameraStateCB, new Handler());
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        mCameraDevice.close();
                        mSurfaceView = findViewById(R.id.videoViewMain);
                        mSurfaceHolder = mSurfaceView.getHolder();
                        mSurfaceHolder.addCallback(CameraHostActivity3.this);
                        try {
                            mCameraIDsList = mCameraManager.getCameraIdList();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        mCameraStateCB = new CameraDevice.StateCallback() {
                            @Override
                            public void onOpened(CameraDevice camera) {
                                mCameraDevice = camera;
                                configureCamera();
                            }

                            @Override
                            public void onDisconnected(CameraDevice camera) {
                            }

                            @Override
                            public void onError(CameraDevice camera, int error) {
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(CameraHostActivity3.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        try {
                            mCameraManager.openCamera(mCameraIDsList[0], mCameraStateCB, new Handler());
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    camera_count++;
                }
                else{

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
    @Override
    protected void onStart() {
        super.onStart();
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                Toast.makeText(getApplicationContext(), "request permission", Toast.LENGTH_SHORT).show();
            }
        } else {
            try {
                mCameraManager.openCamera(mCameraIDsList[0], mCameraStateCB, new Handler());
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        try {
            if (mCaptureSession != null) {
                mCaptureSession.stopRepeating();
                mCaptureSession.close();
                mCaptureSession = null;
            }

            mIsCameraConfigured = false;
        } catch (final CameraAccessException e) {
            e.printStackTrace();
        } catch (final IllegalStateException e2) {
            e2.printStackTrace();
        } finally {
            if (mCameraDevice != null) {
                mCameraDevice.close();
                mCameraDevice = null;
                mCaptureSession = null;
            }
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_CAMERA_OPENED:
            case MSG_SURFACE_READY:
                if (mSurfaceCreated && (mCameraDevice != null) && !mIsCameraConfigured) {
                    configureCamera();
                }
                break;
        }
        return true;
    }

    private void configureCamera() {
        // prepare list of surfaces to be used in capture requests
        List<Surface> sfl = new ArrayList<Surface>();
        sfl.add(mCameraSurface); // surface for viewfinder preview
        // configure camera with all the surfaces to be ever used
        try {
            mCameraDevice.createCaptureSession(sfl, new CameraHostActivity3.CaptureSessionListener(), null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        mIsCameraConfigured = true;
    }
    private void configureCamera(Surface cameraSurface) {
        // prepare list of surfaces to be used in capture requests
        List<Surface> sfl = new ArrayList<Surface>();
        sfl.add(cameraSurface); // surface for viewfinder preview
        // configure camera with all the surfaces to be ever used
        try {
            mCameraDevice.createCaptureSession(sfl, new CameraHostActivity3.CaptureSessionListener(), null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        mIsCameraConfigured = true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    try {
                        mCameraManager.openCamera(mCameraIDsList[0], mCameraStateCB, new Handler());
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                break;
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCameraSurface = holder.getSurface();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCameraSurface = holder.getSurface();
        mSurfaceCreated = true;
        mHandler.sendEmptyMessage(MSG_SURFACE_READY);
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mSurfaceCreated = false;
    }
    private class CaptureSessionListener extends CameraCaptureSession.StateCallback {
        @Override
        public void onConfigureFailed(final CameraCaptureSession session) {
            Log.d(TAG, "CaptureSessionConfigure failed");
        }
        @Override
        public void onConfigured(final CameraCaptureSession session) {
            Log.d(TAG, "CaptureSessionConfigure onConfigured");
            mCaptureSession = session;

            try {
                CaptureRequest.Builder previewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

                if(arr[0]==1) {
                    previewRequestBuilder.addTarget(mCameraSurface);
                }
                else if(arr[1]==1){
                    previewRequestBuilder.addTarget(mClientSurface1);
                }
                else if(arr[2]==1){
                    previewRequestBuilder.addTarget(mClientSurface2);
                }
                else{
                    previewRequestBuilder.addTarget(mClientSurface3);
                }

                mCaptureSession.setRepeatingRequest(previewRequestBuilder.build(), null, null);
            } catch (CameraAccessException e) {
                Log.d(TAG, "setting up preview failed");
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_host, menu);
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
        Intent i = new Intent(this, SubscriberActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CameraHostActivity3.this);
        builder.setTitle("Want to End Session ?");
        // Add OK and Cancel buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(CameraHostActivity3.this, SubscriberActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        setButtonSize(dialog);
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
}