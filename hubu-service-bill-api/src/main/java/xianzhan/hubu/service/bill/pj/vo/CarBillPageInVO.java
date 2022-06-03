package xianzhan.hubu.service.bill.pj.vo;

import lombok.Data;

import java.util.List;

/**
 * @author xianzhan
 * @since 2022-06-03
 */
@Data
public class CarBillPageInVO {

    private List<String> corpIdList;
    private Integer      pageIndex;
    private Integer      pageSize;
}
