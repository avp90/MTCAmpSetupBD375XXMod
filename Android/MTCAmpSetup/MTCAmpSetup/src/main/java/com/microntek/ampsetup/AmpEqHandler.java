package com.microntek.ampsetup;

import android.os.Handler;
import android.os.Message;

class AmpEqHandler extends Handler {
    final AmpEq ampEq;

    AmpEqHandler(AmpEq ampEq) {
        this.ampEq = ampEq;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0) {
            this.ampEq.putSystemString("KeyBalance", this.ampEq.avBalance1 + "," + this.ampEq.avBalance2);
            this.ampEq.mainActivity.SetParameters("av_balance=" + this.ampEq.avBalance1 + "," + (28 - this.ampEq.avBalance2));
            this.ampEq.putSystemString("KeyBalanceMode", "" + this.ampEq.f20u);
            if (this.ampEq.f20u == 0) {
                this.ampEq.putSystemString("KeyBalanceMode1", this.ampEq.avBalance1 + "," + this.ampEq.avBalance2);
            }
            this.ampEq.updateViewForBalance();
        } else if (message.what == 1) {
            this.ampEq.tvAmpSoundMode.setText(this.ampEq.mainActivity.getResources().getString(this.ampEq.avEquMode + R.string.music_style0));
            this.ampEq.putSystemInt("KeyEQmode", this.ampEq.avEquMode);
            this.ampEq.mainActivity.carManager.setEqIdx(this.ampEq.avEquMode);
        } else if (message.what == 2) {
            this.ampEq.mainActivity.SetParameters("av_sub=" + this.ampEq.avCustomSUB);
        } else if (message.what == 3) {
            this.ampEq.putAvDspParameter();
        }
    }
}
