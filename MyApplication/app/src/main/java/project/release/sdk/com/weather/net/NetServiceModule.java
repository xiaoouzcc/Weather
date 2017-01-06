package project.release.sdk.com.weather.net;

import android.app.Application;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import project.release.sdk.com.weather.api.WeatherApi;
import project.release.sdk.com.weather.base.MyConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 左成城
 * @desc
 * @date 2016/12/21 15:53
 */
@Module
public class NetServiceModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application) {

        //添加log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        File httpCacheDirectory = new File(application.getCacheDir(), MyConfig.RESPONSE_CACHE);//设置缓存10M
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
        OkHttpClient singleton = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                //  .addInterceptor(interceptorParam)
                .readTimeout(MyConfig.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                // .retryOnConnectionFailure(true)
                .connectTimeout(MyConfig.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager(application))
                // .cache(cache)
                .build();
        return singleton;
    }

    @Provides
    @Singleton
    public WeatherApi provideApi(OkHttpClient client) {
        //配置
        Retrofit.Builder builder = new Retrofit.Builder();
        //TODO
        builder.baseUrl("http://wthrcdn.etouch.cn/");//设置远程地址
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(client);
        return builder.build().create(WeatherApi.class);
    }



}

