package cn.iocoder.yudao.module.system.dal.dataobject.articlecomment;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 文章评论 DO
 *
 * @author 超级管理员
 */
@TableName("system_article_comment")
@KeySequence("system_article_comment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommentDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 评论人名称
     */
    private String name;
    /**
     * 评论人id
     */
    private Integer userId;
    /**
     * 用户id
     */
    private LocalDateTime commentTime;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 审核状态：0未审核，1已通过，2已拒绝
     */
    private Integer status;
    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

}