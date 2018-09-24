package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

class AmpDspEffectOnClickListener implements OnClickListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspEffectOnClickListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onClick(View view) {
        if (this.ampDsp.viewDspEffectLayout.getVisibility() != 0) {
            this.ampDsp.bttnBalance.setSelected(false);
            this.ampDsp.bttnSound.setSelected(false);
            this.ampDsp.bttnEffect.setSelected(true);
            this.ampDsp.viewDspEqLayout.setVisibility(View.GONE);
            this.ampDsp.viewDspSoundLayout.setVisibility(View.GONE);
            this.ampDsp.viewDspEffectLayout.setVisibility(View.VISIBLE);
            this.ampDsp.viewDspEffectLayout.startAnimation(this.ampDsp.animLeftIn);
        }
    }
}
