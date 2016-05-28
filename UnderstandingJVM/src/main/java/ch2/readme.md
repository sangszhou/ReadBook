## Dump 文件的存放位置
在项目的根目录下,名字 pattern 是 java_pid{pid_num}.hprof

## 生成的 hprof 文件导入到 VisualVM
在 visualVM 中
File -> load -> 文件格式 hprof

## HeapOom
在 HeapOom 这个例子中, 可以从 visualvm 中直接看到 OOMObject 占了内存的绝大部分,实例数也很多. 但是根据以往的经验,
visualvm 中看到的更多的是 char [] 占了大部分内存

## Stack over flow
不会产生 dump 文件

## Xss
-Xss128k：设置每个线程的堆栈大小。JDK5.0以后每个线程堆 栈大小为1M，以前每个线程堆栈大小为256K。根据应用的线程所需内存大小进行调整。
在相同物理内存下，减小这个值能生成更多的线程。但是操作系统对一 个进程内的线程数还是有限制的，不能无限生成，经验值在3000~5000左右。

线程栈的大小是个双刃剑，如果设置过小，可能会出现栈溢出，特别是在该线程内有递归、大的循环时出现溢出的可能性更大，如果该值设置过大，
就有影响到创建栈的数量，如果是多线程的应用，就会出现内存溢出的错误．
JVM可创建的最大线程数限制因素:
线程堆栈大小——》进程的最大内存——》操作系统位数

## unable to create new native thread
Java 线程映射到操作系统的内核线程,所以无限创建线程时,可能会造成系统假死

## JavaMethodAreaOOM
Proxy 的名字是 $ProxyN, 其中 N 会慢慢增加

## DirectMemoryOOM
书上讲了 Unsafe 的有限使用者,但是 unsafe 的创建方式有些看不懂, 和普通的反射有差别
rt.jar 里的东西用处?
在 JDK7,8 下, 书上的例子无法产生 exception

## dt.jar tool.jar rt.jar
dt.jar和tools.jar位于：{Java_Home}/lib/下，而rt.jar位于：{Java_Home}/jre/lib/下,其中：
rt.jar是JAVA基础类库，也就是你在java doc里面看到的所有的类的class文件
dt.jar是关于运行环境的类库
tools.jar是工具类库,编译和运行需要的都是toos.jar里面的类分别是sun.tools.java.*; sun.tols.javac.*;

在Classpath设置这几个变量，是为了方便在程序中 import；Web系统都用到tool.jar。

1. rt.jar
    默认就在Root Classloader的加载路径里面的，而在Claspath配置该变量是不需要的；同时jre/lib目录下的
    其他jar:jce.jar、jsse.jar、charsets.jar、resources.jar都在Root Classloader中
2. tools.jar
    是系统用来编译一个类的时候用到的，即执行javac的时候用到
    javac XXX.java
    实际上就是运行
    java -Calsspath=%JAVA_HOME%\lib\tools.jar xx.xxx.Main XXX.java
    javac就是对上面命令的封装 所以tools.jar 也不用加到classpath里面
3. dt.jar
    是关于运行环境的类库,主要是swing的包   在用到swing时最好加上

