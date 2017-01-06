package project.release.sdk.com.weather;

import android.app.Application;

import project.release.sdk.com.weather.api.WeatherApi;

/**
 * @author 左成城
 * @desc
 * @date 2016/12/28 17:15
 */
public class WeatherApplication extends Application {


    private AppComponent mAppComponent;
    private WeatherApi mWeatherApi;
    private static WeatherApplication mWeatherApplication;

    public static WeatherApplication getAppApplication(){
        return mWeatherApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mWeatherApi = mAppComponent.getWeatherApi();
        mWeatherApplication = (WeatherApplication) mAppComponent.getApplication();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public WeatherApi getWeatherApi() {
        return mWeatherApi;
    }
}

