package jr.cheapenergytabs.converters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import jr.cheapenergytabs.domain.HourPricePVPC;
import jr.cheapenergytabs.dto.HourPriceDTO;
import retrofit2.Converter;

/**
 * Created by juanra on 06/08/2017.
 */

public class DateToSpanishDateConverter implements Converter<Date, String> {
    @Override
    public String convert(Date date) throws IOException {
        String result;
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        result = formatter.format(date);
        return result;
    }
}
