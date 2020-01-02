package pers.jz.netty.example.restaurant.common.order;

import lombok.Data;
import pers.jz.netty.example.restaurant.common.OperationResult;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */
@Data
public class OrderOperationResult extends OperationResult {
    private int tableId;
    private String dish;
    private boolean complete;
}
