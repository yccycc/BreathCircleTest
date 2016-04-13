package com.yctech.breathcircletest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PercentPie extends View {
    private final static int TO_BE_DEALED_COLOR = 0xFF4D50C0;
    private final static int DEALED_COLOR = 0xFFBD814F;
    private Paint mPaint;
    private int mViewWidth;
    private int mViewHeight;
    private int mOneCirclePivotX;
    private int mOneCirclePivotY;
    private int mCircleRadius;
    private int mAnotherCirclePivotX;
    private int mAnotherCirclePivotY;
    private RectF mShareRect = new RectF();
    private int mToDealNum = 3;
    private int dealedNum = 5;
    private float mGapAngel;
    private int mGapLength = 8;
    private float offsetX, offsetY;

    public PercentPie(Context context) {
        super(context, null);
    }

    public PercentPie(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mOneCirclePivotX = mViewWidth / 2;
        mOneCirclePivotY = mViewHeight / 2;
        mAnotherCirclePivotX = mOneCirclePivotX + 10;
        mAnotherCirclePivotY = mOneCirclePivotY - 5;
        mCircleRadius = Math.min(mViewWidth, mViewHeight) / 4;

        mGapAngel = 360 * mToDealNum / (mToDealNum + dealedNum);

        mShareRect = new RectF(mViewWidth / 2 - mCircleRadius, mViewHeight / 2 - mCircleRadius, mViewWidth / 2 + mCircleRadius, mViewHeight / 2 + mCircleRadius);
        offsetX = (float) (mGapLength * Math.cos(Math.PI / 180 * mGapAngel / 2));
        offsetY = (float) (-mGapLength * Math.sin(Math.PI / 180 * mGapAngel / 2));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mShareRect, 0, 360 - mGapAngel, true, mPaint);
        canvas.save();
        canvas.translate(offsetX, offsetY);
        canvas.drawArc(mShareRect, 360 - mGapAngel, mGapAngel, true, mPaint);
        canvas.restore();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
    }
}
