package com.microntek.ampsetup;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class AmpDspCutOnCheckedChangeListener implements OnCheckedChangeListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspCutOnCheckedChangeListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        String fr = this.ampDsp.getSystemString("KeyCUTFREQ");
        if (fr == null || fr.length() <= 0) {
            fr = "0";
        } else {
            fr = "" + Integer.parseInt(fr.split(",")[1]);
        }
        if (z) {
            this.ampDsp.putSystemString("KeyCUTFREQ", "1," + fr);
            this.ampDsp.vsbCut.setEnabled(true);
            this.ampDsp.mainActivity.SetParameters("av_dsp=7,1," + fr);
            return;
        }
        this.ampDsp.putSystemString("KeyCUTFREQ", "0," + fr);
        this.ampDsp.vsbCut.setEnabled(false);
        this.ampDsp.mainActivity.SetParameters("av_dsp=7,0," + fr);
    }
}
