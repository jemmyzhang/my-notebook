package pers.jz.netty.example.restaurant.common.auth;

import lombok.Data;
import pers.jz.netty.example.restaurant.common.AbstractOperationResult;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */
@Data
public class AuthOperationResult extends AbstractOperationResult {
    private final boolean passAuth;
}
