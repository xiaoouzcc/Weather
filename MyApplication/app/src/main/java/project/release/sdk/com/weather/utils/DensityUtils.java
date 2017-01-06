package project.release.sdk.com.weather.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 像数与屏幕密度转换工具类
 *
 * Created by Rob X on 14-6-16.
 */
public class DensityUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxValue,
                context.getResources().getDisplayMetrics());
    }

}
