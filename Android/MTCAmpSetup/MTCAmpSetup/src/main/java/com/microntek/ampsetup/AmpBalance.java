package com.microntek.ampsetup;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class AmpBalance extends View {
    private IAmpBalance ec = null;
    private int ed = 0;
    private int ee = 0;
    private Bitmap imgDSrc = null;
    private Bitmap imgHSrc = null;
    private int eh = 0;
    private int ei;
    private int ej;
    private int left;
    private int top;
    private int imgDSrcWidth;
    private Bitmap imgVSrc = null;

    public AmpBalance(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AmpBalance);
        int resourceId1 = obtainStyledAttributes.getResourceId(R.styleable.AmpBalance_imgHSrc, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.AmpBalance_imgVSrc, 0);
        int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.AmpBalance_imgDSrc, 0);
        Resources resources = context.getResources();
        this.imgHSrc = BitmapFactory.decodeResource(resources, resourceId1);
        this.imgVSrc = BitmapFactory.decodeResource(resources, resourceId2);
        this.imgDSrc = BitmapFactory.decodeResource(resources, resourceId3);
        this.imgDSrcWidth = this.imgDSrc.getWidth();
    }

    private void gp()
    {
        this.left = (((this.ej - this.imgDSrcWidth) * this.ed) / this.eh) + (this.imgDSrcWidth / 2);
        this.top = (((this.ei - this.imgDSrcWidth) * this.ee) / this.eh) + (this.imgDSrcWidth / 2);
        if (this.ec != null)
        {
            this.ec.gr(this.ed, this.ee, 0);
        }
    }

    private void gq(int x, int y) {
        this.ed = ((x - (this.imgDSrcWidth / 2)) * this.eh) / (this.ej - this.imgDSrcWidth);
        this.ee = ((y - (this.imgDSrcWidth / 2)) * this.eh) / (this.ei - this.imgDSrcWidth);
        this.left = x;
        this.top = y;
        if (this.ec != null) {
            this.ec.gr(this.ed, this.ee, 1);
        }
    }

    boolean front() {
        if (this.ee > 0) {
            this.ee--;
        } else {
            this.ee = 0;
        }
        gp();
        invalidate();
        return true;
    }

    boolean back() {
        if (this.ee < this.eh) {
            this.ee++;
        } else {
            this.ee = this.eh;
        }
        gp();
        invalidate();
        return true;
    }

    boolean left() {
        if (this.ed > 0) {
            this.ed--;
        } else {
            this.ed = 0;
        }
        gp();
        invalidate();
        return true;
    }

    boolean right() {
        if (this.ed < this.eh) {
            this.ed++;
        } else {
            this.ed = this.eh;
        }
        gp();
        invalidate();
        return true;
    }

    boolean setValue(int i, int i2) {
        this.ed = i;
        this.ee = i2;
        gp();
        invalidate();
        return true;
    }

    public void gn(int i, int i2, int i3) {
        this.eh = i3;
        this.ed = i;
        this.ee = i2;
    }

    void go(IAmpBalance IAmpBalance) {
        this.ec = IAmpBalance;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(-65536);
        canvas.save();
        canvas.drawBitmap(this.imgHSrc, 0.0f, (float) this.top, paint);
        canvas.drawBitmap(this.imgVSrc, (float) this.left, 0.0f, paint);
        canvas.drawBitmap(this.imgDSrc, (float) (this.left - (this.imgDSrcWidth / 2)), (float) (this.top - (this.imgDSrcWidth / 2)), paint);
        canvas.restore();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.ej = MeasureSpec.getSize(widthMeasureSpec);
        this.ei = MeasureSpec.getSize(heightMeasureSpec);
        this.left = this.ej / 2;
        this.top = this.ei / 2;
        this.left = (((this.ej - this.imgDSrcWidth) * this.ed) / this.eh) + (this.imgDSrcWidth / 2);
        this.top = (((this.ei - this.imgDSrcWidth) * this.ee) / this.eh) + (this.imgDSrcWidth / 2);
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (x < this.imgDSrcWidth / 2) {
            x = this.imgDSrcWidth / 2;
        }
        if (x > this.ej - (this.imgDSrcWidth / 2)) {
            x = this.ej - (this.imgDSrcWidth / 2);
        }
        if (y < this.imgDSrcWidth / 2) {
            y = this.imgDSrcWidth / 2;
        }
        if (y > this.ei - (this.imgDSrcWidth / 2)) {
            y = this.ei - (this.imgDSrcWidth / 2);
        }
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN /*0*/:
                gq(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP /*1*/:
                gq(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE /*2*/:
                gq(x, y);
                invalidate();
                break;
        }
        return true;
    }
}
