package xianzhan.hubu.service.corp.pj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 企业信息
 *
 * @author xianzhan
 * @since 2022-06-01
 */
@Data
@TableName("hb_corp")
public class CorpEntity {

    private Long    id;
    /**
     * 企业 ID
     */
    private String  corpId;
    /**
     * 企业名称
     */
    private String  corpName;
    /**
     * 逻辑删除位，0-未删除，1-已删除
     */
    private Integer isDel;
}
