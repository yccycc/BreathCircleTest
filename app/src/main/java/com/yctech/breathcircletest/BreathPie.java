package com.yctech.breathcircletest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BreathPie extends View{
    private final static int TO_BE_DEALED_COLOR = 0xFF4D50C0;
    private final static int DEALED_COLOR = 0xFFBD814F;
    private Paint mPaint;
    private int mViewWidth;
    private int mViewHeight;
    private int mInnerCirclePivotX;
    private int mInnerCirclePivotY;
    private double mInnerCircleRadius;
    private double mInnerCircleBreathMaxRadius;

    public BreathPie(Context context) {
        super(context, null);
    }

    public BreathPie(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("bitch--onMeasure", getMeasuredWidth() + "&" + getMeasuredHeight());
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mInnerCirclePivotX = mViewWidth/2;
        mInnerCirclePivotY = mViewHeight/2;
        mInnerCircleRadius = Math.min(mViewWidth,mViewHeight)/8;
        mInnerCircleBreathMaxRadius = mInnerCircleRadius*1.1;
        makeInnerCircleBreath();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawInnerCircle(canvas);
    }
    private void drawInnerCircle(Canvas canvas)
    {
        canvas.drawCircle(mInnerCirclePivotX, mInnerCirclePivotY, (int)mInnerCircleRadius, mPaint);
    }
    private void makeInnerCircleBreath()
    {
        new Thread(){
            double x = 0;
            @Override
            public void run() {
                // 构建一个死循环
                while (true) {
                    // 每循环一次，x都加0.1，此处用到曲线方程y=200|sin(x)|;
                    x += 0.1;
                    try {
                        mInnerCircleRadius = mInnerCircleBreathMaxRadius * Math.abs(Math.sin(x));//最大半径200
                        Log.i("bitch","xun_huan_zhong"+"&"+mInnerCircleRadius);
                        //试图刷新方法，另一个刷新方法invalidate()在此处不适用
                        postInvalidate();
                        //为了视觉舒适，每次改变半径后休眠80毫秒，
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    private void initPaint() {
        // 实例化Paint类，得到mPaint对象，这就是我们定义的画笔工具，
        mPaint = new Paint(SYSTEM_UI_LAYOUT_FLAGS);
        // 去边缘锯齿，这个方法可以使画出的图形更加圆滑，自然。
        mPaint.setAntiAlias(true);
        // 设置画笔的颜色
        mPaint.setColor(Color.YELLOW);
        // 设置画笔的粗细程度，这里的参数单位是像素
        mPaint.setStrokeWidth(2);
        // 设置画笔风格
        mPaint.setStyle(Paint.Style.STROKE);
    }
}
