package com.microntek.ampsetup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

import java.util.InvalidPropertiesFormatException;

public class HorizontalSeekBar extends SeekBar {
    protected int value;
    private AmpSeekBar onChangeListener;

    public HorizontalSeekBar(Context context) {
        this(context, null);
    }

    public HorizontalSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842875);
    }

    public HorizontalSeekBar(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        this.value = 0;
    }

    private void requestDisallowInterceptTouchEvent() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    private void setMotionProgress(MotionEvent motionEvent) {
        int realWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int realStart = getPaddingLeft();
        if (isPaddingOffsetRequired()){
            realWidth -= (getLeftPaddingOffset() + getRightPaddingOffset());
            realStart += (getLeftPaddingOffset());
        }
        int thumbOffsetCorrection = getThumb().getBounds().width() / 2 - getThumbOffset();
        realWidth -= thumbOffsetCorrection * 2;
        realStart += thumbOffsetCorrection;

        float touchPos = (motionEvent.getX() - realStart) / (realWidth);
        setProgress((int) (touchPos * ((float) getMax())));
    }

    public synchronized void setValue(int value)
    {
        // it's for visualisation effect
        new Thread(new HorizontalSeekBarRunnable(this, value)).start();
    }

    public void setOnChangeListener(AmpSeekBar onChangeListener)
    {
        this.onChangeListener = onChangeListener;
    }

    protected void onDraw(Canvas canvas) {
        setProgress(this.value);
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setPressed(true);
                setMotionProgress(motionEvent);
                break;
            case MotionEvent.ACTION_UP:
                setMotionProgress(motionEvent);
                setPressed(false);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                setMotionProgress(motionEvent);
                requestDisallowInterceptTouchEvent();
                break;
            case MotionEvent.ACTION_CANCEL:
                setPressed(false);
                invalidate();
                break;
        }
        return true;
    }

    public synchronized void setProgress(int value) {
        this.value = value;
        super.setProgress(value);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
        if (this.onChangeListener != null) {
            this.onChangeListener.setProgress(this, getProgress(), isPressed());
        }
    }
}
