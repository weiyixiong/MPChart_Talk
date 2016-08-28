package com.wyx.chartdemo;

import android.graphics.PointF;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import com.wyx.chartdemo.chart.TouchPointView;

/**
 * @author winney E-mail: weiyixiong@tigerbrokers.com
 * @version 创建时间: 2016/08/04 下午2:43
 *
 *          手势:平移和缩放
 */

public class TouchListener implements View.OnTouchListener {

  TouchPointView chart;
  protected final PointF prevTouchCenter = new PointF();
  protected final PointF touchCenter = new PointF();
  protected final PointF prevTouchPoint = new PointF();
  protected float prevDist = 1f;

  public TouchListener(TouchPointView chart) {
    this.chart = chart;
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    float x, y;
    switch (MotionEventCompat.getActionMasked(event)) {
      //TODO 3.解决可能因为双指按下与放开顺序的不同 引起的平移误判
      case MotionEvent.ACTION_POINTER_UP:
        //if (event.getPointerCount() == 2) { // 之前为双指，切换为单指
        //  // 记录没有离开屏幕的触摸点的坐标
        //  int upActionIndex = event.getActionIndex();
        //  int remainActionIndex = 2 - 1 - upActionIndex;
        //  x = MotionEventCompat.getX(event, remainActionIndex);
        //  y = MotionEventCompat.getY(event, remainActionIndex);
        //  prevTouchPoint.set(x, y);
        //}
        break;
      //TODO 1.记录双指第一次按下的中点
      case MotionEvent.ACTION_POINTER_DOWN:
        //if (event.getPointerCount() >= 2) {
        //  prevDist = getMultiFingersTouchDist(event);
        //  midPoint(prevTouchCenter, event);
        //  chart.drawTouchCenter(prevTouchCenter);
        //}
        break;
      case MotionEvent.ACTION_DOWN:
        x = MotionEventCompat.getX(event, 0);
        y = MotionEventCompat.getY(event, 0);
        prevTouchPoint.set(x, y);
        break;
      case MotionEvent.ACTION_MOVE:
        switch (event.getPointerCount()) {
          case 1:
            x = MotionEventCompat.getX(event, 0);
            y = MotionEventCompat.getY(event, 0);
            chart.postTranslate(x - prevTouchPoint.x, 0);
            prevTouchPoint.set(x, y);
            break;
          //TODO  2.根据双指的移动计算距离变化以及中点
          case 2:
            //float dist = getMultiFingersTouchDist(event);
            //float scaleY = dist / prevDist;
            //// calculate move
            //midPoint(touchCenter, event);
            //chart.postTranslate(touchCenter.x - prevTouchCenter.x, 0);
            //
            //// calculate scale
            //chart.postScale(scaleY, 1.0f, touchCenter.x, touchCenter.y);
            //prevTouchCenter.set(touchCenter);
            //prevDist = dist;
            break;
        }
        break;
    }
    return false;
  }

  protected static final void midPoint(PointF point, MotionEvent event) {
    float x = 0;
    float y = 0;
    int pointerCount = event.getPointerCount();
    for (int i = 0; i < pointerCount; i++) {
      x += event.getX(i);
      y += event.getY(i);
    }
    point.set(x / pointerCount, y / pointerCount);
  }

  protected static final float getMultiFingersTouchDist(MotionEvent event) {
    if (event.getPointerCount() != 2) {
      return 0;
    }
    float x = event.getX(0) - event.getX(1);
    float y = event.getY(0) - event.getY(1);
    return (float) Math.sqrt(x * x + y * y);
  }
}
