package com.microntek.ampsetup;

import android.widget.SeekBar;

class AmpEqFrequencySeekBar extends AmpSeekBar {
    final AmpEq ampEq;

    AmpEqFrequencySeekBar(AmpEq ampEq) {
        this.ampEq = ampEq;
    }

    public void setProgress(SeekBar seekBar, int progress, boolean isPressed) {
        if (isPressed && progress != this.currentValue)
        {
            this.currentValue = progress;

            int id = seekBar.getId();
            if (id == R.id.seekbarBassF) {
                this.ampEq.BD37xx.putBassF(this.ampEq.hsbBassF.getProgress());
            }
            if (id == R.id.seekbarMiddleF) {
                this.ampEq.BD37xx.putMiddleF(this.ampEq.hsbMiddleF.getProgress());
            }
            if (id == R.id.seekbarTrebleF) {
                this.ampEq.BD37xx.putTrebleF(this.ampEq.hsbTrebleF.getProgress());
            }

            this.ampEq.putSystemKeyCustomEqMod();
            this.ampEq.ampEqHandler.removeMessages(3);
            this.ampEq.ampEqHandler.sendEmptyMessageDelayed(3, 100);
        }
    }
}
