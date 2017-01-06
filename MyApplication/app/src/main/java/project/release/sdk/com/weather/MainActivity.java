package project.release.sdk.com.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.release.sdk.com.weather.base.BaseActivity;
import project.release.sdk.com.weather.ui.fragment.ContrastFragment;
import project.release.sdk.com.weather.ui.fragment.SettingFragment;
import project.release.sdk.com.weather.ui.fragment.WeatherFragment;
import project.release.sdk.com.weather.utils.ToastUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    private int mCurrentFragment;
    /**
     * 首页
     */
    public static final int TODAY = 1;
    public static final int CONTRAST = 2;
    public static final int SETTING = 3;

    private WeatherFragment mWeatherFragment = null;
    private ContrastFragment mContrastFragment = null;
    private SettingFragment mSettingFragment = null;
    @Bind(R.id.main_tab_today)
    RadioButton mRadioWall;
    @Bind(R.id.main_tab_contrast)
    RadioButton mRadioMe;

    @Bind(R.id.main_tab_setting)
    RadioButton mMainTabSetting;


    private long mfirstTime;
    private int is_login_in = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment = TODAY;
        is_login_in = getIntent().getIntExtra("is_login_in", 0);
        goFragment(mCurrentFragment);
        //   OtherUtils.getWidthHeight(MainActivity.this);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        mRadioWall.setOnClickListener(this);
        mRadioMe.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void clearDestory() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_tab_today:
                goFragment(TODAY);
                break;
            case R.id.main_tab_contrast:
                goFragment(CONTRAST);
                break;

            case R.id.main_tab_setting:
                goFragment(SETTING);
                break;
        }
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mContrastFragment != null) {
            transaction.hide(mContrastFragment);
        }
        if (mWeatherFragment != null) {
            transaction.hide(mWeatherFragment);
        }
        if (mSettingFragment != null) {
            transaction.hide(mSettingFragment);
        }
    }

    private void goFragment(int type) {
        mCurrentFragment = type;
        mTransaction = mFragmentManager.beginTransaction();
        switch (type) {
            case TODAY:
                hideFragments(mTransaction);
                if (mWeatherFragment == null) {
                    mWeatherFragment = new WeatherFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("is_login_in", is_login_in);
                    mWeatherFragment.setArguments(bundle);
                    mTransaction.add(R.id.main_tab_frame, mWeatherFragment);
                } else {
                    mTransaction.show(mWeatherFragment);
                }
                mTransaction.commitAllowingStateLoss();

                break;
            case CONTRAST:
                hideFragments(mTransaction);
                if (mContrastFragment == null) {
                    mContrastFragment = new ContrastFragment();
                    mTransaction.add(R.id.main_tab_frame, mContrastFragment);
                } else {
                    mTransaction.show(mContrastFragment);
                }
                mTransaction.commitAllowingStateLoss();
                break;
            case SETTING:
                hideFragments(mTransaction);
                if (mSettingFragment == null) {
                    mSettingFragment = new SettingFragment();
                    mTransaction.add(R.id.main_tab_frame, mSettingFragment);
                } else {
                    mTransaction.show(mSettingFragment);
                }
                mTransaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - mfirstTime > 2000) {
                    // 如果两次按键时间间隔大于2秒，则不退出
                    ToastUtil.showToastBottom(getApplicationContext(), "再按一次确认退出！");
                    mfirstTime = secondTime;// 更新firstTime
                    return true;
                } else { // 两次按键小于2秒时，退出应用
                    // System.exit(0);
                    //       PreferenceUtils.setPrefBoolean(getApplicationContext(), "is_aliving", false);
                    this.finish();
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 模拟home键按下
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
