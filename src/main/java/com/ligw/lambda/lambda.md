### 知识树
**什么是lambda**
* lambda概述
> lambda表达式也被称作是箭头函数、匿名函数、闭包\
> lambda表达式体现的是轻量级变成的思想\
> '->'符号是lambda表达式核心操作符好，符号左侧是操作参数，右侧是操作表达式\
> lambda表达式时JDK8的新特性
* MCDA模式（BuildMcad.java）
> Model Code As Data,编码即数据，尽可能轻量级的将代码封装为数据\
> 解决方案：接口&实现类(匿名内部类) \
> 存在问题：语法冗余(传统编码中与数据处理无关的编码较多)、this关键字(在匿名内部类中，this关键字的变量绑定及变量访问存在误区)、变量捕获(在匿名内部类中对变量作用域会有特殊要求)、数据控制等(不友好)
* 问题及优化
* 为什么要用Lambda

**lambda基础**
* 函数式接口(function interface)(FunInt.java)
> 只包含一个"抽象"方法的特殊接口,注解:@FunctionallInterface\
> 函数式接口中允许存在：(唯一的)抽象接口方法、默认接口方法、静态接口方法、继承自Object的抽象方法\
> lambda表达式只能操作一个方法。lambda表达式核心就是一个函数式接口的实现。
* 常见的函数接口(FunIntInJre.java)
> java.lang.Runnable\
> java.lang.Comparable\
> java.lang.Comparator\
> java.io.FileFilter\
> .....
> java.util.function包提供了常用的函数式功能接口
* lambda基本语法(Grammar.java)
> [接口声明] = (参数) -> {执行代码块}\
> 方法体只有一行时可省略大括号
> 方法体只有一行且有返回值时，可在省略大括号的基础上省略return关键字，单行执行结果会自定返回。\
> 参数可不写参数类型，jvm在运行时会依据lambda表达式绑定的接口方法自动判定参数类型
* 变量捕获()
* 类型检查()
* lambda运行原理()

**lambda高级**
* 方法引用   
* stream API
* Stream原理
* 集合元素操作
* 实际需求重构
* 线程安全与性能