package com.abbott.espressotestsample;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
public class CustomMatcher
{

	public static TypeSafeMatcher<View> isTextInLines(final int lines) {
		return new TypeSafeMatcher<View>() {
			@Override
			protected boolean matchesSafely(View item) {
				return ((TextView) item).getLineCount() == lines;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("isTextInLines");
			}
		};
	}

	public static Matcher<View> isCenteredHorizontally() {
		return new TypeSafeMatcher<View>() {
			@Override
			protected boolean matchesSafely(View item) {
				WindowManager windowManager = (WindowManager) item.getContext().getSystemService(Context.WINDOW_SERVICE);
				Display display = windowManager.getDefaultDisplay();
				Point displaySize = new Point();
				display.getSize(displaySize);
				int width = displaySize.x + 1;

				int[] outLocation = new int[2];
				item.getLocationOnScreen(outLocation);
				int viewLeft = outLocation[0];
				int rightMargin = width - (viewLeft + item.getMeasuredWidth());
				// if screen width is an even number and view width an odd one then an error is 1 point
				return Math.abs(rightMargin - viewLeft) < 2;
			}

			@Override
			public void describeTo(Description description) {

			}
		};
	}

	public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
		return new RecyclerViewMatcher(recyclerViewId);
	}

}
