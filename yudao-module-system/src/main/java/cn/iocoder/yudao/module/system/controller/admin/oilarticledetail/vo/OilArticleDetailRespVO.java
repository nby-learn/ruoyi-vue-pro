package cn.iocoder.yudao.module.system.controller.admin.oilarticledetail.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 文章详情 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OilArticleDetailRespVO {

    @Schema(description = "文章详情id", requiredMode = Schema.RequiredMode.REQUIRED, example = "27048")
    @ExcelProperty("文章详情id")
    private Integer id;

    @Schema(description = "上级id", example = "2351")
    @ExcelProperty("上级id")
    private Integer articleId;

    @Schema(description = "文章内容")
    @ExcelProperty("文章内容")
    private String content;

    @Schema(description = "顺序")
    @ExcelProperty("顺序")
    private Integer articleIndex;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}