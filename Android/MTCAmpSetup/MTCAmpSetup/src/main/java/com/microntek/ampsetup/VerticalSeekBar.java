package com.microntek.ampsetup;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class VerticalSeekBar extends SeekBar {

    protected int value;
    private AmpSeekBar onChangeListener;

    public VerticalSeekBar(Context context) {
        this(context, null);
    }

    public VerticalSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842875);
    }

    public VerticalSeekBar(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        this.value =  0;
    }

    private void requestDisallowInterceptTouchEvent() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    private void setMotionProgress(MotionEvent motionEvent) {
        int realHeight = getHeight() - getPaddingLeft() - getPaddingRight();
        int realStart = getPaddingLeft();
        if (isPaddingOffsetRequired()){
            realHeight -= (getLeftPaddingOffset() + getRightPaddingOffset());
            realStart += (getLeftPaddingOffset());
        }
        int thumbOffsetCorrection = getThumb().getBounds().width() / 2 - getThumbOffset();
        realHeight -= thumbOffsetCorrection * 2;
        realStart += thumbOffsetCorrection;

        float touchPos = (motionEvent.getY() - realStart) / (realHeight);
        int progress = getMax() - Math.round((float)getMax() * touchPos);
        setProgress(progress);
    }

    public synchronized void setValue(int i)
    {
        // it's for visualisation effect
        new Thread(new VerticalSeekBarRunnable(this, i)).start();
    }

    public void setOnChangeListener(AmpSeekBar onChangeListener)
    {
        this.onChangeListener = onChangeListener;
    }

    protected void onDraw(Canvas canvas) {
        canvas.rotate(-90.0f);
        canvas.translate((float) (-getHeight()), 0.0f);
        setProgress(this.value);
        super.onDraw(canvas);
    }

    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
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

    public synchronized void setProgress(int progress) {
        this.value = progress;
        super.setProgress(progress);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
        if (this.onChangeListener != null) {
            this.onChangeListener.setProgress(this, getProgress(), isPressed());
        }
    }
}
