package pers.jz.javase.proxy;

import java.util.Date;

/**
 * @author Jemmy Zhang on 2019/12/15.
 */
public class JobImpl implements Job {
    @Override
    public String getName() {
        return "JobImpl";
    }

    @Override
    public int handle(Date date) {
        System.out.println("Handle job action. Execute time: " + date);
        return 0;
    }
}
