## LayoutLine

当前库提供了在一些常用的布局中增加上、下、左、右、等组合线条的使用，减少一些控件在对线布局中的嵌套，目前使用环境针对的是常用线性布局、帧布局、相对布局、以及最新的约束布局。

### 使用

```xml
 <com.nick.widget.InsideLineLinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center_vertical"
        android:orientation="horizontal"
        android:padding="15dp"
        app:line_gravity="Top|Left|Right|Bottom"
        app:line_left_color="#fe6d2d"
        app:line_left_size="2dp"
        app:line_top_color="#ff2b44"
        app:line_top_size="2dp"
        app:line_top_start="10dp"
        app:line_right_color="#666666"
        app:line_right_end="10dp"
        app:line_right_size="3dp"
        app:line_right_start="10dp"
        app:line_bottom_color="#11aac3"
        app:line_bottom_end="20dp"
        app:line_bottom_size="5dp"
        app:line_bottom_start="5dp"/>

```

![image](https://github.com/unknownzhouz/LayoutLine/blob/master/static/1634800863(1).png)

| 线的属性      | 描述             | 格式 |
| --------:    | :-------------------     | :--------    |
| line_gravity | 位置 | Left\|Top\|Right\|Bottom |
| line_color   | 所有线颜色               | color |
| line_size    | 所有线大小            | dimension |
| line_left_size  | 左线大小             | dimension |
| line_left_color | 左线颜色             | color |
| line_left_start | 左线前偏移量         | dimension |
| line_left_end   | 左线后偏移量         | dimension |
| line_top_size  | 上线大小             | dimension |
| line_top_color | 上线颜色             | color |
| line_top_start | 上线前偏移量          | dimension |
| line_top_end   | 上线后偏移量          | dimension |
| line_right_size  | 右线大小           | dimension |
| line_right_color | 右线颜色           | color |
| line_right_start | 右线前偏移量        | dimension |
| line_right_end   | 右线后偏移量        | dimension |
| line_bottom_size  | 下线大小          | dimension |
| line_bottom_color | 下线颜色          | color |
| line_bottom_start | 下线前偏移量       | dimension |
| line_bottom_end   | 下线后偏移量       | dimension |

### Gradle集成

在 project `build.gradle` 添加 `maven` 仓库

```java
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

在 module `build.gradle` 添加依赖

```java
implementation 'com.github.unknownzhouz:LayoutLine:0.1.9'
```