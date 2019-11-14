package pers.jz.javase.designpattern.decorator.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
@Data
public class UserRedPacket {
    private int id;
    private int userId;
    private String sku;
    private BigDecimal packetMoney;
}
