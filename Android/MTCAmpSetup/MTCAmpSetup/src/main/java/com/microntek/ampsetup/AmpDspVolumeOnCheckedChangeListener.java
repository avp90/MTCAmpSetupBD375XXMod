package com.microntek.ampsetup;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class AmpDspVolumeOnCheckedChangeListener implements OnCheckedChangeListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspVolumeOnCheckedChangeListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            this.ampDsp.putSystemInt("KeyVolumeMode", 1);
            this.ampDsp.cx(1);
        } else {
            this.ampDsp.putSystemInt("KeyVolumeMode", 0);
            this.ampDsp.cx(0);
        }
        if (this.ampDsp.getSystemInt("KeyFaderMode", 0) == 1) {
            this.ampDsp.bg.removeMessages(7);
            this.ampDsp.bg.sendEmptyMessageDelayed(7, 100);
        }
    }
}
