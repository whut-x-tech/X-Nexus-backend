package tech.nexus.xnexus.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tech.nexus.xnexus.model.entity.User;

/**
* @author laowang
* @description 针对表【t_user(用户基本信息表)】的数据库操作Mapper
* @createDate 2025-04-08 23:28:15
* @Entity tech.nexus.xnexus.model.entity.User
*/
public interface UserMapper {

    @Select("select count(*) from x_nexus.t_user where username = #{username}")
    int isUserExist(String username);

    @Insert("insert into x_nexus.t_user(id, username, password) values (#{id}, #{username}, #{password})")
    Boolean addUser(User user);
}




