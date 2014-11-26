package com.applite.jsondemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.provider.DocumentsContract.Root;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //json读取
        try {
			InputStreamReader isr=new InputStreamReader(getAssets().open("json.json"), "utf-8");
			BufferedReader br=new BufferedReader(isr);
			String line;
			StringBuffer sb=new StringBuffer();
			while ((line=br.readLine())!=null) {
				sb.append(line);
			}
			
			JSONObject jsonObject=new JSONObject(sb.toString());
			System.out.println(jsonObject.getString("query"));
			JSONArray jsonArray=jsonObject.getJSONArray("web");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object=jsonArray.getJSONObject(i);
				System.out.println(object.getString("key"));
				System.out.println(object.getString("value"));
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // json写入
        JSONObject writeJsonObject=new JSONObject();
        try {
			writeJsonObject.put("name", "applite");
			
			JSONObject tempJsonObject1 =new JSONObject();
			tempJsonObject1.put("key", "1");
			tempJsonObject1.put("value", "vs");
			
			JSONObject tempJsonObject2 =new JSONObject();
			tempJsonObject2.put("key", "2");
			tempJsonObject2.put("value", "xcode");
			
			JSONArray array=new JSONArray();
			array.put(tempJsonObject1);
			array.put(tempJsonObject2);
			
			writeJsonObject.put("ides", array);
			
			System.out.println(writeJsonObject.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
