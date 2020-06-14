## 谈谈对 Java 平台的理解？

Java 特性：

- 面向对象(封装、继承、多态)
- 平台无关性（JVM运行.class文件）
- 语言（泛型，Lambda）
- 类库（集合，并发，网络，IO/NIO）
- JRE（Java运行环境，JVM，类库）
- JDK（Java 开发工具，包括JRE，javac，诊断工具）

Java 是解析运行吗？

> 不正确！

1. Java 源代码经过 Javac 编译成.class文件
2. .class文件经 JVM 解析或编译运行。
   1. 解析：.class文件经过JVM内嵌的解析器解析执行。
   2. 编译：存在JIT编译器（Just In Time Compile 即时编译器）把经常运行的代码作为”热点代码“ 编译与本地平台相关的机器码，并进行各种层次的优化
   3. AOT 编译器：Java 9提供的直接将所有代码编译成机器码执行。

## 序列化和反序列化？

1. 概念
   1. 序列化：把 Java 对象转换为字节序列的过程
   2. 反序列化：把字节序列恢复为 Java 对象的过程
2. 对象序列化的两种主要用途
   1. 把对象的字节序列永久地保存到硬盘上，通常放在一个文件中（持久化对象）
   2. 在网络上传送对象的字节序列。（网络传输对象）

## final、finally、finalize 区别？

final 可以用来修饰类、方法、变量，final修饰的类代表不可以继续扩展，final 的变量是不可以修改的，而 final 的方法也是不可以重写的

finally 则是 Java 保证重点代码一定要被执行的一种机制。我们可以用 try-finally 或者 try-catch-finally 来进行类似关闭 JDBC 连接、保证 unlock 锁等动作。

finalize 是基础类 Object 的一个方法，它的设计目的是包装对象在被垃圾收集前完成特定资源的回收。finalize 已经不推荐使用，并且在 JDK 9 开始被标记 deprecated。

## "==" 和 equals 区别？

==：对于基本类型是比较的值，引用类型则比较的是引用。

equals：默认比较的是引用，只不过很多类重写了 equals 方法，String 、Integer 等把它变成了值比较，所以一般情况下 equals 比较的是指是否相等。