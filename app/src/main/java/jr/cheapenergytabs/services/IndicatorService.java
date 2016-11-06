package jr.cheapenergytabs.services;

import jr.cheapenergytabs.dto.ResponseIndicatorDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by juanra on 04/06/16.
 */
public interface IndicatorService {
    @Headers("Authorization: Token token=\"3426e0505e1c3c67a1713f5c5d42a63bf406a37c088734e29bd6596e5af9fe9d\"")
    @GET(value = "indicators/10229?geo_agg=sum&geo_ids=&time_trunc=hour&time_agg=&locale=es")
    Call<ResponseIndicatorDTO>getIndicator(@Query("start_date") String starDate, @Query("end_date") String endDate);

}
