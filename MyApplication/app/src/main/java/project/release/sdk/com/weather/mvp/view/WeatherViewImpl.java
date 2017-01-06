package project.release.sdk.com.weather.mvp.view;


import project.release.sdk.com.weather.bean.WeatherBean;

/**
 * @author 左成城
 * @desc
 * @date 2016/3/16 18:06
 */
public interface WeatherViewImpl extends MvpView {

    void getSuccess(WeatherBean bean);

    void getFail(String message);
}


