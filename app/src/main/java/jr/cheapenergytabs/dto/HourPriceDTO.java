package jr.cheapenergytabs.dto;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import jr.cheapenergytabs.converters.HourPriceValueToPrintHourPriceValue;

/**
 * Created by juanra on 04/06/16.
 */
public class HourPriceDTO implements Serializable, Comparable<HourPriceDTO> {

    @JsonProperty("value")
    private Double value;

    @JsonProperty("datetime_utc")
    private Date dateTimeUTC;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDateTimeUTC() {
        return dateTimeUTC;
    }

    public void setDateTimeUTC(Date dateTimeUTC) {
        this.dateTimeUTC = dateTimeUTC;
    }

    @Override
    public int compareTo(@NonNull HourPriceDTO hourPriceDTO) {
        return this.getValue().compareTo(hourPriceDTO.getValue());
    }

    public String valuePrint() {
        Double priceValue = getValue() / 1000;
        DecimalFormat df = new DecimalFormat("0.00000");
        df.setRoundingMode(RoundingMode.CEILING);

        return df.format(priceValue) + " €/kWh";
    }
}
