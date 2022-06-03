package xianzhan.hubu.service.bill.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xianzhan.hubu.base.constant.HubuConst;
import xianzhan.hubu.base.pj.model.Response;
import xianzhan.hubu.base.util.CollectionUtil;
import xianzhan.hubu.service.bill.mapper.CarBillMapper;
import xianzhan.hubu.service.bill.pj.entity.CarBillEntity;
import xianzhan.hubu.service.bill.pj.vo.CarBillCorpPage;
import xianzhan.hubu.service.bill.pj.vo.CarBillPage;
import xianzhan.hubu.service.bill.pj.vo.CarBillPageInVO;
import xianzhan.hubu.service.bill.pj.vo.CarBillPageOutVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xianzhan
 * @since 2022-06-03
 */
@Service
public class CarBillServiceImpl extends ServiceImpl<CarBillMapper, CarBillEntity> implements ICarBillService {
    @Override
    public Response<CarBillPageOutVO> page(CarBillPageInVO inVO) {
        Page<CarBillEntity> page = new Page<>(inVO.getPageIndex(), inVO.getPageSize());
        baseMapper.selectPage(page, Wrappers.<CarBillEntity>lambdaQuery()
                .eq(CarBillEntity::getIsDel, HubuConst.IS_DEL_N)
                .in(CarBillEntity::getCorpId, inVO.getCorpIdList())
                .select(CarBillEntity::getCorpId, CarBillEntity::getSupplierCode, CarBillEntity::getOrderId)
        );

        List<CarBillCorpPage> corpBillList = CollectionUtil.newArrayList();
        List<CarBillEntity> carBillList = page.getRecords();
        carBillList.stream()
                .collect(Collectors.groupingBy(CarBillEntity::getCorpId))
                .forEach((corpId, cbList) -> {
                    CarBillCorpPage carBillCorpPage = new CarBillCorpPage();
                    carBillCorpPage.setCorpId(corpId);

                    List<CarBillPage> carBillPages = cbList.stream()
                            .map(cb -> {
                                CarBillPage carBillPage = new CarBillPage();
                                carBillPage.setSupplierCode(cb.getSupplierCode());
                                carBillPage.setOrderId(cb.getOrderId());
                                return carBillPage;
                            })
                            .toList();
                    carBillCorpPage.setBillList(carBillPages);
                });

        CarBillPageOutVO outVO = new CarBillPageOutVO();
        outVO.setCorpBillList(corpBillList);
        return Response.ok(outVO);
    }
}
