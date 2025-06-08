package cn.iocoder.yudao.module.system.service.articlecomment;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.articlecomment.vo.ArticleCommentPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.articlecomment.vo.ArticleCommentSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.articlecomment.ArticleCommentDO;
import cn.iocoder.yudao.module.system.dal.mysql.articlecomment.ArticleCommentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.ARTICLE_COMMENT_NOT_EXISTS;

/**
 * 文章评论 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Resource
    private ArticleCommentMapper articleCommentMapper;

    @Override
    public Integer createArticleComment(ArticleCommentSaveReqVO createReqVO) {
        // 插入
        ArticleCommentDO articleComment = BeanUtils.toBean(createReqVO, ArticleCommentDO.class);
        articleCommentMapper.insert(articleComment);
        // 返回
        return articleComment.getId();
    }

    @Override
    public void updateArticleComment(ArticleCommentSaveReqVO updateReqVO) {
        // 校验存在
        validateArticleCommentExists(updateReqVO.getId());
        // 更新
        ArticleCommentDO updateObj = BeanUtils.toBean(updateReqVO, ArticleCommentDO.class);
        articleCommentMapper.updateById(updateObj);
    }

    @Override
    public void deleteArticleComment(Integer id) {
        // 校验存在
        validateArticleCommentExists(id);
        // 删除
        articleCommentMapper.deleteById(id);
    }

    @Override
    public void deleteArticleCommentListByIds(List<Integer> ids) {
        // 校验存在
        validateArticleCommentExists(ids);
        // 删除
        articleCommentMapper.deleteByIds(ids);
    }

    private void validateArticleCommentExists(List<Integer> ids) {
        List<ArticleCommentDO> list = articleCommentMapper.selectByIds(ids);
        if (CollUtil.isEmpty(list) || list.size() != ids.size()) {
            throw exception(ARTICLE_COMMENT_NOT_EXISTS);
        }
    }

    private void validateArticleCommentExists(Integer id) {
        if (articleCommentMapper.selectById(id) == null) {
            throw exception(ARTICLE_COMMENT_NOT_EXISTS);
        }
    }

    @Override
    public ArticleCommentDO getArticleComment(Integer id) {
        return articleCommentMapper.selectById(id);
    }

    @Override
    public PageResult<ArticleCommentDO> getArticleCommentPage(ArticleCommentPageReqVO pageReqVO) {
        return articleCommentMapper.selectPage(pageReqVO);
    }

}