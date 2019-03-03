package com.nick.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;


/**
 * author : zhengz
 * time   : 2018/3/7
 * desc   : layout布局中可以上、下、左、右类型线条，支持颜色、粗度、以及前后margin偏移量
 */

public class InsideLineLinearLayout extends LinearLayout implements InsideLineSupport{
    private final BehaviorLine mBehaviorLine = BehaviorLine.create(this);

    public InsideLineLinearLayout(Context context) {
        this(context, null);
    }

    public InsideLineLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InsideLineLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBehaviorLine.init(attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
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
