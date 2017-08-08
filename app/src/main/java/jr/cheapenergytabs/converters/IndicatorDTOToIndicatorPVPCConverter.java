package jr.cheapenergytabs.converters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jr.cheapenergytabs.domain.HourPricePVPC;
import jr.cheapenergytabs.domain.IndicatorPVPC;
import jr.cheapenergytabs.dto.HourPriceDTO;
import jr.cheapenergytabs.dto.IndicatorDTO;
import retrofit2.Converter;

/**
 * Created by juanra on 06/08/2017.
 */

public class IndicatorDTOToIndicatorPVPCConverter implements Converter<IndicatorDTO, IndicatorPVPC> {
    @Override
    public IndicatorPVPC convert(IndicatorDTO indicatorDTO) throws IOException {
        IndicatorPVPC indicatorPVPC = new IndicatorPVPC();
        indicatorPVPC.setName(indicatorDTO.getName());

        List<HourPricePVPC> values = new ArrayList<>();
        HourPriceDTOToHourPricePVPCConverter hourPriceDTOToHourPricePVPCConverter = new HourPriceDTOToHourPricePVPCConverter();
        for (HourPriceDTO hourPriceDTO:indicatorDTO.getValues()) {
            values.add(hourPriceDTOToHourPricePVPCConverter.convert(hourPriceDTO));
        }
        indicatorPVPC.setValues(values);
        return indicatorPVPC;
    }
}
