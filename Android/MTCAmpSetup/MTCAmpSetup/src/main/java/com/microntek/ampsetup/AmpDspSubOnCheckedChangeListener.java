package com.microntek.ampsetup;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class AmpDspSubOnCheckedChangeListener implements OnCheckedChangeListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspSubOnCheckedChangeListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        String fr = this.ampDsp.getSystemString("KeyBASS");
        if (fr == null || fr.length() <= 0) {
            fr = "0,0";
        } else {
            String[] split = fr.split(",");
            fr = Integer.parseInt(split[1]) + "," + Integer.parseInt(split[2]);
        }
        if (z) {
            this.ampDsp.putSystemString("KeyBASS", "1," + fr);
            this.ampDsp.vsbSubL.setEnabled(true);
            this.ampDsp.vsbSubR.setEnabled(true);
            this.ampDsp.mainActivity.SetParameters("av_dsp=2,1," + fr);
            return;
        }
        this.ampDsp.putSystemString("KeyBASS", "0," + fr);
        this.ampDsp.vsbSubL.setEnabled(false);
        this.ampDsp.vsbSubR.setEnabled(false);
        this.ampDsp.mainActivity.SetParameters("av_dsp=2,0," + fr);
    }
}
