package com.microntek.ampsetup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MyButton extends FrameLayout {
    ImageView ea;
    ImageView eb;



    public MyButton(Context context) {
        super(context);
    }
    public MyButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutParams layoutParams;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MyButton);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.MyButton_imgSrc/*2*/, 0);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MyButton_imgWidth/*0*/, 10);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MyButton_imgHeight/*1*/, 10);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.MyButton_imgSrc2/*4*/, 0);
        if (resourceId2 != 0) {
            int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MyButton_imgWidth2/*5*/, 0);
            int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MyButton_imgHeight2/*6*/, 0);
            layoutParams = (dimensionPixelSize3 == 0 || dimensionPixelSize4 == 0) ? new FrameLayout.LayoutParams(-1, -1, 17) : new FrameLayout.LayoutParams(dimensionPixelSize3, dimensionPixelSize4, 17);
            this.eb = new ImageView(context);
            this.eb.setImageResource(resourceId2);
            this.eb.setScaleType(ScaleType.FIT_XY);
            this.eb.setDuplicateParentStateEnabled(true);
            this.eb.setLayoutParams(layoutParams);
            addView(this.eb);
        }
        layoutParams = new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize2, 17);
        this.ea = new ImageView(context);
        this.ea.setImageResource(resourceId);
        this.ea.setScaleType(ScaleType.FIT_XY);
        this.ea.setDuplicateParentStateEnabled(true);
        this.ea.setLayoutParams(layoutParams);
        addView(this.ea);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            this.ea.setAlpha(255);
        } else {
            this.ea.setAlpha(80);
        }
    }
}
