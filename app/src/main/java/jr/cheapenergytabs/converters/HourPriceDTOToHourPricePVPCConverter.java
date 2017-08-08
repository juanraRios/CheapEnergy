package jr.cheapenergytabs.converters;

import java.io.IOException;

import jr.cheapenergytabs.domain.HourPricePVPC;
import jr.cheapenergytabs.dto.HourPriceDTO;
import retrofit2.Converter;

/**
 * Created by juanra on 06/08/2017.
 */

public class HourPriceDTOToHourPricePVPCConverter implements Converter<HourPriceDTO, HourPricePVPC> {
    @Override
    public HourPricePVPC convert(HourPriceDTO hourPriceDTO) throws IOException {
        HourPricePVPC hourPricePVPC = new HourPricePVPC();
        hourPricePVPC.setDateTimeUTC(hourPriceDTO.getDateTimeUTC());
        hourPricePVPC.setValue(hourPriceDTO.getValue());
        return hourPricePVPC;
    }
}
