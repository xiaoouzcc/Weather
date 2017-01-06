package project.release.sdk.com.weather.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.release.sdk.com.weather.AppComponent;
import project.release.sdk.com.weather.R;
import project.release.sdk.com.weather.base.BaseFragment;
import project.release.sdk.com.weather.bean.WeatherBean;
import project.release.sdk.com.weather.dagger.component.DaggerWeatherComponent;
import project.release.sdk.com.weather.mvp.presenter.WeatherPresenter;
import project.release.sdk.com.weather.mvp.view.WeatherViewImpl;
import project.release.sdk.com.weather.utils.StringUtils;
import project.release.sdk.com.weather.utils.WeatherUtis;

public class WeatherFragment extends BaseFragment<WeatherPresenter, WeatherViewImpl> implements WeatherViewImpl {
    //声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;
    AMapLocationListener mAMapLocationListener = null;
    public boolean is_refresh = true;

    @Bind(R.id.weather_txt_location)
    TextView mWeatherTxtLocation;
    @Bind(R.id.today_img_show)
    ImageView mTodayImgShow;
    @Bind(R.id.today_txt_temperature)
    TextView mTodayTxtTemperature;
    @Bind(R.id.today_txt_notice)
    TextView mTodayTxtNotice;
    @Bind(R.id.today_txt_state)
    TextView mTodayTxtState;
    @Bind(R.id.today_txt_wind_direction)
    TextView mTodayTxtWindDirection;
    @Bind(R.id.today_txt_wind_power)
    TextView mTodayTxtWindPower;
    @Bind(R.id.today_txt_change)
    TextView mTodayTxtChange;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_today;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerWeatherComponent.builder().appComponent(appComponent).
                build().inject(this);
    }

    @Override
    protected void initViews(View view) {

        location();
    }

    @Override
    protected void initToolbar(View view) {

    }

    @Override
    protected void initListeners() {

        mWeatherTxtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location();
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void clearDestory() {

    }

    private void location() {

        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
        //异步获取定位结果
        mAMapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {

                if (amapLocation != null) {

                    if (amapLocation.getErrorCode() == 0) {
                        if (is_refresh) {
                            //解析定位结果
                            String result = amapLocation.getCity();
                            mWeatherTxtLocation.setText(result);
                            Log.e("result", "result=" + result);
                            mPresenter.getWeather(mWeatherApi, getActivity(), StringUtils.getContent(result));

                        }

                        is_refresh = false;
                    }
                }
            }
        };
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);
        //启动定位
        mLocationClient.startLocation();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void getSuccess(WeatherBean bean) {
        String day = bean.getData().getForecast().get(0).getType();
        mTodayImgShow.setImageDrawable(getActivity().getResources().getDrawable(WeatherUtis.getWeatherState(day)));
        mTodayTxtTemperature.setText(bean.getData().getWendu() + "℃");
        mTodayTxtNotice.setText(bean.getData().getGanmao());
        mTodayTxtState.setText(bean.getData().getForecast().get(0).getType());
        mTodayTxtWindDirection.setText(bean.getData().getForecast().get(0).getFengxiang());
        mTodayTxtWindPower.setText(bean.getData().getForecast().get(0).getFengli());

        String high1 = bean.getData().getForecast().get(0).getHigh();
        String low1 = bean.getData().getForecast().get(0).getLow();
        String temperature = low1.replaceAll("\\D","").trim()+"℃ ~ "+high1.replaceAll("\\D","").trim()+"℃";
        mTodayTxtChange.setText(temperature);

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}


