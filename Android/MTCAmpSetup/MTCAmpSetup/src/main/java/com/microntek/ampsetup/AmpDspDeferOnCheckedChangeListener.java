package com.microntek.ampsetup;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class AmpDspDeferOnCheckedChangeListener implements OnCheckedChangeListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspDeferOnCheckedChangeListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            this.ampDsp.putSystemInt("KeyDeferMode", 1);
        } else {
            this.ampDsp.putSystemInt("KeyDeferMode", 0);
        }
        if (this.ampDsp.getSystemInt("KeyFaderMode", 0) == 1) {
            this.ampDsp.bg.removeMessages(6);
            this.ampDsp.bg.sendEmptyMessageDelayed(6, 100);
        }
    }
}
