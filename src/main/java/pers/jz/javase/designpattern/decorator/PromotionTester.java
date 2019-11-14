package pers.jz.javase.designpattern.decorator;

import org.assertj.core.util.Lists;
import pers.jz.javase.designpattern.decorator.entity.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
public class PromotionTester {
    public static void main(String[] args) {
        Order order = new Order();
        init(order);

        for (OrderDetail orderDetail : order.getList()) {
            BigDecimal payMoney = PromotionFactory.countPayMoney(orderDetail);
            orderDetail.setPayMoney(payMoney);
            System.out.println("最终支付金额：" + orderDetail.getPayMoney());
        }
    }

    private static void init(Order order) {
        OrderDetail orderDetail = new OrderDetail();
        Merchandise merchandise = new Merchandise();
        merchandise.setName("T-shirt");
        merchandise.setPrice(BigDecimal.valueOf(100));

        SupportPromotions redPackage = new SupportPromotions();
        redPackage.setPromotionType(PromotionType.RED_PACKET);
        UserRedPacket userRedPacket = new UserRedPacket();
        userRedPacket.setPacketMoney(BigDecimal.valueOf(10));
        redPackage.setUserRedPacket(userRedPacket);

        SupportPromotions coupon = new SupportPromotions();
        coupon.setPromotionType(PromotionType.COUPON);
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setCouponMoney(BigDecimal.valueOf(20));
        coupon.setUserCoupon(userCoupon);
        Map<PromotionType, SupportPromotions> map = new HashMap<>();

        map.put(redPackage.getPromotionType(), redPackage);
        map.put(coupon.getPromotionType(), coupon);

        merchandise.setSupportPromotions(map);

        orderDetail.setMerchandise(merchandise);

        List<OrderDetail> list = Lists.newArrayList(orderDetail);

        order.setList(list);

    }
}
