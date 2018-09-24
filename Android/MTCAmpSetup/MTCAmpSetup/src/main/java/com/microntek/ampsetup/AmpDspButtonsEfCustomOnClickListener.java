package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

class AmpDspButtonsEfCustomOnClickListener implements OnClickListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspButtonsEfCustomOnClickListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ef_custom1:
                this.ampDsp.ax = 1;
                break;
            case R.id.ef_custom2:
                this.ampDsp.ax = 2;
                break;
            case R.id.ef_custom3:
                this.ampDsp.ax = 3;
                break;
        }
        this.ampDsp.putSystemInt("KeyEfCTmode", this.ampDsp.ax);
        this.ampDsp.cp();
        this.ampDsp.cr();
        this.ampDsp.cu();
        this.ampDsp.cm();
        this.ampDsp.cv();
        this.ampDsp.cq();
        this.ampDsp.bg.removeMessages(5);
        this.ampDsp.bg.sendEmptyMessageDelayed(5, 50);
    }
}
