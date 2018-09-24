package com.microntek.ampsetup;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class AmpDspEfSubOnCheckedChangeListener implements OnCheckedChangeListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspEfSubOnCheckedChangeListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        String fr = this.ampDsp.getSystemString("KeyEFSUB");
        if (fr == null || fr.length() <= 0) {
            fr = "0";
        } else {
            fr = "" + Integer.parseInt(fr.split(",")[1]);
        }
        if (z) {
            this.ampDsp.putSystemString("KeyEFSUB", "1," + fr);
            this.ampDsp.vsbEfSub.setEnabled(true);
            this.ampDsp.mainActivity.SetParameters("av_dsp=8,1," + fr);
            return;
        }
        this.ampDsp.putSystemString("KeyEFSUB", "0," + fr);
        this.ampDsp.vsbEfSub.setEnabled(false);
        this.ampDsp.mainActivity.SetParameters("av_dsp=8,0," + fr);
    }
}
