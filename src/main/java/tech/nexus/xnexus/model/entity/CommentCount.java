package tech.nexus.xnexus.model.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 评论数量表
 * @TableName t_comment_count
 */
@Data
public class CommentCount implements Serializable {
    /**
     * 实例id
     */
    private Long itemId;

    /**
     * 评论数量
     */
    private Long commentNum;

    private static final long serialVersionUID = 1L;
}