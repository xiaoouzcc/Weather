package project.release.sdk.com.weather.mvp.listener;

/**
 * @author 左成城
 * @desc
 * @date 2016/11/7 11:24
 */
public interface BaseListener<T> {

    void loadingSuccess(T t);

    void loadingFail(String message);

}

