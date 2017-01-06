package project.release.sdk.com.weather.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import project.release.sdk.com.weather.AppComponent;
import project.release.sdk.com.weather.R;
import project.release.sdk.com.weather.base.BaseFragment;

public class SettingFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void initToolbar(View view) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
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
}


