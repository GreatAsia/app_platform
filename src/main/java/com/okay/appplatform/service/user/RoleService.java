package com.okay.appplatform.service.user;

import com.okay.appplatform.domain.bean.Role;
import com.okay.appplatform.domain.user.RoleResponse;

import java.util.List;

/**
 * @author XieYangYang
 * @date 2019/11/21 15:14
 */


public interface RoleService {
    public RoleResponse getRoleByRoleSingle(Role role);

    public List<Role> getAllRole(Role role);


    public Role getAllRoleSingle(Role role);
}
