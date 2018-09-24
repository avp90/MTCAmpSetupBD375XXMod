package com.microntek.ampsetup;

import android.widget.SeekBar;

/* renamed from: com.microntek.ampsetup.s */
class AmpDspSeekBarEq extends AmpSeekBar {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspSeekBarEq(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    /* renamed from: l */
    public void setProgress(SeekBar seekBar, int progress, boolean isPressed) {
        if (isPressed) {
            int id = seekBar.getId();
            this.ampDsp.bf = 0;
            this.ampDsp.as = 0;
            switch (id) {
                case R.id.sound_progress1:
                    this.ampDsp.tvSoundProgress[0].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress2:
                    this.ampDsp.tvSoundProgress[1].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress3:
                    this.ampDsp.tvSoundProgress[2].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress4:
                    this.ampDsp.tvSoundProgress[3].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress5:
                    this.ampDsp.tvSoundProgress[4].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress6:
                    this.ampDsp.tvSoundProgress[5].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress7:
                    this.ampDsp.tvSoundProgress[6].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress8:
                    this.ampDsp.tvSoundProgress[7].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress9:
                    this.ampDsp.tvSoundProgress[8].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress10:
                    this.ampDsp.tvSoundProgress[9].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress11:
                    this.ampDsp.tvSoundProgress[10].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress12:
                    this.ampDsp.tvSoundProgress[11].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress13:
                    this.ampDsp.tvSoundProgress[12].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress14:
                    this.ampDsp.tvSoundProgress[13].setText("" + (progress - 10));
                    break;
                case R.id.sound_progress15:
                    this.ampDsp.tvSoundProgress[14].setText("" + (progress - 10));
                    break;
            }
            if (this.ampDsp.vsbSoundProgress[0] != null) {
                this.ampDsp.putSystemString("KeyCustomEQ", "" + this.ampDsp.vsbSoundProgress[0].getProgress() + "," + this.ampDsp.vsbSoundProgress[1].getProgress() + "," + this.ampDsp.vsbSoundProgress[2].getProgress() + "," + this.ampDsp.vsbSoundProgress[3].getProgress() + "," + this.ampDsp.vsbSoundProgress[4].getProgress() + "," + this.ampDsp.vsbSoundProgress[5].getProgress() + "," + this.ampDsp.vsbSoundProgress[6].getProgress() + "," + this.ampDsp.vsbSoundProgress[7].getProgress() + "," + this.ampDsp.vsbSoundProgress[8].getProgress() + "," + this.ampDsp.vsbSoundProgress[9].getProgress() + "," + this.ampDsp.vsbSoundProgress[10].getProgress() + "," + this.ampDsp.vsbSoundProgress[11].getProgress() + "," + this.ampDsp.vsbSoundProgress[12].getProgress() + "," + this.ampDsp.vsbSoundProgress[13].getProgress() + "," + this.ampDsp.vsbSoundProgress[14].getProgress());
            }
            this.ampDsp.bttnEqCustom1.setSelected(false);
            this.ampDsp.bttnEqCustom2.setSelected(false);
            this.ampDsp.bttnEqCustom3.setSelected(false);
            this.ampDsp.putSystemInt("KeyCTmode", this.ampDsp.as);
            this.ampDsp.bg.removeMessages(1);
            this.ampDsp.bg.sendEmptyMessageDelayed(1, 50);
        }
    }
}
