package com.applite.getmyphonenumber;

import java.util.List;

import android.R.raw;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContractAdapter extends BaseAdapter {

	private List<PhoneInfo> lists;
	
	private Context context;
	
	//private LinearLayout layout;
	
	public ContractAdapter(List<PhoneInfo> lists,Context context) {
		this.lists=lists;
		this.context=context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if(convertView==null)	
		{
			LayoutInflater inflater=LayoutInflater.from(context);
			convertView=(LinearLayout)inflater.inflate(R.layout.contract_item,null);
			holder=new ViewHolder();
			holder.contactName=(TextView)convertView.findViewById(R.id.contract_name);
			holder.contactPhone=(TextView)convertView.findViewById(R.id.contract_phone);
			holder.contactName.setText(lists.get(position).getName());
			holder.contactPhone.setText(lists.get(position).getNumber());
			convertView.setTag(holder);
		}
		else {
			holder=(ViewHolder)convertView.getTag();
			holder.contactName.setText(lists.get(position).getName());
			holder.contactPhone.setText(lists.get(position).getNumber());
		}
		
		return convertView;
	}
	
	private static class ViewHolder{
		TextView contactName;
		TextView contactPhone;
	}

}
