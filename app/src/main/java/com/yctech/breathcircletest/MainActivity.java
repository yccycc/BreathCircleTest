package com.yctech.breathcircletest;

import android.app.Activity;
import android.os.Bundle;
/**
 * 
 * @ClassName MainActivity 
 * @Description TODO 
 * @Version 1.0.0
 * @Author 吴迪
 * @Creation 2015年3月19日 下午4:25:24 
 * @Mender 吴迪
 * @Modification 2015年3月19日 下午4:25:24 
*
 */
public class MainActivity extends Activity {
	private CircleView mcircleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mcircleView = (CircleView) findViewById(R.id.circleView1);
		//开启一个线程启动view呼吸
		new Thread(mcircleView).start();
	}
}
