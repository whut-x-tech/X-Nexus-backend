package tech.nexus.xnexus.model.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName t_comment
 */
@Data
public class Comment implements Serializable {
    /**
     * 评论id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 根评论id
     */
    private Long rootId;

    /**
     * 帖子id
     */
    private Long itemId;

    /**
     * 内容
     */
    private String content;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除(0:未删除,1:已删除)
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}