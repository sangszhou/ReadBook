package questions;

/**
 * Created by xinszhou on 16/7/2.
 */
class Parent {
    static String name = "hello";

    {
        System.out.println("3 parent  block");
    }

    static {
        System.out.println("1 parent static block");
    }

    public Parent() {
        System.out.println("4 parent constructor");
    }
}

class Child extends Parent {
    static String childName = "hello";

    {
        System.out.println("5 child  block");
    }

    static {
        System.out.println("2 child static block");
    }

    public Child() {
        System.out.println("6 child constructor");
    }
}

public class StaticIniBlockOrderTest {

    public static void main(String[] args) {
        new Child();//语句(*)
    }
}