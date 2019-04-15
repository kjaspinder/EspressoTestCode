package com.abbott.espressotestsample;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.abbott.espressotestsample.UI.MainActivity;
import com.abbott.espressotestsample.Utils.EspressoIdlingResources;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MainActivityTest
{
	@Rule
	public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
			MainActivity.class);

	@Before
	public void setUp() throws Exception
	{
		//Register idlying resource
		IdlingRegistry.getInstance().register(EspressoIdlingResources.getIdlingResource());
	}

	private String getResourceString(int id) {
		Context targetContext = InstrumentationRegistry.getTargetContext();
		return targetContext.getResources().getString(id);
	}

	@Test
	public void isEmailVisible()
	{
		onView(withId(R.id.email)).check(matches(isCompletelyDisplayed()));
	}

	@Test
	public void isPasswordVisible()
	{
		onView(withId(R.id.password)).check(matches(isCompletelyDisplayed()));
	}

	@Test
	public void isLoginVisible()
	{
		onView(withId(R.id.login)).check(matches(isCompletelyDisplayed()));
	}

	@Test
	public void isSignUpVisible()
	{
		onView(withId(R.id.signup)).check(matches(isCompletelyDisplayed()));
	}

	@Test
	public void loginWithEmptyEmailAndPassword()
	{
		onView(withId(R.id.login)).perform(click());
		onView(withId(R.id.email)).check(matches(hasErrorText(getResourceString(R.string.empty_email))));
	}

	@Test
	public void loginWithEmptyPassword()
	{
		onView(withId(R.id.email)).perform(typeText("test@gmail.com"));
		onView(withId(R.id.login)).perform(click());
		onView(withId(R.id.password)).check(matches(hasErrorText(getResourceString(R.string.empty_password))));
	}

	@Test
	public void loginWithInvalidEmail()
	{
		onView(withId(R.id.email)).perform(typeText("testgmail.com"));
		onView(withId(R.id.password)).perform(typeText("123"));
		onView(withId(R.id.login)).perform(click());
		onView(withId(R.id.email)).check(matches(hasErrorText(getResourceString(R.string.invalid_email))));
	}

	@Test
	public void loginWithInvalidPassword()
	{
		onView(withId(R.id.email)).perform(typeText("test@gmail.com"));
		onView(withId(R.id.password)).perform(typeText("123"));
		onView(withId(R.id.login)).perform(click());
		onView(withId(R.id.password)).check(matches(hasErrorText(getResourceString(R.string.pass_invalid))));
	}

	@Test
	public void loginWithCorrectDetails()
	{
		onView(withId(R.id.email)).perform(typeText("test@gmail.com"));
		onView(withId(R.id.password)).perform(typeText("12345"));
		onView(withId(R.id.login)).perform(click());
		onView(withId(R.id.topText)).check(matches(isCompletelyDisplayed()));

	}

	@Test
	public void testSignUp()
	{
		onView(withId(R.id.signup)).perform(click());
		onView(withId(R.id.name)).check(matches(isCompletelyDisplayed()));
	}



	public void tearDown() throws Exception
	{
		//unregister idling resource
		IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getIdlingResource());
	}



}
