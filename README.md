# LayoutLine

### 介绍

当前库提供了在一些常用的布局中增加上、下、左、右、等组合线条的使用，减少一些控件在对线布局中的嵌套，目前使用环境针对的是常用线性布局、帧布局、相对布局、以及最新的约束布局。

### 使用

```
 <com.nick.widget.InsideLineLinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center_vertical"
        android:orientation="horizontal"
        android:padding="15dp"

        app:line_gravity="Top|Left|Right|Bottom"
  

        app:line_left_color="#fe6d2d"
        app:line_left_size="2dp"

        app:line_right_color="#666666"
        app:line_right_end="10dp"
        app:line_right_size="3dp"
        app:line_right_start="10dp"

        app:line_top_color="#ff2b44"
        app:line_top_size="2dp"
        app:line_top_start="10dp"
        
        app:line_bottom_color="#11aac3"
        app:line_bottom_end="20dp"
        app:line_bottom_size="5dp"
        app:line_bottom_start="5dp"/>

```

