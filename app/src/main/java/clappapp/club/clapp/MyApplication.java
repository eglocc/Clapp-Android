package clappapp.club.clapp;

import android.app.Application;
import android.content.res.Resources;

public class MyApplication extends Application {

    private static Resources sResources;

    @Override
    public void onCreate() {
        super.onCreate();
        sResources = getApplicationContext().getResources();
    }

    public static Resources getResourcesStaticly() {
        return sResources;
    }
}
