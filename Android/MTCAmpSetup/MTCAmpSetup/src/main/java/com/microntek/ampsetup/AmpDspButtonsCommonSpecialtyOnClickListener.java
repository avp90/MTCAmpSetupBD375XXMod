package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

class AmpDspButtonsCommonSpecialtyOnClickListener implements OnClickListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspButtonsCommonSpecialtyOnClickListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_common:
                if (this.ampDsp.layoutSoundCommon.getVisibility() != View.VISIBLE) {
                    this.ampDsp.putSystemInt("KeyFaderMode", 0);
                    this.ampDsp.cl();
                    this.ampDsp.bg.removeMessages(0);
                    this.ampDsp.bg.sendEmptyMessageDelayed(0, 100);
                    return;
                }
                return;
            case R.id.bt_specialty:
                if (this.ampDsp.layoutSoundSpecialty.getVisibility() != View.VISIBLE) {
                    this.ampDsp.putSystemInt("KeyFaderMode", 1);
                    this.ampDsp.cl();
                    this.ampDsp.bg.removeMessages(6);
                    this.ampDsp.bg.sendEmptyMessageDelayed(6, 100);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
