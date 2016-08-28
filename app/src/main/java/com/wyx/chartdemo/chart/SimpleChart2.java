package com.wyx.chartdemo.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.wyx.chartdemo.DataGenerator;

/**
 * @author winney E-mail: weiyixiong@tigerbrokers.com
 * @version 创建时间: 2016/08/03 下午4:19
 *
 *          Matrix的应用
 *
 * 3.支持缩放
 */

public class SimpleChart2 extends View {
  private int SIZE = 1000;
  float[] data = DataGenerator.getData(SIZE);
  float[] point = new float[(SIZE - 1) * 4];

  Matrix matrix = new Matrix();
  Paint linePaint = new Paint();

  public SimpleChart2(Context context, AttributeSet attrs) {
    super(context, attrs);
    linePaint.setAntiAlias(true);
    linePaint.setColor(Color.RED);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    float scaleX = ((float) getWidth()) / (SIZE - 1);
    float scaleY = getHeight() / getRange();
    //3.1 set和post的区别
    matrix.setScale(scaleX, scaleY);
    //3.2 旋转
    matrix.postRotate(20);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    long start = System.currentTimeMillis();

    for (int i = 0; i < data.length - 1; i++) {
      int index = 4 * i;
      point[index] = i;
      point[index + 1] = data[i];
      point[index + 2] = (i + 1);
      point[index + 3] = data[i + 1];
    }
    matrix.mapPoints(point);
    canvas.drawLines(point, linePaint);

    Log.e("drawTime:", System.currentTimeMillis() - start + "ms");
  }

  public float getRange() {
    float min = Float.MAX_VALUE;
    float max = -Float.MAX_VALUE;
    for (int i = 0; i < data.length; i++) {
      min = Math.min(min, data[i]);
      max = Math.max(max, data[i]);
    }
    return max - min;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    data = DataGenerator.getData(++SIZE);
    point = new float[(SIZE - 1) * 4];
    invalidate();
    return super.onTouchEvent(event);
  }
}
