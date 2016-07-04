package questions;

/**
 * Created by xinszhou on 16/7/2.
 */
public class ClassLoader1 {

    private static ClassLoader1 singleTon = new ClassLoader1();

    public static int count1;
    public static int count2 = 0;

    {
        System.out.println("non static block");
    }

    static {
        System.out.println("static block");
    }

    public ClassLoader1() {
        System.out.println("constructor block");
        count1++;
        count2++;
    }

    public static ClassLoader1 getInstance() {
        return singleTon;
    }
}

class Test {
    public static void main(String[] args) {
        ClassLoader1 singleTon = ClassLoader1.getInstance();
//        ClassLoader1 singleTon = new ClassLoader1();
        System.out.println("count1=" + singleTon.count1);
        System.out.println("count2=" + singleTon.count2);
    }

}
