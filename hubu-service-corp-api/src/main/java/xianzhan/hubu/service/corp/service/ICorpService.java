package xianzhan.hubu.service.corp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xianzhan.hubu.base.pj.model.Response;
import xianzhan.hubu.service.corp.pj.entity.CorpEntity;
import xianzhan.hubu.service.corp.pj.vo.CorpPageInVO;
import xianzhan.hubu.service.corp.pj.vo.CorpPageOutVO;

/**
 * 企业服务
 *
 * @author xianzhan
 * @since 2022-06-01
 */
public interface ICorpService extends IService<CorpEntity> {

    /**
     * 分页查询企业
     *
     * @param inVO 参数
     * @return 企业信息
     */
    Response<CorpPageOutVO> page(CorpPageInVO inVO);
}
