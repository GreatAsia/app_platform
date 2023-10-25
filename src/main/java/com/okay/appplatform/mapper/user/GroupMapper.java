package com.okay.appplatform.mapper.user;

import com.okay.appplatform.domain.bean.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer id);

    List<Group> selectByGroup(Group record);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    int updateOrInsertGroup(@Param("groups") List<Group> groups);

    int deleteByRoleId(Group group);
}