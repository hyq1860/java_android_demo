package com.applite.usinglistview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomListViewAdapter extends BaseAdapter {

	private Context context=null;
	public  CustomListViewAdapter(Context context)
	{
		this.context=context;
	}
	
	public Context getContext()
	{
		return context;
	}
	
	//private String[] data=new String[]{"南京","shang","china","asin","american","日本","朝鲜","韩国","小三","大牛","小白"};
	
		private CustomListCellData[] data=new CustomListCellData[]{
				new CustomListCellData("美女1", "美女波霸", R.drawable.img1),
				new CustomListCellData("美女1", "美女波霸", R.drawable.img1)
		};
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			/*
			// TODO Auto-generated method stub
			//需要创建列表项的时候被执行
			System.out.println(">>>>>>");
			
			//TextView tv=new TextView(CustomListView.this);
			//tv.setText(getItem(position));
			//tv.setTextSize(50);
			
			//优化机制  理解convertView 回收的 
			TextView tv;
			if(convertView!=null)
			{
				tv=(TextView)convertView;
			}
			else {
				tv=new TextView(CustomListView.this);
			}
			tv.setText(getItem(position));
			tv.setTextSize(50);
			return tv;
			*/
			
			LinearLayout ll=null;
			if(convertView!=null)
			{
				ll=(LinearLayout)convertView;
			}
			else {
				//布局解释器
				ll=(LinearLayout)LayoutInflater.from(getContext()).inflate(R.layout.custom_listcell, null);
			}
			CustomListCellData data=getItem(position);
			ImageView icon=(ImageView)ll.findViewById(R.id.icon);
			TextView name=(TextView)ll.findViewById(R.id.name);
			TextView desc=(TextView)ll.findViewById(R.id.desc);
			
			icon.setImageResource(data.getIconId());
			name.setText(data.getName());
			desc.setText(data.getDesc());
			return ll;
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		@Override
		public CustomListCellData getItem(int position) {
			// TODO Auto-generated method stub
			return data[position];
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.length;
		}
}
