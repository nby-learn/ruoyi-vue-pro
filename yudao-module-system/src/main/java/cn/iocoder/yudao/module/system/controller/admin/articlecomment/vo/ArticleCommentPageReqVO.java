package cn.iocoder.yudao.module.system.controller.admin.articlecomment.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 文章评论分页 Request VO")
@Data
public class ArticleCommentPageReqVO extends PageParam {

    @Schema(description = "评论人名称", example = "张三")
    private String name;

    @Schema(description = "评论人id", example = "15755")
    private Integer userId;

    @Schema(description = "用户id")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] commentTime;

    @Schema(description = "评论内容")
    private String commentContent;

    @Schema(description = "审核状态：0未审核，1已通过，2已拒绝", example = "2")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "文章id", example = "20752")
    private Integer articleId;

    @Schema(description = "文章标题", example = "20752")
    private String articleTitle;

}