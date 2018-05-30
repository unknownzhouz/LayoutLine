package com.nick.line;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : zhengz
 * time   : 2018/3/8
 * desc   : 绘制线条代理类
 */

public class BehaviorLine {
    public static final int LEFT = 0x1;
    public static final int TOP = 0x2;
    public static final int RIGHT = 0x4;
    public static final int BOTTOM = 0x8;

    private View targetView;
    private LineRect leftLine;
    private LineRect topLine;
    private LineRect rightLine;
    private LineRect bottomLine;

    class LineRect {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        float size;
        float start;
        float end;
        boolean hide;
    }

    private BehaviorLine() {

    }

    public void init(View view, AttributeSet attrs) {
        targetView = view;
        if (null != attrs) {
            TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.InsideLine);
            // 线的位置、高度，颜色
            int lineGravity = a.getInt(R.styleable.InsideLine_line_gravity, -1);
            int lineColor = a.getColor(R.styleable.InsideLine_line_color, 0);
            float lineSize = a.getDimension(R.styleable.InsideLine_line_size, 0);
            if ((lineGravity & LEFT) != 0) {
                setLine(LEFT,
                        a.getDimension(R.styleable.InsideLine_line_left_size, lineSize),
                        a.getColor(R.styleable.InsideLine_line_left_color, lineColor),
                        a.getDimension(R.styleable.InsideLine_line_left_start, 0),
                        a.getDimension(R.styleable.InsideLine_line_left_end, 0));
            }

            if ((lineGravity & TOP) != 0) {
                setLine(TOP,
                        a.getDimension(R.styleable.InsideLine_line_top_size, lineSize),
                        a.getColor(R.styleable.InsideLine_line_top_color, lineColor),
                        a.getDimension(R.styleable.InsideLine_line_top_start, 0),
                        a.getDimension(R.styleable.InsideLine_line_top_end, 0));
            }

            if ((lineGravity & RIGHT) != 0) {
                setLine(RIGHT,
                        a.getDimension(R.styleable.InsideLine_line_right_size, lineSize),
                        a.getColor(R.styleable.InsideLine_line_right_color, lineColor),
                        a.getDimension(R.styleable.InsideLine_line_right_start, 0),
                        a.getDimension(R.styleable.InsideLine_line_right_end, 0));
            }

            if ((lineGravity & BOTTOM) != 0) {
                setLine(BOTTOM,
                        a.getDimension(R.styleable.InsideLine_line_bottom_size, lineSize),
                        a.getColor(R.styleable.InsideLine_line_bottom_color, lineColor),
                        a.getDimension(R.styleable.InsideLine_line_bottom_start, 0),
                        a.getDimension(R.styleable.InsideLine_line_bottom_end, 0));
            }
            a.recycle();
        }
    }

    public void setLine(int lineGravity, float size, @ColorInt int lineColor, float start, float end) {
        if ((lineGravity & LEFT) != 0) {
            leftLine = new LineRect();
            leftLine.paint.setColor(lineColor);
            leftLine.size = size;
            leftLine.start = start;
            leftLine.end = end;
        }
        if ((lineGravity & TOP) != 0) {
            topLine = new LineRect();
            topLine.paint.setColor(lineColor);
            topLine.size = size;
            topLine.start = start;
            topLine.end = end;
        }

        if ((lineGravity & RIGHT) != 0) {
            rightLine = new LineRect();
            rightLine.paint.setColor(lineColor);
            rightLine.size = size;
            rightLine.start = start;
            rightLine.end = end;
        }
        if ((lineGravity & BOTTOM) != 0) {
            bottomLine = new LineRect();
            bottomLine.paint.setColor(lineColor);
            bottomLine.size = size;
            bottomLine.start = start;
            bottomLine.end = end;
        }
    }


    /**
     * 隐藏线
     *
     * @param lineGravity
     */
    public void hideGravity(int lineGravity) {
        hideGravity(lineGravity, true);
    }

    /**
     * 显示线
     *
     * @param lineGravity
     */
    public void showGravity(int lineGravity) {
        hideGravity(lineGravity, false);
    }

    private void hideGravity(int lineGravity, boolean hide) {
        boolean change = false;
        if ((lineGravity & LEFT) != 0) {
            if (null != leftLine && leftLine.hide != hide) {
                leftLine.hide = hide;
                change = true;
            }
        }
        if ((lineGravity & TOP) != 0) {
            if (null != topLine && topLine.hide != hide) {
                topLine.hide = hide;
                change = true;
            }
        }
        if ((lineGravity & RIGHT) != 0) {
            if (null != rightLine && rightLine.hide != hide) {
                rightLine.hide = hide;
                change = true;
            }
        }
        if ((lineGravity & BOTTOM) != 0) {
            if (null != bottomLine && bottomLine.hide != hide) {
                bottomLine.hide = hide;
                change = true;
            }
        }
        if (change) {
            targetView.invalidate();
        }
    }

    public void drawLine(Canvas canvas) {
        if (null == targetView) {
            return;
        }
        int width = targetView.getWidth();
        int height = targetView.getHeight();
        if (width == 0 || height == 0) {
            return;
        }

        int saveCount = canvas.getSaveCount();
        if (null != leftLine && !leftLine.hide) {
            canvas.drawRect(0, leftLine.start, leftLine.size, height - leftLine.end, leftLine.paint);
        }

        if (null != topLine && !topLine.hide) {
            canvas.drawRect(topLine.start, 0, width - topLine.end, topLine.size, topLine.paint);
        }

        if (null != rightLine && !rightLine.hide) {
            canvas.drawRect(width - rightLine.size, rightLine.start, width, height - rightLine.end, rightLine.paint);
        }

        if (null != bottomLine && !bottomLine.hide) {
            canvas.drawRect(bottomLine.start, height - bottomLine.size, width - bottomLine.end, height, bottomLine.paint);
        }
        canvas.restoreToCount(saveCount);
    }


    public static BehaviorLine create() {
        return new BehaviorLine();
    }

}
