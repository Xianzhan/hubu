package xianzhan.hubu.service.bill.pj.vo;

import lombok.Data;

import java.util.List;

/**
 * @author xianzhan
 * @since 2022-06-03
 */
@Data
public class CarBillCorpPage {

    private String            corpId;
    private List<CarBillPage> billList;
}
