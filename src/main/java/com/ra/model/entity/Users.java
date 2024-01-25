package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "fullName",columnDefinition = "nvarchar(50)",nullable = false)
    private String fullName;
    @Column(name = "userName",columnDefinition = "nvarchar(50)",nullable = false,unique = true)
    private String userName;
    @Column(name = "password",columnDefinition = "nvarchar(500)",nullable = false)
    private String password;
    @Column(name = "status")
    private Boolean stastus = true;
    // 1 người có nhiều quên , và 1 quyền thì có nhiều người
    @ManyToMany(fetch = FetchType.EAGER) // lưu đối tượng users và luwu vào roles
    @JoinTable(name = "user_role", // tên của table , sẽ tự tạo ra table mới (thực thể yếu)
    joinColumns = @JoinColumn(name = "user_id"), // khóa ngoại tham chiếu đến bảng Users
            inverseJoinColumns = @JoinColumn(name = "role_id") // khóa ngoại tham chiến đến bảng Role
    )
    private Set<Role> roles;
}
