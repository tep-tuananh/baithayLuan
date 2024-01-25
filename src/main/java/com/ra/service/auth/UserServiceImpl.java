package com.ra.service.auth;

import com.ra.model.dto.request.UserLogin;
import com.ra.model.dto.response.UserReponse;
import com.ra.model.entity.Role;
import com.ra.model.entity.Users;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.security.jwt.JwtProvider;
import com.ra.security.user_principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public Users register(Users users) {
        // mã hóa mật khẩu
        users.setPassword(new BCryptPasswordEncoder().encode(users.getPassword()));
        // mặc định đăng kí tài  khoản là user
        Role role = roleRepository.findByName("ROLE_USER");
        Set<Role> roles= new HashSet<>();
        roles.add(role);
        users.setRoles(roles);
        return userRepository.save(users);
    }

    @Override
    public UserReponse login(UserLogin userLogin) {
        Authentication authentication ;
        try {
            authentication=authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName() ,userLogin.getPassword()));
        }catch (AuthenticationException e)
        {
            throw new RuntimeException("Sai tên tại khoản hoặc mật  khẩu");
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        // tạo token
        String token= jwtProvider.generateToken(userPrincipal);
        // convert sang đối tượng userReponse
        UserReponse userReponse = UserReponse.builder()
                .fullName(userPrincipal.getUsers().getFullName())
                .id(userPrincipal.getUsers().getId())
                        .token(token).
        roles(userPrincipal.getAuthorities().stream().map(item->item.getAuthority()).collect(Collectors.toSet())).
                build();
        return userReponse;
    }
}
