package com.abbott.espressotestsample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.abbott.espressotestsample.UI.SignupActivity;
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
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyBelow;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpActivityTest
{

	@Rule
	public ActivityTestRule<SignupActivity> mActivityRule = new ActivityTestRule<>(
			SignupActivity.class);

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
	public void verifySignupWithNoDetails()
	{
		onView(withText("Sign Up")).perform(click());
		onView(withText(R.string.fill_all_fields)).check(matches(isDisplayed()));
	}

	@Test
	public void verifySignupWithWrongPassword()
	{

		onView(withId(R.id.name)).perform(typeText("test"));
		onView(withId(R.id.email)).perform(typeText("test@gmail.com"));
		onView(withId(R.id.password)).perform(typeText("123456"));
		onView(withId(R.id.confirmPass)).perform(typeText("1234567"));

		onView(withText("Sign Up")).perform(click());
		onView(withText(R.string.password_mismatch)).check(matches(isDisplayed()));
	}

	@Test
	public void viewsDoNotOverlap()
	{

		onView(withId(R.id.confirmPass)).check(isCompletelyBelow(withId(R.id.password)));
		onView(withId(R.id.password)).check(isCompletelyBelow(withId(R.id.email)));
		onView(withId(R.id.email)).check(isCompletelyBelow(withId(R.id.name)));

	}

	//check text is center horizontal positioned

	@Test
	public void textCentrallyPositioned()
	{
		onView(withId(R.id.email)).check(matches(CustomMatcher.isCenteredHorizontally()));
	}

	public void tearDown() throws Exception
	{
		//unregister idling resource
		IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getIdlingResource());
	}
}
