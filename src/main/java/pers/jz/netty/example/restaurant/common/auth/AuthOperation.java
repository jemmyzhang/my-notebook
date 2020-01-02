package pers.jz.netty.example.restaurant.common.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import pers.jz.netty.example.restaurant.common.Operation;
import pers.jz.netty.example.restaurant.common.OperationResult;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */

@Data
@AllArgsConstructor
public class AuthOperation extends Operation {
    private String username;
    private String password;

    @Override
    public OperationResult execute() {
        if ("admin".equalsIgnoreCase(this.username) && "admin".equalsIgnoreCase(this.password)) {
            return new AuthOperationResult(true);
        }
        return new AuthOperationResult(false);
    }
}
