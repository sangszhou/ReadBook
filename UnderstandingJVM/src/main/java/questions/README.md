###ClassLoader1

这个类的结果比较难以理解,和平时看到的先 static, 再 non-static block, constructor 的顺序不同, 这里是先
static 再 non-static

我想知道在 ClassLoader1 的 cinit 到底都有些什么, 目前我觉得可能是这样
cinit 的概念是, 编译器把类变量的赋值和 static 语句块组合到一起

1) ClassLoader1 singleton = new ClassLoader1

2) {
    System.out.println("non static block");
}

3) static {
    System.out.println("static block");
}

这样想也不合理, 和结果不同

那么,就可能是 new ClassLoader1 引发类的创建, 分别执行 non-static block 和
constructor, 因为 1)执行完以后再执行 3)
类变量的赋值到底是啥, 非 static 修饰的变量赋值



