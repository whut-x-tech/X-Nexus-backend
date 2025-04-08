package tech.nexus.xnexus.model.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户收藏记录表
 * @TableName t_collected
 */
@Data
public class Collected implements Serializable {
    /**
     * 收藏id
     */
    private Long id;

    /**
     * 帖子id
     */
    private Long itemId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除(0:正常 1:已取消)
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}