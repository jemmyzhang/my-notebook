package pers.jz.javase.designpattern.decorator.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
@Data
public class Merchandise {
    private String sku;
    private String name;
    private BigDecimal price;
    private Map<PromotionType, SupportPromotions> supportPromotions;
}
