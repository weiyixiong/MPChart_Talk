package com.wyx.chartdemo.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
 *
 * 1.绘制折线
 * 2.根据屏幕比例绘制
 */

public class SimpleChart extends View {
  private int SIZE = 1000;
  float[] data = DataGenerator.getData(SIZE);
  float[] point = new float[(SIZE - 1) * 4];
  float scaleX;
  float scaleY;
  Paint linePaint = new Paint();

  public SimpleChart(Context context, AttributeSet attrs) {
    super(context, attrs);
    linePaint.setAntiAlias(true);
    linePaint.setColor(Color.RED);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    scaleX = ((float) getWidth()) / (SIZE - 1);
    scaleY = getHeight() / getRange();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //{x1,y1,x2,y2....}
    //float[] point = new float[SIZE * 4 - SIZE];
    long start = System.currentTimeMillis();

    for (int i = 0; i < data.length - 1; i++) {
      int index = 4 * i;
      point[index] = i * scaleX;
      point[index + 1] = data[i] * scaleY;
      point[index + 2] = (i + 1) * scaleX;
      point[index + 3] = data[i + 1] * scaleY;
    }
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
