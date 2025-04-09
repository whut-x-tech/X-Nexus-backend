package tech.nexus.xnexus.model.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 收藏统计表
 * @TableName t_collected_count
 */
@Data
public class CollectedCount implements Serializable {
    /**
     * 实例id
     */
    private Long itemId;

    /**
     * 收藏数
     */
    private Integer collectedNum;

    private static final long serialVersionUID = 1L;
}