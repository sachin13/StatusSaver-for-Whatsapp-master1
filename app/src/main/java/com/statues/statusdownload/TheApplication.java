package com.statues.statusdownload;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.statues.statusdownload.injection.component.AppComponent;
import com.statues.statusdownload.injection.component.DaggerAppComponent;
import com.statues.statusdownload.injection.module.AppModule;
import io.fabric.sdk.android.Fabric;


public class TheApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}