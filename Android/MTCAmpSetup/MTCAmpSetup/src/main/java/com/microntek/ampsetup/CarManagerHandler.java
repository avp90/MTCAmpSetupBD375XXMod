package com.microntek.ampsetup;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/* renamed from: com.microntek.ampsetup.e */
class CarManagerHandler extends Handler {
    final /* synthetic */ MainActivity mainActivity;

    CarManagerHandler(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if ("CarData".equals((String) message.obj)) {
            String string = message.getData().getString("type");
            if ("EQ_MODE".equals(string)) {
                if ("true".equals(this.mainActivity.parameterStaDsp)) {
                    this.mainActivity.ampDsp.bj();
                } else {
                    this.mainActivity.ampEq.updateSeekBars();
                }
            } else if (!"LOUND_MODE".equals(string)) {
            } else {
                if ("true".equals(this.mainActivity.parameterStaDsp)) {
                    this.mainActivity.ampDsp.bk();
                } else {
                    this.mainActivity.ampEq.updateLoudnessCheck();
                }
            }
        } else if ("CarEvent".equals((String) message.obj)) {
            Bundle data = message.getData();
            if ("dsp_spk_vol".equals(data.getString("type")) && "true".equals(this.mainActivity.parameterStaDsp)) {
                StringBuffer stringBuffer = new StringBuffer();
                byte[] byteArray = data.getByteArray("value");
                for (int i = 0; i < byteArray.length; i++) {
                    stringBuffer.append((byteArray[i] & 255) + "");
                    if (i != byteArray.length - 1) {
                        stringBuffer.append(",");
                    }
                }
                this.mainActivity.ampDsp.putSystemString("KeyDspVolume", stringBuffer.toString());
                this.mainActivity.ampDsp.bm();
            }
        }
    }
}
