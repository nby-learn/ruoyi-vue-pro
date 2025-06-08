package cn.iocoder.yudao.module.system.controller.admin.articlecomment.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 文章评论 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ArticleCommentRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10959")
    @ExcelProperty("id")
    private Integer id;

    @Schema(description = "评论人名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("评论人名称")
    private String name;

    @Schema(description = "评论人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15755")
    @ExcelProperty("评论人id")
    private Integer userId;

    @Schema(description = "用户id")
    @ExcelProperty("用户id")
    private LocalDateTime commentTime;

    @Schema(description = "评论内容")
    @ExcelProperty("评论内容")
    private String commentContent;

    @Schema(description = "审核状态：0未审核，1已通过，2已拒绝", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("审核状态：0未审核，1已通过，2已拒绝")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "文章id", requiredMode = Schema.RequiredMode.REQUIRED, example = "20752")
    @ExcelProperty("文章id")
    private Integer articleId;

    @Schema(description = "文章标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "20752")
    @ExcelProperty("文章标题")
    private String articleTitle;

}