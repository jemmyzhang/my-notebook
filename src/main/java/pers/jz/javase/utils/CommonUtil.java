package pers.jz.javase.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;

/**
 * @author Jemmy Zhang on 2019/2/8.
 */
public class CommonUtil {

    public static void stdout(){
        System.out.println();
    }

    public static void stdout(Object obj) {
        stdout("", obj);
    }

    public static void stdout(String prefix, Object obj) {
        if (StringUtils.isNotEmpty(prefix)) {
            System.out.print(prefix);
        }
        stdout(obj, false);
    }

    private static void stdout(Object obj, boolean isArrayItem) {
        if (obj != null && obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            System.out.print("[ ");
            for (int i = 0; i < length; i++) {
                stdout(Array.get(obj, i), true);
                if (i != length - 1) {
                    stdout(", ", true);
                }
            }
            System.out.println(" ]");
        } else {
            if (isArrayItem) {
                System.out.print(obj);
            } else {
                System.out.println(obj);
            }
        }
    }
}
