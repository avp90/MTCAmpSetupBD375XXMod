package com.microntek.ampsetup;

/* renamed from: com.microntek.ampsetup.r */
class AmpDspBalanceSCross implements IAmpBalance {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspBalanceSCross(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void gr(int i, int i2, int i3) {
        if (i > 28) {
            i = 28;
        }
        if (i2 > 28) {
            i2 = 28;
        }
        this.ampDsp.dt = i;
        this.ampDsp.du = i2;
        if (i3 == 1) {
            this.ampDsp.cl = 0;
        }
        this.ampDsp.ct();
        this.ampDsp.bg.removeMessages(6);
        this.ampDsp.bg.sendEmptyMessageDelayed(6, 100);
    }
}
