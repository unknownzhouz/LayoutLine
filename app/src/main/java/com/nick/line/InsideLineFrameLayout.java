package com.nick.line;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * author : zhengz
 * time   : 2018/3/7
 * desc   : layout布局中可以上、下、左、右类型线条, 支持多种线条
 */

public class InsideLineFrameLayout extends FrameLayout {
    private final BehaviorLine mBehaviorLine = BehaviorLine.create();

    public InsideLineFrameLayout(Context context) {
        this(context, null);

    }

    public InsideLineFrameLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InsideLineFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBehaviorLine.init(this, attrs);
    }


    public void setLine(int lineGravity, float size, @ColorInt int lineColor) {
        mBehaviorLine.setLine(lineGravity, size, lineColor, 0, 0);
    }


    public void setLine(int lineGravity, float size, @ColorInt int lineColor, float start, float end) {
        mBehaviorLine.setLine(lineGravity, size, lineColor, start, end);
    }

    /**
     * 隐藏线
     *
     * @param lineGravity
     */
    public void hideGravity(int lineGravity) {
        mBehaviorLine.hideGravity(lineGravity);
    }

    /**
     * 显示线
     *
     * @param lineGravity
     */
    public void showGravity(int lineGravity) {
        mBehaviorLine.showGravity(lineGravity);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        mBehaviorLine.drawLine(canvas);
    }
}
