package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Email
    @NotBlank
    @Size(max = 80)
    String email;
    @NotBlank
    @Size(max = 30)
    String username;
    @NotBlank
    String password;

    @Column(name = "is_enabled")
    boolean isEnabled;

    @Column(name = "account_No_Expired")
    boolean accountNonExpired;

    @Column(name = "account_No_Locked")
    boolean accountNonLocked;

    @Column(name = "credential_No_Expired")
    boolean credentialNonExpired;


    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<RoleEntity> roles;
}
