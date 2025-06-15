package cn.iocoder.yudao.module.system.controller.admin.oilarticledetail;

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

import cn.iocoder.yudao.module.system.controller.admin.oilarticledetail.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.oilarticledetail.OilArticleDetailDO;
import cn.iocoder.yudao.module.system.service.oilarticledetail.OilArticleDetailService;

@Tag(name = "管理后台 - 文章详情")
@RestController
@RequestMapping("/system/oil-article-detail")
@Validated
public class OilArticleDetailController {

    @Resource
    private OilArticleDetailService oilArticleDetailService;

    @PostMapping("/create")
    @Operation(summary = "创建文章详情")
    @PreAuthorize("@ss.hasPermission('system:oil-article-detail:create')")
    public CommonResult<Integer> createOilArticleDetail(@Valid @RequestBody OilArticleDetailSaveReqVO createReqVO) {
        return success(oilArticleDetailService.createOilArticleDetail(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新文章详情")
    @PreAuthorize("@ss.hasPermission('system:oil-article-detail:update')")
    public CommonResult<Boolean> updateOilArticleDetail(@Valid @RequestBody OilArticleDetailSaveReqVO updateReqVO) {
        oilArticleDetailService.updateOilArticleDetail(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文章详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:oil-article-detail:delete')")
    public CommonResult<Boolean> deleteOilArticleDetail(@RequestParam("id") Integer id) {
        oilArticleDetailService.deleteOilArticleDetail(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除文章详情")
                @PreAuthorize("@ss.hasPermission('system:oil-article-detail:delete')")
    public CommonResult<Boolean> deleteOilArticleDetailList(@RequestParam("ids") List<Integer> ids) {
        oilArticleDetailService.deleteOilArticleDetailListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得文章详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:oil-article-detail:query')")
    public CommonResult<OilArticleDetailRespVO> getOilArticleDetail(@RequestParam("id") Integer id) {
        OilArticleDetailDO oilArticleDetail = oilArticleDetailService.getOilArticleDetail(id);
        return success(BeanUtils.toBean(oilArticleDetail, OilArticleDetailRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得文章详情分页")
    @PreAuthorize("@ss.hasPermission('system:oil-article-detail:query')")
    public CommonResult<PageResult<OilArticleDetailRespVO>> getOilArticleDetailPage(@Valid OilArticleDetailPageReqVO pageReqVO) {
        PageResult<OilArticleDetailDO> pageResult = oilArticleDetailService.getOilArticleDetailPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OilArticleDetailRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出文章详情 Excel")
    @PreAuthorize("@ss.hasPermission('system:oil-article-detail:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOilArticleDetailExcel(@Valid OilArticleDetailPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OilArticleDetailDO> list = oilArticleDetailService.getOilArticleDetailPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "文章详情.xls", "数据", OilArticleDetailRespVO.class,
                        BeanUtils.toBean(list, OilArticleDetailRespVO.class));
    }

}