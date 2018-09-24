package com.microntek.ampsetup;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class AmpDspCheckBoxesEqOnTouchListener implements OnTouchListener {
    private long fs;
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspCheckBoxesEqOnTouchListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.fs = motionEvent.getEventTime() - motionEvent.getDownTime();
        if (this.fs > 100) {
            this.ampDsp.ax = 0;
            this.ampDsp.putSystemInt("KeyEfCTmode", this.ampDsp.ax);
            this.ampDsp.cp();
        }
        return false;
    }
}
