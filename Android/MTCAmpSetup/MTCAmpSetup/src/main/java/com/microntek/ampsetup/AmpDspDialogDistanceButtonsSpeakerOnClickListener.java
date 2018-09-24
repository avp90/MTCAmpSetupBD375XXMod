package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.microntek.ampsetup.y */
class AmpDspDialogDistanceButtonsSpeakerOnClickListener implements OnClickListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspDialogDistanceButtonsSpeakerOnClickListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onClick(View view) {
        String fr = this.ampDsp.getSystemString("KeyDistance");
        if (fr == null || fr.length() <= 0) {
            this.ampDsp.ce = 140;
            this.ampDsp.cz = 110;
        } else {
            String[] split = fr.split(",");
            this.ampDsp.ce = Integer.parseInt(split[0]);
            this.ampDsp.cz = Integer.parseInt(split[1]);
        }
        AmpDsp ampDsp;
        switch (view.getId()) {
            case R.id.speaker_h_down:
                ampDsp = this.ampDsp;
                ampDsp.ce = ampDsp.ce - 1;
                if (this.ampDsp.ce < 130) {
                    return;
                }
                break;
            case R.id.speaker_h_up:
                ampDsp = this.ampDsp;
                ampDsp.ce = ampDsp.ce + 1;
                if (this.ampDsp.ce > 150) {
                    return;
                }
                break;
            case R.id.speaker_v_down:
                ampDsp = this.ampDsp;
                ampDsp.cz = ampDsp.cz - 1;
                if (this.ampDsp.cz < 100) {
                    return;
                }
                break;
            case R.id.speaker_v_up:
                ampDsp = this.ampDsp;
                ampDsp.cz = ampDsp.cz + 1;
                if (this.ampDsp.cz > 120) {
                    return;
                }
                break;
        }
        this.ampDsp.putSystemString("KeyDistance", this.ampDsp.ce + "," + this.ampDsp.cz);
        this.ampDsp.cw();
        this.ampDsp.bg.removeMessages(6);
        this.ampDsp.bg.sendEmptyMessageDelayed(6, 100);
    }
}
