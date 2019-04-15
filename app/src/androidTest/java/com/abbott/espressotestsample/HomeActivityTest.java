package com.abbott.espressotestsample;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.abbott.espressotestsample.UI.HomeActivity;
import com.abbott.espressotestsample.Utils.EspressoIdlingResources;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Locale;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.abbott.espressotestsample.CustomMatcher.withRecyclerView;
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HomeActivityTest
{
	@Rule
	public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule<>(
			HomeActivity.class);

	@Rule
	public final ScreenshotWatcher mScreenshotWatcher = new ScreenshotWatcher();

	@ClassRule
	public static final ForceLocale localeTestRule = new ForceLocale(Locale.FRENCH);

	@Rule
	public final GrantPermissionRule mGrantPermissionRule =
			GrantPermissionRule.grant(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE);

	@Before
	public void setUp() throws Exception
	{
		//Register idlying resource
		IdlingRegistry.getInstance().register(EspressoIdlingResources.getIdlingResource());

	}

	@Test
	public void checkSettingScreen()
	{
		onView(withId(R.id.topText)).check(matches(isDisplayed()));

	}

	@Test
	public void checkTopTextFitTwoLines()
	{
		mScreenshotWatcher.captureScreenshot("checkTopTextFitTwoLines");
		onView(withId(R.id.topText)).check(matches(CustomMatcher.isTextInLines(2)));
	}

	@Test
	public void checkRecyclerViewHighAlarmIsOn()
	{

		onView(withRecyclerView(R.id.alarmMenuList).atPosition(1))
				.check(matches(hasDescendant(withText("High Glucose Alarm"))));
		onView(withRecyclerView(R.id.alarmMenuList).atPosition(1))
				.check(matches(hasDescendant(withText("ON"))));
	}

	@Test
	public void checkHighAlarmDescriptionScreen()
	{
		onView(withRecyclerView(R.id.alarmMenuList).atPosition(1)).perform(click());

		onView(withText("High Glucose Alarm")).check(matches(isDisplayed()));

	}

	@Test
	public void checkContentsOfRecyclerViewDoesNotOverlap()
	{
		onView(withRecyclerView(R.id.alarmMenuList).
				atPositionOnView(0, R.id.textMenuName)).
				check(isCompletelyAbove(withRecyclerView(R.id.alarmMenuList).
						atPositionOnView(0, R.id.textGlucoseAlarm)));
	}

	public void tearDown() throws Exception
	{
		//unregister idling resource
		IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getIdlingResource());
	}
}
