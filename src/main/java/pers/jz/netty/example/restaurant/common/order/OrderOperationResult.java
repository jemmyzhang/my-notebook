package pers.jz.netty.example.restaurant.common.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import pers.jz.netty.example.restaurant.common.AbstractOperationResult;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */
@Data
public class OrderOperationResult extends AbstractOperationResult {
    private int tableId;
    private String dish;
    private boolean complete;
}
