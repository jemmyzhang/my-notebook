package pers.jz.javase.designpattern.decorator;

import pers.jz.javase.designpattern.decorator.entity.OrderDetail;
import pers.jz.javase.designpattern.decorator.entity.PromotionType;
import pers.jz.javase.designpattern.decorator.entity.SupportPromotions;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
public class PromotionFactory {

    public static BigDecimal countPayMoney(OrderDetail orderDetail) {

        Map<PromotionType, SupportPromotions> supportPromotions = orderDetail.getMerchandise().getSupportPromotions();

        IBaseCount baseCount = new BaseCount();
        if (Objects.nonNull(supportPromotions)) {
            for (PromotionType promotionType : supportPromotions.keySet()) {
                baseCount = promotion(supportPromotions.get(promotionType), baseCount);
            }
        }
        return baseCount.countPayMoney(orderDetail);
    }

    private static IBaseCount promotion(SupportPromotions supportPromotions, IBaseCount baseCount) {
        if (Objects.equals(supportPromotions.getPromotionType(), PromotionType.COUPON)) {
            baseCount = new CouponDecorator(baseCount);
        } else if (Objects.equals(supportPromotions.getPromotionType(), PromotionType.RED_PACKET)) {
            baseCount = new RedPackageDecorator(baseCount);
        }
        return baseCount;
    }
}
