package jr.cheapenergytabs;

import android.os.Bundle;
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

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jr.cheapenergytabs.converters.HourPricePVPCToHourPriceDTOConverter;
import jr.cheapenergytabs.domain.DaoSession;
import jr.cheapenergytabs.domain.HourPricePVPC;
import jr.cheapenergytabs.domain.HourPricePVPCDao;
import jr.cheapenergytabs.domain.IndicatorPVPC;
import jr.cheapenergytabs.domain.IndicatorPVPCDao;
import jr.cheapenergytabs.dto.HourPriceDTO;
import jr.cheapenergytabs.dto.IndicatorDTO;
import jr.cheapenergytabs.dto.ResponseIndicatorDTO;
import jr.cheapenergytabs.fragments.FirstFragment;
import jr.cheapenergytabs.fragments.SummaryFragment;
import jr.cheapenergytabs.services.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private IndicatorDTO indicatorDTO;
    private IndicatorDTO todayIndicatorDTO;
    private IndicatorDTO tomorrowIndicatorDTO;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private IndicatorPVPCDao indicatorPVPCDao;
    private HourPricePVPCDao hourPricePVPCDao;

    private Query<IndicatorPVPC> todayIndicatorQuery;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private final static String LOG = String.valueOf(MainActivity.class);

    private void loadTabs() {

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        indicatorDTO = new IndicatorDTO();
        indicatorDTO.setValues(new ArrayList<HourPriceDTO>());
        todayIndicatorDTO = new IndicatorDTO();
        todayIndicatorDTO.setValues(new ArrayList<HourPriceDTO>());
        tomorrowIndicatorDTO = new IndicatorDTO();
        tomorrowIndicatorDTO.setValues(new ArrayList<HourPriceDTO>());

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        indicatorPVPCDao = daoSession.getIndicatorPVPCDao();
        hourPricePVPCDao = daoSession.getHourPricePVPCDao();

        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.set(todayCalendar.get(Calendar.YEAR), todayCalendar.get(Calendar.MONTH), todayCalendar.get(Calendar.DATE), 0, 0, 0);

        Calendar tomorrowConsultHourCalendar = Calendar.getInstance();
        tomorrowConsultHourCalendar.set(tomorrowConsultHourCalendar.get(Calendar.YEAR), tomorrowConsultHourCalendar.get(Calendar.MONTH), tomorrowConsultHourCalendar.get(Calendar.DATE), 20, 15, 0);

        todayIndicatorQuery = indicatorPVPCDao.queryBuilder().whereOr(IndicatorPVPCDao.Properties.DateTimeUTC.eq(todayCalendar.getTime()), IndicatorPVPCDao.Properties.DateTimeUTC.gt(todayCalendar.getTime())).build();

        List<IndicatorPVPC> todayIndicatorList = todayIndicatorQuery.list();

        if (todayIndicatorList.isEmpty() || todayIndicatorList.get(0).getValues().isEmpty() || (todayIndicatorList.get(0).getValues().size() < 48 && new Date().after(tomorrowConsultHourCalendar.getTime()))) {
            retrofitCall(new Date());

//            List<HourPriceDTO> todayIndicatorDTOValues = new ArrayList<>((indicatorDTO.getValues().size() >= 24) ? indicatorDTO.getValues().subList(0, 24) : new ArrayList<HourPriceDTO>());
//            todayIndicatorDTO.setValues(todayIndicatorDTOValues);
//
//            List<HourPriceDTO> tomorrowIndicatorDTOValues = new ArrayList<>((indicatorDTO.getValues().size() >= 48) ? indicatorDTO.getValues().subList(24, 48) : new ArrayList<HourPriceDTO>());
//            tomorrowIndicatorDTO.setValues(tomorrowIndicatorDTOValues);

        } else {
            QueryBuilder<IndicatorPVPC> queryBuilder = indicatorPVPCDao.queryBuilder();
            queryBuilder.where(IndicatorPVPCDao.Properties.DateTimeUTC.eq(todayCalendar.getTime()));
            List<IndicatorPVPC> list = todayIndicatorQuery.list();
            IndicatorPVPC indicatorPVPC = list.get(0);
            List<HourPricePVPC> hoursDomain = indicatorPVPC.getValues();

//          Converter HourPricePVPC to HourPriceDTO
            HourPricePVPCToHourPriceDTOConverter hourPricePVPCToHourPriceDTOConverter = new HourPricePVPCToHourPriceDTOConverter();
            List<HourPriceDTO> hoursDTO = new ArrayList<>();
            for (HourPricePVPC hourPricePVPC : hoursDomain) {
                HourPriceDTO hourPriceDTO = new HourPriceDTO();
                try {
                    hourPriceDTO = hourPricePVPCToHourPriceDTOConverter.convert(hourPricePVPC);
                } catch (IOException e) {
                    Log.e(LOG, "Error when execute converter hourPricePVPCToHourPriceDTOConverter", e);
                }
                hoursDTO.add(hourPriceDTO);
                List<HourPriceDTO> todayIndicatorDTOValues = new ArrayList<>((hoursDTO.size() >= 24) ? hoursDTO.subList(0, 24) : new ArrayList<HourPriceDTO>());
                todayIndicatorDTO.setValues(todayIndicatorDTOValues);

                List<HourPriceDTO> tomorrowIndicatorDTOValues = new ArrayList<>((hoursDTO.size() >= 48) ? hoursDTO.subList(24, 48) : new ArrayList<HourPriceDTO>());
                tomorrowIndicatorDTO.setValues(tomorrowIndicatorDTOValues);
            }
            loadTabs();
        }
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

    private void retrofitCall(Date date) {

        Calendar calendar;
        String formatToday;
        String formatTomorrow;
        SimpleDateFormat simpleDateFormat;

        calendar = Calendar.getInstance();
        calendar.setTime(date);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        formatToday = simpleDateFormat.format(calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        formatTomorrow = simpleDateFormat.format(calendar.getTime());

        Call<ResponseIndicatorDTO> call = ServiceFactory.getIndicatorService().getIndicator(formatToday + "T00:00:00", formatTomorrow + "T23:00:00");
        call.enqueue(new Callback<ResponseIndicatorDTO>() {
            @Override
            public void onResponse(Call<ResponseIndicatorDTO> call, Response<ResponseIndicatorDTO> response) {
                indicatorDTO = response.body().getIndicator();

                IndicatorPVPC indicatorPVPCToday = new IndicatorPVPC();
                indicatorPVPCToday.setDateTimeUTC(new Date());
                indicatorPVPCToday.setName("Prueba");
                indicatorPVPCDao.insert(indicatorPVPCToday);

                for (HourPriceDTO hourPriceDTO : indicatorDTO.getValues()) {
                    HourPricePVPC hour = new HourPricePVPC();
                    hour.setIdIndicatorPVPC(indicatorPVPCToday.getId());
                    hour.setValue(hourPriceDTO.getValue());
                    hour.setDateTimeUTC(hourPriceDTO.getDateTimeUTC());
                    hourPricePVPCDao.insert(hour);
                }
                List<HourPriceDTO> todayIndicatorDTOValues = new ArrayList<>((indicatorDTO.getValues().size() >= 24) ? indicatorDTO.getValues().subList(0, 24) : new ArrayList<HourPriceDTO>());
                todayIndicatorDTO.getValues().addAll(todayIndicatorDTOValues);

                List<HourPriceDTO> tomorrowIndicatorDTOValues = new ArrayList<>((indicatorDTO.getValues().size() >= 48) ? indicatorDTO.getValues().subList(24, 48) : new ArrayList<HourPriceDTO>());
                tomorrowIndicatorDTO.setValues(tomorrowIndicatorDTOValues);
                loadTabs();
            }

            @Override
            public void onFailure(Call<ResponseIndicatorDTO> call, Throwable t) {
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

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment result = new Fragment();

            if (position == 0) {
                result = SummaryFragment.newInstance(todayIndicatorDTO);
            }
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
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }
}
