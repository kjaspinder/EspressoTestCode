package com.abbott.espressotestsample.UI;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abbott.espressotestsample.Interfaces.OnClickListener;
import com.abbott.espressotestsample.R;
import com.abbott.espressotestsample.model.AlarmMenuItemViewModel;

import java.util.List;
public class AlarmsMenuAdapter extends RecyclerView.Adapter<AlarmsMenuAdapter.ViewHolder>
{

	private List<AlarmMenuItemViewModel> mMenuList;
	private OnClickListener mOnClickListener;


	AlarmsMenuAdapter(List<AlarmMenuItemViewModel> menuList, OnClickListener listener)
	{
		this.mMenuList = menuList;
		this.mOnClickListener = listener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_alarm_setting_list_item, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position)
	{
		AlarmMenuItemViewModel alarmMenuItemViewModel = mMenuList.get(position);
		if(alarmMenuItemViewModel != null)
		{
			viewHolder.textMenuName.setText(alarmMenuItemViewModel.getMenuName());
			viewHolder.textGlucoseAlarm.setText(alarmMenuItemViewModel.getMenuGlucoseAlarm());
			viewHolder.textState.setText(alarmMenuItemViewModel.getAlarmStatus());
		}
	}

	@Override
	public int getItemCount()
	{
		return mMenuList.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
	{
		TextView textMenuName;
		TextView textGlucoseAlarm;
		TextView textState;

		public ViewHolder(View v)
		{
			super(v);
			textMenuName = v.findViewById(R.id.textMenuName);
			textGlucoseAlarm = v.findViewById(R.id.textGlucoseAlarm);
			textState = v.findViewById(R.id.textState);
			v.setOnClickListener(this);

		}


		@Override
		public void onClick(View v)
		{
			mOnClickListener.onClick(getAdapterPosition());
		}
	}


}


