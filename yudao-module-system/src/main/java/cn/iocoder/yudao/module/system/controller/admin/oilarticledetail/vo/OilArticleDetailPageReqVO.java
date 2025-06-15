package cn.iocoder.yudao.module.system.controller.admin.oilarticledetail.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 文章详情分页 Request VO")
@Data
public class OilArticleDetailPageReqVO extends PageParam {

    @Schema(description = "上级id", example = "2351")
    private Integer articleId;

    @Schema(description = "文章内容")
    private String content;

    @Schema(description = "顺序")
    private Integer articleIndex;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "是否加密，0为否，1为是")
    private Integer secret;

}