package project.release.sdk.com.weather.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.release.sdk.com.weather.AppComponent;
import project.release.sdk.com.weather.R;
import project.release.sdk.com.weather.base.BaseFragment;
import project.release.sdk.com.weather.bean.WeatherBean;
import project.release.sdk.com.weather.bean.WeatherBeans;
import project.release.sdk.com.weather.dagger.component.DaggerWeatherComponent;
import project.release.sdk.com.weather.mvp.presenter.WeatherPresenter;
import project.release.sdk.com.weather.mvp.view.WeatherViewImpl;
import project.release.sdk.com.weather.wedgit.ContrastView;

public class ContrastFragment extends BaseFragment<WeatherPresenter, WeatherViewImpl> implements WeatherViewImpl{

    @Bind(R.id.today_contrast)
    ContrastView mTodayContrast;


    private List<WeatherBeans> mBeansList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comtrast;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

        DaggerWeatherComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected void initViews(View view) {
        mBeansList = new ArrayList<>();
        mPresenter.getWeather(mWeatherApi,getActivity(),"重庆");
    }

    @Override
    protected void initToolbar(View view) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

        List<WeatherBeans> list = new ArrayList<>();
        for (int i = 0;i<5;i++){
            int Temp = (int) Math.round(Math.random() * 7 + 18);
            WeatherBeans beans = new WeatherBeans();
            beans.setDay(i+1+"");
            beans.setHigh(Temp);
            int temp = (int) Math.round(Math.random() * 3 + 15);
            beans.setLow(temp);
            list.add(beans);
        }

    }

    @Override
    protected void clearDestory() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void getSuccess(WeatherBean bean) {
        WeatherBeans beans = new WeatherBeans();
        String high = bean.getData().getYesterday().getHigh();
        String low = bean.getData().getYesterday().getLow();
        beans.setHigh(Integer.parseInt(high.replaceAll("\\D","").trim()));
        beans.setLow(Integer.parseInt(low.replaceAll("\\D","").trim()));
        String day = bean.getData().getYesterday().getDate();
        beans.setDay(day.replaceAll("\\D","").trim()+"号");
        mBeansList.add(beans);

        for (int i = 0 ;i< bean.getData().getForecast().size();i++){
            WeatherBeans bean1 = new WeatherBeans();
            String high1 = bean.getData().getForecast().get(i).getHigh();
            String low1 = bean.getData().getForecast().get(i).getLow();
            String day1 = bean.getData().getForecast().get(i).getDate();
            bean1.setHigh(Integer.parseInt(high1.replaceAll("\\D","").trim()));
            bean1.setLow(Integer.parseInt(low1.replaceAll("\\D","").trim()));
            bean1.setDay(day1.replaceAll("\\D","").trim()+"号");
            mBeansList.add(bean1);
        }
        mTodayContrast.setListData(mBeansList);
    }

    @Override
    public void getFail(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void stopProgress() {

    }
}


