package pers.jz.javase.designpattern.decorator;

import pers.jz.javase.designpattern.decorator.entity.OrderDetail;

import java.math.BigDecimal;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
public interface IBaseCount {

    BigDecimal countPayMoney(OrderDetail orderDetail);

}
