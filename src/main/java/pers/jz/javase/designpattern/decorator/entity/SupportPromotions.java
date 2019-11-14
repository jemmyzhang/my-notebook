package pers.jz.javase.designpattern.decorator.entity;

import lombok.Data;

/**
 * @author Jemmy Zhang on 2019/11/14.
 */
@Data
public class SupportPromotions implements Cloneable{
    private int id;
    private PromotionType promotionType;
    private int priority;
    private UserCoupon userCoupon;
    private UserRedPacket userRedPacket;

    public SupportPromotions clone(){
        SupportPromotions supportPromotions = null;
        try {
            supportPromotions = (SupportPromotions) super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return supportPromotions;
    }
}
