package pers.jz.javase.reflection;

import java.util.Map;

/**
 * @author Jemmy Zhang on 2019/2/7.
 */
public class Student {
    private String uuid;
    private String name;
    private Integer sex;
    private Integer age;
    private Map<String, String> properties;

    public static final Integer MALE = 0;
    public static final Integer FEMALE = 1;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public boolean isMale() {
        return MALE.equals(sex);
    }
}
