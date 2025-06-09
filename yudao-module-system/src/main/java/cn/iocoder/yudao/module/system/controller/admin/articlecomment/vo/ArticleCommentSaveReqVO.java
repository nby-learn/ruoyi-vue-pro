package cn.iocoder.yudao.module.system.controller.admin.articlecomment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 文章评论新增/修改 Request VO")
@Data
public class ArticleCommentSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10959")
    private Integer id;

    @Schema(description = "评论人名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
//    @NotEmpty(message = "评论人名称不能为空")
    private String name;

    @Schema(description = "评论人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15755")
//    @NotNull(message = "评论人id不能为空")
    private Integer userId;

    @Schema(description = "用户id")
    private LocalDateTime commentTime;

    @Schema(description = "评论内容")
    private String commentContent;

    @Schema(description = "审核状态：0未审核，1已通过，2已拒绝", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "审核状态：0未审核，1已通过，2已拒绝不能为空")
    private Integer status;

    @Schema(description = "文章id", requiredMode = Schema.RequiredMode.REQUIRED, example = "20752")
//    @NotNull(message = "文章id不能为空")
    private Integer articleId;

    @Schema(description = "文章标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "20752")
    private Integer articleTitle;

}