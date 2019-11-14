package pers.jz.javase.designpattern.decorator.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
@Data
public class OrderDetail {
    private int id;
    private int orderId;
    private Merchandise merchandise;
    private BigDecimal payMoney;
}
