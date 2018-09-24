package com.microntek.ampsetup;

import android.widget.SeekBar;

class AmpEqGainSeekBar extends AmpSeekBar {
    final AmpEq ampEq;

    AmpEqGainSeekBar(AmpEq ampEq) {
        this.ampEq = ampEq;
    }

    public void setProgress(SeekBar seekBar, int progress, boolean isPressed) {
        if (isPressed && progress != this.currentValue)
        {
            this.currentValue = progress;

            int id = seekBar.getId();

            if (id == R.id.seekbarInputG) {
                this.ampEq.BD37xx.putInputG(this.ampEq.vsbInputG.getProgress());
                this.ampEq.tvInputG.setText(this.ampEq.formatGain(progress));

                this.ampEq.putSystemKeyCustomEqMod();

                this.ampEq.ampEqHandler.removeMessages(3);
                this.ampEq.ampEqHandler.sendEmptyMessageDelayed(3, 100);
                return;
            }

            this.ampEq.avEquMode = 0; // custom
            if (id == R.id.seekbarBassG) {
                this.ampEq.avCustomEq[0] = this.ampEq.vsbBassG.getProgress();
                this.ampEq.tvBassG.setText(this.ampEq.formatGain(progress - 20));
            }
            if (id == R.id.seekbarMiddleG) {
                this.ampEq.avCustomEq[1] = this.ampEq.vsbMiddleG.getProgress();
                this.ampEq.tvMiddleG.setText(this.ampEq.formatGain(progress - 20));
            }
            if (id == R.id.seekbarTrebleG) {
                this.ampEq.avCustomEq[2] = this.ampEq.vsbTrebleG.getProgress();
                this.ampEq.tvTrebleG.setText(this.ampEq.formatGain(progress - 20));
            }

            this.ampEq.putSystemKeyCustomEq();
            // for compatibility with original AmpSetup
            this.ampEq.putSystemString("KeyCustomEQ9", "" +
                    this.ampEq.avCustomEq[0]/2 + "," + this.ampEq.avCustomEq[0]/2 + "," + this.ampEq.avCustomEq[0]/2 + "," +
                    this.ampEq.avCustomEq[1]/2 + "," + this.ampEq.avCustomEq[1]/2 + "," + this.ampEq.avCustomEq[1]/2 + "," +
                    this.ampEq.avCustomEq[2]/2 + "," + this.ampEq.avCustomEq[2]/2 + "," + this.ampEq.avCustomEq[2]/2);

            this.ampEq.ampEqHandler.removeMessages(1);
            this.ampEq.ampEqHandler.sendEmptyMessageDelayed(1, 100);
        }
    }
}
