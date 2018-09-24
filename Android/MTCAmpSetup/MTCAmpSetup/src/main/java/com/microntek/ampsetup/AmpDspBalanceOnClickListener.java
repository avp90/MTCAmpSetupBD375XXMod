package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

class AmpDspBalanceOnClickListener implements OnClickListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspBalanceOnClickListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onClick(View view) {
        if (this.ampDsp.viewDspEqLayout.getVisibility() != 0) {
            this.ampDsp.bttnBalance.setSelected(true);
            this.ampDsp.bttnSound.setSelected(false);
            this.ampDsp.bttnEffect.setSelected(false);
            this.ampDsp.viewDspEqLayout.setVisibility(View.VISIBLE);
            this.ampDsp.viewDspSoundLayout.setVisibility(View.GONE);
            this.ampDsp.viewDspEffectLayout.setVisibility(View.GONE);
            this.ampDsp.viewDspEqLayout.startAnimation(this.ampDsp.animLeftIn);
        }
    }
}
