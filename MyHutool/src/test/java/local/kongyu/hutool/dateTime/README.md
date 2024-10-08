# 日期时间

官方文档：https://www.hutool.cn/docs/#/core/%E6%97%A5%E6%9C%9F%E6%97%B6%E9%97%B4/%E6%A6%82%E8%BF%B0



## [介绍](https://www.hutool.cn/docs/#/core/日期时间/概述?id=介绍)

日期时间包是Hutool的核心包之一，提供针对JDK中Date和Calendar对象的封装，封装对象如下：

## [日期时间工具](https://www.hutool.cn/docs/#/core/日期时间/概述?id=日期时间工具)

- `DateUtil` 针对日期时间操作提供一系列静态方法
- `DateTime` 提供类似于Joda-Time中日期时间对象的封装，继承自Date类，并提供更加丰富的对象方法。
- `FastDateFormat` 提供线程安全的针对Date对象的格式化和日期字符串解析支持。此对象在实际使用中并不需要感知，相关操作已经封装在`DateUtil`和`DateTime`的相关方法中。
- `DateBetween` 计算两个时间间隔的类，除了通过构造新对象使用外，相关操作也已封装在`DateUtil`和`DateTime`的相关方法中。
- `TimeInterval` 一个简单的计时器类，常用于计算某段代码的执行时间，提供包括毫秒、秒、分、时、天、周等各种单位的花费时长计算，对象的静态构造已封装在`DateUtil`中。
- `DatePattern` 提供常用的日期格式化模式，包括`String`类型和`FastDateFormat`两种类型。

## [日期枚举](https://www.hutool.cn/docs/#/core/日期时间/概述?id=日期枚举)

考虑到`Calendar`类中表示时间的字段（field）都是使用`int`表示，在使用中非常不便，因此针对这些`int`字段，封装了与之对应的Enum枚举类，这些枚举类在`DateUtil`和`DateTime`相关方法中做为参数使用，可以更大限度的缩小参数限定范围。

这些定义的枚举值可以通过`getValue()`方法获得其与`Calendar`类对应的int值，通过`of(int)`方法从`Calendar`中int值转为枚举对象。

与`Calendar`对应的这些枚举包括：

- `Month` 表示月份，与Calendar中的int值一一对应。
- `Week` 表示周，与Calendar中的int值一一对应

### [月份枚举](https://www.hutool.cn/docs/#/core/日期时间/概述?id=月份枚举)

通过月份枚举可以获得某个月的最后一天

```java
// 31
int lastDay = Month.of(Calendar.JANUARY).getLastDay(false);
```

另外，Hutool还定义了**季度**枚举。`Season.SPRING`为第一季度，表示1~3月。季度的概念并不等同于季节，因为季节与月份并不对应，季度常用于统计概念。

## [时间枚举](https://www.hutool.cn/docs/#/core/日期时间/概述?id=时间枚举)

时间枚举`DateUnit`主要表示某个时间单位对应的毫秒数，常用于计算时间差。

例如：`DateUnit.MINUTE`表示分，也表示一分钟的毫米数，可以通过调用其`getMillis()`方法获得其毫秒数。

