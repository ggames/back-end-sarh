package com.fich.sarh.auth.Infrastructure.adapter.input.rest.controller;

import com.fich.sarh.auth.Application.ports.entrypoint.api.UserSaveServicePort;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.mapper.RoleRestMapper;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.UserRequest;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper.UserMapper;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserSaveServicePort userSave;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserSaveServicePort userSave, PasswordEncoder passwordEncoder) {
        this.userSave = userSave;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest createUser) {

        Set<RoleEntity> roles = RoleRestMapper.INSTANCE.toRoleEntityList(createUser.getRoles()) ;

        UserEntity userEntity = UserEntity.builder()
                   .username(createUser.getUsername())
                   .password(passwordEncoder.encode(createUser.getPassword()))
                   .email(createUser.getEmail())
                   .roles(roles).build();

        userSave.saveUsername(UserMapper.INSTANCE.toUserDTO(userEntity));

        return ResponseEntity.ok(userEntity);
    }
 /*   @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest createUser){

        Set<RoleRequest> roles = createUser.getRoles().stream().map(
                role -> RoleRequest.builder().name(role.getName()).build()
        ).collect(Collectors.toSet());

        UserDTO userDTO = UserDTO.builder()
                .username(createUser.getUsername())
                .password(createUser.getPassword())
                .email(createUser.getEmail())
                .roles(RoleRestMapper.INSTANCE.toRoleDTOList(roles)).build();

        userSave.saveUsername(userDTO);

        return ResponseEntity.ok(userDTO);
    }*/



}
