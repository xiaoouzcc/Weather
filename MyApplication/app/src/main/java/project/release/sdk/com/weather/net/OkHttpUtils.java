package project.release.sdk.com.weather.net;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import project.release.sdk.com.weather.base.MyConfig;

/**
 * OkHttpClient自定义工具类
 */
public class OkHttpUtils {

    private static OkHttpClient singleton;

    public static OkHttpClient getInstance(Context context) {
        if (singleton == null) {
            synchronized (OkHttpUtils.class) {
                if (singleton == null) {

                    //添加log
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    File httpCacheDirectory = new File(context.getCacheDir(), MyConfig.RESPONSE_CACHE);//设置缓存10M
                    Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
                    ///添加参数
                    Interceptor interceptorParam = new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            HttpUrl url = request.url().newBuilder().addQueryParameter(
                                    "token", "---").build();
                            request = request.newBuilder().url(url).build();
                            return chain.proceed(request);
                        }
                    };
                    singleton = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            //  .addInterceptor(interceptorParam)
                            .readTimeout(MyConfig.HTTP_READ_TIMEOUT,TimeUnit.MILLISECONDS)
                           // .retryOnConnectionFailure(true)
                            .connectTimeout(MyConfig.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                            .cookieJar(new CookiesManager(context))
                           // .cache(cache)
                            .build();


                }
            }
        }
        return singleton;
    }
}