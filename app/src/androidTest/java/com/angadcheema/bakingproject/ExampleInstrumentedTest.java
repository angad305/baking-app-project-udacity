package com.angadcheema.bakingproject;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import androidx.test.espresso.contrib.RecyclerViewActions;
import android.content.Context;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import com.angadcheema.bakingproject.Activity.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

  @Rule
  public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
      MainActivity.class);

  @Test
  public void useAppContext() {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    assertEquals("com.angadcheema.bakingproject", appContext.getPackageName());

  }

  @Test
  public void MyTests() {
    // Let the UI load completely first
    try {
      Thread.sleep(8000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    //Recyclerview scroll to position and check text
    onView(ViewMatchers.withId(R.id.master_recycler_view))
        .perform(RecyclerViewActions.scrollToPosition(0));
    onView(withText(R.string.nutella)).check(matches(isDisplayed()));

  }

}