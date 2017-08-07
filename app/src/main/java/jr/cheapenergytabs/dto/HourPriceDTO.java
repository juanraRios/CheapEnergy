package jr.cheapenergytabs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by juanra on 04/06/16.
 */
public class HourPriceDTO implements Serializable {

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

}
