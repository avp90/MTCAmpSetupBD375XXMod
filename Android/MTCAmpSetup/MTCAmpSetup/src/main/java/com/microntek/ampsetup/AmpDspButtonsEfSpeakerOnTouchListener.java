package com.microntek.ampsetup;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class AmpDspButtonsEfSpeakerOnTouchListener implements OnTouchListener {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspButtonsEfSpeakerOnTouchListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN /*0*/) {
            this.ampDsp.dy = motionEvent.getX();
            this.ampDsp.dz = motionEvent.getY();
            return false;
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP /*1*/) {
            if (this.ampDsp.bh) {
                this.ampDsp.bh = false;
                this.ampDsp.bi = false;
            }
            this.ampDsp.ci();
            return false;
        } else if (motionEvent.getAction() != MotionEvent.ACTION_MOVE /*2*/) {
            return false;
        } else {
            if (!this.ampDsp.bh) {
                this.ampDsp.bh = this.ampDsp.cb(this.ampDsp.dy, this.ampDsp.dz, motionEvent.getX(), motionEvent.getY(), motionEvent.getDownTime(), motionEvent.getEventTime(), 500);
            }
            if (!this.ampDsp.bh || this.ampDsp.bi) {
                return this.ampDsp.bh;
            } else {
                this.ampDsp.bq(view.getId());
                this.ampDsp.bi = true;
                return false;
            }
        }
    }
}
