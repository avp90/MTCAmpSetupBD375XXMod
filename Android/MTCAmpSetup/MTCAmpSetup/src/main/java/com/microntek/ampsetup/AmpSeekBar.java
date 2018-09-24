package com.microntek.ampsetup;

import android.widget.SeekBar;

public abstract class AmpSeekBar {
    int currentValue = 0;
    abstract void setProgress(SeekBar seekBar, int progress, boolean isPressed);
}