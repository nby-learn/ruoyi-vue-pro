package cn.iocoder.yudao.module.system.service.oilarticle;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.dict.core.DictFrameworkUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticlePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticleSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticle.OilArticleDO;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticledetail.OilArticleDetailDO;
import cn.iocoder.yudao.module.system.dal.mysql.oilarticle.OilArticleMapper;
import cn.iocoder.yudao.module.system.dal.mysql.oilarticledetail.OilArticleDetailMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
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

    @Resource
    private OilArticleDetailMapper oilArticleDetailMapper;

    @Override
    public Integer createOilArticle(OilArticleSaveReqVO createReqVO) {
        // 插入
        OilArticleDO oilArticle = BeanUtils.toBean(createReqVO, OilArticleDO.class);
        String fthName = DictFrameworkUtils.parseDictDataLabel("article_type", oilArticle.getFthId().toString());
        oilArticle.setFthName(fthName);
        oilArticle.setClickCount("0");
        oilArticleMapper.insert(oilArticle);
        // 插入正文内容
        String pubText1 = createReqVO.getPubText1();
        if (StringUtils.isNotBlank(pubText1)) {
            oilArticleDetailMapper.insert(
                    OilArticleDetailDO.builder()
                            .articleId(oilArticle.getId())
                            .content(pubText1)
                            .articleIndex(1)
                            .secret(0)
                            .build()
            );
        }
        String priText1 = createReqVO.getPriText1();
        if (StringUtils.isNotBlank(priText1)) {
            oilArticleDetailMapper.insert(
                    OilArticleDetailDO.builder()
                            .articleId(oilArticle.getId())
                            .content(priText1)
                            .articleIndex(2)
                            .secret(1)
                            .build()
            );
        }
        String pubText2 = createReqVO.getPubText2();
        if (StringUtils.isNotBlank(pubText2)) {
            oilArticleDetailMapper.insert(
                    OilArticleDetailDO.builder()
                            .articleId(oilArticle.getId())
                            .content(pubText2)
                            .articleIndex(3)
                            .secret(0)
                            .build()
            );
        }
        // 返回
        return oilArticle.getId();
    }

    @Override
    public void updateOilArticle(OilArticleSaveReqVO updateReqVO) {
        // 校验存在
        validateOilArticleExists(updateReqVO.getId());
        // 更新
        OilArticleDO updateObj = BeanUtils.toBean(updateReqVO, OilArticleDO.class);

        String fthName = DictFrameworkUtils.parseDictDataLabel("article_type", updateReqVO.getFthId().toString());
        updateReqVO.setFthName(fthName);
        // 更新正文内容一
        String pubText1 = updateReqVO.getPubText1();
        OilArticleDetailDO oilArticleDetailDO1 = oilArticleDetailMapper.selectOne(
                new LambdaQueryWrapperX<OilArticleDetailDO>()
                        .eq(OilArticleDetailDO::getArticleId, updateReqVO.getId())
                        .eq(OilArticleDetailDO::getArticleIndex, 1)
        );
        if (StringUtils.isNotBlank(pubText1)) {
            if (oilArticleDetailDO1 != null) {
                oilArticleDetailMapper.updateById(
                        new OilArticleDetailDO().setId(oilArticleDetailDO1.getId())
                                .setContent(pubText1));
            } else {
                oilArticleDetailMapper.insert(
                        OilArticleDetailDO.builder()
                                .articleId(updateReqVO.getId())
                                .content(pubText1)
                                .articleIndex(1)
                                .secret(0)
                                .build()
                );
            }
        } else {
            if (oilArticleDetailDO1 != null) {
                oilArticleDetailMapper.deleteById(oilArticleDetailDO1.getId());
            }
        }

        String priText1 = updateReqVO.getPriText1();
        OilArticleDetailDO oilArticleDetailDO2 = oilArticleDetailMapper.selectOne(
                new LambdaQueryWrapperX<OilArticleDetailDO>()
                        .eq(OilArticleDetailDO::getArticleId, updateReqVO.getId())
                        .eq(OilArticleDetailDO::getArticleIndex, 2)
        );
        if (StringUtils.isNotBlank(priText1)) {
            if (oilArticleDetailDO2 != null) {
                oilArticleDetailMapper.updateById(
                        new OilArticleDetailDO().setId(oilArticleDetailDO2.getId())
                                .setContent(priText1));
            } else {
                oilArticleDetailMapper.insert(
                        OilArticleDetailDO.builder()
                                .articleId(updateReqVO.getId())
                                .content(priText1)
                                .articleIndex(2)
                                .secret(1)
                                .build()
                );
            }
        } else {
            if (oilArticleDetailDO2 != null) {
                oilArticleDetailMapper.deleteById(oilArticleDetailDO2.getId());
            }
        }

        String pubText2 = updateReqVO.getPubText2();
        OilArticleDetailDO oilArticleDetailDO3 = oilArticleDetailMapper.selectOne(
                new LambdaQueryWrapperX<OilArticleDetailDO>()
                        .eq(OilArticleDetailDO::getArticleId, updateReqVO.getId())
                        .eq(OilArticleDetailDO::getArticleIndex, 3)
        );
        if (StringUtils.isNotBlank(pubText2)) {
            if (oilArticleDetailDO3 != null) {
                oilArticleDetailMapper.updateById(
                        new OilArticleDetailDO().setId(oilArticleDetailDO3.getId())
                                .setContent(pubText2));
            } else {
                oilArticleDetailMapper.insert(
                        OilArticleDetailDO.builder()
                                .articleId(updateReqVO.getId())
                                .content(pubText2)
                                .articleIndex(3)
                                .secret(0)
                                .build()
                );
            }
        } else {
            if (oilArticleDetailDO3 != null) {
                oilArticleDetailMapper.deleteById(oilArticleDetailDO3.getId());
            }
        }
        oilArticleMapper.updateById(updateObj);
    }

    @Override
    public void updateOilArticleStatus(OilArticleSaveReqVO updateReqVO) {

        // 校验存在
        validateOilArticleExists(updateReqVO.getId());
        this.oilArticleMapper
                .updateById(new OilArticleDO()
                        .setId(updateReqVO.getId())
                        .setStatus(updateReqVO.getStatus()));
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

    @Override
    public String getPriText2(Integer id) {
        List<OilArticleDetailDO> oilArticleDetailDOS = oilArticleDetailMapper.selectList(
                new LambdaQueryWrapperX<OilArticleDetailDO>()
                        .eq(OilArticleDetailDO::getArticleId, id)
                        .eq(OilArticleDetailDO::getArticleIndex, 2)
        );
        return oilArticleDetailDOS.size() > 0 ? oilArticleDetailDOS.get(0).getContent() : "";
    }

    @Override
    public String getPubText2(Integer id) {
        List<OilArticleDetailDO> oilArticleDetailDOS = oilArticleDetailMapper.selectList(
                new LambdaQueryWrapperX<OilArticleDetailDO>()
                        .eq(OilArticleDetailDO::getArticleId, id)
                        .eq(OilArticleDetailDO::getArticleIndex, 3)
        );
        return oilArticleDetailDOS.size() > 0 ? oilArticleDetailDOS.get(0).getContent() : "";
    }

    @Override
    public String getPubText1(Integer id) {
        List<OilArticleDetailDO> oilArticleDetailDOS = oilArticleDetailMapper.selectList(
                new LambdaQueryWrapperX<OilArticleDetailDO>()
                        .eq(OilArticleDetailDO::getArticleId, id)
                        .eq(OilArticleDetailDO::getArticleIndex, 1)
        );
        return oilArticleDetailDOS.size() > 0 ? oilArticleDetailDOS.get(0).getContent() : "";
    }
}