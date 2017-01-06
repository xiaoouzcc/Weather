package project.release.sdk.com.weather.api;

import project.release.sdk.com.weather.bean.WeatherBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author 左成城
 * @desc
 * @date 2017/1/3 10:19
 */
public interface WeatherApi {
   // @FormUrlEncoded
    @GET("weather_mini")
    Call<WeatherBean> getWeather(@Query("city") String cityName);

}

