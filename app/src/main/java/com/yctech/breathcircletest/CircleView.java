package com.yctech.breathcircletest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * @ClassName CircleView
 * @Description 简单自定义view 圆环呼吸效果
 * @Version 1.0.0
 * @Author 吴迪
 * @Creation 2015年3月19日 下午4:03:27
 * @Mender 吴迪
 * @Modification 2015年3月19日 下午4:03:27
 *
 */
public class CircleView extends View implements Runnable {
	private Context context;
	private Paint mPaint;
	private double radio;
	

	public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

	}

	public CircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initPaint();// 初始化画笔，不要在onDraw方法里初始化否则会造成内存浪费
	}

	public CircleView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// canvas画布的draw....方法可以在canvas画布上绘制出各种图形，当然，画图需要用到画笔工具，那么这个画笔工具可以是我们new出来的。
		canvas.drawCircle(300, 300, (int) radio, mPaint);// 这里的四个参数分别是圆的圆心坐标X,Y；圆半径，和画笔

	}

	private void initPaint() {
		// 实例化Paint类，得到mPaint对象，这就是我们定义的画笔工具，
		mPaint = new Paint(SYSTEM_UI_LAYOUT_FLAGS);
		// 去边缘锯齿，这个方法可以使画出的图形更加圆滑，自然。
		mPaint.setAntiAlias(true);
		// 设置画笔的颜色
		mPaint.setColor(Color.BLUE);
		// 设置画笔的粗细程度，这里的参数单位是像素
		mPaint.setStrokeWidth(10);
		// 设置画笔风格
		mPaint.setStyle(Paint.Style.STROKE);
	}

	// 定义一个变量x，下边的曲线方程会用到
	double x = 0;

	@Override
	public void run() {
		// 构建一个死循环
		while (true) {
			// 每循环一次，x都加0.1，此处用到曲线方程y=200|sin(x)|;
			x += 0.1;
			try {
				radio = 200 * Math.abs(Math.sin(x));//最大半径200
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

}
