package com.microntek.ampsetup;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class AmpEqLoudnessOnCheckedChangeListener implements OnCheckedChangeListener {
    final AmpEq ampEq;

    AmpEqLoudnessOnCheckedChangeListener(AmpEq ampEq) {
        this.ampEq = ampEq;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            this.ampEq.putSystemInt("key_Lud", 1);
            this.ampEq.mainActivity.carManager.setLud(true);
            return;
        }
        this.ampEq.putSystemInt("key_Lud", 0);
        this.ampEq.mainActivity.carManager.setLud(false);
    }
}
