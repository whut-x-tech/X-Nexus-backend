package tech.nexus.xnexus.model.vo.forum;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuqiao
 * @since 2025-04-10
 */
@Data
public class ForumVo implements Serializable {
    /**
     * 帖子id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发帖人id
     */
    private Long userId;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
