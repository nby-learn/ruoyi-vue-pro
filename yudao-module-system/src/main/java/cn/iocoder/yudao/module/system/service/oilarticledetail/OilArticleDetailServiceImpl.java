package cn.iocoder.yudao.module.system.service.oilarticledetail;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.oilarticledetail.vo.OilArticleDetailPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.oilarticledetail.vo.OilArticleDetailSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticledetail.OilArticleDetailDO;
import cn.iocoder.yudao.module.system.dal.mysql.oilarticledetail.OilArticleDetailMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.infra.enums.ErrorCodeConstants.OIL_ARTICLE_DETAIL_NOT_EXISTS;

/**
 * 文章详情 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class OilArticleDetailServiceImpl implements OilArticleDetailService {

    @Resource
    private OilArticleDetailMapper oilArticleDetailMapper;

    @Override
    public Integer createOilArticleDetail(OilArticleDetailSaveReqVO createReqVO) {
        // 插入
        OilArticleDetailDO oilArticleDetail = BeanUtils.toBean(createReqVO, OilArticleDetailDO.class);
        oilArticleDetailMapper.insert(oilArticleDetail);
        // 返回
        return oilArticleDetail.getId();
    }

    @Override
    public void updateOilArticleDetail(OilArticleDetailSaveReqVO updateReqVO) {
        // 校验存在
        validateOilArticleDetailExists(updateReqVO.getId());
        // 更新
        OilArticleDetailDO updateObj = BeanUtils.toBean(updateReqVO, OilArticleDetailDO.class);
        oilArticleDetailMapper.updateById(updateObj);
    }

    @Override
    public void deleteOilArticleDetail(Integer id) {
        // 校验存在
        validateOilArticleDetailExists(id);
        // 删除
        oilArticleDetailMapper.deleteById(id);
    }

    @Override
        public void deleteOilArticleDetailListByIds(List<Integer> ids) {
        // 校验存在
        validateOilArticleDetailExists(ids);
        // 删除
        oilArticleDetailMapper.deleteByIds(ids);
        }

    private void validateOilArticleDetailExists(List<Integer> ids) {
        List<OilArticleDetailDO> list = oilArticleDetailMapper.selectByIds(ids);
        if (CollUtil.isEmpty(list) || list.size() != ids.size()) {
            throw exception(OIL_ARTICLE_DETAIL_NOT_EXISTS);
        }
    }

    private void validateOilArticleDetailExists(Integer id) {
        if (oilArticleDetailMapper.selectById(id) == null) {
            throw exception(OIL_ARTICLE_DETAIL_NOT_EXISTS);
        }
    }

    @Override
    public OilArticleDetailDO getOilArticleDetail(Integer id) {
        return oilArticleDetailMapper.selectById(id);
    }

    @Override
    public PageResult<OilArticleDetailDO> getOilArticleDetailPage(OilArticleDetailPageReqVO pageReqVO) {
        return oilArticleDetailMapper.selectPage(pageReqVO);
    }

}