package com.microntek.ampsetup;

import android.widget.SeekBar;

/* renamed from: com.microntek.ampsetup.t */
class AmpDspSeekBarEf extends AmpSeekBar {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspSeekBarEf(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    /* renamed from: l */

    public void setProgress(SeekBar sekBar, int progress, boolean isPressed) {
        if (isPressed) {
            this.ampDsp.ax = 0;
            this.ampDsp.putSystemInt("KeyEfCTmode", this.ampDsp.ax);
            this.ampDsp.cp();
            switch (sekBar.getId()) {
                case R.id.lud_tseekbar:
                case R.id.lud_bseekbar:
                    this.ampDsp.putSystemString("KeyLudGain", "" + this.ampDsp.vsbLudT.getProgress() + "," + this.ampDsp.vsbLudB.getProgress());
                    this.ampDsp.bg.removeMessages(2);
                    this.ampDsp.bg.sendEmptyMessageDelayed(2, 50);
                    return;
                case R.id.spa_seekbar:
                    this.ampDsp.putSystemString("KeySPA", "1," + this.ampDsp.vsbSpa.getProgress());
                    this.ampDsp.bg.removeMessages(3);
                    this.ampDsp.bg.sendEmptyMessageDelayed(3, 50);
                    return;
                case R.id.cut_seekbar:
                    this.ampDsp.putSystemString("KeyCUTFREQ", "1," + this.ampDsp.vsbCut.getProgress());
                    this.ampDsp.bg.removeMessages(8);
                    this.ampDsp.bg.sendEmptyMessageDelayed(8, 50);
                    return;
                case R.id.sub_lseekbar:
                case R.id.sub_rseekbar:
                    this.ampDsp.putSystemString("KeyBASS", "1," + this.ampDsp.vsbSubL.getProgress() + "," + this.ampDsp.vsbSubR.getProgress());
                    this.ampDsp.bg.removeMessages(4);
                    this.ampDsp.bg.sendEmptyMessageDelayed(4, 50);
                    return;
                case R.id.efsub_seekbar:
                    this.ampDsp.putSystemString("KeyEFSUB", "1," + this.ampDsp.vsbEfSub.getProgress());
                    this.ampDsp.bg.removeMessages(9);
                    this.ampDsp.bg.sendEmptyMessageDelayed(9, 50);
                    return;
                default:
                    return;
            }
        }
    }
}
