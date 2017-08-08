package jr.cheapenergytabs.converters;

import java.io.IOException;

import jr.cheapenergytabs.domain.HourPricePVPC;
import jr.cheapenergytabs.dto.HourPriceDTO;
import retrofit2.Converter;

/**
 * Created by juanra on 06/08/2017.
 */

public class HourPricePVPCToHourPriceDTOConverter implements Converter<HourPricePVPC, HourPriceDTO> {
    @Override
    public HourPriceDTO convert(HourPricePVPC hourPricePVPC) throws IOException {
        HourPriceDTO hourPriceDTO = new HourPriceDTO();
        hourPriceDTO.setValue(hourPricePVPC.getValue());
        hourPriceDTO.setDateTimeUTC(hourPricePVPC.getDateTimeUTC());
        return hourPriceDTO;
    }
}
