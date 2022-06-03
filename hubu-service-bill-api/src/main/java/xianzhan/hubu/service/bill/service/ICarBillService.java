package xianzhan.hubu.service.bill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xianzhan.hubu.base.pj.model.Response;
import xianzhan.hubu.service.bill.pj.entity.CarBillEntity;
import xianzhan.hubu.service.bill.pj.vo.CarBillPageInVO;
import xianzhan.hubu.service.bill.pj.vo.CarBillPageOutVO;

/**
 * @author xianzhan
 * @since 2022-06-03
 */
public interface ICarBillService extends IService<CarBillEntity> {

    /**
     * 根据企业分页查询用车账单
     *
     * @param inVO 查询参数
     * @return 企业用车账单
     */
    Response<CarBillPageOutVO> page(CarBillPageInVO inVO);
}
