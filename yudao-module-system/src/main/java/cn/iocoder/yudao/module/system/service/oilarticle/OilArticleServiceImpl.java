package cn.iocoder.yudao.module.system.service.oilarticle;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticlePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticleSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticle.OilArticleDO;
import cn.iocoder.yudao.module.system.dal.mysql.oilarticle.OilArticleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.infra.enums.ErrorCodeConstants.OIL_ARTICLE_NOT_EXISTS;

/**
 * 文章 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OilArticleServiceImpl implements OilArticleService {

    @Resource
    private OilArticleMapper oilArticleMapper;

    @Override
    public Integer createOilArticle(OilArticleSaveReqVO createReqVO) {
        // 插入
        OilArticleDO oilArticle = BeanUtils.toBean(createReqVO, OilArticleDO.class);
        oilArticleMapper.insert(oilArticle);
        // 返回
        return oilArticle.getId();
    }

    @Override
    public void updateOilArticle(OilArticleSaveReqVO updateReqVO) {
        // 校验存在
        validateOilArticleExists(updateReqVO.getId());
        // 更新
        OilArticleDO updateObj = BeanUtils.toBean(updateReqVO, OilArticleDO.class);
        oilArticleMapper.updateById(updateObj);
    }

    @Override
    public void deleteOilArticle(Integer id) {
        // 校验存在
        validateOilArticleExists(id);
        // 删除
        oilArticleMapper.deleteById(id);
    }

    @Override
        public void deleteOilArticleListByIds(List<Integer> ids) {
        // 校验存在
        validateOilArticleExists(ids);
        // 删除
        oilArticleMapper.deleteByIds(ids);
        }

    private void validateOilArticleExists(List<Integer> ids) {
        List<OilArticleDO> list = oilArticleMapper.selectByIds(ids);
        if (CollUtil.isEmpty(list) || list.size() != ids.size()) {
            throw exception(OIL_ARTICLE_NOT_EXISTS);
        }
    }

    private void validateOilArticleExists(Integer id) {
        if (oilArticleMapper.selectById(id) == null) {
            throw exception(OIL_ARTICLE_NOT_EXISTS);
        }
    }

    @Override
    public OilArticleDO getOilArticle(Integer id) {
        return oilArticleMapper.selectById(id);
    }

    @Override
    public PageResult<OilArticleDO> getOilArticlePage(OilArticlePageReqVO pageReqVO) {
        return oilArticleMapper.selectPage(pageReqVO);
    }

}