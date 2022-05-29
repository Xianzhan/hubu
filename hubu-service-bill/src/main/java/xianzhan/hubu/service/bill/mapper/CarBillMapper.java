package xianzhan.hubu.service.bill.mapper;

import xianzhan.hubu.service.bill.pj.entity.CarBillEntity;

/**
 * @author xianzhan
 * @since 2022-05-30
 */
public interface CarBillMapper {

    int insert(CarBillEntity entity);

    CarBillEntity selectById(long id);

    int deleteById(long id);
}
