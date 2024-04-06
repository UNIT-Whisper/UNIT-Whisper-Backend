package com.unit.whisper.service;


import com.unit.whisper.common.helper.UserHelper;
import com.unit.whisper.domain.user.User;
import com.unit.whisper.domain.whisper.Whisper;
import com.unit.whisper.dto.WhisperCreateRequest;
import com.unit.whisper.repository.WhisperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WhisperService {

    private final WhisperRepository whisperRepository;
    private final UserHelper userHelper;

    @Transactional
    public Long saveWhisper(WhisperCreateRequest request) {
        User currentUser = userHelper.getCurrentUser();

        Whisper whisper = request.toEntity();
        whisperRepository.save(whisper);
        currentUser.addWhisper(whisper);

        return whisper.getId();
    }
}
