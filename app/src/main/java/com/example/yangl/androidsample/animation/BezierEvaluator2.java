package com.example.yangl.androidsample.animation;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/3/1
 * version:
 * update:
 */

public class BezierEvaluator2 implements TypeEvaluator<Point> {

    private Point controlPoint;

    public BezierEvaluator2(Point controlPoint){
        this.controlPoint = controlPoint;
    }

    @Override
    public Point evaluate(float t, Point startValue, Point endValue) {
        int x = (int) ((1 - t) * (1 - t) * startValue.x + 2 * t * (1 - t) * controlPoint.x + t * t * endValue.x);
        int y = (int) ((1 - t) * (1 - t) * startValue.y + 2 * t * (1 - t) * controlPoint.y + t * t * endValue.y);
        return new Point(x, y);
    }
}
