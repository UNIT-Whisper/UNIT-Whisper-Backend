package com.unit.whisper.service;


import com.unit.whisper.domain.user.User;
import com.unit.whisper.domain.whisper.Whisper;
import com.unit.whisper.error.exception.UserNotFoundException;
import com.unit.whisper.repository.UserRepository;
import com.unit.whisper.repository.WhisperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WhisperService {

    private final WhisperRepository whisperRepository;
    private final UserRepository userRepository;

    public void saveWhisper(final Long memberId, final Whisper whisper) {
        User user = userRepository.findById(memberId).orElseThrow(UserNotFoundException::new);
        whisper.userUpdate(user);

        whisperRepository.save(whisper);
    }
}
