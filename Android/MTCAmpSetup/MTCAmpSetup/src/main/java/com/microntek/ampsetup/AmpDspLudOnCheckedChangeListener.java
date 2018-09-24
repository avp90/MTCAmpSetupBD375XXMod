package com.microntek.ampsetup;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class AmpDspLudOnCheckedChangeListener implements OnCheckedChangeListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspLudOnCheckedChangeListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        String fr = this.ampDsp.getSystemString("KeyLudGain");
        if (fr == null || fr.length() <= 0) {
            fr = "0,0";
        } else {
            String[] split = fr.split(",");
            fr = Integer.parseInt(split[0]) + "," + Integer.parseInt(split[1]);
        }
        if (z) {
            this.ampDsp.vsbLudT.setEnabled(true);
            this.ampDsp.vsbLudB.setEnabled(true);
            this.ampDsp.putSystemInt("key_Lud", 1);
            this.ampDsp.putSystemString("KeyLudGain", fr);
            this.ampDsp.mainActivity.carManager.setLud(true);
            return;
        }
        this.ampDsp.vsbLudT.setEnabled(false);
        this.ampDsp.vsbLudB.setEnabled(false);
        this.ampDsp.putSystemInt("key_Lud", 0);
        this.ampDsp.putSystemString("KeyLudGain", fr);
        this.ampDsp.mainActivity.carManager.setLud(false);
    }
}
