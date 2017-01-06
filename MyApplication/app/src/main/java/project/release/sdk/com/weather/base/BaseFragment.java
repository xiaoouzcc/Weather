package project.release.sdk.com.weather.base;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import project.release.sdk.com.weather.AppComponent;
import project.release.sdk.com.weather.WeatherApplication;
import project.release.sdk.com.weather.api.WeatherApi;
import project.release.sdk.com.weather.mvp.presenter.BasePresenter;
import project.release.sdk.com.weather.wedgit.CustomSmallProgressDialog;

/**
 * @author 左成城
 * @desc <p>
 * fragment基类
 * </p>
 * @date 2015-6-1上午11:51:40
 */
public abstract class BaseFragment<T extends BasePresenter,T1> extends Fragment {

    private CustomSmallProgressDialog mSmallProgressDialog;
    protected View self;

    public WeatherApi mWeatherApi;
    @Inject
    public T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(WeatherApplication.getAppApplication().getAppComponent());
        mWeatherApi = WeatherApplication.getAppApplication().getWeatherApi();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (this.self == null) {
            this.self = inflater.inflate(this.getLayoutId(), container, false);
        }
        if (this.self.getParent() != null) {
            ViewGroup parent = (ViewGroup) this.self.getParent();
            parent.removeView(this.self);
        }

        ButterKnife.bind(this, this.self);
        mWeatherApi = WeatherApplication.getAppApplication().getWeatherApi();
        if (mPresenter != null){
            mPresenter.attachView(this);
        }
        this.initToolbar(this.self);
        this.initViews(this.self);
        this.initData();
        this.initListeners();


        return this.self;
    }

    protected abstract int getLayoutId();

    protected abstract void setupActivityComponent(AppComponent appComponent);


    protected abstract void initViews(View view);

    /**
     * Initialize the toolbar in the layout
     *
     */
    protected abstract void initToolbar(View view);

    /**
     * Initialize the View of the listener
     */
    protected abstract void initListeners();

    /**
     * Initialize the Activity data
     */
    protected abstract void initData();

    /**
     * 退出界面清除数据
     */
    protected abstract void clearDestory();

    @Override
    public void onDestroyView() {
        clearDestory();
        if (mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    public AnimationDrawable startInterProgressDialog(ImageView imageView) {

        AnimationDrawable animationDrawable = (AnimationDrawable) imageView
                .getBackground();
        animationDrawable.start();
        return animationDrawable;
    }

    public void stopInterProgressDialog(AnimationDrawable animationDrawable) {
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
    }

    public void startSmallProgressDialog() {
        if (mSmallProgressDialog == null) {
            if (getActivity() != null) {
                mSmallProgressDialog = CustomSmallProgressDialog
                        .createDialog(getActivity());
                mSmallProgressDialog.setCanceledOnTouchOutside(false);
                mSmallProgressDialog.setCancelable(false);
            }

            // mSmallProgressDialog.setMessage(getResources().getString(R.string.loading));
        }

        mSmallProgressDialog.show();
    }

    public void stopSmallProgressDialog() {
        if (mSmallProgressDialog != null) {
            mSmallProgressDialog.dismiss();
            mSmallProgressDialog = null;
        }
    }
}
