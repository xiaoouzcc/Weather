package project.release.sdk.com.weather.mvp.model;

import android.content.Context;
import android.util.Log;

import project.release.sdk.com.weather.api.WeatherApi;
import project.release.sdk.com.weather.bean.WeatherBean;
import project.release.sdk.com.weather.mvp.listener.WeatherListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author 左成城
 * @desc
 * @date 2017/1/4 17:45
 */
public class WeatherModel {

    private WeatherListener mWeatherListener;


    public WeatherModel(WeatherListener weatherListener) {
        this.mWeatherListener = weatherListener;
    }

    public void getWeather(WeatherApi weatherApi,final Context context, final String cityName) {

        Log.e("getWeather","getWeather"+weatherApi);
        weatherApi.getWeather(cityName).enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                if (response != null && response.isSuccess()) {
                    if (response.body().getDesc().equals("OK")) {
                        mWeatherListener.loadingSuccess(response.body());
                    } else {
                        mWeatherListener.loadingFail(response.body().getDesc());
                    }
                } else {
                    mWeatherListener.loadingFail("error");
                }
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {

            }
        });
    }
}

