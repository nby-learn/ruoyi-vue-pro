package cn.iocoder.yudao.module.system.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticlePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticleRespVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticle.OilArticleDO;
import cn.iocoder.yudao.module.system.service.oilarticle.OilArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "前台文章接口")
@RestController
@RequestMapping("/app/oil-article")
@Validated
public class ArticleController {

    @Resource
    private OilArticleService oilArticleService;

    @GetMapping("/page")
    @Operation(summary = "获得文章分页")
    public CommonResult<PageResult<OilArticleRespVO>> getOilArticlePage(@Valid OilArticlePageReqVO pageReqVO) {
        PageResult<OilArticleDO> pageResult = oilArticleService.getOilArticlePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OilArticleRespVO.class));
    }
}
