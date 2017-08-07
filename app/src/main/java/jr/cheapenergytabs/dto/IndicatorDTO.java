package jr.cheapenergytabs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by juanra on 04/06/16.
 */
public class IndicatorDTO implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("values")
    private List<HourPriceDTO> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<HourPriceDTO> getValues() {
        return values;
    }

    public void setValues(List<HourPriceDTO> values) {
        this.values = values;
    }
}
