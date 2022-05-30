package xianzhan.hubu.service.bill.pj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用车账单实体类
 *
 * @author xianzhan
 * @since 2022-05-30
 */
@Data
@TableName("hb_car_bill")
public class CarBillEntity {

    private Long    id;
    /**
     * 企业 ID
     */
    private String  corpId;
    /**
     * 供应商 CODE
     */
    private String  supplierCode;
    /**
     * 订单 ID
     */
    private String  orderId;
    /**
     * 逻辑删除位，0-未删除，1-已删除
     */
    private Integer isDel;
}
