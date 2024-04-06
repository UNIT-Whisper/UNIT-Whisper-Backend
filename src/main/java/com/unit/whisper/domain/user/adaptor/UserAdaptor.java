package com.unit.whisper.domain.user.adaptor;

import static com.unit.whisper.enumeration.ResultCode.NOT_FOUND_USER;

import com.unit.whisper.common.annotation.Adaptor;
import com.unit.whisper.domain.user.User;
import com.unit.whisper.exception.BaseException;
import com.unit.whisper.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BaseException(NOT_FOUND_USER));
    }
}
