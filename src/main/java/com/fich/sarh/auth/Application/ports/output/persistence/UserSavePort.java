package com.fich.sarh.auth.Application.ports.output.persistence;

import com.fich.sarh.auth.Domain.model.UserDTO;

public interface UserSavePort {

    UserDTO saveUsername(UserDTO user);
}
