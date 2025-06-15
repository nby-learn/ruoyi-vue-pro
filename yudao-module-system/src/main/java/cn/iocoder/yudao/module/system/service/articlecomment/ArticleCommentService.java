package cn.iocoder.yudao.module.system.service.articlecomment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.articlecomment.vo.ArticleCommentPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.articlecomment.vo.ArticleCommentSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.articlecomment.ArticleCommentDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 文章评论 Service 接口
 *
 * @author 超级管理员
 */
public interface ArticleCommentService {

    /**
     * 创建文章评论
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createArticleComment(@Valid ArticleCommentSaveReqVO createReqVO);

    /**
     * 更新文章评论
     *
     * @param updateReqVO 更新信息
     */
    void updateArticleComment(@Valid ArticleCommentSaveReqVO updateReqVO);

    /**
     * 删除文章评论
     *
     * @param id 编号
     */
    void deleteArticleComment(Integer id);

    /**
    * 批量删除文章评论
    *
    * @param ids 编号
    */
    void deleteArticleCommentListByIds(List<Integer> ids);

    /**
     * 获得文章评论
     *
     * @param id 编号
     * @return 文章评论
     */
    ArticleCommentDO getArticleComment(Integer id);

    /**
     * 获得文章评论分页
     *
     * @param pageReqVO 分页查询
     * @return 文章评论分页
     */
    PageResult<ArticleCommentDO> getArticleCommentPage(ArticleCommentPageReqVO pageReqVO);

    PageResult<ArticleCommentDO> getAppArticleCommentPage(@Valid ArticleCommentPageReqVO pageReqVO);
}