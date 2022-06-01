package xianzhan.hubu.base.pj.model;

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

    public static final int CODE_OK  = 0;
    public static final int CODE_ERR = -1;

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

    /**
     * @return 该响应是否成功
     * @apiNote 此处不用 isOk 是因为个别框架会序列化成 ok 字段
     */
    public boolean areOk() {
        return CODE_OK == code;
    }

    public static <T> Response<T> ok() {
        return new Response<>(CODE_OK, null, null);
    }

    public static <T> Response<T> ok(T t) {
        return new Response<>(CODE_OK, null, t);
    }

    public static <T> Response<T> err(String message) {
        return new Response<>(CODE_ERR, message, null);
    }
}
