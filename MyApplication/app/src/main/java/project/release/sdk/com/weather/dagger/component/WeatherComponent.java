package project.release.sdk.com.weather.dagger.component;

import dagger.Component;
import project.release.sdk.com.weather.AppComponent;
import project.release.sdk.com.weather.dagger.module.WeatherModule;
import project.release.sdk.com.weather.dagger.scopes.CustomScopeName;
import project.release.sdk.com.weather.ui.fragment.ContrastFragment;
import project.release.sdk.com.weather.ui.fragment.WeatherFragment;

/**
 * @author 左成城
 * @desc
 * @date 2016/12/22 15:24
 */

/**
 * 还有一种注入，直接在LoginPresenter的构造函数上写inject实现注入，不需要WeatherModule类
 */
@CustomScopeName
@Component(dependencies = AppComponent.class,modules = WeatherModule.class)
public interface WeatherComponent {

    WeatherFragment inject(WeatherFragment fragment);
    ContrastFragment inject(ContrastFragment fragment);
}

