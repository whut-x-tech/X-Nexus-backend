package tech.nexus.xnexus.service;

import jakarta.validation.Valid;
import tech.nexus.xnexus.model.request.forum.ForumAddRequest;
import tech.nexus.xnexus.model.request.forum.ForumPageRequest;
import tech.nexus.xnexus.model.request.forum.ForumUpdateRequest;
import tech.nexus.xnexus.model.vo.forum.ForumVo;
import tech.nexus.xnexus.util.sql.Page;

/**
 * @author liuqiao
 * @since 2025-04-10
 */
public interface ForumService {

    /**
     * 新增一条论坛帖子
     * @param forumAddRequest 帖子信息
     * @return ture 添加成功 false 添加失败
     */
    Boolean addForum(ForumAddRequest forumAddRequest);

    /**
     * 根据 id 获取论坛帖子
     * @param id 帖子 id
     */
    ForumVo getForum(long id);

    /**
     * 分页查询论坛帖子
     * @param forumPageRequest 查询条件
     */
    Page<ForumVo> page(@Valid ForumPageRequest forumPageRequest);

    /**
     * 逻辑删除论坛帖子
     * @param id 帖子 id
     */
    Boolean deleteForum(long id);

    /**
     * 根据 id 更新帖子
     * @param forumUpdateRequest 帖子信息
     */
    Boolean updateForum(@Valid ForumUpdateRequest forumUpdateRequest);
}
