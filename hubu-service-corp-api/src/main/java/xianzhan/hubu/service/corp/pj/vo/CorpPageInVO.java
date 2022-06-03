package xianzhan.hubu.service.corp.pj.vo;

import lombok.Data;

/**
 * @author xianzhan
 * @since 2022-06-01
 */
@Data
public class CorpPageInVO {

    private String  corpId;
    /**
     * 当前页
     */
    private Integer pageIndex;
    /**
     * 页大小
     */
    private Integer pageSize;
}
