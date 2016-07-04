package ch2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinszhou on 16/5/27.
 */

/**
 * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOom {

    static class OOMObject {}

    public static void main(String args[]) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while(true) {
            list.add(new OOMObject());
        }
    }
}
