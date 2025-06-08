package cn.iocoder.yudao.module.system.controller.admin.oilarticle;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticlePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticleRespVO;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticleSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticle.OilArticleDO;
import cn.iocoder.yudao.module.system.enums.article.ArticleStatusEnum;
import cn.iocoder.yudao.module.system.service.oilarticle.OilArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "前台文章接口")
@RestController
@RequestMapping("/app/oil-article")
@Validated
public class AppArticleController {

    @Resource
    private OilArticleService oilArticleService;

    @PostMapping("/page")
    @Operation(summary = "获得文章分页")
    @PermitAll
    public CommonResult<PageResult<OilArticleRespVO>> getOilArticlePage(@Valid @RequestBody OilArticlePageReqVO pageReqVO) {
        pageReqVO.setStatus(ArticleStatusEnum.ACCESS.getStatus());
        PageResult<OilArticleDO> pageResult = oilArticleService.getOilArticlePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OilArticleRespVO.class));
    }

    @GetMapping("/{id}")
    @Operation(summary = "获得文章分页")
    public CommonResult<OilArticleRespVO> getOilArticlePage(@PathVariable Integer id) {
        OilArticleDO oilArticle = oilArticleService.getOilArticle(id);
        return success(BeanUtils.toBean(oilArticle, OilArticleRespVO.class));
    }

    @PostMapping("/")
    @Operation(summary = "发布文章")
    public CommonResult<Integer> createOilArticle(@Valid @RequestBody OilArticleSaveReqVO createReqVO) {
        return success(oilArticleService.createOilArticle(createReqVO));
    }
}
