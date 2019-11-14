package pers.jz.javase.designpattern.decorator;

import org.springframework.core.annotation.Order;
import pers.jz.javase.designpattern.decorator.IBaseCount;
import pers.jz.javase.designpattern.decorator.entity.OrderDetail;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
public abstract class BaseCountDecorator implements IBaseCount {

    private IBaseCount count;

    public BaseCountDecorator(IBaseCount count){
        this.count = count;
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        if(Objects.nonNull(count)){
            payTotalMoney = count.countPayMoney(orderDetail);
        }
        return payTotalMoney;
    }
}
