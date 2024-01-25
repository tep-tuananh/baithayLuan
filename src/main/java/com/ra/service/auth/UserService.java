package com.ra.service.auth;

import com.ra.model.dto.request.UserLogin;
import com.ra.model.dto.response.UserReponse;
import com.ra.model.entity.Users;

public interface UserService {
    // ĐĂNG KÍ TÀI KHOẢM
    Users  register(Users users);
    UserReponse login(UserLogin userLogin);
}
