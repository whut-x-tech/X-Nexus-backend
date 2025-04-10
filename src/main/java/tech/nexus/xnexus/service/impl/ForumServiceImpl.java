package tech.nexus.xnexus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.nexus.xnexus.common.ErrorCode;
import tech.nexus.xnexus.exception.BusinessException;
import tech.nexus.xnexus.exception.ThrowUtils;
import tech.nexus.xnexus.mapper.ForumMapper;
import tech.nexus.xnexus.model.entity.Forum;
import tech.nexus.xnexus.model.request.forum.ForumAddRequest;
import tech.nexus.xnexus.model.request.forum.ForumPageRequest;
import tech.nexus.xnexus.model.request.forum.ForumUpdateRequest;
import tech.nexus.xnexus.model.vo.forum.ForumVo;
import tech.nexus.xnexus.service.ForumService;
import tech.nexus.xnexus.util.UserHolder;
import tech.nexus.xnexus.util.sql.OrderEnum;
import tech.nexus.xnexus.util.sql.Page;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author liuqiao
 * @since 2025-04-10
 */
@Service
@AllArgsConstructor
public class ForumServiceImpl implements ForumService {

    private final ForumMapper forumMapper;

    private final static Set<String> fieldSet = new HashSet<>();

    static {
        String[] fields = {
                "title",
                "content",
                "user_id",
                "update_time",
                "create_time"
        };
        Collections.addAll(fieldSet, fields);
    }

    @Override
    public Boolean addForum(ForumAddRequest forumAddRequest) {
        final Forum forum = BeanUtil.copyProperties(forumAddRequest, Forum.class);
        forum.setId(IdUtil.getSnowflakeNextId());
        forum.setUserId(UserHolder.get().getId());
        return forumMapper.addForum(forum) == 1;
    }

    @Override
    public ForumVo getForum(long id) {
        final Forum forum = forumMapper.getForum(id);
        ThrowUtils.throwIf(forum == null, ErrorCode.PARAMS_ERROR, "帖子不存在");

        return BeanUtil.copyProperties(forum, ForumVo.class);
    }

    @Override
    public Page<ForumVo> page(ForumPageRequest forumPageRequest) {
        final List<String> orderList = forumPageRequest.getOrderList();
        final List<String> fieldList = forumPageRequest.getFieldList();
        for (String s : fieldList) {
            ThrowUtils.throwIf(!fieldSet.contains(s), ErrorCode.PARAMS_ERROR, "排序字段错误");
        }
        ThrowUtils.throwIf(!OrderEnum.check(orderList), ErrorCode.PARAMS_ERROR, "排序方式错误");

        ThrowUtils.throwIf(forumPageRequest.getLastId() == null
                        && forumPageRequest.getPageSize() * forumPageRequest.getCurrent() > 10000,
                ErrorCode.PARAMS_ERROR,
                "大偏移查询必须指定上次偏移️量");

        if (CollectionUtil.isNotEmpty(fieldList) != CollectionUtil.isNotEmpty(orderList)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "排序必须指定字段和排序方法");
        }
        if (CollectionUtil.isNotEmpty(fieldList) && fieldList.size() != orderList.size()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "排序字段和排序方法必须一一对应");

        }

        final long total = forumMapper.countForum(forumPageRequest);

        final List<Forum> forumList = forumMapper.pageForum(forumPageRequest,
                (long) forumPageRequest.getPageSize() * forumPageRequest.getCurrent());
        final List<ForumVo> forumVoList = forumList.stream().map(f -> BeanUtil.copyProperties(f, ForumVo.class)).toList();

        final Page<ForumVo> page = new Page<>();
        page.setRecords(forumVoList);
        page.setCurrent(forumPageRequest.getCurrent());
        page.setPageSize(forumPageRequest.getPageSize());
        page.setTotal(total);
        return page;
    }

    @Override
    public Boolean deleteForum(long id) {
        return forumMapper.deleteForum(id) == 1;
    }

    @Override
    public Boolean updateForum(ForumUpdateRequest forumUpdateRequest) {
        final long id = forumUpdateRequest.getId();
        final long userId = forumMapper.getForumUserId(id);

        ThrowUtils.throwIf(userId != UserHolder.get().getId(), ErrorCode.NO_AUTH_ERROR, "帖子所属人错误");

        return forumMapper.updateForum(forumUpdateRequest) == 1;
    }

}
