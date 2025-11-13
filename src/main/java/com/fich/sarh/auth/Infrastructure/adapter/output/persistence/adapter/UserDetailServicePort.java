package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.adapter;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fich.sarh.auth.Application.ports.output.persistence.UserDetailsPort;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.security.jwt.JwtUtils;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.mapper.RoleRestMapper;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.LoginRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.UserRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.AuthResponse;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.RoleResponse;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository.RoleRepository;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository.UserRepository;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class UserDetailServicePort implements UserDetailsPort {


    Logger logger = LoggerFactory.getLogger(UserDetailServicePort.class);

    private JwtUtils jwtUtils;
    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserDetailServicePort(JwtUtils jwtUtils, UserRepository userRepository, RoleRepository roleRepository) {
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        logger.error("USERNAME " + username);

        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles().forEach(role -> authorityList.add(new
                SimpleGrantedAuthority("ROLE_"+ role.getRoleEnum().name())));

        userEntity.getRoles().stream().flatMap(role -> role.getPermissionSet().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .disabled(!userEntity.isEnabled())
                .accountExpired(!userEntity.isAccountNonExpired())
                .credentialsExpired(!userEntity.isCredentialNonExpired())
                .accountLocked(!userEntity.isAccountNonLocked())
                .authorities(authorityList)
                .build();
    }

    public AuthResponse createUser(UserRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();

        Set<String> rolesRequest = request.getRoles() == null ?
                Collections.emptySet() : request.getRoles().stream().map(rol -> rol.getRoleEnum()
                .name()).collect(Collectors.toSet());

        Set<RoleEntity> roleEntityList = roleRepository.findRoleEntitiesByRoleEnumIn(rolesRequest)
                .stream().collect(Collectors.toSet());

        if (roleEntityList.isEmpty() && !rolesRequest.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder().encode(password))
                .roles(roleEntityList)
                .isEnabled(true)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .credentialNonExpired(true).build();

        UserEntity userSaved = userRepository.save(userEntity);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userSaved.getRoles().forEach(role -> authorities
                .add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userSaved.getRoles().stream().flatMap(role ->
                        role.getPermissionSet().stream())
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .forEach(authorities::add);
              //  .forEach(permission ->
              //          authorities.add(new SimpleGrantedAuthority(permission.getName())));


        UserDetails userDetails = User.builder()

                .username(userSaved.getUsername())
                .password(userSaved.getPassword())
                .authorities(authorities)
                .disabled(!userSaved.isEnabled())
                .accountExpired(!userSaved.isAccountNonExpired())
                .credentialsExpired(!userSaved.isCredentialNonExpired())
                .accountLocked(!userSaved.isAccountNonLocked())
                .build();

        //SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());

        String accessToken = jwtUtils.createToken(authentication);
        String refreshToken = jwtUtils.createRefreshToken(authentication);

        Set<String> authorities_roles = userSaved.getRoles().stream()
                .map(rol -> rol.getRoleEnum().name())
                .collect(Collectors.toSet());

        //  AuthResponse authResponse = new AuthResponse(username, "User created successfully", accessToken, refreshToken, authorities_roles, true);
        return new AuthResponse(userSaved.getId(), userSaved.getUsername(), "User created successfully", accessToken,
                refreshToken, rolesRequest, true);
    }

    public AuthResponse loginUser(LoginRequest authLoginRequest) {

        logger.info("LOGIN " + authLoginRequest);


        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        String refreshToken = jwtUtils.createRefreshToken(authentication);

        Set<String> authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        AuthResponse authResponse = new AuthResponse(null,username, "User loged succesfully", accessToken, refreshToken, authorities, true);


        return authResponse;
    }

    public AuthResponse refreshToken(String refreshToken) {

        try {
            // 1️⃣ Validar el token de refresh
            DecodedJWT decoded = jwtUtils.validateRefreshToken(refreshToken);


            if (!jwtUtils.isRefreshToken(decoded)) {
                throw new IllegalArgumentException("Invalid refresh token");
            }



            // 3️⃣ Obtener el usuario del token
            String username = decoded.getSubject();
            UserDetails userDetails = this.loadUserByUsername(username);

            logger.info("USUARIO USUARIO USUARIO  " + decoded.getSubject());

            // 4️⃣ Crear una nueva autenticación (sin contraseña)
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    null,
                    userDetails.getAuthorities()
            );

            // 5️⃣ Generar nuevo access token
            String newAccessToken = jwtUtils.createToken(authentication);

            Set<String> authorities = userDetails.getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

            // 6️⃣ Devolver respuesta coherente
            return new AuthResponse(null,
                    username,
                    "Access token successfully renewed",
                    newAccessToken,
                    refreshToken, // El refresh no cambia
                    authorities,
                    true
            );

        } catch (JWTVerificationException e) {
            throw new IllegalArgumentException("Invalid or expired refresh token", e);
        } catch (UsernameNotFoundException e) {
            logger.error("Usuario no encontrado al refrescar token: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error refreshing access token", e);
        }
    }


   /* public AuthResponse refreshToken(String refreshToken){

        DecodedJWT decoded = jwtUtils.validateToken(refreshToken);

        // Opcional: asegurarse que es un refresh token real
         if (!jwtUtils.isRefreshToken(decoded)) {
             throw new RuntimeException("Invalid refresh token");
             }

        String username = decoded.getSubject();
        UserDetails userDetails = this.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        String newAccessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(
                username,
                "Access token renewed",
                newAccessToken,
                refreshToken,
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet()),
                true
        );
    }*/


    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder().matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}