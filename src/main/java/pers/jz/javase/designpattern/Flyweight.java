package pers.jz.javase.designpattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jemmy Zhang on 2019/2/10.
 */
public class Flyweight {
    static class FlyweightFactory {
        private static Map<String, AbstractFlyweight> flyweights = new HashMap<>();

        public static synchronized AbstractFlyweight getFlyweight(String key){
            if(!flyweights.containsKey(key)){
                AbstractFlyweight flyweight=new ConcreteFlyweight(key);
                flyweights.put(key,flyweight);
                System.out.println("创建一个新的Flyweight.");
                return flyweight;
            }else {
                return flyweights.get(key);
            }
        }
    }

    static class UnsharedConcreteFlyweight {
        private String info;

        public UnsharedConcreteFlyweight(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    interface AbstractFlyweight {
        void operation(UnsharedConcreteFlyweight unsharedConcreteFlyweight);
    }

    static class ConcreteFlyweight implements AbstractFlyweight {

        private String key;

        public ConcreteFlyweight(String key) {
            this.key = key;
        }

        @Override
        public void operation(UnsharedConcreteFlyweight unsharedConcreteFlyweight) {
            System.out.println(key+": " + unsharedConcreteFlyweight.getInfo());
        }
    }

    public static void main(String[] args) {
        AbstractFlyweight flyweight = FlyweightFactory.getFlyweight("Hello");
        flyweight.operation(new UnsharedConcreteFlyweight("Ted"));
        flyweight.operation(new UnsharedConcreteFlyweight("Fly"));
        flyweight = FlyweightFactory.getFlyweight("Hello");
        flyweight.operation(new UnsharedConcreteFlyweight("Sky"));

    }


}

