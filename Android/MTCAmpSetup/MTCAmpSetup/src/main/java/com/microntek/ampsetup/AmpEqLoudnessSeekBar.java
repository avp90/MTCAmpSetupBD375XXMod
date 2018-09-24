package com.microntek.ampsetup;

import android.widget.SeekBar;

class AmpEqLoudnessSeekBar extends AmpSeekBar {
    final AmpEq ampEq;

    AmpEqLoudnessSeekBar(AmpEq ampEq) {
        this.ampEq = ampEq;
    }

    public void setProgress(SeekBar seekBar, int progress, boolean isPressed) {
        if (isPressed && progress != this.currentValue)
        {
            this.currentValue = progress;

            int id = seekBar.getId();
            if (id == R.id.seekbarLoudnessG) {
                this.ampEq.BD37xx.putLoudnessG(this.ampEq.vsbLoudnessG.getProgress());
                this.ampEq.tvLoudnessG.setText(this.ampEq.formatGain(progress));
            }
            if (id == R.id.seekbarLoudnessF) {
                this.ampEq.BD37xx.putLoudnessF(this.ampEq.hsbLoudnessF.getProgress());
            }
            if (id == R.id.seekbarLoudnessH) {
                this.ampEq.BD37xx.putLoudnessH(this.ampEq.hsbLoudnessH.getProgress());
            }

            this.ampEq.putSystemKeyCustomEqMod();
            this.ampEq.ampEqHandler.removeMessages(3);
            this.ampEq.ampEqHandler.sendEmptyMessageDelayed(3, 100);
        }
    }
}

