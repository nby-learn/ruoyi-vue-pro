package cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 文章 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OilArticleRespVO {

    @Schema(description = "文章id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6252")
    @ExcelProperty("文章id")
    private Integer id;

    @Schema(description = "上级id", example = "7240")
    @ExcelProperty("上级id")
    private Integer fthId;

    @Schema(description = "文章标题")
    @ExcelProperty("文章标题")
    private String title;

    @Schema(description = "文章来源")
    @ExcelProperty("文章来源")
    private String source;

    @Schema(description = "发布时间")
    @ExcelProperty("发布时间")
    private LocalDateTime publishTime;

    @Schema(description = "点击数", example = "9046")
    @ExcelProperty("点击数")
    private String clickCount;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "上级名称", example = "赵六")
    @ExcelProperty("上级名称")
    private String fthName;

    @Schema(description = "审核状态", example = "赵六")
    @ExcelProperty("审核状态")
    private Integer status;

    @Schema(description = "公开段落一", example = "赵六")
    private String pubText1;

    @Schema(description = "加密段落一", example = "赵六")
    private String priText1;

    @Schema(description = "公开段落二", example = "赵六")
    private String pubText2;

    private Boolean hasContent = false;

    private String content = "";

}