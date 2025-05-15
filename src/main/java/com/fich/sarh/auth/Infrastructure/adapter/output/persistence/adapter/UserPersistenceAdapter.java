package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.auth.Application.ports.output.persistence.UserRetrievePort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserSavePort;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper.UserMapper;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository.UserRepository;
import com.fich.sarh.common.PersistenceAdapter;

import java.util.Optional;

@PersistenceAdapter
public class UserPersistenceAdapter implements UserRetrievePort, UserSavePort {

    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return Optional.of(userRepository.findByUsername(username).map(
                UserMapper.INSTANCE::toUserDTO
        )).get();
    }

    @Override
    public UserDTO saveUsername(UserDTO user) {
        return  UserMapper.INSTANCE.toUserDTO(userRepository.save(UserMapper.INSTANCE.toUserEntity(user)));
    }


}
