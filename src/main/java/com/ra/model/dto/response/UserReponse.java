package com.ra.model.dto.response;

import com.ra.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
// thông tin trả ra
public class UserReponse {
    private Long id;
    private String fullName;
    private String token;
    private final String type="Bearer Token";
    private Set<String> roles;
}
