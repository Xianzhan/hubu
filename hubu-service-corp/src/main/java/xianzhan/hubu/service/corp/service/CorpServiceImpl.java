package xianzhan.hubu.service.corp.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xianzhan.hubu.base.constant.HubuConst;
import xianzhan.hubu.base.pj.model.Response;
import xianzhan.hubu.base.util.StringUtil;
import xianzhan.hubu.service.corp.mapper.CorpMapper;
import xianzhan.hubu.service.corp.pj.entity.CorpEntity;
import xianzhan.hubu.service.corp.pj.vo.CorpPage;
import xianzhan.hubu.service.corp.pj.vo.CorpPageInVO;
import xianzhan.hubu.service.corp.pj.vo.CorpPageOutVO;

import java.util.List;

/**
 * @author xianzhan
 * @since 2022-06-01
 */
@Service
public class CorpServiceImpl extends ServiceImpl<CorpMapper, CorpEntity> implements ICorpService {

    @Override
    public Response<CorpPageOutVO> page(CorpPageInVO inVO) {
        Page<CorpEntity> page = new Page<>(inVO.getPageIndex(), inVO.getPageSize());
        page(page, Wrappers.<CorpEntity>lambdaQuery()
                .eq(StringUtil.isNotEmpty(inVO.getCorpId()), CorpEntity::getCorpId, inVO.getCorpId())
                .eq(CorpEntity::getIsDel, HubuConst.IS_DEL_N)
        );
        List<CorpPage> corpPages = page.getRecords().stream()
                .map(c -> {
                    CorpPage corpPage = new CorpPage();
                    corpPage.setCorpId(c.getCorpId());
                    corpPage.setCorpName(c.getCorpName());
                    return corpPage;
                })
                .toList();
        CorpPageOutVO outVO = new CorpPageOutVO();
        outVO.setPage(corpPages);
        return Response.ok(outVO);
    }
}
