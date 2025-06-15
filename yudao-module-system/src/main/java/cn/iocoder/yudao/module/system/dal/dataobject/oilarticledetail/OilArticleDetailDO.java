package cn.iocoder.yudao.module.system.dal.dataobject.oilarticledetail;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 文章详情 DO
 *
 * @author 超级管理员
 */
@TableName("system_oil_article_detail")
@KeySequence("system_oil_article_detail_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OilArticleDetailDO extends BaseDO {

    /**
     * 文章详情id
     */
    @TableId
    private Integer id;
    /**
     * 上级id
     */
    private Integer articleId;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 顺序
     */
    private Integer articleIndex;
    /**
     * 是否加密，0为否，1为是
     */
    private Integer secret;
}