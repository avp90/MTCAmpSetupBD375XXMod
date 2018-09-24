package com.microntek.ampsetup;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class AmpDspSpaOnCheckedChangeListener implements OnCheckedChangeListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspSpaOnCheckedChangeListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        String fr = this.ampDsp.getSystemString("KeySPA");
        if (fr == null || fr.length() <= 0) {
            fr = "0";
        } else {
            fr = "" + Integer.parseInt(fr.split(",")[1]);
        }
        if (z) {
            this.ampDsp.putSystemString("KeySPA", "1," + fr);
            this.ampDsp.vsbSpa.setEnabled(true);
            this.ampDsp.mainActivity.SetParameters("av_dsp=3,1," + fr);
            return;
        }
        this.ampDsp.putSystemString("KeySPA", "0," + fr);
        this.ampDsp.vsbSpa.setEnabled(false);
        this.ampDsp.mainActivity.SetParameters("av_dsp=3,0," + fr);
    }
}
