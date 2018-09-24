package com.microntek.ampsetup;

import android.os.SystemProperties;
import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.microntek.ampsetup.j */
class AmpEqButtonsSoundEffectChangeOnClickListener implements OnClickListener {
    final /* synthetic */ AmpEq ampEq;

    AmpEqButtonsSoundEffectChangeOnClickListener(AmpEq ampEq) {
        this.ampEq = ampEq;
    }

    public void onClick(View view) {
        if (SystemProperties.get("ro.product.customer.sub").equals("KGLMMR")) {
            this.ampEq.avEquMode = this.ampEq.getSystemInt("KeyEQmode", 1);
        } else {
            this.ampEq.avEquMode = this.ampEq.getSystemInt("KeyEQmode", 0);
        }
        if (this.ampEq.avEquMode > 6 || this.ampEq.avEquMode < 0) {
            this.ampEq.avEquMode = 0;
        }
        switch (view.getId()) {
            case R.id.soundeffect_arrowhead_left:
                this.ampEq.avEquMode = (this.ampEq.avEquMode + 6) % 7;
                break;
            case R.id.soundeffect_arrowhead_right:
                this.ampEq.avEquMode = (this.ampEq.avEquMode + 1) % 7;
                break;
            default:
                return;
        }
        this.ampEq.ampEqHandler.removeMessages(1);
        this.ampEq.ampEqHandler.sendEmptyMessageDelayed(1, 50);
    }
}
