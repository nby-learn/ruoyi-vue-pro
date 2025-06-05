package cn.iocoder.yudao.module.system.controller.admin.oilarticle;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticle.OilArticleDO;
import cn.iocoder.yudao.module.system.service.oilarticle.OilArticleService;

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

    @PutMapping("/update")
    @Operation(summary = "更新文章")
    @PreAuthorize("@ss.hasPermission('system:oil-article:update')")
    public CommonResult<Boolean> updateOilArticle(@Valid @RequestBody OilArticleSaveReqVO updateReqVO) {
        oilArticleService.updateOilArticle(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文章")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:oil-article:delete')")
    public CommonResult<Boolean> deleteOilArticle(@RequestParam("id") Integer id) {
        oilArticleService.deleteOilArticle(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除文章")
                @PreAuthorize("@ss.hasPermission('system:oil-article:delete')")
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
        return success(BeanUtils.toBean(oilArticle, OilArticleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得文章分页")
    @PreAuthorize("@ss.hasPermission('system:oil-article:query')")
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