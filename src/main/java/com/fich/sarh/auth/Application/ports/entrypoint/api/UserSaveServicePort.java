package com.fich.sarh.auth.Application.ports.entrypoint.api;

import com.fich.sarh.auth.Domain.model.UserDTO;
import org.aspectj.apache.bcel.classfile.Module;

import java.util.Optional;

public interface UserSaveServicePort {

    UserDTO saveUsername(UserDTO user);


}
