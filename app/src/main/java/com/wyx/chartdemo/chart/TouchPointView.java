package com.wyx.chartdemo.chart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.wyx.chartdemo.TouchListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author winney E-mail: weiyixiong@tigerbrokers.com
 * @version 创建时间: 2016/08/03 下午4:27
 */

public class TouchPointView extends View {
  public static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
  /**
   * final bitmap that contains all information and is drawn to the screen
   */
  protected Bitmap mDrawBitmap;

  /**
   * the canvas that is used for drawing on the bitmap
   */
  protected Canvas mDrawCanvas;

  Paint touchPaint;

  List<Point> touchPoint = new ArrayList<>(2);
  List<PointF> drawPoint = new ArrayList<>(2);

  public TouchPointView(Context context, AttributeSet attrs) {
    super(context, attrs);
    touchPaint = new Paint();
    touchPaint.setStrokeWidth(5);
    touchPaint.setStyle(Paint.Style.STROKE);
    touchPaint.setColor(Color.BLACK);

    //开启手势
    setOnTouchListener(new TouchListener(this));
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (mDrawBitmap == null || mDrawCanvas == null || needRecreate(mDrawBitmap)) {
      if (mDrawBitmap != null) {
        mDrawBitmap.recycle();
      }
      // use RGB_565 for best performance
      mDrawBitmap = Bitmap.createBitmap(getWidth(), getHeight(), BITMAP_CONFIG);
      mDrawCanvas = new Canvas(mDrawBitmap);
    }
    mDrawCanvas.drawColor(Color.WHITE);
  }

  protected void drawTouchPoint(Canvas canvas) {
    for (int i = 0; i < touchPoint.size(); i++) {
      canvas.drawCircle(touchPoint.get(i).x, touchPoint.get(i).y, 80, touchPaint);
    }
    for (int i = 0; i < drawPoint.size(); i++) {
      canvas.drawCircle(drawPoint.get(i).x, drawPoint.get(i).y, 80, touchPaint);
    }
    if (touchPoint.size() == 2) {
      canvas.drawLine(touchPoint.get(0).x, touchPoint.get(0).y, touchPoint.get(1).x, touchPoint.get(1).y, touchPaint);
    }
  }

  private boolean needRecreate(Bitmap bitmap) {
    int width = bitmap.getWidth();
    int height = bitmap.getHeight();
    return width < getWidth() || height < getHeight();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    touchPoint.clear();
    for (int i = 0; i < event.getPointerCount() && i < 2; i++) {
      touchPoint.add(new Point((int) event.getX(i), (int) event.getY(i)));
    }
    if (event.getAction() == MotionEvent.ACTION_UP) {
      touchPoint.clear();
    }
    invalidate();
    return true;
  }

  public void postTranslate(float v, int i) {
  }

  public void drawTouchCenter(PointF prevTouchCenter) {
    drawPoint.add(prevTouchCenter);
  }

  public void postScale(float scaleY, float v, float x, float y) {
  }
}
