package com.nick.line;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;


/**
 * author : zhengz
 * time   : 2018/3/7
 * desc   : layout布局中可以上、下、左、右类型线条，支持颜色、粗度、以及前后margin偏移量
 */

public class InsideLineLinearLayout extends LinearLayout {
    private final BehaviorLine mBehaviorLine = BehaviorLine.create();

    public InsideLineLinearLayout(Context context) {
        this(context, null);
    }

    public InsideLineLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InsideLineLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
