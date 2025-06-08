package cn.iocoder.yudao.module.system.enums.article;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件审核状态枚举
 *
 * @author nby
 * @since 2022/4/10 13:39
 */
@Getter
@AllArgsConstructor
public enum ArticleStatusEnum {

    INIT(0), // 审核中
    ACCESS(1), // 审核通过
    DENY(2), // 审核拒绝
    ;

    private final int status;

}
