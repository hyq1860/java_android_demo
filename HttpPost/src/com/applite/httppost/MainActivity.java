package com.applite.httppost;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.BreakIterator;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import com.applite.httppost.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btnHttpPost).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//异步任务处理
				new AsyncTask<String, Void, Void>(){

					@Override
					protected Void doInBackground(String... params) {
						try {
							URL url = new URL(params[0]);
							HttpURLConnection connection = (HttpURLConnection)url.openConnection();
							//设置可以向服务器输入数据
							connection.setDoInput(true);
							connection.setDoOutput(true);
							connection.setRequestMethod("POST");
							
							OutputStreamWriter osw= new OutputStreamWriter(connection.getOutputStream(),"utf-8");
							BufferedWriter bw=new BufferedWriter(osw);
							bw.write("keyfrom=applite&key=793234865&type=data&doctype=json&version=1.1&q=good");
							bw.flush();
							
							InputStream is=connection.getInputStream();
							InputStreamReader isr=new InputStreamReader(is,"utf-8");
							BufferedReader reader = new BufferedReader(isr);
							String line;
							while ((line=reader.readLine())!=null) {
								System.out.println(line);
							}
							reader.close();
							isr.close();
							is.close();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}}.execute("http://fanyi.youdao.com/openapi.do");
				
			}
		});
    }
}
