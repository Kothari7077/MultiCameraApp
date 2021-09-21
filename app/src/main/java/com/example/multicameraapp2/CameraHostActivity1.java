package com.example.multicameraapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
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
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CameraHostActivity1 extends AppCompatActivity implements SurfaceHolder.Callback, Handler.Callback {

    MediaPlayer mp, mp1;
    int count = 0;
    TextView txt, txt1;
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
    private Surface mClientSurface = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_host1);
        ArrayList<String> selectedItems = (ArrayList<String>) getIntent().getSerializableExtra("string");
        String[] arr = selectedItems.toArray(new String[0]);
        txt = findViewById(R.id.text);
        txt1 = findViewById(R.id.text1);

        String t1 = arr[0];
        txt1.setText(t1);

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
//        mp = MediaPlayer.create(this, R.raw.usain_bolt);
//
//        SurfaceView sv = (SurfaceView) findViewById(R.id.videoViewMain);
//        SurfaceHolder holder = sv.getHolder();
//        holder.addCallback(new SurfaceHolder.Callback(){
//           @Override
//           public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }
//
//           @Override
//           public void surfaceCreated(SurfaceHolder holder) {
//               mp.setDisplay(holder);
//               mp.start();
//           }
//           @Override
//           public void surfaceDestroyed(SurfaceHolder holder) {
//           }
//       });

        mp1 = MediaPlayer.create(this, R.raw.galaxy_watxh4);
        SurfaceView sv1 = findViewById(R.id.videoView1);
        SurfaceHolder holder1 = sv1.getHolder();
        holder1.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceChanged(SurfaceHolder holder1, int format, int width, int height) {
                mClientSurface = holder1.getSurface();
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
//               if(count%2==0){
//                   mp1.release();
//                   mp1 = MediaPlayer.create(CameraHostActivity1.this, R.raw.usain_bolt);
//                   mp1.setDisplay(holder1);
//                   mp1.start();
//                   mp.release();
//                   mp = MediaPlayer.create(CameraHostActivity1.this, R.raw.danny_makaskil);
//                   mp.setDisplay(holder);
//                   mp.start();
//               }
//               else{
//                   mp1.release();
//                   mp1 = MediaPlayer.create(CameraHostActivity1.this, R.raw.danny_makaskil);
//                   mp1.setDisplay(holder1);
//                   mp1.start();
//                   mp.release();
//                   mp = MediaPlayer.create(CameraHostActivity1.this, R.raw.usain_bolt);
//                   mp.setDisplay(holder);
//                   mp.start();
//               }
                Log.v(TAG,"inside onclick at the start count:"+count);
                if (count % 2 == 0) {
                    count++;
                    Log.v(TAG,"inside if at the start count:"+count);
                    Log.v(TAG, "if part: ");
                    mCameraDevice.close();
                    Log.v(TAG, "cameraDevice closed: ");
                    mp1.release();
                    Log.v(TAG, "mp1 released: ");
                    mSurfaceView = findViewById(R.id.videoView1);
                    mSurfaceHolder = mSurfaceView.getHolder();
                    mSurfaceHolder.addCallback(CameraHostActivity1.this);
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
                            configureCamera(mClientSurface);
                        }

                        @Override
                        public void onDisconnected(CameraDevice camera) {
                        }

                        @Override
                        public void onError(CameraDevice camera, int error) {
                        }
                    };
                    if (ActivityCompat.checkSelfPermission(CameraHostActivity1.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        mCameraManager.openCamera(mCameraIDsList[0], mCameraStateCB, new Handler());
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                    mp1 = MediaPlayer.create(CameraHostActivity1.this, R.raw.galaxy_watxh4);
                   SurfaceView sv1 =  findViewById(R.id.videoViewMain);
                   SurfaceHolder holder1 = sv1.getHolder();
                   mp1.setDisplay(holder1);
                   mp1.start();
                    Log.v(TAG,"inside if at the end count:"+count);
                   txt.setText(t1);
                   txt1.setText("Me");
               }
               else{
                   count++;
                    Log.v(TAG,"inside else at the start count:"+count);
                   mCameraDevice.close();
                   mp1.release();
                   mSurfaceView = findViewById(R.id.videoViewMain);
                   mSurfaceHolder = mSurfaceView.getHolder();
                   mSurfaceHolder.addCallback(CameraHostActivity1.this);
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
                    if (ActivityCompat.checkSelfPermission(CameraHostActivity1.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        mCameraManager.openCamera(mCameraIDsList[0], mCameraStateCB, new Handler());
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                   txt1.setText("Me");
                   mp1 = MediaPlayer.create(CameraHostActivity1.this, R.raw.galaxy_watxh4);
                   SurfaceView sv1 = findViewById(R.id.videoView1);
                   SurfaceHolder holder = sv1.getHolder();
                   mp1.setDisplay(holder);
                   mp1.start();

                   txt1.setText(t1);
                   txt.setText("Me");
                    Log.v(TAG,"inside else at the end count:"+count);
               }

                Log.v(TAG,"inside onclick at the end count:"+count);
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(null != mp)
            mp.release();
        if(null != mp1)
            mp1.release();
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
            mCameraDevice.createCaptureSession(sfl, new CameraHostActivity1.CaptureSessionListener(), null);
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
            mCameraDevice.createCaptureSession(sfl, new CameraHostActivity1.CaptureSessionListener(), null);
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
                        count++;
                        Log.v(TAG,"inside permissions result count:"+count);
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
                Log.v(TAG,"inside onConfigured count:"+count);
                if(count%2==0){
                    previewRequestBuilder.addTarget(mCameraSurface);
                }
                else{
                    previewRequestBuilder.addTarget(mClientSurface);
                }
                Log.v(TAG,"inside onConfigured2 count:"+count);
                mCaptureSession.setRepeatingRequest(previewRequestBuilder.build(), null, null);
            } catch (CameraAccessException e) {
                Log.d(TAG, "setting up preview failed");
                e.printStackTrace();
            }
        }
    }
}