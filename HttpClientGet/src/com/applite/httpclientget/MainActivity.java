package com.applite.httpclientget;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText et;
	private TextView tv;
	
	private HttpClient client;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        client=new DefaultHttpClient();
        
        et = (EditText)findViewById(R.id.editText1);
        tv = (TextView)findViewById(R.id.tv);
        
        findViewById(R.id.btnHttpClientGet).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//android中 10.0.0.2可以访问到localhost
				//readNet("http://fanyi.youdao.com/openapi.do?keyfrom=applite&key=793234865&type=data&doctype=json&version=1.1&q="+et.getText());
				readNet("http://182.92.167.82:5001/api/data");
			}
		});
        
    }

    private void readNet(String url)
    {
    	new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				String url=params[0];
				HttpGet get=new HttpGet(url);
				try {
					HttpResponse response = client.execute(get);
					String value= EntityUtils.toString(response.getEntity());
					System.out.println(value);
					return value;
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				//super.onPostExecute(result);
				tv.setText(result);
			}
			
			
			
		}.execute(url);
    }

   
}
