package jr.cheapenergytabs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import jr.cheapenergytabs.dto.HourPriceDTO;
import jr.cheapenergytabs.dto.IndicatorDTO;
import jr.cheapenergytabs.R;

public class IndicatorAdapter extends RecyclerView.Adapter<IndicatorAdapter.IndicatorViewHolder> {

    private  IndicatorDTO indicator;
    private Context mainContext;

    public IndicatorAdapter(Context mainContext, IndicatorDTO indicator) {
        this.mainContext = mainContext;
        this.indicator = indicator;
    }

    public class IndicatorViewHolder extends RecyclerView.ViewHolder{

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
        priceValue = hourPrice.getValue()/1000;
        df = new DecimalFormat("0.00000");
        df.setRoundingMode(RoundingMode.CEILING);
        dateValue = hourPrice.getDateTimeUTC();

        viewHolder.price.setText(df.format(priceValue)+" €/kWh");
        viewHolder.date.setText(dateToSpanishDate(dateValue));

    }

    @Override
    public int getItemCount() {
        return indicator.getValues().size();
    }

    private String dateToSpanishDate(Date date){
        String result;
        SimpleDateFormat formatter;

        formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        result = formatter.format(date);

        return result;
    }

}
