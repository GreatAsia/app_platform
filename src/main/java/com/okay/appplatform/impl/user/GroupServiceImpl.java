package com.okay.appplatform.impl.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okay.appplatform.common.constant.Constant;
import com.okay.appplatform.domain.bean.Group;
import com.okay.appplatform.domain.bean.Menu;
import com.okay.appplatform.domain.user.UserResponse;
import com.okay.appplatform.mapper.user.GroupMapper;
import com.okay.appplatform.service.user.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auth 谢扬扬
 * @date 2020/8/28 14:19
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    GroupMapper groupMapper;

    @Override
    public Group getGroupByGroup(Group Group) {
        return null;
    }

    @Override
    public Map<Integer, List<Menu>> getGroupMenuByGroup(Group Group) {
        return null;
    }

    @Override
    public int insertOrUpdateGroupArray(JSONObject json, HttpSession session) {
        JSONArray jsonArray = json.getJSONArray("params");
        UserResponse onlineUser = (UserResponse) session.getAttribute(Constant.ONLINE_USER);
        Group DelGroup = new Group();
        DelGroup.setRoleId(json.getInteger("roleId"));
        DelGroup.setUpdateBy(onlineUser.getUserName());
        DelGroup.setUpdateAt(new Date());
        groupMapper.deleteByRoleId(DelGroup);

        if(jsonArray.size()<=0){
            return 1;
        }

        List<Group> groups = new ArrayList<>();

        for (Object g : jsonArray) {
            JSONObject groupJSON = JSONObject.parseObject(JSONObject.toJSONString(g));
            Group group = new Group();
            group.setId(groupJSON.getInteger("id"));
            group.setRoleId(json.getInteger("roleId"));
            group.setMenuId(groupJSON.getString("menuId"));
            group.setIsDelete("0");
            group.setCreateBy(onlineUser.getUserName());
            group.setUpdateBy(onlineUser.getUserName());
            group.setUpdateAt(new Date());
            groups.add(group);
        }
        return groupMapper.updateOrInsertGroup(groups);
    }
}
