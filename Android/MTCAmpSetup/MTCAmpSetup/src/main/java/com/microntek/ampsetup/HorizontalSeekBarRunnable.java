package com.microntek.ampsetup;

import android.os.SystemClock;

class HorizontalSeekBarRunnable implements Runnable
{
    final HorizontalSeekBar horizontalSeekBar;
    final int value;

    HorizontalSeekBarRunnable(HorizontalSeekBar horizontalSeekBar, int value) {
        this.horizontalSeekBar = horizontalSeekBar;
        this.value = value;
    }

    public void run()
    {
        int currentValue = this.horizontalSeekBar.value;
        for (int i = 1; i <= 100; i++)
        {
            this.horizontalSeekBar.value = ((int) (((float) (this.value - currentValue)) * ((((float) i) * 1.0f) / 100.0f))) + currentValue;
            this.horizontalSeekBar.postInvalidate();
            SystemClock.sleep(3);
        }
    }
}
