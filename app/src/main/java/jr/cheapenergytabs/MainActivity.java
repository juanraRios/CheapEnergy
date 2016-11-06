package jr.cheapenergytabs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import jr.cheapenergytabs.dto.IndicatorDTO;
import jr.cheapenergytabs.dto.ResponseIndicatorDTO;
import jr.cheapenergytabs.fragments.FirstFragment;
import jr.cheapenergytabs.services.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private IndicatorDTO todayIndicatorDTO;
    private IndicatorDTO tomorrowIndicatorDTO;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private void loadTabs() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        retrofitCall();

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadTabs();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        retrofitCall();
        loadTabs();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void retrofitCall() {

        String formatToday;
        String formatTomorrow;
        Calendar today;
        Calendar tomorrow;
        SimpleDateFormat simpleDateFormat;

        today = Calendar.getInstance();
        tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE, 1);

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        formatToday = simpleDateFormat.format(today.getTime());
        formatTomorrow = simpleDateFormat.format(tomorrow.getTime());

        Call<ResponseIndicatorDTO> call = ServiceFactory.getIndicatorService().getIndicator(formatToday + "T00:00:00", formatToday + "T23:00:00");
        call.enqueue(new Callback<ResponseIndicatorDTO>() {
            @Override
            public void onResponse(Call<ResponseIndicatorDTO> call, Response<ResponseIndicatorDTO> response) {
                todayIndicatorDTO = response.body().getIndicator();

            }

            @Override
            public void onFailure(Call<ResponseIndicatorDTO> call, Throwable t) {
                Log.e("onFailure", t.toString(), t);
            }
        });
        Call<ResponseIndicatorDTO> call2 = ServiceFactory.getIndicatorService().getIndicator(formatTomorrow, formatTomorrow + "T23:00:00");
        call2.enqueue(new Callback<ResponseIndicatorDTO>() {
            @Override
            public void onResponse(Call<ResponseIndicatorDTO> call2, Response<ResponseIndicatorDTO> response) {
                tomorrowIndicatorDTO = response.body().getIndicator();

            }

            @Override
            public void onFailure(Call<ResponseIndicatorDTO> call2, Throwable t) {
                Log.e("onFailure", t.toString(), t);
            }
        });
    }

    @Override
    public void onBackPressed() {
        int currentItem = mViewPager.getCurrentItem();
        if (currentItem == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(currentItem - 1);
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public Fragment getItem(int position) {
            Fragment result = new Fragment();

            if (position == 1) {
                result = FirstFragment.newInstance(todayIndicatorDTO);
            }
            if (position == 2) {
                result = FirstFragment.newInstance(tomorrowIndicatorDTO);
            }
            return result;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
