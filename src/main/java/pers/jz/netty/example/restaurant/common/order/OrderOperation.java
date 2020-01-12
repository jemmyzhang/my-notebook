package pers.jz.netty.example.restaurant.common.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import pers.jz.netty.example.restaurant.common.Operation;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */

@Data
@AllArgsConstructor
public class OrderOperation extends Operation {
    private int tableId;
    private String dish;


    @Override
    public OrderOperationResult execute() {
        System.out.println("order's executing startup with orderRequest: " + toString());
        System.out.println("order's executing complete");
        OrderOperationResult orderResponse = new OrderOperationResult();
        orderResponse.setTableId(tableId);
        orderResponse.setDish(dish);
        orderResponse.setComplete(true);
        return orderResponse;
    }
}
