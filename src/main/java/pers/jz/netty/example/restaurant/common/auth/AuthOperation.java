package pers.jz.netty.example.restaurant.common.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import pers.jz.netty.example.restaurant.common.AbstractOperation;
import pers.jz.netty.example.restaurant.common.AbstractOperationResult;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */

@Data
@AllArgsConstructor
public class AuthOperation extends AbstractOperation {
    private String username;
    private String password;

    @Override
    public AbstractOperationResult execute() {
        if ("admin".equalsIgnoreCase(this.username) && "admin".equalsIgnoreCase(this.password)) {
            return new AuthOperationResult(true);
        }
        return new AuthOperationResult(false);
    }
}
