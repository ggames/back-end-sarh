package com.fich.sarh.auth.Application.ports.entrypoint.api;

import com.fich.sarh.auth.Domain.model.UserDTO;

import java.util.Optional;

public interface UserRetrieveServicePort {

    Optional<UserDTO> findByUsername(String username);
}
