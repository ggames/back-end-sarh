package com.fich.sarh.auth.Application.ports.entrypoint.api;

import com.fich.sarh.auth.Domain.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRetrieveServicePort {

    List<UserDTO> findAllUsers();
    Optional<UserDTO> findByUsername(String username);

    byte[] getPhotoByUsername(String username);
}
