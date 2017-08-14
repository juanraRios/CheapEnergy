package jr.cheapenergytabs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import jr.cheapenergytabs.R;
import jr.cheapenergytabs.converters.DateToSpanishDateConverter;
import jr.cheapenergytabs.dto.HourPriceDTO;
import jr.cheapenergytabs.dto.IndicatorDTO;

public class IndicatorAdapter extends RecyclerView.Adapter<IndicatorAdapter.IndicatorViewHolder> {

    private IndicatorDTO indicator;
    private Context mainContext;

    private final static String LOG = String.valueOf(IndicatorAdapter.class);


    public IndicatorAdapter(Context mainContext, IndicatorDTO indicator) {
        this.mainContext = mainContext;
        this.indicator = indicator;
    }

    public class IndicatorViewHolder extends RecyclerView.ViewHolder {

        protected TextView date;
        protected TextView price;

        // Precarga de los textview en las variables
        public IndicatorViewHolder(View view) {
            super(view);

            this.date = (TextView) view.findViewById(R.id.date_cardView);
            this.price = (TextView) view.findViewById(R.id.price_cardView);
        }

    }

    //Este metodo crea e infla la cardView
    @Override
    public IndicatorViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hour_card, viewGroup,
                false);

        return new IndicatorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IndicatorViewHolder viewHolder, int position) {

        IndicatorDTO indicator;
        HourPriceDTO hourPrice;
        Double priceValue;
        DecimalFormat df;
        Date dateValue;

        indicator = this.indicator;
        hourPrice = indicator.getValues().get(position);
        priceValue = hourPrice.getValue() / 1000;
        df = new DecimalFormat("0.00000");
        df.setRoundingMode(RoundingMode.CEILING);
        dateValue = hourPrice.getDateTimeUTC();

        viewHolder.price.setText(df.format(priceValue) + " €/kWh");
        DateToSpanishDateConverter dateToSpanishDateConverter = new DateToSpanishDateConverter();
        try {
            viewHolder.date.setText(dateToSpanishDateConverter.convert(dateValue));
        } catch (IOException e) {
            Log.e(LOG, "Error when execute converter dateToSpanishDateConverter", e);
        }

    }

    @Override
    public int getItemCount() {
        return indicator.getValues().size();
    }

}
