package xianzhan.hubu.service.bill.pj.entity;

import lombok.Data;

/**
 * @author xianzhan
 * @since 2022-05-30
 */
@Data
public class CarBillEntity {

    private Long    id;
    private String  corpId;
    private String  supplierCode;
    private String  orderId;
    private Integer isDel;
}
