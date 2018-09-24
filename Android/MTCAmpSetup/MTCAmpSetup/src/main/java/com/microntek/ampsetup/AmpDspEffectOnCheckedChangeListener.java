package com.microntek.ampsetup;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class AmpDspEffectOnCheckedChangeListener implements OnCheckedChangeListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspEffectOnCheckedChangeListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.ampDsp.putSystemInt("key_efcheck", 1);
            this.ampDsp.cg();
            return;
        }
        this.ampDsp.putSystemInt("key_efcheck", 0);
        this.ampDsp.cf();
    }
}
