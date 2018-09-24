package com.microntek.ampsetup;

/* renamed from: com.microntek.ampsetup.h */
class AmpEqBalance implements IAmpBalance {
    final /* synthetic */ AmpEq ampEq;

    AmpEqBalance(AmpEq ampEq) {
        this.ampEq = ampEq;
    }

    public void gr(int i, int i2, int i3) {
        if (i > 28) {
            i = 28;
        }
        if (i2 > 28) {
            i2 = 28;
        }
        this.ampEq.avBalance1 = i;
        this.ampEq.avBalance2 = i2;
        if (i3 == 1) {
            this.ampEq.f20u = 0;
        }
        this.ampEq.updateViewForBalance();
        this.ampEq.ampEqHandler.removeMessages(0);
        this.ampEq.ampEqHandler.sendEmptyMessageDelayed(0, 100);
    }
}
