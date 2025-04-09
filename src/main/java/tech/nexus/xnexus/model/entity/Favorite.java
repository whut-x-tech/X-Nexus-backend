package tech.nexus.xnexus.model.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 点赞表
 * @TableName t_favorite
 */
@Data
public class Favorite implements Serializable {
    /**
     * 点赞id
     */
    private Long id;

    /**
     * 实例id
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
     * 是否删除(0:未删除,1:已删除)
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}