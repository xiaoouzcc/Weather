package project.release.sdk.com.weather.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class OtherUtils {

    /**
     * 得到版本号
     *
     * @param context
     * @return
     * @author 左成城
     * @data 2015-6-1 上午10:56:03
     */
    public static String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo;
        String version = "";
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    0);
            version = packInfo.versionName + "";
        } catch (NameNotFoundException e) {

            e.printStackTrace();
        }

        return version;
    }

    /**
     * 得到当前手机号码(unused)
     *
     * @param context
     * @return
     * @author 左成城
     * @data 2015-6-1 下午2:48:01
     */
    public static String getCurrentPhoneNum(Context context) {

        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String phoneId = tm.getLine1Number();
        return phoneId;
    }

    /**
     * 判断分辨率，得到相应的字体大小
     *
     * @return
     * @author 左成城
     * @data 2015-6-9 上午9:55:17
     */
    public static int judgeSize(Context context) {
        int size = 0;
        int width = PreferenceUtils.getPrefInt(context, "width", 720);
        if (width < 700) {
            size = 24;
        } else if (700 < width && width < 1000) {
            size = 32;
        } else if (1000 < width && width < 3000) {
            size = 36;
        } else {
            size = 44;
        }
        return size;

    }

    /**
     * 获取手机Imei号
     *
     * @param context
     * @return
     * @author 左成城
     * @data 2015-6-17 下午2:07:19
     */
    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        LogUtil.d("OtherUtils", "getImei:" + imei);
        return imei;
    }

    /**
     * 判断是否是登录状态
     *
     * @param context
     * @return
     * @author 左成城
     * @data 2015-6-29 上午9:52:37
     */
    public static boolean isLogin(Context context) {

        boolean is_login = PreferenceUtils.getPrefBoolean(context, "is_login",
                false);
        if (is_login)
            return true;
        else
            return false;
    }

    /**
     * 获取渠道号
     *
     * @param context
     * @param name
     * @return
     * @author 左成城
     * @data 2015-6-30 上午11:19:01
     */
    public static String getMeta(Context context, String name) {

        String msg = "";
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            msg = appInfo.metaData.getString(name);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 拨打普通电话
     *
     * @param context
     * @author 左成城
     * @data 2015-6-30 上午11:20:07
     */
    public static void callPhoneNormal(Context context, String phone) {

        Intent intent2 = new Intent("android.intent.action.CALL",
                Uri.parse("tel:" + phone));
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent2);
    }

    /**
     * 得到单个高度
     *
     * @param listView
     * @return
     * @author 左成城
     * @data 2015-7-7 下午7:37:41
     */
    public static int getHeight(ListView listView) {
        ListAdapter mAdapter = listView.getAdapter();
        if (mAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        View item = mAdapter.getView(0, null, listView);
        item.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        item.measure(0, 0);
        totalHeight = item.getMeasuredHeight() + listView.getDividerHeight()
                + listView.getPaddingTop() + listView.getPaddingBottom();
        return totalHeight;
    }


    /**
     * 判断服务是否运行
     *
     * @param mContext
     * @param className
     * @return
     * @author 左成城
     * @data 2015-7-9 上午11:19:24
     */
    public static boolean isServiceRunning(Context mContext, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(30);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 获取定位城市
     *
     * @param context
     * @param latitude
     * @param longitude
     * @param mHandler
     * @author Administrator
     * @data 2015-7-9 上午11:36:02
     */
    public static void getLocationAddress(final Context context,
                                          final String latitude, final String longitude,
                                          final Handler mHandler) {
        try {

            String dic = "http://maps.google.cn/maps/api/geocode/json?latlng="
                    + latitude + "," + longitude + "&language=CN";

            URL url = new URL(dic); //创建URL对象
            //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000); //设置连接超时为5秒
            conn.setRequestMethod("GET"); //设定请求方式
            conn.connect(); //建立到远程对象的实际连接
            StringBuffer result = new StringBuffer();
            InputStreamReader isr = null;
            BufferedReader bufferReader = null;
            try {
                isr = new InputStreamReader(conn.getInputStream());
                bufferReader = new BufferedReader(isr);
                String inputLine = "";
                while ((inputLine = bufferReader.readLine()) != null) {
                    result.append(inputLine);
                }
            } catch (Exception e) {

            } finally {
                bufferReader.close();
                isr.close();
            }
            final JSONObject jsonObject = new JSONObject(result
                    .toString());
            if ("OK".equals(jsonObject.optString("status"))) {
                JSONArray array = jsonObject.optJSONArray("results");
                if (array != null && array.length() > 0) {

                    JSONObject cityIndex = array.optJSONObject(0);
                    String formatted_address = cityIndex.optString("formatted_address");
                    LogUtil.e("这是地址" + formatted_address);
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("formatted_address", formatted_address);
                    message.setData(bundle);
                    message.what = 0x11;
                    mHandler.sendMessage(message);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//                    if (conn != null) {
//                        conn.disconnect(); //中断连接
//                    }
        }
    }

    /**
     * 两个double相加并且保留两位小数
     *
     * @param one
     * @param two
     * @return
     * @author Administrator
     * @data 2015-7-10 上午9:50:20
     */
    public static String getStringTwoAdd(double one, double two) {
        BigDecimal b1 = new BigDecimal(one);
        BigDecimal b2 = new BigDecimal(two);
        double alls = b1.add(b2).doubleValue();
        String all = StringUtils.getTwo(alls);
        return all;
    }

    /**
     * show soft input
     *
     * @param context
     * @param token
     * @author shuwanli
     * @data 2015年8月10日 下午5:37:45
     */
    public static void showSoftInputFromInputMethod(Context context,
                                                    IBinder token) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInputFromInputMethod(token, 0);
    }

    /**
     * hide soft input
     *
     * @param context
     * @param token
     * @author shuwanli
     * @data 2015年8月10日 下午5:38:53
     */
    public static void hideSoftInputFromWindow(Context context, IBinder token) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(token, 0);
    }

    /**
     * 获取渠道号
     *
     * @author 左成城
     * @data 2015-9-6 下午5:55:06
     * @param context
     * @return
     */
    private static String channel = null;

    public static String getChannel(Context context) {
        if (channel != null) {
            return channel;
        }
        final String start_flag = "META-INF/O2O_";
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.contains(start_flag)) {
                    channel = entryName.replace(start_flag, "");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (channel == null || channel.length() <= 0) {
            channel = "test0629";// 读不到渠道号就默认官方渠道
        }
        return channel;
    }


    /**
     * 隐藏键盘
     * @param activity
     */
    public static void hideSoftInput(Activity activity) {
        if (activity != null) {
            final InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (imm != null) {
                View focusView = activity.getCurrentFocus();
                if (focusView != null) {
                    IBinder windowToken = focusView.getWindowToken();
                    if (windowToken != null) {
                        imm.hideSoftInputFromWindow(windowToken, 0);
                    }
                }
            }
        }
    }
}
