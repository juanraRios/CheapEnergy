package jr.cheapenergytabs;

import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.empty;


/**
 * Created by juanra on 15/08/2017.
 */

public class SummaryFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkAllCardViewFieldAreFilled() {
        onView(withId(R.id.currentContentTextView)).check((ViewAssertion) empty());
    }

}
