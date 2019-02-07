package pers.jz.javase.reflection;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * @author Jemmy Zhang on 2019/2/7.
 */
public class ClassSamples {

    enum Score {
        GOOD, NORMAL, BAD
    }

    /**
     * 获取Class对象
     */
    public static void findClasses() {
        stdout(String.class);
        stdout(Long.class);
        stdout(Integer.class);
        stdout(Double.class);

        stdout(int.class);
        stdout(long.class);
        stdout(double.class);
        Class<Double> doubleClass = double.class;
        stdout(doubleClass);
        stdout(void.class);

        String[] stringArray = new String[10];
        stdout(stringArray.getClass());
        int[] oneDimArray = new int[3];
        stdout(oneDimArray.getClass());
        int[][] twoDimArray = new int[2][3];
        stdout(twoDimArray.getClass());

        stdout(Score.GOOD.getClass());
        stdout(Score.class);
    }

    public static void classMethods() {
        Class<String> stringClass = String.class;
        stdout(stringClass.getName());
        stdout(stringClass.getSimpleName());
        stdout(stringClass.getCanonicalName());
        stdout(stringClass.getPackage());
        stdout();

        int[][] twoDimArray = new int[2][3];
        Class<? extends int[][]> twoDimClass = twoDimArray.getClass();
        stdout(twoDimClass.getName());
        stdout(twoDimClass.getSimpleName());
        stdout(twoDimClass.getCanonicalName());
        stdout(twoDimClass.getPackage());
        stdout();

        Student student = new Student();
        Class<? extends Student> studentClass = student.getClass();
        Field[] fields = studentClass.getFields();
        stdout("Fields: ", fields);
        Field[] declaredFields = studentClass.getDeclaredFields();
        stdout("Declared fields: ", declaredFields);
        stdout();

        //baseId是从接口里继承下来的。
        try {
            stdout("Get parent field [baseId]: ", studentClass.getField("baseId"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //私有属性
        try {
            stdout("Get declared field [name]: ", studentClass.getDeclaredField("name"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }

    public static void initClasses() {

    }

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

    public static void main(String[] args) {
        //findClasses();
        classMethods();

    }


}

