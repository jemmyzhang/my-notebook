package pers.jz.totp;

import com.google.common.base.Preconditions;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Objects;

/**
 * 基于TOTP算法实现的Server端动态令牌
 *
 * @author Jemmy Zhang on 2019/5/31.
 */
public class TOTPAuthenticator {


    public final int SECONDS_IN_MILLS = 1000;

    public final int CODE_STRING_LENGTH = 6;

    private String base64SecretKey;

    private int refreshFrequency;

    /**
     * 构造函数
     *
     * @param base64SecretKey  基于Base64加密的秘钥（应用标识）
     * @param refreshFrequency 刷新频率（秒）
     */
    public TOTPAuthenticator(String base64SecretKey, int refreshFrequency) {
        this.base64SecretKey = base64SecretKey;
        this.refreshFrequency = refreshFrequency;
    }

    public String generateDynamicCode() {
        return generateDynamicCode(base64SecretKey, refreshFrequency);
    }

    private String generateDynamicCode(String secretKey, int refreshFrequency) {
        long baseTime = calculateBaseTime(refreshFrequency);
        byte[] hmac = calculateHmac(secretKey, baseTime);
        return calculateDynamicCode(hmac);
    }

    /**
     * 计算6位动态码
     * 1、经过sha1计算的hmac有20字节，160位。
     * 2、取最后一个字节中的后4位作为索引，索引（index）范围为0-15（16进制能表达的位置）
     * 3、hmac[index],hmac[index+1],hmac[index+2]，hmac[index+3]这4个byte正好能组成一个int。
     * 4、(index,index+4)能表示的范围从(0,3)~(15,18)，能覆盖剩余19字节。
     * 5、将整形的动态码转换为长度为CODE_STRING_LENGTH字符串，如果长了就截取最后6位，端了就在末尾补0。
     *
     * @param hmac 经过sha1计算的hmac
     * @return 6位动态码
     */
    private String calculateDynamicCode(byte[] hmac) {
        Preconditions.checkArgument(Objects.nonNull(hmac) && hmac.length == 20);
        int index = hmac[hmac.length - 1] & 0xF;
        int intCode = hashToInt(hmac, index);
        return completionCode(String.valueOf(intCode));
    }

    /**
     * 填充动态码。
     * 如果code长度大于CODE_STRING_LENGTH，就截取最后CODE_STRING_LENGTH位；如果长度小于CODE_STRING_LENGTH，就在末尾补0.
     *
     * @param code
     * @return
     */
    private String completionCode(String code) {
        Preconditions.checkNotNull(code);
        StringBuilder codeBuilder = new StringBuilder();
        if (code.length() < CODE_STRING_LENGTH) {
            codeBuilder.append(code);
            for (int i = code.length(); i < CODE_STRING_LENGTH; i++) {
                codeBuilder.append(0);
            }
        } else {
            codeBuilder.append(code, code.length() - CODE_STRING_LENGTH, code.length());
        }
        return codeBuilder.toString();
    }

    private int hashToInt(byte[] hash, int index) {
        return ((hash[index] & 0X7F) << 24) | ((hash[index + 1] & 0XFF) << 16) | ((hash[index + 2] & 0XFF) << 8) | (hash[index + 3] & 0XFF);
    }

    /**
     * 根据刷新频率计算基准时间。
     * 例如：比如现在是12:01:00,刷新频率60秒，那12:01:00-12:01:59内算出来的基准时间都是相同的，都是12:01:00。
     *
     * @param refreshFrequency 刷新频率（秒）
     * @return 基准时间戳
     */
    private long calculateBaseTime(int refreshFrequency) {
        long internalInMillis = (refreshFrequency * SECONDS_IN_MILLS);
        long nowTimeInMillis = Calendar.getInstance().getTimeInMillis();
        return nowTimeInMillis / internalInMillis * internalInMillis;
    }

    /**
     * 计算HMAC，利用秘钥和时戳作为两个计算因子
     * HMAC的基本算法为：hmac = sha1(secretKey+sha1(secretKey+timestamp))
     *
     * @param secretKey         秘钥
     * @param timeValidInMillis 时戳
     * @return
     */
    private byte[] calculateHmac(String secretKey, long timeValidInMillis) {
        return DigestUtils.sha1(buildString(secretKey, DigestUtils.sha1Hex(buildString(secretKey, timeValidInMillis))));
    }

    private <T> byte[] buildString(String originString, T appendObj) {
        StringBuilder builder = new StringBuilder(originString);
        return builder.append(appendObj).toString().getBytes(Charset.forName("UTF-8"));
    }

    public static void main(String[] args) {
        TOTPAuthenticator authenticator = new TOTPAuthenticator("helloWorld", 30);
        while (true) {
            try {
                Thread.sleep(1000L);
                System.out.println(authenticator.generateDynamicCode());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
