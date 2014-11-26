package com.applite.camera;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private SurfaceView cameraPreview;
	private Camera camera=null;
	private Callback cameraPreviewHolderCallback=new Callback() {
		
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			stopPreview();
		}
		
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			startPreview();
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private void startPreview(){
		camera=Camera.open();
		try {
			camera.setPreviewDisplay(cameraPreview.getHolder());
			camera.setDisplayOrientation(90);
			camera.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void stopPreview(){
		camera.stopPreview();
		camera.release();
	}
	
	private String saveTempFile(byte[] bytes){
		try {
			File f=File.createTempFile("img", "");
			
			FileOutputStream fos=new FileOutputStream(f);
			fos.write(bytes);
			fos.flush();
			fos.close();
			
			return f.getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cameraPreview=(SurfaceView)findViewById(R.id.cameraPreview);
        cameraPreview.getHolder().addCallback(cameraPreviewHolderCallback);
        
        findViewById(R.id.btnTakePic).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				camera.takePicture(null, null, new Camera.PictureCallback() {
					
					@Override
					public void onPictureTaken(byte[] data, Camera camera) {
						// TODO Auto-generated method stub
						String path=saveTempFile(data);
						if(path!=null)
						{
							Intent i=new Intent(MainActivity.this, ImagePreviewActivity.class);
							i.putExtra("path",path);
							startActivity(i);
						}
						else {
							Toast.makeText(MainActivity.this, "保存照片失败", Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
