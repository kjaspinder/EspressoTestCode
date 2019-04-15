package com.abbott.espressotestsample.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AlarmMenuItemViewModel implements Parcelable
{
	private String menuName;
	private String alarmStatus;
	private String menuGlucoseAlarm;

	public AlarmMenuItemViewModel(String menuName, String alarmStatus, String menuGlucoseAlarm)
	{
		this.menuName = menuName;
		this.alarmStatus = alarmStatus;
		this.menuGlucoseAlarm = menuGlucoseAlarm;
	}

	protected AlarmMenuItemViewModel(Parcel in)
	{
		menuName = in.readString();
		alarmStatus = in.readString();
		menuGlucoseAlarm = in.readString();
	}
	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(menuName);
		dest.writeString(alarmStatus);
		dest.writeString(menuGlucoseAlarm);
	}
	@Override
	public int describeContents()
	{
		return 0;
	}
	public static final Creator<AlarmMenuItemViewModel> CREATOR = new Creator<AlarmMenuItemViewModel>()
	{
		@Override
		public AlarmMenuItemViewModel createFromParcel(Parcel in)
		{
			return new AlarmMenuItemViewModel(in);
		}

		@Override
		public AlarmMenuItemViewModel[] newArray(int size)
		{
			return new AlarmMenuItemViewModel[size];
		}
	};
	public String getMenuName()
	{
		return menuName;
	}
	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}
	public String getAlarmStatus()
	{
		return alarmStatus;
	}
	public void setAlarmStatus(String alarmStatus)
	{
		this.alarmStatus = alarmStatus;
	}
	public String getMenuGlucoseAlarm()
	{
		return menuGlucoseAlarm;
	}
	public void setMenuGlucoseAlarm(String menuGlucoseAlarm)
	{
		this.menuGlucoseAlarm = menuGlucoseAlarm;
	}
}