package cn.iocoder.yudao.module.system.dal.mysql.oilarticledetail;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticledetail.OilArticleDetailDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.oilarticledetail.vo.*;

/**
 * 文章详情 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface OilArticleDetailMapper extends BaseMapperX<OilArticleDetailDO> {

    default PageResult<OilArticleDetailDO> selectPage(OilArticleDetailPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OilArticleDetailDO>()
                .eqIfPresent(OilArticleDetailDO::getArticleId, reqVO.getArticleId())
                .eqIfPresent(OilArticleDetailDO::getContent, reqVO.getContent())
                .eqIfPresent(OilArticleDetailDO::getArticleIndex, reqVO.getArticleIndex())
                .betweenIfPresent(OilArticleDetailDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OilArticleDetailDO::getId));
    }

}