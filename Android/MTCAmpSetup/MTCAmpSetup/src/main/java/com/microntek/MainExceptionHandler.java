package com.microntek;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Process;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

/* renamed from: com.microntek.a */
public class MainExceptionHandler implements UncaughtExceptionHandler {
    private static MainExceptionHandler mainExceptionHandler;
    private final String gi = "/mnt/sdcard/carsh/";
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
    private HashMap<String, String> mDeviceInfo;
    private Context context;
    private UncaughtExceptionHandler rootHandler;
    private String packageName;

    public MainExceptionHandler(Context context) {
        this.context = context.getApplicationContext();
        this.packageName = this.context.getPackageName().replace(".", "_");
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static MainExceptionHandler installHandler(Context context) {
        if (mainExceptionHandler == null) {
            mainExceptionHandler = new MainExceptionHandler(context);
        }
        return mainExceptionHandler;
    }

    private boolean gu(Throwable th) {
        if (th == null) {
            return false;
        }
        getDeviceInfo();
        dumpExceptionToSDCard(th);
        return true;
    }

    private String dumpExceptionToSDCard(Throwable th) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        String writer = stringWriter.toString();
        printWriter.close();
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry entry : this.mDeviceInfo.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "\n");
        }
        stringBuffer.append(writer);
        File file = new File("/mnt/sdcard/carsh/");
        if (!file.exists()) {
            file.mkdirs();
        }
        String str = this.packageName + "-" + this.dateFormat.format(new Date()) + ".log";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/mnt/sdcard/carsh/" + str);
            fileOutputStream.write(stringBuffer.toString().getBytes());
            fileOutputStream.close();
            return str;
        } catch (Exception e) {
            return null;
        }
    }

    private void getDeviceInfo() {
        if (this.mDeviceInfo == null) {
            this.mDeviceInfo = new HashMap();
        }
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 1);
            if (packageInfo != null) {
                this.mDeviceInfo.put("versionName", packageInfo.versionName);
                this.mDeviceInfo.put("versionCode", "" + packageInfo.versionCode);
            }
        } catch (NameNotFoundException e) {
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (gu(th)) {
            Process.killProcess(Process.myPid());
            System.exit(1);
            return;
        }
        if (this.rootHandler == null) {
            this.rootHandler = Thread.getDefaultUncaughtExceptionHandler();
        }
        this.rootHandler.uncaughtException(thread, th);
    }
}
