package pers.jz.totp;

import javax.sound.midi.Soundbank;
import java.lang.reflect.GenericArrayType;
import java.util.Calendar;
import java.util.Date;

/**
 * 基于TOTP算法实现的Server端动态令牌
 *
 * @author Jemmy Zhang on 2019/5/31.
 */
public class TOTPAuthenticator {

    private String secretKey;

    private int internalSeconds;

    private final int SECONDS_IN_MILLS = 1000;

    public TOTPAuthenticator(String secretKey, int internalSeconds) {
        this.secretKey = secretKey;
        this.internalSeconds = internalSeconds;
    }

    public String generateDynamicCode() {
        return generateDynamicCode(secretKey, Calendar.getInstance().getTimeInMillis(), internalSeconds);
    }

    private String generateDynamicCode(String secretKey, long currentTimeInMills, int internalSeconds) {
        long timeValid = (internalSeconds * SECONDS_IN_MILLS);
        //System.out.println(Long.toBinaryString(timeValid));
        long timeMask = 0;
        long flag = timeValid;
        while (flag != 0L) {
            timeMask = timeMask << 1 | 1;
            flag = flag >> 1;
        }
        System.out.println(~timeMask);
        System.out.println(Long.toBinaryString(timeValid));
        System.out.println(Long.toBinaryString(currentTimeInMills & ~timeMask));
        currentTimeInMills = ((currentTimeInMills & ~timeMask) | timeValid);
        System.out.println(Long.toBinaryString(currentTimeInMills));
        System.out.println("Standard time1: " + " " + new Date((currentTimeInMills & ~timeMask)));
        System.out.println("Standard time2: " + " " + new Date(currentTimeInMills));
        System.out.println(currentTimeInMills + " " + new Date());
        return null;
    }

    public static void main(String[] args) {
        TOTPAuthenticator authenticator = new TOTPAuthenticator("hello", 30);
        while (true) {
            try {
                Thread.sleep(1000L);
                authenticator.generateDynamicCode();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
