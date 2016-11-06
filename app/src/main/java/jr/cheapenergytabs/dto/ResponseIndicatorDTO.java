package jr.cheapenergytabs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juanra on 04/06/16.
 */
public class ResponseIndicatorDTO {
    @JsonProperty("indicator")
    IndicatorDTO indicator;

    public IndicatorDTO getIndicator() {
        return indicator;
    }

    public void setIndicator(IndicatorDTO indicator) {
        this.indicator = indicator;
    }
}
