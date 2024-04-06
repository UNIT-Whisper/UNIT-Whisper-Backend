package com.unit.whisper.common.helper;

import static com.unit.whisper.enumeration.ResultCode.NOT_FOUND_USER;

import com.unit.whisper.common.annotation.Helper;
import com.unit.whisper.common.security.SecurityUtils;
import com.unit.whisper.domain.user.User;
import com.unit.whisper.exception.BaseException;
import com.unit.whisper.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Helper
@RequiredArgsConstructor
public class UserHelper {

    private final UserRepository userRepository;

    public Long getCurrentUserId() {
        return SecurityUtils.getCurrentUserId();
    }

    public User getCurrentUser() {
        return userRepository
                .findById(getCurrentUserId())
                .orElseThrow(() -> new BaseException(NOT_FOUND_USER));
    }
}
