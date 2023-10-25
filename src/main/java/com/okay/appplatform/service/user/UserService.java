package com.okay.appplatform.service.user;

import com.okay.appplatform.domain.bean.User;
import com.okay.appplatform.domain.user.UserResponse;

import java.util.List;

public interface UserService {

    public UserResponse findByUserRes(User user);


    public List<User> findUserByPage(User user);


    public User findUserSingle(User user);

    public boolean updateUser(User user);

    public boolean insertUser(User user);

}
