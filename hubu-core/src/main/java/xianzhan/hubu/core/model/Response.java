package xianzhan.hubu.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应类
 *
 * @author xianzhan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    public static final int CODE_OK = 0;

    /**
     * 响应码，0 成功
     */
    private int    code;
    /**
     * 失败时展示的信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T      data;

    public static <T> Response<T> ok(T t) {
        return new Response<>(CODE_OK, null, t);
    }
}
