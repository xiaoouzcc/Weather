package project.release.sdk.com.weather;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import project.release.sdk.com.weather.api.WeatherApi;
import project.release.sdk.com.weather.net.NetServiceModule;

/**
 * ProjectName：MyApplication
 * Author：Administrator
 * Time: 2017/1/3 10:16
 * Remark
 */
@Singleton
@Component(modules = {AppModule.class,NetServiceModule.class})
public interface AppComponent {

     Application getApplication();

     WeatherApi getWeatherApi();
}
