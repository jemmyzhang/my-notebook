package pers.jz.javase.reflection;

import java.lang.reflect.Field;

import static pers.jz.javase.utils.CommonUtil.stdout;

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


    public static void main(String[] args) {
        //findClasses();
        classMethods();

    }


}

