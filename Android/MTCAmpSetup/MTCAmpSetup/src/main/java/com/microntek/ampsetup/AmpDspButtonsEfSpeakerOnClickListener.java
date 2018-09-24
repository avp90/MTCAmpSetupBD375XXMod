package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.microntek.ampsetup.z */
class AmpDspButtonsEfSpeakerOnClickListener implements OnClickListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspButtonsEfSpeakerOnClickListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onClick(View view) {
        String fr = this.ampDsp.getSystemString("KeyDspVolume");
        if (fr == null || fr.length() <= 0) {
            this.ampDsp.bn = 0;
            this.ampDsp.dp = 0;
            this.ampDsp.bj = 0;
            this.ampDsp.dl = 0;
        } else {
            String[] split = fr.split(",");
            this.ampDsp.bn = Integer.parseInt(split[0]);
            this.ampDsp.dp = Integer.parseInt(split[1]);
            this.ampDsp.bj = Integer.parseInt(split[2]);
            this.ampDsp.dl = Integer.parseInt(split[3]);
        }
        AmpDsp ampDsp;
        switch (view.getId()) {
            case R.id.left_front_down:
                ampDsp = this.ampDsp;
                ampDsp.bn = ampDsp.bn + 1;
                if (this.ampDsp.bn > 60) {
                    return;
                }
                break;
            case R.id.left_front_up:
                ampDsp = this.ampDsp;
                ampDsp.bn = ampDsp.bn - 1;
                if (this.ampDsp.bn < 0) {
                    return;
                }
                break;
            case R.id.left_back_down:
                ampDsp = this.ampDsp;
                ampDsp.bj = ampDsp.bj + 1;
                if (this.ampDsp.bj > 60) {
                    return;
                }
                break;
            case R.id.left_back_up:
                ampDsp = this.ampDsp;
                ampDsp.bj = ampDsp.bj - 1;
                if (this.ampDsp.bj < 0) {
                    return;
                }
                break;
            case R.id.right_front_down:
                ampDsp = this.ampDsp;
                ampDsp.dp = ampDsp.dp + 1;
                if (this.ampDsp.dp > 60) {
                    return;
                }
                break;
            case R.id.right_front_up:
                ampDsp = this.ampDsp;
                ampDsp.dp = ampDsp.dp - 1;
                if (this.ampDsp.dp < 0) {
                    return;
                }
                break;
            case R.id.right_back_down:
                ampDsp = this.ampDsp;
                ampDsp.dl = ampDsp.dl + 1;
                if (this.ampDsp.dl > 60) {
                    return;
                }
                break;
            case R.id.right_back_up:
                ampDsp = this.ampDsp;
                ampDsp.dl = ampDsp.dl - 1;
                if (this.ampDsp.dl < 0) {
                    return;
                }
                break;
        }
        this.ampDsp.putSystemString("KeyDspVolume", this.ampDsp.bn + "," + this.ampDsp.dp + "," + this.ampDsp.bj + "," + this.ampDsp.dl);
        this.ampDsp.bm();
        this.ampDsp.bg.removeMessages(7);
        this.ampDsp.bg.sendEmptyMessageDelayed(7, 50);
    }
}
