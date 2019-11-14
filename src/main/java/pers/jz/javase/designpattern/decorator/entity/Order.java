package pers.jz.javase.designpattern.decorator.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
@Data
public class Order {
    private int id;
    private String orderNo;
    private BigDecimal totalPayMoney;
    private List<OrderDetail> list;
}
