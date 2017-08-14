package jr.cheapenergytabs.converters;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import retrofit2.Converter;

/**
 * Created by juanra on 09/08/2017.
 */

public class HourPriceValueToPrintHourPriceValue implements Converter<Double,String> {

    @Override
    public String convert(Double value) throws IOException {

        Double priceValue = value/1000;
        DecimalFormat df = new DecimalFormat("0.00000");
        df.setRoundingMode(RoundingMode.CEILING);

        return df.format(priceValue).concat(" €/kWh");
    }
}
