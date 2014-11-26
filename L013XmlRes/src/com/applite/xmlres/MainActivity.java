package com.applite.xmlres;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.support.v7.app.ActionBarActivity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        XmlResourceParser xmlParser= getResources().getXml(R.xml.custom);
        try {
			while(xmlParser.getEventType()!=XmlResourceParser.END_DOCUMENT)
			{
				if(xmlParser.getEventType()==XmlResourceParser.START_TAG)
				{
					if(xmlParser.getName().equals("user"))
					{
						String name=xmlParser.getAttributeValue(null, "name");
						String age=xmlParser.getAttributeValue(null,"age");
						
						System.out.println(String.format("名字：%s,年龄：%s", name,age));
					}
				}
				xmlParser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
