package project.release.sdk.com.weather.mvp.presenter;

public interface Presenter<V> {

    void attachView(V mvpView);

    void detachView();

    void onDestroyed();

}
