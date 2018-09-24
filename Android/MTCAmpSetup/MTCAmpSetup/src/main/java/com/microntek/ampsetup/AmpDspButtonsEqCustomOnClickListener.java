package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.microntek.ampsetup.v */
class AmpDspButtonsEqCustomOnClickListener implements OnClickListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspButtonsEqCustomOnClickListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onClick(View view) {
        this.ampDsp.bf = 0;
        switch (view.getId()) {
            case R.id.eq_custom1:
                this.ampDsp.as = 1;
                break;
            case R.id.eq_custom2:
                this.ampDsp.as = 2;
                break;
            case R.id.eq_custom3:
                this.ampDsp.as = 3;
                break;
        }
        this.ampDsp.putSystemInt("KeyCTmode", this.ampDsp.as);
        this.ampDsp.bg.removeMessages(1);
        this.ampDsp.bg.sendEmptyMessageDelayed(1, 50);
    }
}
