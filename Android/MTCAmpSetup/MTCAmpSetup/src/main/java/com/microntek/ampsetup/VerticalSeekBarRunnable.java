package com.microntek.ampsetup;

import android.os.SystemClock;

class VerticalSeekBarRunnable implements Runnable
{
    final VerticalSeekBar verticalSeekBar;
    final int value;

    VerticalSeekBarRunnable(VerticalSeekBar verticalSeekBar, int value) {
        this.verticalSeekBar = verticalSeekBar;
        this.value = value;
    }

    public void run()
    {
        int currentValue = this.verticalSeekBar.value;
        for (int i = 1; i <= 100; i++)
        {
            this.verticalSeekBar.value = ((int) (((float) (this.value - currentValue)) * ((((float) i) * 1.0f) / 100.0f))) + currentValue;
            this.verticalSeekBar.postInvalidate();
            SystemClock.sleep(3);
        }
    }
}
