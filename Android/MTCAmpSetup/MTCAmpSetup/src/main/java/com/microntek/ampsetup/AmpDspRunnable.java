package com.microntek.ampsetup;

import android.os.Message;

class AmpDspRunnable implements Runnable {
    final /* synthetic */ AmpDsp ampDsp;
    final /* synthetic */ int gb;

    AmpDspRunnable(AmpDsp ampDsp, int i) {
        this.ampDsp = ampDsp;
        this.gb = i;
    }

    public void run() {
        Message message = new Message();
        message.what = this.gb;
        this.ampDsp.cf.sendMessage(message);
    }
}
