package com.microntek.ampsetup;

import android.app.Application;
import com.microntek.MainExceptionHandler;

public class AmpApp extends Application {
    public void onCreate() {
        MainExceptionHandler.installHandler(getApplicationContext());
        super.onCreate();
    }
}
