package pers.jz.javase.designpattern.decorator;

import pers.jz.javase.designpattern.decorator.entity.OrderDetail;
import pers.jz.javase.designpattern.decorator.entity.PromotionType;

import java.math.BigDecimal;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
public class RedPackageDecorator extends BaseCountDecorator {
    public RedPackageDecorator(IBaseCount count) {
        super(count);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        super.countPayMoney(orderDetail);
        BigDecimal totalPay = countRedPackageMoney(orderDetail);
        return totalPay;
    }

    public BigDecimal countRedPackageMoney(OrderDetail orderDetail) {
        BigDecimal redPacketMoney = orderDetail.getMerchandise().getSupportPromotions().get(PromotionType.RED_PACKET).getUserRedPacket().getPacketMoney();
        System.out.println("红包优惠金额：" + redPacketMoney);
        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(redPacketMoney));
        return redPacketMoney;
    }
}
