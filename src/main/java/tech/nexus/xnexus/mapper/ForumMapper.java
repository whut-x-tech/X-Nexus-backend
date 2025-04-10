package tech.nexus.xnexus.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tech.nexus.xnexus.model.entity.Forum;
import tech.nexus.xnexus.model.request.forum.ForumPageRequest;
import tech.nexus.xnexus.model.request.forum.ForumUpdateRequest;

import java.util.List;

/**
* @author laowang
* @description 针对表【t_forum(论坛帖子表)】的数据库操作Mapper
* @createDate 2025-04-08 23:28:41
* @Entity tech.nexus.xnexus.model.entity.Forum
*/
public interface ForumMapper {

    @Insert("insert into x_nexus.t_forum (id, title, content, user_id) values (#{id}, #{title}, #{content}, #{userId})")
    int addForum(Forum forum);

    @Select("select id, title, content, user_id, update_time, create_time from x_nexus.t_forum where id = #{id} and is_delete = 0")
    Forum getForum(@Param("id") long id);

    @Update("update x_nexus.t_forum set is_delete = 1 where id = #{id}")
    int deleteForum(@Param("id") long id);

    @Select("select user_id from x_nexus.t_forum where id = #{id} and is_delete = 0")
    long getForumUserId(long id);


    int updateForum(ForumUpdateRequest forum);

    List<Forum> pageForum(@Param("forum") ForumPageRequest forum, @Param("offset") long offset);

    long countForum(ForumPageRequest forum);
}




