package com.abbott.espressotestsample.Utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperMethods
{
	public static boolean isEmailValid(String email)
	{
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isPasswordValid(String password)
	{
		if (password.length() < 5)
		{
			return false;
		}
		return true;
	}

	public static boolean isStringEmpty(String text)
	{
		if (TextUtils.isEmpty(text))
		{
			return true;
		}
		return false;
	}

	public static void showDialog(Context c,String message)
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);
		alertDialogBuilder.setPositiveButton(message,
				(dialog, which) -> dialog.dismiss());
		alertDialogBuilder.show();
	}
}
