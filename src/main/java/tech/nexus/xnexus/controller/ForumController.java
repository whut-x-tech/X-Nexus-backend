package tech.nexus.xnexus.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.nexus.xnexus.common.BaseResponse;
import tech.nexus.xnexus.common.ResultUtils;
import tech.nexus.xnexus.model.request.forum.ForumAddRequest;
import tech.nexus.xnexus.model.request.forum.ForumPageRequest;
import tech.nexus.xnexus.model.request.forum.ForumUpdateRequest;
import tech.nexus.xnexus.model.vo.forum.ForumVo;
import tech.nexus.xnexus.service.ForumService;
import tech.nexus.xnexus.util.sql.Page;

/**
 * 处理论坛相关请求控制器
 *
 * @author liuqiao
 * @since 2025-04-09
 */
@RequestMapping("/forum")
@RestController
@AllArgsConstructor
public class ForumController {

    private ForumService forumService;

    @PostMapping("add")
    public BaseResponse<Boolean> add(@RequestBody @Valid ForumAddRequest forumAddRequest) {

        return ResultUtils.success(forumService.addForum(forumAddRequest));
    }

    @PostMapping("update")
    public BaseResponse<Boolean> updte(@RequestBody @Valid ForumUpdateRequest forumUpdateRequest) {

        return ResultUtils.success(forumService.updateForum(forumUpdateRequest));
    }

    @GetMapping("get/{id}")
    public BaseResponse<ForumVo> get(@Min(value = 1, message = "id 错误") @PathVariable("id") long id) {

        return ResultUtils.success(forumService.getForum(id));
    }

    @PostMapping("page")
    public BaseResponse<Page<ForumVo>> page(@RequestBody @Valid ForumPageRequest forumPageRequest) {

        return ResultUtils.success(forumService.page(forumPageRequest));
    }

    @GetMapping("delete/{id}")
    public BaseResponse<Boolean> delete(@Min(value = 1, message = "id 错误") @PathVariable("id") long id) {

        return ResultUtils.success(forumService.deleteForum(id));
    }

}
