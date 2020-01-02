package pers.jz.netty.example.restaurant.common.auth;

import lombok.Data;
import pers.jz.netty.example.restaurant.common.OperationResult;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */
@Data
public class AuthOperationResult extends OperationResult {
    private final boolean passAuth;
}
