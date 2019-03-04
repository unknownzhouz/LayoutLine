package com.nick.widget;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;


/**
 * author : zhengz
 * time   : 2018/3/8
 * desc   : 绘制线条代理类
 */

public class BehaviorLine {

    private View targetView;
    private LineRect leftLine;
    private LineRect topLine;
    private LineRect rightLine;
    private LineRect bottomLine;


    private BehaviorLine(@NonNull View view) {
        targetView = view;
    }

    public void init(AttributeSet attrs) {
        if (null != attrs) {
            TypedArray a = targetView.getContext().obtainStyledAttributes(attrs, R.styleable.InsideLine);
            // 默认线的位置、高度，颜色
            int lineGravity = a.getInt(R.styleable.InsideLine_line_gravity, 0);
            int lineColor = a.getColor(R.styleable.InsideLine_line_color, 0);
            float lineSize = a.getDimension(R.styleable.InsideLine_line_size, 0);
            if ((lineGravity & InsideLineSupport.Gravity.LEFT) != 0) {
                setLine(InsideLineSupport.Gravity.LEFT,
                        a.getDimension(R.styleable.InsideLine_line_left_size, lineSize),
                        a.getColor(R.styleable.InsideLine_line_left_color, lineColor),
                        a.getDimension(R.styleable.InsideLine_line_left_start, 0),
                        a.getDimension(R.styleable.InsideLine_line_left_end, 0));
            }

            if ((lineGravity & InsideLineSupport.Gravity.TOP) != 0) {
                setLine(InsideLineSupport.Gravity.TOP,
                        a.getDimension(R.styleable.InsideLine_line_top_size, lineSize),
                        a.getColor(R.styleable.InsideLine_line_top_color, lineColor),
                        a.getDimension(R.styleable.InsideLine_line_top_start, 0),
                        a.getDimension(R.styleable.InsideLine_line_top_end, 0));
            }

            if ((lineGravity & InsideLineSupport.Gravity.RIGHT) != 0) {
                setLine(InsideLineSupport.Gravity.RIGHT,
                        a.getDimension(R.styleable.InsideLine_line_right_size, lineSize),
                        a.getColor(R.styleable.InsideLine_line_right_color, lineColor),
                        a.getDimension(R.styleable.InsideLine_line_right_start, 0),
                        a.getDimension(R.styleable.InsideLine_line_right_end, 0));
            }

            if ((lineGravity & InsideLineSupport.Gravity.BOTTOM) != 0) {
                setLine(InsideLineSupport.Gravity.BOTTOM,
                        a.getDimension(R.styleable.InsideLine_line_bottom_size, lineSize),
                        a.getColor(R.styleable.InsideLine_line_bottom_color, lineColor),
                        a.getDimension(R.styleable.InsideLine_line_bottom_start, 0),
                        a.getDimension(R.styleable.InsideLine_line_bottom_end, 0));
            }
            a.recycle();
        }
    }

    public void setLine(@InsideLineSupport.Gravity int gravity, float size, @ColorInt int lineColor) {
        setLine(gravity, size, lineColor, 0, 0);
    }


    public void setLine(@InsideLineSupport.Gravity int gravity, float size, @ColorInt int lineColor, float start, float end) {
        boolean change = false;
        if ((gravity & InsideLineSupport.Gravity.LEFT) != 0) {
            if (null == leftLine) {
                leftLine = new LineRect();
            }
            if (leftLine.update(size, lineColor, start, end)) {
                change = true;
            }
        }
        if ((gravity & InsideLineSupport.Gravity.TOP) != 0) {
            if (null == topLine) {
                topLine = new LineRect();
            }
            if (topLine.update(size, lineColor, start, end)) {
                change = true;
            }
        }

        if ((gravity & InsideLineSupport.Gravity.RIGHT) != 0) {
            if (null == rightLine) {
                rightLine = new LineRect();
            }
            if (rightLine.update(size, lineColor, start, end)) {
                change = true;
            }

        }
        if ((gravity & InsideLineSupport.Gravity.BOTTOM) != 0) {
            if (null == bottomLine) {
                bottomLine = new LineRect();
            }
            if (bottomLine.update(size, lineColor, start, end)) {
                change = true;
            }
        }

        if (change) {
            targetView.invalidate();
        }
    }


    public void removeLine(@InsideLineSupport.Gravity int gravity) {
        boolean change = false;
        if ((gravity & InsideLineSupport.Gravity.LEFT) != 0 && null != leftLine) {
            leftLine = null;
            change = true;
        }

        if ((gravity & InsideLineSupport.Gravity.TOP) != 0 && null != topLine) {
            topLine = null;
            change = true;
        }

        if ((gravity & InsideLineSupport.Gravity.RIGHT) != 0 && null != rightLine) {
            rightLine = null;
            change = true;

        }
        if ((gravity & InsideLineSupport.Gravity.BOTTOM) != 0 && null != bottomLine) {
            bottomLine = null;
            change = true;
        }

        if (change) {
            targetView.invalidate();
        }
    }


    /**
     * 隐藏线
     *
     * @param gravity
     */
    public void hideLine(@InsideLineSupport.Gravity int gravity) {
        displayLine(gravity, true);
    }

    /**
     * 显示线
     *
     * @param gravity
     */
    public void showLine(@InsideLineSupport.Gravity int gravity) {
        displayLine(gravity, false);
    }


    private void displayLine(@InsideLineSupport.Gravity int gravity, boolean hide) {
        boolean change = false;
        if ((gravity & InsideLineSupport.Gravity.LEFT) != 0) {
            if (null != leftLine && leftLine.hide(hide)) {
                change = true;
            }
        }
        if ((gravity & InsideLineSupport.Gravity.TOP) != 0) {
            if (null != topLine && topLine.hide(hide)) {
                change = true;
            }
        }
        if ((gravity & InsideLineSupport.Gravity.RIGHT) != 0) {
            if (null != rightLine && rightLine.hide(hide)) {
                change = true;
            }
        }
        if ((gravity & InsideLineSupport.Gravity.BOTTOM) != 0) {
            if (null != bottomLine && bottomLine.hide(hide)) {
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
        if (null != leftLine && !leftLine._hide) {
            canvas.drawRect(0, leftLine._start, leftLine._size, height - leftLine._end, leftLine._paint);
        }

        if (null != topLine && !topLine._hide) {
            canvas.drawRect(topLine._start, 0, width - topLine._end, topLine._size, topLine._paint);
        }

        if (null != rightLine && !rightLine._hide) {
            canvas.drawRect(width - rightLine._size, rightLine._start, width, height - rightLine._end, rightLine._paint);
        }

        if (null != bottomLine && !bottomLine._hide) {
            canvas.drawRect(bottomLine._start, height - bottomLine._size, width - bottomLine._end, height, bottomLine._paint);
        }
        canvas.restoreToCount(saveCount);
    }


    static class LineRect {
        Paint _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        float _size;
        float _start;
        float _end;
        boolean _hide;

        boolean update(float size,
                       @ColorInt int color,
                       float start,
                       float end) {
            boolean change = false;
            if (0 != color && _paint.getColor() != color) {
                _paint.setColor(color);
                change = true;
            }

            if (_size != size) {
                _size = size;
                change = true;
            }

            if (_start != start) {
                _start = start;
                change = true;
            }
            if (_end != end) {
                _end = end;
                change = true;
            }
            return change;
        }

        boolean hide(boolean hide) {
            if (_hide != hide) {
                _hide = hide;
                return true;
            }
            return false;
        }


    }

    public static BehaviorLine create(View view) {
        return new BehaviorLine(view);
    }

}
