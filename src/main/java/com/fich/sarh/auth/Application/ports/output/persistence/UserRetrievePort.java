package com.fich.sarh.auth.Application.ports.output.persistence;

import com.fich.sarh.auth.Domain.model.UserDTO;

import java.util.Optional;

public interface UserRetrievePort {

    Optional<UserDTO> findByUsername(String username);

    byte[] getPhotoByUsername(String username);

    boolean existsUsername(String username);
}
