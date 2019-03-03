package com.nick.widget;

import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface InsideLineSupport {


    @IntDef({Gravity.LEFT, Gravity.TOP, Gravity.RIGHT, Gravity.BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    @interface Gravity {
        int LEFT = 0x1;
        int TOP = 0x2;
        int RIGHT = 0x4;
        int BOTTOM = 0x8;
    }


    void setLine(@Gravity int gravity, float size, @ColorInt int color);

    /**
     * 设置线条属性
     * @param gravity
     * @param size
     * @param color
     * @param start
     * @param end
     */
    void setLine(@Gravity int gravity, float size, @ColorInt int color, float start, float end);

    /**
     * 删除线条
     * @param gravity
     */
    void removeLine(@Gravity int gravity);

    /**
     * 显示线条
     * @param gravity
     */
    void showLine(@Gravity int gravity);

    /**
     * 隐藏线条
     * @param gravity
     */
    void hideLine(@Gravity int gravity);

}
