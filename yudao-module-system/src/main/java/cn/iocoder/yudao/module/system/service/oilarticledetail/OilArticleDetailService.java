package cn.iocoder.yudao.module.system.service.oilarticledetail;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.oilarticledetail.vo.OilArticleDetailPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.oilarticledetail.vo.OilArticleDetailSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticledetail.OilArticleDetailDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 文章详情 Service 接口
 *
 * @author 超级管理员
 */
public interface OilArticleDetailService {

    /**
     * 创建文章详情
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createOilArticleDetail(@Valid OilArticleDetailSaveReqVO createReqVO);

    /**
     * 更新文章详情
     *
     * @param updateReqVO 更新信息
     */
    void updateOilArticleDetail(@Valid OilArticleDetailSaveReqVO updateReqVO);

    /**
     * 删除文章详情
     *
     * @param id 编号
     */
    void deleteOilArticleDetail(Integer id);

    /**
    * 批量删除文章详情
    *
    * @param ids 编号
    */
    void deleteOilArticleDetailListByIds(List<Integer> ids);

    /**
     * 获得文章详情
     *
     * @param id 编号
     * @return 文章详情
     */
    OilArticleDetailDO getOilArticleDetail(Integer id);

    /**
     * 获得文章详情分页
     *
     * @param pageReqVO 分页查询
     * @return 文章详情分页
     */
    PageResult<OilArticleDetailDO> getOilArticleDetailPage(OilArticleDetailPageReqVO pageReqVO);

}