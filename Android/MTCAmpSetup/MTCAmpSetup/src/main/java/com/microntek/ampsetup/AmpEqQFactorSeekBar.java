package com.microntek.ampsetup;

import android.widget.SeekBar;

class AmpEqQFactorSeekBar extends AmpSeekBar {
    final AmpEq ampEq;

    AmpEqQFactorSeekBar(AmpEq ampEq) {
        this.ampEq = ampEq;
    }

    public void setProgress(SeekBar seekBar, int progress, boolean isPressed) {
        if (isPressed && progress != this.currentValue)
        {
            this.currentValue = progress;

            int id = seekBar.getId();

            if (id == R.id.seekbarBassQ) {
                this.ampEq.BD37xx.putBassQ(this.ampEq.hsbBassQ.getProgress());
            }
            if (id == R.id.seekbarMiddleQ) {
                this.ampEq.BD37xx.putMiddleQ(this.ampEq.hsbMiddleQ.getProgress());
            }
            if (id == R.id.seekbarTrebleQ) {
                this.ampEq.BD37xx.putTrebleQ(this.ampEq.hsbTrebleQ.getProgress());
            }

            this.ampEq.putSystemKeyCustomEqMod();
            this.ampEq.ampEqHandler.removeMessages(3);
            this.ampEq.ampEqHandler.sendEmptyMessageDelayed(3, 100);
        }
    }
}
