package com.applite.usinglistview;

import android.R.anim;
import android.support.v7.app.ActionBarActivity;
import android.text.AndroidCharacter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnItemClickListener {

	private ListView lv;
	private ArrayAdapter<String> arrayAdapter;
	private ArrayAdapter<ListCellData> customerDataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        //注意android.R与自己项目的R
        //系统定义的
        //arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        //自定义的 列表项
        //arrayAdapter=new ArrayAdapter<String>(this, R.layout.list_cell);
        
        customerDataAdapter=new ArrayAdapter<ListCellData>(this, R.layout.list_cell);
        
        lv=(ListView)findViewById(R.id.listview);
        
        //设置adapter
        //lv.setAdapter(arrayAdapter);
        lv.setAdapter(customerDataAdapter);
        
        lv.setOnItemClickListener(this);
        
        //arrayAdapter.add("你好，上海");
        //arrayAdapter.add("你好，南京");
        
        customerDataAdapter.add(new ListCellData("林志玲", 1, 38));
        customerDataAdapter.add(new ListCellData("林志颍", 1, 38));
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


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		ListCellData data=customerDataAdapter.getItem(position);
		
		Toast.makeText(this, String.format("名字:%s,年龄：%d", data.getUserName(),data.getAge()), Toast.LENGTH_SHORT).show();
	}
}
