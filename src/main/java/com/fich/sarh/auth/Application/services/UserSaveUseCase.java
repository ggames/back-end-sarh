package com.fich.sarh.auth.Application.services;

import com.fich.sarh.auth.Application.ports.entrypoint.api.UserSaveServicePort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserSavePort;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.common.UseCase;

@UseCase
public class UserSaveUseCase implements UserSaveServicePort {

    private final UserSavePort userSavePort;

    public UserSaveUseCase(UserSavePort userSavePort) {
        this.userSavePort = userSavePort;
    }

    @Override
    public UserDTO saveUsername(UserDTO user) {

        return userSavePort.saveUsername(user);
    }
}
