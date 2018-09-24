package com.microntek.ampsetup;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class AmpDspButtonsEfCustomOnTouchListener implements OnTouchListener {
    private long fq;
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspButtonsEfCustomOnTouchListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        this.fq = motionEvent.getEventTime() - motionEvent.getDownTime();
        if (this.fq >= 1500) {
            this.ampDsp.at = this.ampDsp.getCheckedAsInt(this.ampDsp.cbLud) + "," + this.ampDsp.vsbLudT.getProgress() + "," + this.ampDsp.vsbLudB.getProgress() + "," + this.ampDsp.getCheckedAsInt(this.ampDsp.cbSpa) + "," + this.ampDsp.vsbSpa.getProgress() + "," + this.ampDsp.getCheckedAsInt(this.ampDsp.cbCut) + "," + this.ampDsp.vsbCut.getProgress() + "," + this.ampDsp.getCheckedAsInt(this.ampDsp.cbSub) + "," + this.ampDsp.vsbSubL.getProgress() + "," + this.ampDsp.vsbSubR.getProgress() + "," + this.ampDsp.getCheckedAsInt(this.ampDsp.cbEfSub) + "," + this.ampDsp.vsbEfSub.getProgress();
            switch (view.getId())
            {
                case R.id.ef_custom1:
                    this.ampDsp.putSystemString("KeyCustom1EF", this.ampDsp.at);
                    this.ampDsp.bttnEfCustom1.setSelected(true);
                    this.ampDsp.bttnEfCustom2.setSelected(false);
                    this.ampDsp.bttnEfCustom3.setSelected(false);
                    break;
                case R.id.ef_custom2:
                    this.ampDsp.putSystemString("KeyCustom2EF", this.ampDsp.at);
                    this.ampDsp.bttnEfCustom1.setSelected(false);
                    this.ampDsp.bttnEfCustom2.setSelected(true);
                    this.ampDsp.bttnEfCustom3.setSelected(false);
                    break;
                case R.id.ef_custom3:
                    this.ampDsp.putSystemString("KeyCustom3EF", this.ampDsp.at);
                    this.ampDsp.bttnEfCustom1.setSelected(false);
                    this.ampDsp.bttnEfCustom2.setSelected(false);
                    this.ampDsp.bttnEfCustom3.setSelected(true);
                    break;
            }
        }
        return false;
    }
}
