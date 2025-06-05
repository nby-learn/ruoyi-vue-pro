package cn.iocoder.yudao.module.system.dal.dataobject.oilarticle;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 文章 DO
 *
 * @author 芋道源码
 */
@TableName("system_oil_article")
@KeySequence("system_oil_article_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OilArticleDO extends BaseDO {

    /**
     * 文章id
     */
    @TableId
    private Integer id;
    /**
     * 上级id
     */
    private Integer fthId;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章来源
     */
    private String source;
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    /**
     * 点击数
     */
    private String clickCount;
    /**
     * 上级名称
     */
    private String fthName;


}