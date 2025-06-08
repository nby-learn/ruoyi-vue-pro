package cn.iocoder.yudao.module.system.controller.admin.articlecomment;

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

import cn.iocoder.yudao.module.system.controller.admin.articlecomment.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.articlecomment.ArticleCommentDO;
import cn.iocoder.yudao.module.system.service.articlecomment.ArticleCommentService;

@Tag(name = "管理后台 - 文章评论")
@RestController
@RequestMapping("/system/article-comment")
@Validated
public class ArticleCommentController {

    @Resource
    private ArticleCommentService articleCommentService;

    @PostMapping("/create")
    @Operation(summary = "创建文章评论")
    @PreAuthorize("@ss.hasPermission('system:article-comment:create')")
    public CommonResult<Integer> createArticleComment(@Valid @RequestBody ArticleCommentSaveReqVO createReqVO) {
        return success(articleCommentService.createArticleComment(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新文章评论")
    @PreAuthorize("@ss.hasPermission('system:article-comment:update')")
    public CommonResult<Boolean> updateArticleComment(@Valid @RequestBody ArticleCommentSaveReqVO updateReqVO) {
        articleCommentService.updateArticleComment(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文章评论")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:article-comment:delete')")
    public CommonResult<Boolean> deleteArticleComment(@RequestParam("id") Integer id) {
        articleCommentService.deleteArticleComment(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除文章评论")
                @PreAuthorize("@ss.hasPermission('system:article-comment:delete')")
    public CommonResult<Boolean> deleteArticleCommentList(@RequestParam("ids") List<Integer> ids) {
        articleCommentService.deleteArticleCommentListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得文章评论")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:article-comment:query')")
    public CommonResult<ArticleCommentRespVO> getArticleComment(@RequestParam("id") Integer id) {
        ArticleCommentDO articleComment = articleCommentService.getArticleComment(id);
        return success(BeanUtils.toBean(articleComment, ArticleCommentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得文章评论分页")
    @PreAuthorize("@ss.hasPermission('system:article-comment:query')")
    public CommonResult<PageResult<ArticleCommentRespVO>> getArticleCommentPage(@Valid ArticleCommentPageReqVO pageReqVO) {
        PageResult<ArticleCommentDO> pageResult = articleCommentService.getArticleCommentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ArticleCommentRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出文章评论 Excel")
    @PreAuthorize("@ss.hasPermission('system:article-comment:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportArticleCommentExcel(@Valid ArticleCommentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ArticleCommentDO> list = articleCommentService.getArticleCommentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "文章评论.xls", "数据", ArticleCommentRespVO.class,
                        BeanUtils.toBean(list, ArticleCommentRespVO.class));
    }

}