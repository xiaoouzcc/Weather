package project.release.sdk.com.weather;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author 左成城
 * @desc
 * @date 2017/1/3 10:17
 */
@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application){

        this.mApplication = application;

    }
    @Singleton
    @Provides
    public Application provideApplication(){
        return mApplication;
    }

}

