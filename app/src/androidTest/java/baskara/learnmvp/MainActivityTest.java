package baskara.learnmvp;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @Test
    public void testShouldShowErrorIfInputEmailIsBlank() throws Exception {
        onView(withId(R.id.inputEmail)).perform(typeText("email"), closeSoftKeyboard());
        onView(withId(R.id.inputPassword)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withText("Email and Password should not be empty")).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}