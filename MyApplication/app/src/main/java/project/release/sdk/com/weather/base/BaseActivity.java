package project.release.sdk.com.weather.base;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import project.release.sdk.com.weather.wedgit.CustomSmallProgressDialog;

public abstract class BaseActivity extends AppCompatActivity {
    private CustomSmallProgressDialog mSmallProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.getLayoutId());
        ButterKnife.bind(this);
        this.initToolbar(savedInstanceState);
        this.initViews(savedInstanceState);
        this.initData();
        this.initListeners();
    }

    protected abstract int getLayoutId();

    protected <V extends View> V findView(int id) {

        return (V) this.findViewById(id);
    }



    protected abstract void initViews(Bundle savedInstanceState);

    /**
     * Initialize the toolbar in the layout
     *
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initToolbar(Bundle savedInstanceState);

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
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        clearDestory();
        super.onDestroy();
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
            mSmallProgressDialog = CustomSmallProgressDialog
                    .createDialog(BaseActivity.this);
            mSmallProgressDialog.setCanceledOnTouchOutside(false);
            mSmallProgressDialog.setCancelable(false);

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



    @Override
    protected void onPause() {
      //  OtherUtils.hideSoftInput(this);
        super.onPause();
    }
}
