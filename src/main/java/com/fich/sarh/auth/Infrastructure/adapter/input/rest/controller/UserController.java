package com.fich.sarh.auth.Infrastructure.adapter.input.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fich.sarh.auth.Application.ports.entrypoint.api.RoleRetrieveServicePort;
import com.fich.sarh.auth.Application.ports.entrypoint.api.UserSaveServicePort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserRetrievePort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserUploadPort;
import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper.RoleMapper;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper.UserMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserSaveServicePort userSave;

    private final UserUploadPort userUploadPort;
    private final PasswordEncoder passwordEncoder;

     private final RoleRetrieveServicePort roleRetrieveServicePort;

     private final UserRetrievePort userRetrievePort;
    Logger logger = LoggerFactory.getLogger(getClass());

    public UserController(UserSaveServicePort userSave, UserUploadPort userUploadPort, PasswordEncoder passwordEncoder, RoleRetrieveServicePort roleRetrieveServicePort, UserRetrievePort userRetrievePort) {
        this.userSave = userSave;
        this.userUploadPort = userUploadPort;
        this.passwordEncoder = passwordEncoder;

        this.roleRetrieveServicePort = roleRetrieveServicePort;
        this.userRetrievePort = userRetrievePort;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("all")
    public ResponseEntity<?> fetchAllUsers() {
        return ResponseEntity.ok().body(userRetrievePort.findAllUsers());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createUser(@Valid @RequestPart("createUser")UserDTO createUser,
                                        @RequestPart(value = "file", required = false) MultipartFile file) throws JsonProcessingException {



       // ObjectMapper mapper = new ObjectMapper();

       // Set<RoleDTO> roles = mapper.convertValue()

        //UserRequest createUser = mapper.readValue(createUserJson, UserRequest.class);


        Set<RoleDTO> roles = createUser.getRoles().stream()
                .map(role -> {
                    return roleRetrieveServicePort.fetchByRoleEnum(role.getRoleEnum());
                })
                .collect(Collectors.toSet());

        Set<RoleEntity> roles_entity = RoleMapper.INSTANCE.toEntityList(roles);


        logger.info("ROLES " + roles_entity);



        //Set<RoleEntity> roles_final = RoleRestMapper.INSTANCE.;





        //    createUser.setRoles(new HashSet<>(roles));


        String filename = "";
        if (file != null && !file.isEmpty()) {
            filename = userUploadPort.uploadProfilePicture(file);
        }
        logger.info("ARCHIVO " + filename);
        UserEntity userEntity = UserEntity.builder()
                .username(createUser.getUsername())
                .password(passwordEncoder.encode(createUser.getPassword()))
                .email(createUser.getEmail())
                .profilePicturePath(filename)
                .roles(roles_entity).build();



        return  ResponseEntity.status(HttpStatus.CREATED)                                                                                                                                                                                                                                                                                                                                                               .body(userSave.saveUsername(UserMapper.INSTANCE.toUserDTO(userEntity)));
    }

   /* @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createUser(@Valid @RequestPart("createUser") String createUserJson,
                                        @RequestPart(value = "file", required = false) MultipartFile file) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        UserRequest createUser = mapper.readValue(createUserJson, UserRequest.class);

        *//*Set<RoleDTO> roles = createUser.getRoles().stream().map(
                role -> {
                    RoleEnum roleEnum = RoleEnum.valueOf(role.getRoleEnum().name());
                    roleRetrieveServicePort.fetchByRoleEnum(roleEnum)
                }).collect(Collectors.toSet());

        Set<RoleEntity> roles_entity = RoleMapper.INSTANCE.toEntityList(roles);*//*

         Set<RoleEntity> roles = RoleRestMapper.INSTANCE.toRoleEntityList(createUser.getRoles());


         logger.info( "ROLES " + roles);
  

    //    createUser.setRoles(new HashSet<>(roles));


        String filename = "";
        if (file != null && !file.isEmpty()) {
            filename = userUploadPort.uploadProfilePicture(file);
        }
        logger.info("ARCHIVO " + filename);
        UserEntity userEntity = UserEntity.builder()
                .username(createUser.getUsername())
                .password(passwordEncoder.encode(createUser.getPassword()))
                .email(createUser.getEmail())
                .profilePicturePath(filename)
                .roles(roles).build();



        return  ResponseEntity.status(HttpStatus.CREATED).body(userSave.saveUsername(UserMapper.INSTANCE.toUserDTO(userEntity)));
    }*/

    @PreAuthorize("hasRole('USER')")
    @GetMapping("{username}/photo")

    public ResponseEntity<byte[]> getUserPhoto(@PathVariable String username)throws IOException
    {
        byte[] imageBytes = userRetrievePort.getPhotoByUsername(username);

        String contentType = Files.probeContentType(Paths.get("uploads/profile-pictures/" + username +".jpg"));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null? contentType: "image/jpg"))
                .body(imageBytes);
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
