package com.fich.sarh.auth.Application.services;

import com.fich.sarh.auth.Application.ports.entrypoint.api.UserRetrieveServicePort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserRetrievePort;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.common.UseCase;

import java.util.Optional;

@UseCase
public class UserRetrieveUseCase implements UserRetrieveServicePort {

   private final UserRetrievePort userRetrievePort;

    public UserRetrieveUseCase(UserRetrievePort userRetrievePort) {
        this.userRetrievePort = userRetrievePort;
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {

        return userRetrievePort.findByUsername(username) ;
    }
}
