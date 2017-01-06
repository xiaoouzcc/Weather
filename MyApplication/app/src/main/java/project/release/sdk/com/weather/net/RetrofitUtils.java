package project.release.sdk.com.weather.net;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static Retrofit singleton;

    public static <T> T createApi(Class<T> clazz, Context context) {
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                if (singleton == null) {
                    //配置
                    Retrofit.Builder builder = new Retrofit.Builder();
                    //TODO
                    builder.baseUrl("http://wthrcdn.etouch.cn/");//设置远程地址
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
                    builder.client(OkHttpUtils.getInstance(context));
                    singleton = builder.build();
                }
            }
        }
        return singleton.create(clazz);
    }
}
