package project.release.sdk.com.weather.wedgit;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import project.release.sdk.com.weather.R;


/**
 * @author 左成城/zuochengcheng
 * @desc <p>
 * 加载dialog类---里面显示
 * </p>
 * @date 2015-3-18上午10:19:02
 */
public class CustomSmallProgressDialog extends Dialog {

    private static CustomSmallProgressDialog customProgressDialog = null;

    public CustomSmallProgressDialog(Context context) {
        super(context);
    }

    public CustomSmallProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static CustomSmallProgressDialog createDialog(Context context) {
        customProgressDialog = new CustomSmallProgressDialog(context,
                R.style.CustomProgressDialog);
        customProgressDialog.setContentView(R.layout.custom_small_progress_dialog);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

        return customProgressDialog;
    }

    /**
     * 动画
     */
    public void onWindowFocusChanged(boolean hasFocus) {

        if (customProgressDialog == null) {
            return;
        }

        ImageView imageView = (ImageView) customProgressDialog
                .findViewById(R.id.loadingImageView_small);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView
                .getBackground();
        animationDrawable.start();
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
    }

    /**
     * 设置标题
     *
     * @param strTitle
     * @return
     * @author 左成城
     * @data 2015-6-3 上午11:02:46
     */
    public CustomSmallProgressDialog setTitile(String strTitle) {
        return customProgressDialog;
    }

    /**
     * 设置内容
     *
     * @param strMessage
     * @return
     * @author 左成城
     * @data 2015-6-3 上午11:02:59
     */
    public CustomSmallProgressDialog setMessage(String strMessage) {
        TextView tvMsg = (TextView) customProgressDialog
                .findViewById(R.id.id_tv_loadingmsg_small);

        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }

        return customProgressDialog;
    }
}