package ch2;

/**
 * Created by xinszhou on 16/5/27.
 */
public class CreateInfiniteThread {
    private void dontStop() {
        while(true) {}
    }

    public void createInfiniteThread() {
        while(true) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String args[]) {
        CreateInfiniteThread cit = new CreateInfiniteThread();
        cit.createInfiniteThread();
    }

}
