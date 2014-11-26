package com.applite.httpclientpost;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.applite.httpclientget.R;

import android.R.string;
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
				//readNet("http://182.92.167.82:5001/api",et.getText().toString());
				readNet("http://fanyi.youdao.com/openapi.do",et.getText().toString());
			}
		});
        
    }

    private void readNet(String url,String in)
    {
    	new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				String url=params[0];
				HttpPost post=new HttpPost(url);
				try {
					//StringEntity entity=new StringEntity(params[1]);
					//post.setEntity(entity);
					List<BasicNameValuePair> list=new ArrayList<BasicNameValuePair>();
					//keyfrom=applite&key=793234865&type=data&doctype=json&version=1.1&q=good
					list.add(new BasicNameValuePair("keyfrom", "applite"));
					list.add(new BasicNameValuePair("key", "793234865"));
					list.add(new BasicNameValuePair("type", "data"));
					list.add(new BasicNameValuePair("doctype", "json"));
					list.add(new BasicNameValuePair("version", "1.1"));
					list.add(new BasicNameValuePair("q", "sex"));
					//格式化为
					post.setEntity(new UrlEncodedFormEntity(list));
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				try {
					HttpResponse response = client.execute(post);
					String value= EntityUtils.toString(response.getEntity());
					System.out.println(value);
					return value;
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				//super.onPostExecute(result);
				System.out.println(result);
				tv.setText(result);
			}
			
			
			
		}.execute(url);
    }

   
}
