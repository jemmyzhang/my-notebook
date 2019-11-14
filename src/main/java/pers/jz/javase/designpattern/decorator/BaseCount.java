package pers.jz.javase.designpattern.decorator;

import pers.jz.javase.designpattern.decorator.entity.OrderDetail;

import java.math.BigDecimal;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
public class BaseCount implements IBaseCount {

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        orderDetail.setPayMoney(orderDetail.getMerchandise().getPrice());
        System.out.println("原商品价格为" + orderDetail.getPayMoney());
        return orderDetail.getPayMoney();
    }

}
