package pers.jz.web.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jemmy Zhang on 2019/6/6.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestData<T> {
    private T data;
}
