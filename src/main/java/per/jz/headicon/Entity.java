package per.jz.headicon;

import java.io.Serializable;

/**
 * @author jemmyzhang on 2018/1/10.
 */
public class Entity implements Serializable{

    private static final long serialVersionUID = -2344438523186505173L;

    private String name;

    private String avatar;

    private Data data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
