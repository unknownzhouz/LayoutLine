package com.nick.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;


/**
 * author : zhengz
 * time   : 2018/3/7
 * desc   : layout布局中可以上、下、左、右类型线条, 支持多种线条
 */

public class InsideLineConstraintLayout extends ConstraintLayout implements InsideLineSupport {
    private final BehaviorLine mBehaviorLine = BehaviorLine.create(this);

    public InsideLineConstraintLayout(Context context) {
        this(context, null);
    }

    public InsideLineConstraintLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InsideLineConstraintLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBehaviorLine.init(attrs);
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        mBehaviorLine.drawLine(canvas);
    }

    @Override
    public void setLine(int gravity, float size, int lineColor) {
        setLine(gravity, size, lineColor, 0, 0);
    }

    @Override
    public void setLine(int gravity, float size, int lineColor, float start, float end) {
        mBehaviorLine.setLine(gravity, size, lineColor, start, end);
    }

    @Override
    public void removeLine(int gravity) {
        mBehaviorLine.removeLine(gravity);
    }

    @Override
    public void showLine(int gravity) {
        mBehaviorLine.showLine(gravity);
    }

    @Override
    public void hideLine(int gravity) {
        mBehaviorLine.hideLine(gravity);
    }
}
