package cn.iocoder.yudao.module.system.controller.admin.oilarticledetail.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 文章详情新增/修改 Request VO")
@Data
public class OilArticleDetailSaveReqVO {

    @Schema(description = "文章详情id", requiredMode = Schema.RequiredMode.REQUIRED, example = "27048")
    private Integer id;

    @Schema(description = "上级id", example = "2351")
    private Integer articleId;

    @Schema(description = "文章内容")
    private String content;

    @Schema(description = "顺序")
    private Integer articleIndex;

    @Schema(description = "是否加密，0为否，1为是")
    private Integer secret;

}