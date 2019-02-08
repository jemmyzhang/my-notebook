package pers.jz.javase.designpattern;

import java.io.*;

/**
 * 原型模式
 * @author Jemmy Zhang on 2019/2/8.
 */
public class Prototype {

    static class User implements Serializable {

        private static final long serialVersionUID = -1427201771194623655L;
        private String userName = "Anonymous";
        private Integer age = 0;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    interface ProtoInterface {
        void action();
    }

    static class CustomType implements ProtoInterface, Cloneable, Serializable {

        private static final long serialVersionUID = -887498875327179441L;
        private boolean deepCopy = false;
        private User user = new User();


        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public CustomType() {
            System.out.println("具体原型创建成功");
        }

        public CustomType(boolean deepCopy) {
            this.deepCopy = deepCopy;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            if (deepCopy) {
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(this);
                    oos.flush();
                    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
                    return ois.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return super.clone();
        }

        @Override
        public void action() {
            System.out.println("Do some action.");
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CustomType shallow = new CustomType();
        CustomType shallowCopy = (CustomType) shallow.clone();
        shallowCopy.getUser().setAge(20);
        System.out.println(shallow.getUser().getAge());
        shallowCopy.action();

        CustomType deep = new CustomType(true);
        CustomType deepCopy = (CustomType) deep.clone();
        deepCopy.getUser().setAge(20);
        System.out.println(deep.getUser().getAge());
        deepCopy.action();
    }
}
