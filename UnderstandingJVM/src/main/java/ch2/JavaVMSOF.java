package ch2;

/**
 * Created by xinszhou on 16/5/27.
 */

/**
 * -Xss128k  stack space size
 */
public class JavaVMSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength ++;
        stackLeak();
    }

    public static void main(String args[]) {
        JavaVMSOF sof = new JavaVMSOF();
        try {
            sof.stackLeak();
        } catch (Throwable e) {
            System.out.println("Stack length: " + sof.stackLength);

            e.printStackTrace();
        }
    }

}
