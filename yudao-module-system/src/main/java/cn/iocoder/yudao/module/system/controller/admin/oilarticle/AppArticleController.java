package cn.iocoder.yudao.module.system.controller.admin.oilarticle;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.framework.tenant.core.aop.TenantIgnore;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticlePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticleRespVO;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticleSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticle.OilArticleDO;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticledetail.OilArticleDetailDO;
import cn.iocoder.yudao.module.system.dal.mysql.oilarticledetail.OilArticleDetailMapper;
import cn.iocoder.yudao.module.system.enums.article.ArticleStatusEnum;
import cn.iocoder.yudao.module.system.service.oilarticle.OilArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @TenantIgnore
    public CommonResult<PageResult<OilArticleRespVO>> getOilArticlePage(@Valid @RequestBody OilArticlePageReqVO pageReqVO) {
        pageReqVO.setStatus(ArticleStatusEnum.ACCESS.getStatus());
        PageResult<OilArticleDO> pageResult = oilArticleService.getOilArticlePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OilArticleRespVO.class));
    }

    @Resource
    private OilArticleDetailMapper oilArticleDetailMapper;

    @GetMapping("/{id}")
    @Operation(summary = "获得文章分页")
    @TenantIgnore
    @PermitAll
    public CommonResult<OilArticleRespVO> getOilArticlePage(@PathVariable Integer id) {
        OilArticleDO oilArticle = oilArticleService.getOilArticle(id);
        OilArticleRespVO bean = BeanUtils.toBean(oilArticle, OilArticleRespVO.class);
        List<OilArticleDetailDO> details = oilArticleDetailMapper.selectList(
                new LambdaQueryWrapperX<OilArticleDetailDO>()
                        .eq(OilArticleDetailDO::getArticleId, id)
                        .orderByAsc(OilArticleDetailDO::getArticleIndex)
        );
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        for (OilArticleDetailDO detail : details) {
            switch (detail.getArticleIndex()) {
                case 1:
                    bean.setPubText1(detail.getContent());
                    break;
                case 2: {
                    String content = detail.getContent();
                    if (StringUtils.isNotBlank(content)) {
                        bean.setHasContent(true);
                        if (loginUserId != null) {
                            bean.setContent(content);
                        }
                    }
                    break;
                }
                case 3:
                    bean.setPubText2(detail.getContent());
                    break;
                default:
                    break;
            }
        }
        return success(bean);
    }

    @PostMapping("/")
    @Operation(summary = "发布文章")
    public CommonResult<Integer> createOilArticle(@Valid @RequestBody OilArticleSaveReqVO createReqVO) {
        return success(oilArticleService.createOilArticle(createReqVO));
    }
}
