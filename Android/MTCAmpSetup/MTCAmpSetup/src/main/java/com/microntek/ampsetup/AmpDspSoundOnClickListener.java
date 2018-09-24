package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

class AmpDspSoundOnClickListener implements OnClickListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspSoundOnClickListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onClick(View view) {
        if (this.ampDsp.viewDspSoundLayout.getVisibility() != 0) {
            this.ampDsp.bttnBalance.setSelected(false);
            this.ampDsp.bttnSound.setSelected(true);
            this.ampDsp.bttnEffect.setSelected(false);
            this.ampDsp.viewDspEqLayout.setVisibility(View.GONE);
            this.ampDsp.viewDspSoundLayout.setVisibility(View.VISIBLE);
            this.ampDsp.viewDspEffectLayout.setVisibility(View.GONE);
            this.ampDsp.viewDspSoundLayout.startAnimation(this.ampDsp.animLeftIn);
        }
    }
}
