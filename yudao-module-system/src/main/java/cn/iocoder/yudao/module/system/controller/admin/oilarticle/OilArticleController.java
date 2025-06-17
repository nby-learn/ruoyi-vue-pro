package cn.iocoder.yudao.module.system.controller.admin.oilarticle;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.tenant.core.aop.TenantIgnore;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticlePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticleRespVO;
import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.OilArticleSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticle.OilArticleDO;
import cn.iocoder.yudao.module.system.service.oilarticle.OilArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 文章")
@RestController
@RequestMapping("/system/oil-article")
@Validated
public class OilArticleController {

    @Resource
    private OilArticleService oilArticleService;

    @PostMapping("/create")
    @Operation(summary = "创建文章")
    @PreAuthorize("@ss.hasPermission('system:oil-article:create')")
    public CommonResult<Integer> createOilArticle(@Valid @RequestBody OilArticleSaveReqVO createReqVO) {
        return success(oilArticleService.createOilArticle(createReqVO));
    }

    @PutMapping("/updateStatus")
    @Operation(summary = "更新文章")
    @PreAuthorize("@ss.hasPermission('system:oil-article:update')")
    @TenantIgnore
    public CommonResult<Boolean> updateStatus(@Valid @RequestBody OilArticleSaveReqVO updateReqVO) {
        oilArticleService.updateOilArticleStatus(updateReqVO);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新文章")
    @PreAuthorize("@ss.hasPermission('system:oil-article:update')")
    @TenantIgnore
    public CommonResult<Boolean> updateOilArticle(@Valid @RequestBody OilArticleSaveReqVO updateReqVO) {
        oilArticleService.updateOilArticle(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文章")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:oil-article:delete')")
    @TenantIgnore
    public CommonResult<Boolean> deleteOilArticle(@RequestParam("id") Integer id) {
        oilArticleService.deleteOilArticle(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除文章")
                @PreAuthorize("@ss.hasPermission('system:oil-article:delete')")
    @TenantIgnore
    public CommonResult<Boolean> deleteOilArticleList(@RequestParam("ids") List<Integer> ids) {
        oilArticleService.deleteOilArticleListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得文章")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:oil-article:query')")
    public CommonResult<OilArticleRespVO> getOilArticle(@RequestParam("id") Integer id) {
        OilArticleDO oilArticle = oilArticleService.getOilArticle(id);
        String pub1 = oilArticleService.getPubText1(id);
        String pub2 = oilArticleService.getPubText2(id);
        String pri1 = oilArticleService.getPriText2(id);
        OilArticleRespVO bean = BeanUtils.toBean(oilArticle, OilArticleRespVO.class);
        bean.setPubText1(pub1);
        bean.setPubText2(pub2);
        bean.setPriText1(pri1);
        return success(bean);
    }

    @GetMapping("/page")
    @Operation(summary = "获得文章分页")
    @PreAuthorize("@ss.hasPermission('system:oil-article:query')")
    @TenantIgnore
    public CommonResult<PageResult<OilArticleRespVO>> getOilArticlePage(@Valid OilArticlePageReqVO pageReqVO) {
        PageResult<OilArticleDO> pageResult = oilArticleService.getOilArticlePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OilArticleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出文章 Excel")
    @PreAuthorize("@ss.hasPermission('system:oil-article:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOilArticleExcel(@Valid OilArticlePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OilArticleDO> list = oilArticleService.getOilArticlePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "文章.xls", "数据", OilArticleRespVO.class,
                        BeanUtils.toBean(list, OilArticleRespVO.class));
    }

}