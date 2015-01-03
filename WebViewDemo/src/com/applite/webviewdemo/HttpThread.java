package com.applite.webviewdemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpConnection;

import android.os.Environment;

public class HttpThread extends Thread {
	
	private String mUrl;
	
	public HttpThread(String url) {
		this.mUrl=url;
	}
	
	@Override
	public void run() {
		try {
			URL httpUrl=new URL(mUrl);
			HttpURLConnection connection=(HttpURLConnection) httpUrl.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			InputStream in =connection.getInputStream();
			
			File downloadFile;
			File sdFile;
			FileOutputStream out=null;;
			//判断sd卡是否挂载可用
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			{
				downloadFile=Environment.getExternalStorageDirectory();
				sdFile=new File(downloadFile,"test.apk");
				
				out=new FileOutputStream(sdFile);
			}
			
			byte[] b=new byte[6*1024];
			int lenght;
			while((lenght=in.read())!=-1){
				if(out!=null)
				{
					out.write(b, 0, lenght);
				}
			}
			
			if(out!=null)
			{
				//关掉
				out.close();
			}
			if(in!=null)
			{
				in.close();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e){
			
		}
	}
}
