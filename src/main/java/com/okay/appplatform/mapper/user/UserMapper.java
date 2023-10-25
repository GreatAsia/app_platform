package com.okay.appplatform.mapper.user;

import com.okay.appplatform.domain.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectByUserSingle(User user);

    User selectUserRoleByUser(User user);

    List<User> selectByUser(User user);

    List<User>   selectUserAndRoleByPage(User user);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

}
