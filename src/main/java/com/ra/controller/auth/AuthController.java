package com.ra.controller.auth;

import com.ra.model.dto.request.UserLogin;
import com.ra.model.dto.response.UserReponse;
import com.ra.model.entity.Users;
import com.ra.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    // đăng kí tài khoản
    @PostMapping("/sig-up")
    public ResponseEntity<?> sigUp(@RequestBody Users users)
    {
        Users usersNew= userService.register(users);
        if(usersNew!=null)
        {
            return new ResponseEntity<>("Đăng Ký thành công", HttpStatus.CREATED);
        }else
        {
            return new ResponseEntity<>("Đăng ký thất bại",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // đăng nhập tài khoản
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin userLogin)
    {
        UserReponse userReponse = userService.login(userLogin);
        return new ResponseEntity<>(userReponse,HttpStatus.OK);
    }

}
