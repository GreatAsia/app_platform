package com.okay.appplatform.impl.user;

import com.okay.appplatform.common.convert.RoleConvert;
import com.okay.appplatform.domain.bean.Group;
import com.okay.appplatform.domain.bean.Menu;
import com.okay.appplatform.domain.bean.Role;
import com.okay.appplatform.domain.user.RoleResponse;
import com.okay.appplatform.mapper.user.RoleMapper;
import com.okay.appplatform.service.user.MenuService;
import com.okay.appplatform.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author XieYangYang
 * @date 2019/11/21 17:11
 */
@Service("RoleService")
public class RoleServiceImpl implements RoleService {


    @Resource
    RoleMapper roleMapper;

    @Autowired
    MenuService MenuService;

    @Override
    public RoleResponse getRoleByRoleSingle(Role role) {
        Role resRole = roleMapper.selectByRoleSingle(role);
        RoleResponse roleResponse = RoleConvert.roleToRoleResponse(resRole);
        Group group = new Group();
        group.setRoleId(roleResponse.getId());
        List<Menu> menus = MenuService.getMenusByRoleId(roleResponse.getId());
        roleResponse.setMenus(menus);
        return roleResponse;
    }

    @Override
    public List<Role> getAllRole(Role role) {
        List<Role> resRoles = roleMapper.selectByRole(role);
        return resRoles;
    }


    @Override
    public Role getAllRoleSingle(Role role) {
        Role resRole = roleMapper.selectByRoleSingle(role);
        return resRole;
    }
}
