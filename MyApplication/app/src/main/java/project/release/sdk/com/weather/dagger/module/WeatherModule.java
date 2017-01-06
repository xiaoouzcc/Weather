package project.release.sdk.com.weather.dagger.module;

import dagger.Module;
import dagger.Provides;
import project.release.sdk.com.weather.dagger.scopes.CustomScopeName;
import project.release.sdk.com.weather.mvp.presenter.WeatherPresenter;

/**
 * @author 左成城
 * @desc
 * @date 2016/12/27 15:20
 */
@Module
public class WeatherModule {
    @CustomScopeName
    @Provides
    WeatherPresenter provideWeatherFragmentPresenter() {
        return new WeatherPresenter();
    }

}

