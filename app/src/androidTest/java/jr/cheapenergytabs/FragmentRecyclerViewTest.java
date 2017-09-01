package jr.cheapenergytabs;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


/**
 * Created by juanra on 15/08/2017.
 */

public class FragmentRecyclerViewTest {
    
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void countItemsRecyclerViewListThenIGet24() {
        onView(withText("Hoy")).perform(click());
        onView(allOf(withId(R.id.recycler_view), isDisplayed())).check(new RecyclerViewItemCountAssertion(24));
    }

}
