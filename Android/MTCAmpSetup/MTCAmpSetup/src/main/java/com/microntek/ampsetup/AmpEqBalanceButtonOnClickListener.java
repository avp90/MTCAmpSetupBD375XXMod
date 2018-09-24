package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.microntek.ampsetup.l */
class AmpEqBalanceButtonOnClickListener implements OnClickListener {
    final /* synthetic */ AmpEq ampEq;

    AmpEqBalanceButtonOnClickListener(AmpEq ampEq) {
        this.ampEq = ampEq;
    }

    public void onClick(View view) {
        if (this.ampEq.viewAmpEqLayout.getVisibility() != 0) {
            this.ampEq.changeView();
        }
    }
}
