package cn.iocoder.yudao.module.system.service.oilarticle;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticle.OilArticleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 文章 Service 接口
 *
 * @author 芋道源码
 */
public interface OilArticleService {

    /**
     * 创建文章
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createOilArticle(@Valid OilArticleSaveReqVO createReqVO);

    /**
     * 更新文章
     *
     * @param updateReqVO 更新信息
     */
    void updateOilArticle(@Valid OilArticleSaveReqVO updateReqVO);

    /**
     * 删除文章
     *
     * @param id 编号
     */
    void deleteOilArticle(Integer id);

    /**
    * 批量删除文章
    *
    * @param ids 编号
    */
    void deleteOilArticleListByIds(List<Integer> ids);

    /**
     * 获得文章
     *
     * @param id 编号
     * @return 文章
     */
    OilArticleDO getOilArticle(Integer id);

    /**
     * 获得文章分页
     *
     * @param pageReqVO 分页查询
     * @return 文章分页
     */
    PageResult<OilArticleDO> getOilArticlePage(OilArticlePageReqVO pageReqVO);

}