package com.microntek.ampsetup;

import android.app.Activity;
import android.microntek.CarManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    protected String parameterStaDsp;
    protected AmpEq ampEq;
    public CarManager carManager;
    protected AmpDsp ampDsp;

    public String getParameters(String str)
    {
        if (!BuildConfig.DEBUG) {
            return this.carManager.getParameters(str);
        }
        else {
            return "";
        }
    }

    public void SetParameters(String str)
    {
        if (!BuildConfig.DEBUG) {
            this.carManager.setParameters(str);
        }
        Log.d("MainActivity", str);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        this.carManager = new CarManager();
        this.parameterStaDsp = getParameters("sta_dsp=");
        //this.parameterStaDsp = "true";
        if ("true".equals(this.parameterStaDsp))
        {
            this.ampDsp = new AmpDsp(this);
            setContentView(this.ampDsp.bh());
            this.ampDsp.bi();
        }
        else
        {
            this.ampEq = new AmpEq(this);
            setContentView(this.ampEq.getContentId());
            this.ampEq.m22p();
        }
        this.carManager.attach(new CarManagerHandler(this), "CarData,CarEvent");
    }

    protected void onDestroy()
    {
        if ("true".equals(this.parameterStaDsp))
        {
            this.ampDsp.bn();
        }
        else
        {
            this.ampEq.unregisterReceiver();
        }
        this.carManager.detach();
        super.onDestroy();
    }
}
