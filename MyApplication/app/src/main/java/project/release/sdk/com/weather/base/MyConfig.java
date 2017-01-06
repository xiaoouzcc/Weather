package project.release.sdk.com.weather.base;


import android.util.Log;

/**
 * Created by Administrator on 2015/10/23.
 */
public class MyConfig {

    //  public static String base_url = "http://192.168.10.32:8004/";// 测试环境
    public static String base_url = "http://jk.fumao.online/";// 正式环境
    // public static String base_url = "http://47.88.195.161:8004/";// 正式环境


    //public static String base_url = "http://192.168.2.36:8080/wm-intface/";// 调试环境
    // public static String base_url = "http://192.168.2.76:8080/wm-intface/";// 调试环境
    //图片地址
    public static String picture_url = base_url + "document/download-";// 调试环境


    public static String CONTACT_PHONE = "10086";


    public static String RESPONSE_CACHE = "FUMAO";
    public static int RESPONSE_CACHE_SIZE = 10;
    public static int HTTP_CONNECT_TIMEOUT = 8000;
    public static int HTTP_READ_TIMEOUT = 8000;
    public static boolean DEBUG = true;
    /**
     * 设置应用日志打印输出等级 VERBOSE-2,DEBUG-3,INFO-4,WARN-5,ERROR-6,ASSERT-7
     */
    public static final int LEVEL = Log.VERBOSE;
    /**
     * 设置应用日志打印统一TAG
     */
    public static final String TAG = "AXT";


    public static String PREF_LATITUDE = "latitude";
    public static String PREF_LONGITUDE = "longitude";
    public static String PREF_LOCK_PHONE = "lock_phone";
    public static String PREF_ACCOUNT = "account";
    public static String PREF_ACAR_ID = "avar_id";
    public static String PREF_LOCATION = "location";
    //保存账号密码
    public static String PREF_USERNAME = "username";
    public static String PREF_PASSWORD = "password";

    /**
     * 未送CHANGE_STATE_IN_THE_ROOM
     */
    public static final int CHANGE_STATE_IN_THE_ROOM = 3;
    /**
     * 已送
     */
    public static final int CHANGE_STATE_TAKE_FOOD = 4;
    /**
     * 已完成
     */
    public static int CHANGE_STATESTATE_COMPLETE = 5;


    public static String SCREEN_WIDTH = "width";

    public static String SCREEN_HEIGHT = "height";


    public static int STATE_SMS_REGISTER = 1;//注册
    public static int STATE_SMS_FINDPWD = 2;//找回密码
    public static int STATE_SMS_MODIFYPHONE = 3;//修改绑定手机号


    public static int RESULT_STATE_SUCCESS = 1;//成功
    public static int RESULT_STATE_FAIL = 0;//失败

}
