package com.microntek.ampsetup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: com.microntek.ampsetup.x */
class AmpDspBroadcastReceiver extends BroadcastReceiver {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspBroadcastReceiver(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!action.equals("com.microntek.eqchange")) {
            if (action.equals("com.microntek.balancechange")) {
                this.ampDsp.cj();
                this.ampDsp.putSystemString("KeyBalanceMode1", this.ampDsp.an + "," + this.ampDsp.ao);
                this.ampDsp.bt = 0;
                this.ampDsp.ck();
            } else if (!action.equals("com.microntek.loundchange") && action.equals("com.microntek.ampclose")) {
                this.ampDsp.mainActivity.finish();
            }
        }
    }
}
