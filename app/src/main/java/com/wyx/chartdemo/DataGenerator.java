package com.wyx.chartdemo;

import java.util.Random;

/**
 * @author winney E-mail: weiyixiong@tigerbrokers.com
 * @version 创建时间: 2016/08/03 下午4:23
 */

public class DataGenerator {

  private static float[] data;

  public static float[] getData(int size) {
    if (data == null || data.length != size) {
      data = new float[size];
      for (int i = 0; i < data.length; i++) {
        data[i] = new Random().nextFloat() * 100;
      }
    }
    return data;
  }
}
