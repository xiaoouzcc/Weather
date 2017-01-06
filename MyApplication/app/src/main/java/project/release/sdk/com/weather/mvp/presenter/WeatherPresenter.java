package project.release.sdk.com.weather.mvp.presenter;

import android.content.Context;
import android.util.Log;

import project.release.sdk.com.weather.api.WeatherApi;
import project.release.sdk.com.weather.bean.WeatherBean;
import project.release.sdk.com.weather.mvp.listener.WeatherListener;
import project.release.sdk.com.weather.mvp.model.WeatherModel;
import project.release.sdk.com.weather.mvp.view.WeatherViewImpl;


/**
 * @author 左成城
 * @desc
 * @date 2016/3/16 18:03
 */
public class WeatherPresenter extends BasePresenter<WeatherViewImpl> implements WeatherListener {

    private WeatherModel mWeatherModel;

    public WeatherPresenter() {
        mWeatherModel = new WeatherModel(this);
    }


    public void getWeather(WeatherApi weatherApi, Context context, String cityName) {
        Log.e("getWeather111","getWeather111");
        getMvpView().showProgress();
        mWeatherModel.getWeather(weatherApi,context,cityName );
    }


    @Override
    public void loadingSuccess(WeatherBean bean) {
        getMvpView().getSuccess(bean);
        getMvpView().stopProgress();
    }

    @Override
    public void loadingFail(String message) {
       getMvpView().stopProgress();
       getMvpView().getFail(message);
    }
}


