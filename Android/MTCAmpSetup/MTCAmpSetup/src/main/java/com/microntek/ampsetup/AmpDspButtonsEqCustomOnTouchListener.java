package com.microntek.ampsetup;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* renamed from: com.microntek.ampsetup.w */
class AmpDspButtonsEqCustomOnTouchListener implements OnTouchListener {
    private long fh;
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspButtonsEqCustomOnTouchListener(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.fh = motionEvent.getEventTime() - motionEvent.getDownTime();
        if (this.fh >= 1500) {
            if (this.ampDsp.vsbSoundProgress[0] != null) {
                this.ampDsp.au = "" + this.ampDsp.vsbSoundProgress[0].getProgress() + "," + this.ampDsp.vsbSoundProgress[1].getProgress() + "," + this.ampDsp.vsbSoundProgress[2].getProgress() + "," + this.ampDsp.vsbSoundProgress[3].getProgress() + "," + this.ampDsp.vsbSoundProgress[4].getProgress() + "," + this.ampDsp.vsbSoundProgress[5].getProgress() + "," + this.ampDsp.vsbSoundProgress[6].getProgress() + "," + this.ampDsp.vsbSoundProgress[7].getProgress() + "," + this.ampDsp.vsbSoundProgress[8].getProgress() + "," + this.ampDsp.vsbSoundProgress[9].getProgress() + "," + this.ampDsp.vsbSoundProgress[10].getProgress() + "," + this.ampDsp.vsbSoundProgress[11].getProgress() + "," + this.ampDsp.vsbSoundProgress[12].getProgress() + "," + this.ampDsp.vsbSoundProgress[13].getProgress() + "," + this.ampDsp.vsbSoundProgress[14].getProgress();
            }
            switch (view.getId()) {
                case R.id.eq_custom1:
                    this.ampDsp.putSystemString("KeyCustom1EQ", this.ampDsp.au);
                    this.ampDsp.bttnEqCustom1.setSelected(true);
                    this.ampDsp.bttnEqCustom2.setSelected(false);
                    this.ampDsp.bttnEqCustom3.setSelected(false);
                    break;
                case R.id.eq_custom2:
                    this.ampDsp.putSystemString("KeyCustom2EQ", this.ampDsp.au);
                    this.ampDsp.bttnEqCustom1.setSelected(false);
                    this.ampDsp.bttnEqCustom2.setSelected(true);
                    this.ampDsp.bttnEqCustom3.setSelected(false);
                    break;
                case R.id.eq_custom3:
                    this.ampDsp.putSystemString("KeyCustom3EQ", this.ampDsp.au);
                    this.ampDsp.bttnEqCustom1.setSelected(false);
                    this.ampDsp.bttnEqCustom2.setSelected(false);
                    this.ampDsp.bttnEqCustom3.setSelected(true);
                    break;
            }
        }
        return false;
    }
}
