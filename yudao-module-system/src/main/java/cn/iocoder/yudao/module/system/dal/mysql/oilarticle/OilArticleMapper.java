package cn.iocoder.yudao.module.system.dal.mysql.oilarticle;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticle.OilArticleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.*;

/**
 * 文章 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OilArticleMapper extends BaseMapperX<OilArticleDO> {

    default PageResult<OilArticleDO> selectPage(OilArticlePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OilArticleDO>()
                .eqIfPresent(OilArticleDO::getFthId, reqVO.getFthId())
                .eqIfPresent(OilArticleDO::getTitle, reqVO.getTitle())
                .eqIfPresent(OilArticleDO::getSource, reqVO.getSource())
                .betweenIfPresent(OilArticleDO::getPublishTime, reqVO.getPublishTime())
                .eqIfPresent(OilArticleDO::getClickCount, reqVO.getClickCount())
                .betweenIfPresent(OilArticleDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(OilArticleDO::getFthName, reqVO.getFthName())
                .orderByDesc(OilArticleDO::getId));
    }

}