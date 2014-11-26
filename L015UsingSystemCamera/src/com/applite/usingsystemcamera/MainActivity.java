package com.applite.usingsystemcamera;

import java.io.File;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.R.integer;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

	private static final int REQUEST_CODE_TAKE_PICTURE=1;
	private File currentFile=null;
	private ImageView iv;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        iv=(ImageView)findViewById(R.id.iv);
        
        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File dir=new File(Environment.getExternalStorageDirectory(),"pictures");
				if(!dir.exists())
				{
					dir.mkdirs();
				}
				
				currentFile=new File(dir,System.currentTimeMillis()+".jpg");
				if(!currentFile.exists())
				{
					try {
						currentFile.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentFile));
				startActivityForResult(i, REQUEST_CODE_TAKE_PICTURE);
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	switch (requestCode) {
		case REQUEST_CODE_TAKE_PICTURE:
			iv.setImageURI(Uri.fromFile(currentFile));
			break;

		default:
			break;
		}
    	
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
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
