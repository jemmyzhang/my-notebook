package pers.jz.javase.designpattern.decorator;

import pers.jz.javase.designpattern.decorator.entity.OrderDetail;
import pers.jz.javase.designpattern.decorator.entity.PromotionType;

import java.math.BigDecimal;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
public class CouponDecorator extends BaseCountDecorator {

    public CouponDecorator(IBaseCount count) {
        super(count);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        super.countPayMoney(orderDetail);
        BigDecimal payTotal = countCouponPayMoney(orderDetail);
        return payTotal;
    }


    private BigDecimal countCouponPayMoney(OrderDetail orderDetail) {
        BigDecimal coupon = orderDetail.getMerchandise().getSupportPromotions().get(PromotionType.COUPON).getUserCoupon().getCouponMoney();
        System.out.println("优惠券金额：" + coupon);
        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(coupon));
        return orderDetail.getPayMoney();
    }
}
