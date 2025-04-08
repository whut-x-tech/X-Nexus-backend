package tech.nexus.xnexus.model.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 点赞数量表
 * @TableName t_favorite_count
 */
@Data
public class FavoriteCount implements Serializable {
    /**
     * 实例id
     */
    private Long itemId;

    /**
     * 点赞数量
     */
    private Integer favoriteNum;

    private static final long serialVersionUID = 1L;
}