package jr.cheapenergytabs.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by juanra on 04/06/16.
 */
public class ServiceFactory {

    private static IndicatorService indicatorService;
    private static final String BASE_URL = "https://api.esios.ree.es";
    private static Retrofit retrofitService;

    public static Retrofit getRetrofit() {
        if (retrofitService == null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            retrofitService = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(getConverterFactory())
                    .client(okHttpClient)
                    .build();
        }
        return retrofitService;
    }

    private static JacksonConverterFactory getConverterFactory() {
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

        objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return JacksonConverterFactory.create(objMapper);
    }

    public static IndicatorService getIndicatorService() {
        if (indicatorService == null) {

            indicatorService = getRetrofit().create(IndicatorService.class);

        }
        return indicatorService;
    }

}
