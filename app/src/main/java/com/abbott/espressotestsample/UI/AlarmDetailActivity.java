package com.abbott.espressotestsample.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.abbott.espressotestsample.R;
import com.abbott.espressotestsample.model.AlarmMenuItemViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlarmDetailActivity extends AppCompatActivity
{

	@BindView(R.id.details)
	TextView mDetails;



	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_details);
		ButterKnife.bind(this);

		AlarmMenuItemViewModel model = getIntent().getParcelableExtra("alarm_details");

		mDetails.setText(model.getMenuName());
	}
}
