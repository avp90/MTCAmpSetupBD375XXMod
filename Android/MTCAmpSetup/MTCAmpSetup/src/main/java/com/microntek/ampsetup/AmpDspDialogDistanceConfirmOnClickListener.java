package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

class AmpDspDialogDistanceConfirmOnClickListener implements OnClickListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspDialogDistanceConfirmOnClickListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void onClick(View view) {
        this.ampDsp.dialogDspDistance.dismiss();
    }
}
