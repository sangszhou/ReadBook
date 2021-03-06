package ch2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by xinszhou on 16/5/28.
 */

/**
 * -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String [] args) throws Exception {
        // get constructor
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);

        while (true) {
            unsafe.allocateMemory(_1MB);
        }

    }
}
