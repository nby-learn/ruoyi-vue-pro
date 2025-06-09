package cn.iocoder.yudao.module.system.controller.admin.oilarticle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 文章新增/修改 Request VO")
@Data
public class OilArticleSaveReqVO {

    @Schema(description = "文章id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6252")
    private Integer id;

    @Schema(description = "上级id", example = "7240")
    private Integer fthId;

    @Schema(description = "文章标题")
    private String title;

    @Schema(description = "文章来源")
    private String source;

    @Schema(description = "发布时间")
    private LocalDateTime publishTime;

    @Schema(description = "点击数", example = "9046")
    private String clickCount;

    @Schema(description = "上级名称", example = "赵六")
    private String fthName;

    @Schema(description = "上级名称", example = "赵六")
    private Integer status;

}