package com.okay.appplatform.service.user;

import com.alibaba.fastjson.JSONObject;
import com.okay.appplatform.domain.bean.Group;
import com.okay.appplatform.domain.bean.Menu;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author XieYangYang
 * @date 2019/11/21 16:39
 */
public interface GroupService {

    public Group getGroupByGroup(Group Group);
    public Map<Integer, List<Menu>> getGroupMenuByGroup(Group Group);


    public int insertOrUpdateGroupArray(JSONObject json, HttpSession session);
}
