package com.wyx.chartdemo.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import com.wyx.chartdemo.DataGenerator;

/**
 * @author winney E-mail: weiyixiong@tigerbrokers.com
 * @version 创建时间: 2016/08/03 下午4:19
 *
 *  4.extends TouchPointView
 *  4.1 双缓冲的优缺点
 */

public class SimpleChart3 extends TouchPointView {
  private int SIZE = 9000;
  float[] data = DataGenerator.getData(SIZE);

  float max = 0;
  float min = 0;
  Matrix matrix = new Matrix();
  Paint linePaint = new Paint();

  public SimpleChart3(Context context, AttributeSet attrs) {
    super(context, attrs);
    linePaint.setAntiAlias(true);
    linePaint.setStyle(Paint.Style.STROKE);
    linePaint.setColor(Color.RED);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    float scaleX = ((float) getWidth()) / (SIZE - 1);
    float scaleY = getHeight() / getRange();
    matrix.setScale(scaleX, scaleY);
  }

  RectF rect = new RectF();

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    long start = System.currentTimeMillis();

    for (int i = 0; i < data.length - 1; i++) {
      rect.left = i;
      rect.top = (int) data[i];
      rect.right = i + 1;
      rect.bottom = this.max;
      matrix.mapRect(rect);
      canvas.drawRect(rect, linePaint);
    }
    drawTouchPoint(canvas);
    Log.e("drawTime:", System.currentTimeMillis() - start + "ms");
  }

  public float getRange() {
    float min = Float.MAX_VALUE;
    float max = -Float.MAX_VALUE;
    for (int i = 0; i < data.length; i++) {
      min = Math.min(min, data[i]);
      max = Math.max(max, data[i]);
    }
    this.max = max;
    this.min = min;
    return max - min;
  }
}
