package jr.cheapenergytabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import jr.cheapenergytabs.R;
import jr.cheapenergytabs.converters.HourPriceValueToPrintHourPriceValue;
import jr.cheapenergytabs.dto.HourPriceDTO;
import jr.cheapenergytabs.dto.IndicatorDTO;

public class SummaryFragment extends Fragment {

    private final static String LOG = String.valueOf(SummaryFragment.class);
    private IndicatorDTO indicatorDTO;

    public SummaryFragment() {
        super();
    }


    public static SummaryFragment newInstance(IndicatorDTO indicatorDTO) {
        SummaryFragment result = new SummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("indicatorDTO", indicatorDTO);
        result.setArguments(bundle);
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        indicatorDTO = (IndicatorDTO) getArguments().getSerializable("indicatorDTO");
        View rootView = inflater.inflate(R.layout.sumary_fragment, container, false);

        ArrayList<HourPriceDTO> hourPriceDTOOrderedList = new ArrayList<>(indicatorDTO.getValues());
        Collections.sort(hourPriceDTOOrderedList);
        DecimalFormat df = new DecimalFormat("##.##");

        TextView currentContentTextView = (TextView) rootView.findViewById(R.id.currentContentTextView);
        for (HourPriceDTO hourPriceDTO : indicatorDTO.getValues()) {
            Calendar hourPrice = Calendar.getInstance();
            hourPrice.setTime(hourPriceDTO.getDateTimeUTC());
            Integer hour = hourPrice.get(Calendar.HOUR_OF_DAY);

            Calendar now = Calendar.getInstance();
            Integer currentHour = now.get(Calendar.HOUR_OF_DAY);

            if (hour.equals(currentHour)) {
                currentContentTextView.setText(hourPriceDTO.valuePrint());
            }
        }

        TextView textView = (TextView) rootView.findViewById(R.id.bestContentTextView);
        HourPriceDTO minHourPriceDTO = hourPriceDTOOrderedList.get(0);
        textView.setText(minHourPriceDTO.valuePrint());

        TextView worstContentTextView = (TextView) rootView.findViewById(R.id.worstContentTextView);
        HourPriceDTO maxHourPriceDTO = hourPriceDTOOrderedList.get(hourPriceDTOOrderedList.size() - 1);
        worstContentTextView.setText(maxHourPriceDTO.valuePrint());

        TextView avgContentTextView = (TextView) rootView.findViewById(R.id.avgContentTextView);
        HourPriceValueToPrintHourPriceValue converter = new HourPriceValueToPrintHourPriceValue();
        try {
            avgContentTextView.setText(converter.convert(calculateAverage(indicatorDTO.getValues())));
        } catch (IOException e) {
            Log.e(LOG, "Error when execute converter HourPriceValueToPrintHourPriceValue", e);
        }

        TextView maxContentTextView = (TextView) rootView.findViewById(R.id.maxContentTextView);
        Double percentMaxDifference = 100 - ((minHourPriceDTO.getValue() * 100) / maxHourPriceDTO.getValue());

        maxContentTextView.setText(df.format(percentMaxDifference).concat("%"));

        return rootView;
    }

    private Double calculateAverage(List<HourPriceDTO> hourPriceDtoList) {
        Double avg = 0.0;
        if (!hourPriceDtoList.isEmpty()) {
            for (HourPriceDTO hourPriceDto : hourPriceDtoList) {
                avg += hourPriceDto.getValue();
            }
            return avg / hourPriceDtoList.size();
        }
        return avg;
    }

}