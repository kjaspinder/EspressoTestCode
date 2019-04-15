package com.abbott.espressotestsample.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.abbott.espressotestsample.R;
import com.abbott.espressotestsample.Utils.HelperMethods;


import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener
{
	@BindView(R.id.name)
	EditText name;

	@BindView(R.id.email)
	EditText email;

	@BindView(R.id.password)
	EditText password;

	@BindView(R.id.confirmPass)
	EditText confirmPass;

	@BindView(R.id.signup)
	Button signup;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		ButterKnife.bind(this);

		signup.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		if (v.getId() == R.id.signup)
		{
			if (TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(email.getText().toString()) && TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(confirmPass.getText().toString()))
			{
				HelperMethods.showDialog(this,getString(R.string.fill_all_fields));
			}
			else
			{
				if (!HelperMethods.isEmailValid(email.getText().toString()))
				{
					HelperMethods.showDialog(this,getString(R.string.invalid_email));
					return;
				}
				if (!password.getText().toString().equals(confirmPass.getText().toString()))
				{
					HelperMethods.showDialog(this,getString(R.string.password_mismatch));
					return;
				}

				Intent i = new Intent(SignupActivity.this, HomeActivity.class);
				startActivity(i);
				finish();

			}
		}
	}




}
