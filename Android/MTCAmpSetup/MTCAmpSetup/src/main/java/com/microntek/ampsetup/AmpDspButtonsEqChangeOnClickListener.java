package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.microntek.ampsetup.u */
class AmpDspButtonsEqChangeOnClickListener implements OnClickListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspButtonsEqChangeOnClickListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onClick(View view) {
        this.ampDsp.bf = this.ampDsp.getSystemInt("KeyEQmode", 0);
        if (this.ampDsp.bf > 6 || this.ampDsp.bf < 0) {
            this.ampDsp.bf = 0;
        }
        switch (view.getId()) {
            case R.id.soundeffect_arrowhead_left:
                this.ampDsp.bf = (this.ampDsp.bf + 6) % 7;
                break;
            case R.id.soundeffect_arrowhead_right:
                this.ampDsp.bf = (this.ampDsp.bf + 1) % 7;
                break;
            case R.id.eq_reset:
                this.ampDsp.bf = 5;
                break;
            default:
                return;
        }
        this.ampDsp.bg.removeMessages(1);
        this.ampDsp.bg.sendEmptyMessageDelayed(1, 50);
    }
}
