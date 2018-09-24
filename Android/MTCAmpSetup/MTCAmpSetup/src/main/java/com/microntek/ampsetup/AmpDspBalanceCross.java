package com.microntek.ampsetup;

/* renamed from: com.microntek.ampsetup.q */
class AmpDspBalanceCross implements IAmpBalance {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspBalanceCross(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void gr(int i, int i2, int i3) {
        if (i > 28) {
            i = 28;
        }
        if (i2 > 28) {
            i2 = 28;
        }
        this.ampDsp.an = i;
        this.ampDsp.ao = i2;
        if (i3 == 1) {
            this.ampDsp.bt = 0;
        }
        this.ampDsp.ck();
        this.ampDsp.bg.removeMessages(0);
        this.ampDsp.bg.sendEmptyMessageDelayed(0, 100);
    }
}
