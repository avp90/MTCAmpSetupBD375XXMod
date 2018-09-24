package com.microntek.ampsetup;

import android.widget.SeekBar;

class AmpEqSubSeekBar extends AmpSeekBar {
    final AmpEq ampEq;

    AmpEqSubSeekBar(AmpEq ampEq) {
        this.ampEq = ampEq;
    }

    public void setProgress(SeekBar seekBar, int progress, boolean isPressed) {
        if (isPressed && progress != this.currentValue)
        {
            this.currentValue = progress;

            int id = seekBar.getId();
            if (id == R.id.seekbarSubG)
            {
                this.ampEq.avCustomSUB = this.ampEq.vsbSubG.getProgress() - 79;
                this.ampEq.tvSubG.setText(this.ampEq.formatGain(this.ampEq.avCustomSUB));
                if (this.ampEq.vsbSubG != null) {
                    this.ampEq.putSystemInt("KeyCustomSUB", this.ampEq.avCustomSUB);
                }
                this.ampEq.ampEqHandler.removeMessages(2);
                this.ampEq.ampEqHandler.sendEmptyMessageDelayed(2, 100);
                return;
            }

            if (id == R.id.seekbarSubCutOff)
            {
                this.ampEq.BD37xx.putSubCutOff(this.ampEq.hsbSubCutOff.getProgress());
            }
            if (id == R.id.seekbarSubOut)
            {
                this.ampEq.BD37xx.putSubOut(this.ampEq.hsbSubOut.getProgress());
            }
            if (id == R.id.seekBarSubPhase)
            {
                this.ampEq.BD37xx.putSubPhase(this.ampEq.hsbSubPhase.getProgress());
            }
            this.ampEq.putSystemKeyCustomEqMod();
            this.ampEq.ampEqHandler.removeMessages(3);
            this.ampEq.ampEqHandler.sendEmptyMessageDelayed(3, 100);
        }
    }
}
