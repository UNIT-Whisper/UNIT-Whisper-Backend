package com.unit.whisper.entrypoint;


import com.unit.whisper.dto.WhisperCreateRequest;
import com.unit.whisper.service.WhisperService;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/whisper")
@RequiredArgsConstructor
public class WhisperController {

    private final WhisperService service;

    @PostMapping
    @Operation(summary = "속삭임 생성", description = "사용자들의 속삭임을 저장한다.")
    public ResponseEntity<String> createWhisper(
            @AuthenticationPrincipal Long memberId,
            @Valid @RequestBody WhisperCreateRequest request) {
        service.saveWhisper(2L, request);

        return ResponseEntity.ok().body("속삭임이 생성 완료되었습니다.");
    }
}
