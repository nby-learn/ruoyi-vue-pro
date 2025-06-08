package cn.iocoder.yudao.module.system.dal.mysql.articlecomment;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.articlecomment.ArticleCommentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.articlecomment.vo.*;

/**
 * 文章评论 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface ArticleCommentMapper extends BaseMapperX<ArticleCommentDO> {

    default PageResult<ArticleCommentDO> selectPage(ArticleCommentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ArticleCommentDO>()
                .likeIfPresent(ArticleCommentDO::getName, reqVO.getName())
                .eqIfPresent(ArticleCommentDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(ArticleCommentDO::getCommentTime, reqVO.getCommentTime())
                .eqIfPresent(ArticleCommentDO::getCommentContent, reqVO.getCommentContent())
                .eqIfPresent(ArticleCommentDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ArticleCommentDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ArticleCommentDO::getArticleId, reqVO.getArticleId())
                .orderByDesc(ArticleCommentDO::getId));
    }

}